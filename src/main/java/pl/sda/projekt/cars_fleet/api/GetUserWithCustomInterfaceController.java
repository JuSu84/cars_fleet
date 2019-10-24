package pl.sda.projekt.cars_fleet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetUserWithCustomInterfaceController {
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @GetMapping(value = "/username")
    @ResponseBody
    public String currentUserName(){
        Authentication authentication = authenticationFacade.getAuthentication();
        return  authentication.getName();
    }
}
