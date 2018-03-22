package com.chenyou.admin.service;

import com.chenyou.admin.dao.VipClassDao;
import com.chenyou.admin.domain.VipClass;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Shell Li on 2017/12/11.
 */
@Service
public class VipClassService {
    final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private VipClassDao vipClassDao;

    public List<VipClass> getAll(){
        return vipClassDao.getAll();
    }

    public VipClass getSingle(long vipId){
        return vipClassDao.findOne(vipId);
    }

    public void saveOne(VipClass vipClass){
        vipClassDao.saveOne(vipClass);
    }

    public void editOne(VipClass vipClass) {
        vipClassDao.updateOne(vipClass);
    }

}
