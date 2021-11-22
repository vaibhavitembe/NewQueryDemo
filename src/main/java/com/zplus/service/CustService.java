package com.zplus.service;

import com.zplus.dto.CustDto;
import com.zplus.dto.req.CustomerReq;

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


}
