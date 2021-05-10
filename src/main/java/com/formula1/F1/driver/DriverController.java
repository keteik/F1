package com.formula1.F1.driver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DriverController {

    @GetMapping("/driver")
    public String greetingForm(Model model) {
        model.addAttribute("driver", new Driver());
        return "driver";
    }

    @PostMapping("/driver")
    public String greetingSubmit(@ModelAttribute Driver driver, Model model) {
        model.addAttribute("driver", driver);
        return "result";
    }

}
