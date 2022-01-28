package com.zplus.service;

import com.zplus.dto.CustDto;
import com.zplus.dto.req.*;
import com.zplus.dto.res.ChangePasswordResDto;
import com.zplus.dto.res.CustomerLoginResDto;
import com.zplus.dto.res.DateResDto;
import com.zplus.dto.res.ForgotPasswordResDto;



import java.util.Date;
import java.util.List;

public interface CustService {
    Boolean insertCustQuery(CustDto custDto);

    Boolean updateCustQuery(CustDto custDto);

    List getAllCustDetails();

    List getByCustomerJoining(Date custStartDate, Date custEndDate);

    List getCustomerNameDesc();

    List getByPoint();

    List custNameWiseSearch(String searchtext);

    Boolean UpdateCustomerIdWiseCustName(CustomerReq customerReq);


    CustomerLoginResDto customerLogin(CustomerLoginReqDto customerLoginReqDto);

    ChangePasswordResDto changePassword(ChangePasswordReqDto changePasswordReqDto);

//    ForgotPasswordResDto forgotPassword(ForgotPasswordReqDto forgotPasswordReqDto);


    ForgotPasswordResDto otpVerification(OTPReqDto otpReqDto);

    ForgotPasswordResDto updatePassword(UpdatePasswordReqDto updatePasswordReqDto);

    Boolean checkcustMobileNo(String mobileNo);

    Boolean updateCustIdWiseCuststatus(CustomerReq customerReq);


    DateResDto displayAllRecordOnDate(DateReqDto dateReqDto);

    Integer getByActiveStatus();

    ForgotPasswordResDto forgotPassword(ForgotPasswordReqDto forgotPasswordReqDto);
}

