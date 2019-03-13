package me.riccalioliojr.apps.service;

import me.riccalioliojr.apps.domain.PaymentPlan;
import me.riccalioliojr.apps.model.Request;
import me.riccalioliojr.apps.model.Response;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanGeneratorService {

    public static List<Response> generatePlan(Request request) {

        List<Response> responseList = new ArrayList<>();

        PaymentPlan paymentPlan = new PaymentPlan(request);

        for (int counter = 0; counter < request.getDuration(); counter++) {
            Response response = new Response(paymentPlan);
            responseList.add(response);
            paymentPlan = new PaymentPlan(request, response, counter + 1);
        }

        return responseList;
    }
}
