package ua.skillsup.examproject.isut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.examproject.isut.controller.dto.TransDto;
import ua.skillsup.examproject.isut.dao.entity.Transaction;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;
import ua.skillsup.examproject.isut.service.ActionService;

@RestController
@RequestMapping("transaction")
public class TransController {
    private final ActionService service;

    @Autowired
    public TransController(ActionService service) {
        this.service = service;
    }

    @GetMapping(produces = {"application/json"})
    public Iterable<TransDto> getAllTrans() {return service.getAllTransactions();}


    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<String> addTransaction(@RequestBody Transaction trans) {
        try{
            long result = service.createTransaction(trans.getItem().getId(), trans.getOwner().getId(), trans.getCount());
            return new ResponseEntity<String>("Transaction was applied (id="+result+")", HttpStatus.ACCEPTED);
        }catch (NotEnoughDataToProcessTransaction ex){
            return new ResponseEntity<String>(ex.getMessage()+ " Transaction was not applied for "+trans.toString(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
