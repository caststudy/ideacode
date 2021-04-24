package com.fldmvc.service;


import com.fldmvc.dao.Dao;
import com.fldmvc.mapper.FldMapper;
import com.fldmvc.model.Fld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FldServiceImpl implements FldService{

    Dao dao = new Dao();
    @Autowired
    FldMapper  fldMapper;

    @Override
    public boolean insertFld(Fld fld) {


        String sql = "insert into fld(flda,fldb,fldc,fldd) values(?,?,?,?)";
        return dao.update(sql, new Object[]{fld.getFlda(), fld.getFldb(),
                fld.getFldc(), fld.getFldd()});
    }

    @Override
    public List getFld(String fldb) {
//        return dao.getFld("select * from fld where fldb like '%" + fldb + "%'");
        return  fldMapper.getAll(fldb);
    }

    @Override
    public boolean checkFlda(int flda) {
        return dao.isExist("select * from fld where flda='" + flda + "'");
    }

    @Override
    public boolean deleteFld(int flda) {
        return dao.update("delete from fld where flda =?",new Object[]{flda});
    }
}
