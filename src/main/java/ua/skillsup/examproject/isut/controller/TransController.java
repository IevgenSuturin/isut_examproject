package ua.skillsup.examproject.isut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.examproject.isut.controller.dto.TransDto;
import ua.skillsup.examproject.isut.controller.dto.TransInfoDto;
import ua.skillsup.examproject.isut.dao.entity.Transaction;
import ua.skillsup.examproject.isut.dao.support.PeriodType;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;
import ua.skillsup.examproject.isut.service.ChangeDataService;
import ua.skillsup.examproject.isut.service.GetInformationService;

@RestController
@RequestMapping("transactions")
public class TransController {
    private final GetInformationService infoService;
    private final ChangeDataService dataService;

    @Autowired
    public TransController(GetInformationService infoService, ChangeDataService dataService) {
        this.infoService = infoService;
        this.dataService = dataService;
    }

    @GetMapping(produces = {"application/json"})
    public Iterable<TransDto> getAllTrans() {return infoService.getAllTransactions();}

    @GetMapping(produces = {"application/json"}, value = "amount-for-period/{typeOfPeriod}")
    public Iterable<TransInfoDto> getTransAmountForPeriod(@PathVariable PeriodType typeOfPeriod){
        return infoService.getStatisticForPeriod(typeOfPeriod);
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<String> addTransaction(@RequestBody Transaction trans) {
        try{
            long result = dataService.createTransaction(trans.getItem().getId(), trans.getOwner().getId(), trans.getCount());
            return new ResponseEntity<String>("Transaction was applied (id="+result+")", HttpStatus.ACCEPTED);
        }catch (NotEnoughDataToProcessTransaction ex){
            return new ResponseEntity<String>(ex.getMessage()+ " Transaction was not applied for "+trans.toString(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
