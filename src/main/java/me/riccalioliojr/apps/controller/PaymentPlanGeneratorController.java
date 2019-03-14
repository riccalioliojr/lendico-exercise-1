package me.riccalioliojr.apps.controller;

import me.riccalioliojr.apps.model.Request;
import me.riccalioliojr.apps.model.Response;
import me.riccalioliojr.apps.service.PaymentPlanGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "generate-plan")
public class PaymentPlanGeneratorController {

    @Autowired
    private PaymentPlanGeneratorService paymentPlanGeneratorService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Response> generatePaymentPlan(@RequestBody final Request request) {
        return paymentPlanGeneratorService.generatePaymentPlan(request);
    }
}