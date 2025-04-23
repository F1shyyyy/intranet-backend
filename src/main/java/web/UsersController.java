package web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UsersController {
    @RequestMapping("/")
    public String requestUsers(Model model){
        model.addAttribute("message", "TEST");
        return "main";
    }
}
