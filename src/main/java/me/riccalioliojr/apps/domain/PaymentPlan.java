package me.riccalioliojr.apps.domain;

import me.riccalioliojr.apps.constants.Constants;
import me.riccalioliojr.apps.model.Request;
import me.riccalioliojr.apps.model.Response;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class PaymentPlan {
    private BigDecimal borrowerPaymentAmount;
    private String date;
    private BigDecimal initialOutstandingPrincipal;
    private BigDecimal interest;
    private BigDecimal principal;
    private BigDecimal remainingOutstandingPrincipal;

    public PaymentPlan(Request request) {
        this.borrowerPaymentAmount = roundOff(calculateBorrowerPaymentAmount(request));
        this.date = generatePaymentDate(request.getStartDate());
        this.initialOutstandingPrincipal = roundOff(BigDecimal.valueOf(request.getLoanAmount()));
        this.interest = roundOff(calculateInterest(request.getNominalRate(), this.initialOutstandingPrincipal));
        this.principal = roundOff(calculatePrincipal(this.borrowerPaymentAmount));
        this.remainingOutstandingPrincipal = roundOff(this.initialOutstandingPrincipal.subtract(this.principal));
    }

    public PaymentPlan(Request request, Response response, int counter) {
        this.initialOutstandingPrincipal = response.getRemainingOutstandingPrincipal();
        this.date = generatePaymentDate(request.getStartDate(), counter);
        this.interest = roundOff(calculateInterest(request.getNominalRate(), this.initialOutstandingPrincipal));
        this.principal = roundOff(calculatePrincipal(response.getBorrowerPaymentAmount()));
        this.borrowerPaymentAmount = roundOff(this.principal.add(this.interest));
        this.remainingOutstandingPrincipal = roundOff(this.initialOutstandingPrincipal.subtract(this.principal));
    }

    private BigDecimal roundOff(BigDecimal bigDecimal) {
        return bigDecimal.setScale(Constants.DECIMALS, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal calculateBorrowerPaymentAmount(Request request) {
        return BigDecimal.valueOf(borrowerPaymentAmountNumerator(request) / borrowerPaymentAmountDenominator(request));
    }

    private double borrowerPaymentAmountNumerator(Request request) {
        return request.getLoanAmount() * interestRateOverPaymentsPerYear(request.getNominalRate());
    }

    private double borrowerPaymentAmountDenominator(Request request) {
        double base = 1 + interestRateOverPaymentsPerYear(request.getNominalRate());
        double subtrahend = Math.pow(base, -request.getDuration());
        return (1 - subtrahend);
    }

    private double interestRateOverPaymentsPerYear(double nominalRate) {
        return (toDecimal(nominalRate) / Constants.PAYMENTS_PER_YEAR);
    }

    private double toDecimal(Double value) {
        return (value / 100);
    }

    private String generatePaymentDate(String startDateTime) {
        LocalDate startDate = convertStringToLocalDate(startDateTime);
        return convertLocalDateToString(startDate);
    }

    private String generatePaymentDate(String startDateTime, int counter) {
        LocalDate startDate = convertStringToLocalDate(startDateTime);
        LocalDate paymentDate = startDate.plusMonths(counter);
        return convertLocalDateToString(paymentDate);
    }

    private LocalDate convertStringToLocalDate(String dateTimeAsString) {
        Instant instant = Instant.parse(dateTimeAsString);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
        return localDateTime.toLocalDate();
    }

    private String convertLocalDateToString(LocalDate localDate) {
        ZonedDateTime zonedDateTime = ZonedDateTime
                .of(LocalDateTime.of(localDate, LocalTime.MIDNIGHT), ZoneId.of("UTC"));
        return zonedDateTime.format(DateTimeFormatter.ISO_INSTANT);
    }

    private BigDecimal calculateInterest(double nominalRate, BigDecimal initialOutstandingPrincipal) {
        double interestInCents = (nominalRate * Constants.DAYS_PER_MONTH * initialOutstandingPrincipal.doubleValue())
                / Constants.DAYS_PER_YEAR;
        return BigDecimal.valueOf(interestInCents).movePointLeft(Constants.DECIMALS);
    }

    private BigDecimal calculatePrincipal(BigDecimal annuity) {
        BigDecimal principal = annuity.subtract(this.interest);

        if (principal.compareTo(this.initialOutstandingPrincipal) > 0) {
            return this.initialOutstandingPrincipal;
        } else {
            return principal;
        }
    }

    public BigDecimal getBorrowerPaymentAmount() {
        return this.borrowerPaymentAmount;
    }

    public String getDate() {
        return this.date;
    }

    public BigDecimal getInitialOutstandingPrincipal() {
        return this.initialOutstandingPrincipal;
    }

    public BigDecimal getInterest() {
        return this.interest;
    }

    public BigDecimal getPrincipal() {
        return this.principal;
    }

    public BigDecimal getRemainingOutstandingPrincipal() {
        return this.remainingOutstandingPrincipal;
    }
}
