package com.chenyou.admin.service;

import com.chenyou.admin.dao.VipUserDao;
import com.chenyou.admin.domain.VipUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Shell Li on 2018/2/2.
 */
@Service
public class VipUserService {

    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VipUserDao vipUserDao;

    public List<VipUser> getVipUserByUid(long uid) {
        return vipUserDao.findListByUid(uid);
    }

}
