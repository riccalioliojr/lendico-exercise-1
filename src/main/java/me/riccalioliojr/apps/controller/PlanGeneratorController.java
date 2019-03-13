package me.riccalioliojr.apps.controller;

import me.riccalioliojr.apps.model.Request;
import me.riccalioliojr.apps.model.Response;
import me.riccalioliojr.apps.service.PlanGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/generate-plan")
public class PlanGeneratorController {

    @Autowired
    private PlanGeneratorService planGeneratorService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    public static List<Response> post(@RequestBody Request request) {
        return PlanGeneratorService.generatePlan(request);
    }
}