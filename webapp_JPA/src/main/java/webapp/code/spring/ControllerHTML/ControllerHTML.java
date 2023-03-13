package webapp.code.spring.ControllerHTML;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class ControllerHTML
{
    @GetMapping("/first")
    public String Start()
    {
        return "menu/first";
    }

    @GetMapping("/end")
    public String End()
    {
        return "menu/end";
    }
}
