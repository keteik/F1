package com.formula1.F1.constructor;

import com.formula1.F1.driver.Driver;
import com.formula1.F1.driver.DriverConstructorApiCaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConstructorDriverController {

    @GetMapping("/constructor-driver")
    public String constructorForm(Model model) {
        model.addAttribute("constructorId", new Driver());
        return "constructor/constructorDriverGet";
    }

    @PostMapping("/constructor-driver")
    public String driverSubmit(@ModelAttribute Constructor constructor, Model model) {
        ConstructorDriverApiCaller apiCaller = new ConstructorDriverApiCaller(constructor.getId());
        apiCaller.getDriverConstructors(constructor);

        model.addAttribute("constructorDriverList", constructor.getConstructorDriver());
        return "constructor/constructorDriverResult";
    }
}
