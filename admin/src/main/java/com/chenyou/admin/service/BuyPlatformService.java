package com.chenyou.admin.service;

import com.chenyou.admin.dao.BuyPlatformDao;
import com.chenyou.admin.domain.BuyPlatform;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Shell Li on 2017/12/12.
 */
@Service
public class BuyPlatformService {
    final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private BuyPlatformDao dao;

    public List<BuyPlatform> getAll(){
        return dao.findAll();
    }

    public BuyPlatform getOne(long id){
        return dao.findOne(id);
    }

    @Transactional
    public void save(HttpServletRequest request){
        String id = request.getParameter("id"), platformID = request.getParameter("platformID"),
            platformName = request.getParameter("platformName"), invalidFlag = request.getParameter("invalidFlag");
        BuyPlatform buyPlatform = null;
        if (StringUtils.isEmpty(id)){
            buyPlatform = new BuyPlatform();
        } else {
            buyPlatform = dao.findOne(Long.valueOf(id));
        }
        buyPlatform.setPlatformID(Long.valueOf(platformID));
        buyPlatform.setPlatformName(platformName);
        buyPlatform.setInvalidFlag(Integer.valueOf(invalidFlag));
        if (buyPlatform.getId() == 0L){
            dao.saveOne(buyPlatform);
        } else {
            dao.updateOne(buyPlatform);
        }
    }




}
