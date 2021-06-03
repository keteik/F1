package com.formula1.F1.result;

import com.formula1.F1.constructor.Constructor;
import com.formula1.F1.constructor.ConstructorDriverApiCaller;
import com.formula1.F1.driver.Driver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SeasonRoundResultController {
    @GetMapping("/season-round")
    public String constructorForm(Model model) {
        Result result = new Result();

        model.addAttribute("season", result);
        model.addAttribute("round", result);

        return "result/seasonRoundResultGet";
    }

    @PostMapping("/season-round")
    public String driverSubmit(@ModelAttribute Result result, Model model) {
        SeasonRoundResultApiCaller apiCaller = new SeasonRoundResultApiCaller(result.getSeason(), result.getRound());
        apiCaller.getSeasonRoundResult(result);

        model.addAttribute("seasonRoundList", result.getSeasonRoundResult());
        model.addAttribute("raceName", result.getRaceName());

        return "result/seasonRoundResult";
    }
}
