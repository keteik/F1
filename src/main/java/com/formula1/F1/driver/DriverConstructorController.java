package com.formula1.F1.driver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DriverConstructorController {
    @GetMapping("/driver-constructor")
    public String constructorForm(Model model) {
        model.addAttribute("driverId", new Driver());
        return "driver/driverConstructorGet";
    }

    @PostMapping("/driver-constructor")
    public String driverSubmit(@ModelAttribute Driver driver, Model model) {
        DriverConstructorApiCaller apiCaller = new DriverConstructorApiCaller(driver.getId());
        apiCaller.getDriverConstructors(driver);

        model.addAttribute("driverConstructorList", driver.getDriverConstructor());
        return "driver/driverConstructorResult";
    }
}
