package com.chenyou.admin.service;

import com.chenyou.admin.dao.SysUserRateDao;
import com.chenyou.admin.domain.SysUserRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Shell Li on 2018/2/2.
 */
@Service
public class SysUserRateService {
    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysUserRateDao sysUserRateDao;

    /**
     * 更改用户的中奖率
     * @param dataList
     */
    public void updateUserRate(List<SysUserRate> dataList) {
        if (dataList != null && dataList.size() > 0) {
            for (SysUserRate sysUserRate : dataList) {
                SysUserRate userRateExist = sysUserRateDao.findOne(sysUserRate.getUid());
                if (userRateExist == null) {
                    sysUserRateDao.insertOne(sysUserRate);
                } else {
                    sysUserRateDao.updateOne(sysUserRate);
                }
            }
        }
    }

    public SysUserRate getOne(long uid) {
        SysUserRate sysUserRate = sysUserRateDao.findOne(new Long(uid).intValue());
        return sysUserRate;
    }

}
