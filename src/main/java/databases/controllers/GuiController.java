package databases.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GuiController {

    @GetMapping("/")
    public String showPrograms(Model model){
        return "main";
    }
}
