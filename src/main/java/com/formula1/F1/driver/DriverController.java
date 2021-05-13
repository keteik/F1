package com.formula1.F1.driver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DriverController {

    @GetMapping("/driver")
    public String driverForm(Model model) {
        model.addAttribute("driver", new Driver());
        return "driver";
    }

    @PostMapping("/driver")
    public String driverSubmit(@ModelAttribute Driver driver, Model model) {
        DriverApiCaller apiCaller = new DriverApiCaller(driver.getId());
        apiCaller.getDriverInformation(driver);

        model.addAttribute("id", driver);
        model.addAttribute("number", driver);
        model.addAttribute("code", driver);
        model.addAttribute("url", driver);
        model.addAttribute("name", driver);
        model.addAttribute("surname", driver);
        model.addAttribute("dateOfBirth", driver);
        model.addAttribute("nationality", driver);
        return "result";
    }

}
