package me.riccalioliojr.apps.service;

import me.riccalioliojr.apps.domain.PaymentPlan;
import me.riccalioliojr.apps.model.Request;
import me.riccalioliojr.apps.model.Response;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentPlanGeneratorService {

    public List<Response> generatePaymentPlan(final Request request) {
        request.validate();
        final List<PaymentPlan> paymentPlanList = generatePaymentPlanList(request);
        return generateResponseList(paymentPlanList);
    }

    private List<PaymentPlan> generatePaymentPlanList(final Request request) {

        final List<PaymentPlan> paymentPlanList = new ArrayList<>();

        PaymentPlan paymentPlan = new PaymentPlan(request);
        paymentPlanList.add(paymentPlan);

        for (int counter = 1; counter < request.getDuration(); counter++) {
            paymentPlan = new PaymentPlan(request, paymentPlan, counter);
            paymentPlanList.add(paymentPlan);
        }

        return paymentPlanList;
    }

    private List<Response> generateResponseList(final List<PaymentPlan> paymentPlanList) {

        final List<Response> responseList = new ArrayList<>();

        for (final PaymentPlan paymentPlan : paymentPlanList) {
            final Response response = new Response(paymentPlan);
            responseList.add(response);
        }

        return responseList;
    }
}
