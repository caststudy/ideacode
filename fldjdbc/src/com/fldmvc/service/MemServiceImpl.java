package com.fldmvc.service;


import com.fldmvc.dao.Dao;
import com.fldmvc.model.Member;

public class MemServiceImpl implements MemService{

    Dao dao = new Dao();


    @Override
    public boolean login(Member member) {
        return dao.isExist("select * from `member` where memid=? and mempass=?",new Object[]{member.getMemid(),member.getMempass()});
    }
}
