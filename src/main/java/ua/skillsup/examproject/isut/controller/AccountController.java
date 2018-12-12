package ua.skillsup.examproject.isut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.skillsup.examproject.isut.controller.dto.AccDto;
import ua.skillsup.examproject.isut.controller.dto.OwnerDto;
import ua.skillsup.examproject.isut.service.ActionService;

@Controller
@RequestMapping("accounts")
public class AccountController {
    private final ActionService service;

    @Autowired
    public AccountController(ActionService service) {
        this.service = service;
    }

    @GetMapping(produces = {"application/json"})
    public @ResponseBody Iterable<AccDto> getAllActiveOwners() {return service.getAllAccounts();}

}
