package com.formula1.F1.schedule;

import com.formula1.F1.driver.Driver;
import com.formula1.F1.driver.DriverResultApiCaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ScheduleController {

    @GetMapping("/season-schedule")
    public String driverForm(Model model) {

        Schedule schedule = new Schedule();
        model.addAttribute("seasonId",schedule);

        return "schedule/scheduleSeasonGet";
    }

    @PostMapping("/season-schedule")
    public String driverSubmit(@ModelAttribute Schedule schedule, Model model) {

        ScheduleApiCaller apiCaller = new ScheduleApiCaller(schedule.getId());
        apiCaller.getSeasonSchedule(schedule);

        model.addAttribute("scheduleSeasonResult", schedule.getScheduleSeason());
        return "schedule/scheduleSeasonResult";
    }
}
