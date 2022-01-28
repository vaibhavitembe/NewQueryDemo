package com.zplus.controller;

import com.zplus.dto.CustDto;
import com.zplus.dto.req.*;
import com.zplus.dto.res.ChangePasswordResDto;
import com.zplus.dto.res.CustomerLoginResDto;
import com.zplus.dto.res.DateResDto;
import com.zplus.dto.res.ForgotPasswordResDto;
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

    //Insert Customer Query
    @PostMapping
    private ResponseEntity insertCustQuery(@RequestBody CustDto custDto) {

        Boolean flag = custService.insertCustQuery(custDto);

        if (flag)
            return new ResponseEntity(flag, HttpStatus.OK);
        else
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Update Customer Query
    @PutMapping
    private ResponseEntity updateCustQuery(@RequestBody CustDto custDto) {
        Boolean flag = custService.updateCustQuery(custDto);

        if (flag)
            return new ResponseEntity(flag, HttpStatus.OK);
        else
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Get All Customer Details
    @GetMapping
    private ResponseEntity getAllCustDetails() {
        List list = custService.getAllCustDetails();

        if (list.size() != 0)
            return new ResponseEntity(list, HttpStatus.OK);
        else
            return new ResponseEntity(list, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Find Customer Name Descending
    @PostMapping(value = "/FindCustomerNameDesc")
    private ResponseEntity getCustomerNameDesc() {
        List list = custService.getCustomerNameDesc();

        return new ResponseEntity(list, HttpStatus.OK);
    }

    //Find Joining Date
    @PostMapping(value = "/FindJoiningDate")
    private ResponseEntity getByCustomerJoining(@RequestBody CustReq custReq) {
        List list = custService.getByCustomerJoining(custReq.getCustStartDate(), custReq.getCustEndDate());
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //Find Point
    @PostMapping(value = "/FindPoint")
    private ResponseEntity getByPoint() {
        List list = custService.getByPoint();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //Customer Name Wise Search
    @PostMapping(value = "/custNameWiseSearch/{searchtext}")
    private ResponseEntity custNameWiseSearch(@PathVariable String searchtext) {
        List list = custService.custNameWiseSearch(searchtext);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //Update Customer Id Wise CustName
    @PostMapping(value = "/UpdateCustomerIdWiseCustName")
    private ResponseEntity UpdateCustomerIdWiseCustName(@RequestBody CustomerReq customerReq) {
        Boolean flag = custService.UpdateCustomerIdWiseCustName(customerReq);
        if (flag)
            return new ResponseEntity(flag, HttpStatus.OK);
        else
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Login API Controller
    @PostMapping(value = "/customerLogin")
    private ResponseEntity customerLogin(@RequestBody CustomerLoginReqDto customerLoginReqDto) {
        CustomerLoginResDto customerLoginResDto = custService.customerLogin(customerLoginReqDto);
        return new ResponseEntity(customerLoginResDto, HttpStatus.OK);
    }

    //Change Password
    @PostMapping(value = "/changePassword")
    private ResponseEntity changePassword(@RequestBody ChangePasswordReqDto changePasswordReqDto) {
        ChangePasswordResDto changePasswordResDto = custService.changePassword(changePasswordReqDto);
        return new ResponseEntity(changePasswordResDto, HttpStatus.OK);

    }

    // Forgot Password
    @PostMapping(value = "/forgotPassword")
    private ResponseEntity forgotPassword(@RequestBody ForgotPasswordReqDto forgotPasswordReqDto) {
        ForgotPasswordResDto forgotPasswordResDto = custService.forgotPassword(forgotPasswordReqDto);
        return new ResponseEntity(forgotPasswordResDto, HttpStatus.OK);
    }

    //OTP Verification
    @PostMapping(value = "/otpVerification")
    private ResponseEntity otpVerification(@RequestBody OTPReqDto otpReqDto) {
        ForgotPasswordResDto forgotPasswordResDto = custService.otpVerification(otpReqDto);
        return new ResponseEntity(forgotPasswordResDto, HttpStatus.OK);
    }

    //Update Password
    @PostMapping(value = "/updatePassword")
    private ResponseEntity updatePassword(@RequestBody UpdatePasswordReqDto updatePasswordReqDto) {
        ForgotPasswordResDto forgotPasswordResDto = custService.updatePassword(updatePasswordReqDto);
        return new ResponseEntity(forgotPasswordResDto, HttpStatus.OK);

    }

    //Display all record on date
    @PostMapping(value = "/displayAllRecordOnDate")
    private ResponseEntity displayAllRecordOnDate(@RequestBody DateReqDto dateReqDto) {
        DateResDto dateResDto = custService.displayAllRecordOnDate(dateReqDto);
        return new ResponseEntity(dateResDto, HttpStatus.OK);
    }

    //CheckMobileNo.
    @PostMapping(value = "/checkCustMobileNo/{mobileNo}")
    private ResponseEntity checkcustMobileNo(@PathVariable String mobileNo) {
        Boolean flag = custService.checkcustMobileNo(mobileNo);
        if (flag)
            return new ResponseEntity(flag, HttpStatus.OK);
        else
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //updateCustIdWiseCuststatus
    @PostMapping(value = "/updateCustIdWiseCuststatus")
    private ResponseEntity updateCustIdWiseCuststatus(@RequestBody CustomerReq customerReq) {
        Boolean flag = custService.updateCustIdWiseCuststatus(customerReq);
        if (flag)
            return new ResponseEntity(flag, HttpStatus.OK);
        else
            return new ResponseEntity(flag, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Count Api for active.
    @PostMapping(value = "/getByActiveStatus")
    private ResponseEntity getByActiveStatus() {
        Integer cnt = custService.getByActiveStatus();
        return new ResponseEntity(cnt, HttpStatus.OK);

    }

}



