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

    public Response(PaymentPlan paymentPlan) {
        this.borrowerPaymentAmount = paymentPlan.getBorrowerPaymentAmount();
        this.date = paymentPlan.getDate();
        this.initialOutstandingPrincipal = paymentPlan.getInitialOutstandingPrincipal();
        this.interest = paymentPlan.getInterest();
        this.principal = paymentPlan.getPrincipal();
        this.remainingOutstandingPrincipal = paymentPlan.getRemainingOutstandingPrincipal();
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
