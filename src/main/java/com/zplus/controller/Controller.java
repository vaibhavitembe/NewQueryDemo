package com.zplus.controller;

import com.zplus.dto.CustDto;
import com.zplus.dto.req.CustReq;
import com.zplus.dto.req.CustomerReq;
import com.zplus.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "custquerydemo")

public class Controller {

    @Autowired
    private CustService custService;

    @PostMapping
    private ResponseEntity insertCustQuery(@RequestBody CustDto custDto) {

        Boolean flag = custService.insertCustQuery(custDto);

        if (flag)
            return new ResponseEntity(flag, HttpStatus.OK);
        else
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
}

    @PutMapping
    private ResponseEntity updateCustQuery(@RequestBody CustDto custDto) {
        Boolean flag = custService.updateCustQuery(custDto);

        if (flag)
            return new ResponseEntity(flag, HttpStatus.OK);
        else
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    private ResponseEntity getAllCustDetails() {
        List list = custService.getAllCustDetails();

        if (list.size() != 0)
            return new ResponseEntity(list, HttpStatus.OK);
        else
            return new ResponseEntity(list, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/FindCustomerNameDesc")
    private ResponseEntity getCustomerNameDesc() {
        List list = custService.getCustomerNameDesc();

        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PostMapping(value = "/FindJoiningDate")
    private ResponseEntity getByCustomerJoining(@RequestBody CustReq custReq) {
        List list = custService.getByCustomerJoining(custReq.getCustStartDate(), custReq.getCustEndDate());
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping(value = "/FindPoint")
    private ResponseEntity getByPoint() {
        List list = custService.getByPoint();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping(value = "/custNameWiseSearch/{searchtext}")
    private ResponseEntity custNameWiseSearch(@PathVariable String searchtext) {
        List list = custService.custNameWiseSearch(searchtext);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping(value = "/UpdateCustomerIdWiseCustName")
    private ResponseEntity UpdateCustomerIdWiseCustName(@RequestBody CustomerReq customerReq) {
        Boolean flag = custService.UpdateCustomerIdWiseCustName(customerReq);
        if (flag)
            return new ResponseEntity(flag, HttpStatus.OK);
        else
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}





