package com.formula1.F1.circuit;

import com.formula1.F1.schedule.Schedule;
import com.formula1.F1.schedule.ScheduleApiCaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CircuitSeasonController {

    @GetMapping("/circuit-season")
    public String driverForm(Model model) {

        Circuit circuit = new Circuit();
        model.addAttribute("season", circuit);

        return "circuit/circuitSeasonGet";
    }

    @PostMapping("/circuit-season")
    public String driverSubmit(@ModelAttribute Circuit circuit, Model model) {

        CircuitSeasonApiCaller apiCaller = new CircuitSeasonApiCaller(circuit.getSeason());
        apiCaller.getSeasonCircuit(circuit);

        model.addAttribute("circuitSeason", circuit.getCircuitSeason());
        return "circuit/circuitSeasonResult";
    }
}
