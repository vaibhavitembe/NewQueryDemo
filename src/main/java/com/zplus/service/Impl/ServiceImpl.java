package com.zplus.service.Impl;

import com.zplus.dao.CustDao;
import com.zplus.dto.CustDto;
import com.zplus.dto.req.CustomerReq;
import com.zplus.model.CustModel;
import com.zplus.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceImpl implements CustService {

    @Autowired
    private CustDao custDao;

    @Override
    public Boolean insertCustQuery(CustDto custDto) {

        CustModel custModel = new CustModel();

        custModel.setCustName(custDto.getCustName());
        custModel.setCustJoiningDate(custDto.getCustJoiningDate());
        custModel.setPoint(custDto.getPoint());
        custModel.setMobileNo(custDto.getMobileNo());
        custModel.setPassword(custDto.getPassword());


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
}
