package com.formula1.F1.driver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DriverInformationController {

    @GetMapping("/driver-information")
    public String driverForm(Model model) {
        model.addAttribute("driverId", new Driver());
        return "driver/driverInformationGet";
    }

    @PostMapping("/driver-information")
    public String driverSubmit(@ModelAttribute Driver driver, Model model) {
        DriverInformationApiCaller apiCaller = new DriverInformationApiCaller(driver.getId());
        apiCaller.getDriverInformation(driver);

        model.addAttribute("driverInformation", driver.getDriverInformation());
        return "driver/driverInformationResult";
    }

}
