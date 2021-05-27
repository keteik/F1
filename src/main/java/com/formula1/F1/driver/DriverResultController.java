package com.formula1.F1.driver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DriverResultController {

    @GetMapping("/driver-season-result")
    public String driverForm(Model model) {
        Driver driver = new Driver();

        model.addAttribute("driverId",driver);
        model.addAttribute("season", driver);
        return "driverSeasonResultGet";
    }

    @PostMapping("/driver-season-result")
    public String driverSubmit(@ModelAttribute Driver driver, Model model) {

        DriverResultApiCaller  apiCaller = new DriverResultApiCaller(driver.getId(), driver.getSeason());
        apiCaller.getDriverSeason(driver);

        model.addAttribute("driverSeasonResult", driver.getDriverResult());
        return "driverSeasonResult";
    }
}
