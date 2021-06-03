package com.formula1.F1;

import com.formula1.F1.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @GetMapping("/")
    public String constructorForm(Model model) {

        return "index";
    }
}
