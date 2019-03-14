package me.riccalioliojr.apps.model;

import me.riccalioliojr.apps.domain.PaymentPlan;

import java.math.BigDecimal;

public class Response {
    private BigDecimal borrowerPaymentAmount;
    private String date;
    private BigDecimal initialOutstandingPrincipal;
    private BigDecimal interest;
    private BigDecimal principal;
    private BigDecimal remainingOutstandingPrincipal;

    public Response(final PaymentPlan paymentPlan) {
        this.borrowerPaymentAmount = paymentPlan.getBorrowerPaymentAmount();
        this.date = paymentPlan.getDate();
        this.initialOutstandingPrincipal = paymentPlan.getInitialOutstandingPrincipal();
        this.interest = paymentPlan.getInterest();
        this.principal = paymentPlan.getPrincipal();
        this.remainingOutstandingPrincipal = paymentPlan.getRemainingOutstandingPrincipal();
    }

    public BigDecimal getBorrowerPaymentAmount() {
        return borrowerPaymentAmount;
    }

    public String getDate() {
        return date;
    }

    public BigDecimal getInitialOutstandingPrincipal() {
        return initialOutstandingPrincipal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public BigDecimal getRemainingOutstandingPrincipal() {
        return remainingOutstandingPrincipal;
    }
}
