package com.zplus.service.Impl;

import com.zplus.configuration.RandomNumberGenerator;
//import com.zplus.configuration.SmsPanel;

import com.zplus.dao.CustDao;
import com.zplus.dto.CustDto;
import com.zplus.dto.req.*;
import com.zplus.dto.res.*;
import com.zplus.model.CustModel;
import com.zplus.service.CustService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class ServiceImpl implements CustService {

    @Autowired
    private CustDao custDao;
    @Autowired
    private com.zplus.BankAuction.configuration.SendMailComponent sendMailComponent;



    @Override
    public Boolean insertCustQuery(CustDto custDto) {

        CustModel custModel = new CustModel();

        custModel.setCustName(custDto.getCustName());
        custModel.setCustJoiningDate(custDto.getCustJoiningDate());
        custModel.setPoint(custDto.getPoint());
        custModel.setMobileNo(custDto.getMobileNo());
        custModel.setPassword(custDto.getPassword());
        custModel.setOtp(custDto.getOtp());
        custModel.setEmailId(custDto.getEmail());

        try {
            custDao.save(custModel);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateCustQuery(CustDto custDto) {
        CustModel custModel = new CustModel();

        custModel.setCustId(custDto.getCustId());
        custModel.setCustName(custDto.getCustName());
        custModel.setPassword(custDto.getPassword());
        custModel.setPoint(custDto.getPoint());
        custModel.setMobileNo(custDto.getMobileNo());
        custModel.setCustJoiningDate(custDto.getCustJoiningDate());
        custModel.setOtp(custDto.getOtp());
        custModel.setEmailId(custDto.getEmail());


        try {
            custDao.save(custModel);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getAllCustDetails() {
        return (List) custDao.findAll();

    }

    @Override
    public List getByCustomerJoining(Date custStartDate, Date custEndDate) {
        List list = custDao.findByCustomerDate(custStartDate, custEndDate);
        return list;
    }

    @Override
    public List getCustomerNameDesc() {
        List list = custDao.findCustomerNameDesc();
        return list;
    }

    @Override
    public List getByPoint() {
        List list = custDao.findByPoint();
        return list;
    }

    @Override
    public List custNameWiseSearch(String searchtext) {
        List list = custDao.custNameWiseSearch(searchtext);
        return list;
    }

    @Override
    public Boolean UpdateCustomerIdWiseCustName(CustomerReq customerReq) {
        try {
            Integer flag = custDao.UpdateCustomerIdWiseCustName(customerReq.getCustId(), customerReq.getCustName());
            if (flag == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Login API
    @Override
    public CustomerLoginResDto customerLogin(CustomerLoginReqDto customerLoginReqDto) {
        CustomerLoginResDto customerLoginResDto = new CustomerLoginResDto();
        CustModel custModel = custDao.findAllByMobileNo(customerLoginReqDto.getUserName());

        if (custModel == null) {
            customerLoginResDto.setMsg("Mobile Number or emailId not exists");
            customerLoginResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
            return customerLoginResDto;
        }
        if (custModel.getMobileNo().equals(customerLoginReqDto.getUserName())) {
            if (custModel.getPassword().equals(customerLoginReqDto.getUserPassword())) {
                customerLoginResDto.setMsg("Login Successfully");
                customerLoginResDto.setResponseCode(HttpStatus.OK.value());
                BeanUtils.copyProperties(custModel, customerLoginResDto);
                return customerLoginResDto;
            } else {
                customerLoginResDto.setMsg("Wrong Password");
                customerLoginResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
                return customerLoginResDto;
            }
        } else {
            customerLoginResDto.setMsg("Invalid mob no");
            customerLoginResDto.setResponseCode(HttpStatus.BAD_REQUEST.value());
            return customerLoginResDto;
        }
    }

    //ChangePassword:
    @Override
    public ChangePasswordResDto changePassword(ChangePasswordReqDto changePasswordReqDto) {
        ChangePasswordResDto changePasswordResDto = new ChangePasswordResDto();
        String dbpassword = custDao.findOldPasswordByCustId((changePasswordReqDto.getCustId()));

        if (dbpassword.equals(changePasswordReqDto.getOldPassword())) {
            Integer updatePassword = custDao.updatePassword(changePasswordReqDto.getCustId(), changePasswordReqDto.getNewPassword());
            if (updatePassword > 0) {
                System.out.println("password update successfully");
                changePasswordResDto.setMsg("password update successfully");
                changePasswordResDto.setFlag(true);
                return changePasswordResDto;
            } else {

                changePasswordResDto.setMsg("Inavlid Password");

                changePasswordResDto.setFlag(false);
                return changePasswordResDto;
            }
        } else {
            changePasswordResDto.setFlag(false);
            changePasswordResDto.setMsg("password not mach");
            System.out.println("password not mach..!");
            return changePasswordResDto;
        }
    }

//Forget Password
    @Override
    public ForgotPasswordResDto forgotPassword(ForgotPasswordReqDto forgotPasswordReqDto) {
        CustModel custModel = custDao.findByEmailId(forgotPasswordReqDto.getEmailId());
        if (custModel != null) {
            Integer otp = RandomNumberGenerator.getNumber();
            Integer flag = custDao.updateOtp(custModel.getCustId(), otp);

            ForgotPasswordResDto forgotPasswordResDto = new ForgotPasswordResDto();

            String content = "<h3> Dear Customer Your AuctionBanks verification OTP code is </h3> </br> <h1> " + otp+"  </h1></br> Please DO NOT share this OTP with anyone.";
            String senderId = custModel.getEmailId();
            String subject = "Auction Bank Mail : Email Verification OTP";

            try {
                sendMailComponent.sendMail(senderId, content, subject);
                forgotPasswordResDto.setFlag(true);
                forgotPasswordResDto.setCustId(custModel.getCustId());
                forgotPasswordResDto.setEmailId(custModel.getEmailId());
                return forgotPasswordResDto;
            } catch (Exception e) {
                e.printStackTrace();
                forgotPasswordResDto.setFlag(false);
                return forgotPasswordResDto;
            }
        } else {
            ForgotPasswordResDto forgotPasswordResDto = new ForgotPasswordResDto();
            forgotPasswordResDto.setFlag(false);
            return forgotPasswordResDto;
        }
    }

    //OTP Verification
    @Override
    public ForgotPasswordResDto otpVerification(OTPReqDto otpReqDto) {
        CustModel custModel = custDao.findOne(otpReqDto.getCustId());
        ForgotPasswordResDto forgotPasswordResDto = new ForgotPasswordResDto();
        if (otpReqDto.getOtp().equals(custModel.getOtp())) {
            forgotPasswordResDto.setFlag(true);
            forgotPasswordResDto.setCustId(custModel.getCustId());
            return forgotPasswordResDto;
        } else {
            forgotPasswordResDto.setFlag(false);
            forgotPasswordResDto.setCustId(custModel.getCustId());
            forgotPasswordResDto.setEmailId(custModel.getEmailId());
            return forgotPasswordResDto;
        }
    }

    //UpdatePassword
    @Override
    public ForgotPasswordResDto updatePassword(UpdatePasswordReqDto updatePasswordReqDto) {
        Integer custId = custDao.updatePassword(updatePasswordReqDto.getCustId(), updatePasswordReqDto.getPassword());
        ForgotPasswordResDto forgotPasswordResDto = new ForgotPasswordResDto();

        if (custId == 0) {
            forgotPasswordResDto.setFlag(false);
            forgotPasswordResDto.setCustId(updatePasswordReqDto.getCustId());
            return forgotPasswordResDto;
        } else {
            forgotPasswordResDto.setFlag(true);
            forgotPasswordResDto.setCustId(updatePasswordReqDto.getCustId());
            return forgotPasswordResDto;
        }
    }

    //DisplayAllRecordOn Date
    @Override
    public DateResDto displayAllRecordOnDate(DateReqDto dateReqDto) {
        DateResDto dateResDto = custDao.displayAllRecordOnDate(dateReqDto.getCustJoiningDate());
       return dateResDto;

    }

    @Override
    public Integer getByActiveStatus() {
        Integer cnt = custDao.getByCountActiveStatus();
        return  cnt;
    }


    @Override
    public Boolean checkcustMobileNo(String mobileNo) {
        try {
            CustModel custModel = custDao.findByMobileNo(mobileNo);

            if (custModel != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public Boolean updateCustIdWiseCuststatus(CustomerReq customerReq) {
        try {
            Integer flag = custDao.updateCustIdWiseCustStatus(customerReq.getCustId(), customerReq.getStatus());

            if (flag == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}


















