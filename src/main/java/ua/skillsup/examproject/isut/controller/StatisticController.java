package ua.skillsup.examproject.isut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.skillsup.examproject.isut.controller.dto.TransInfoDto;
import ua.skillsup.examproject.isut.service.ActionService;

import java.util.List;

@RestController
@RequestMapping("statistic")
public class StatisticController{
    private final ActionService service;

    @Autowired
    public StatisticController(ActionService service) {
        this.service = service;
    }

    @GetMapping(produces = {"application/json"}, value = "getTrAmountForPeriod/{typeOfPeriod}")
    public Iterable<TransInfoDto> getTransAmountForPeriod(@PathVariable short typeOfPeriod){
        return service.getStatisticForPeriod(typeOfPeriod);
    }

}
