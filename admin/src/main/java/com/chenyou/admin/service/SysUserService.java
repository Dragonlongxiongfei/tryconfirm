package com.chenyou.admin.service;

import com.chenyou.admin.Utils.StringTools;
import com.chenyou.admin.dao.SysUserDao;
import com.chenyou.admin.domain.SysUser;
import com.chenyou.admin.domain.VipUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amoslong on 2017/11/29.
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private VipUserService vipUserService;

    public List<SysUser> getBuyer(){
        int type = 1;
        return sysUserDao.selectByType(type);
    }
    public List<SysUser> unchecklist(){
        return sysUserDao.unchecklist();
    }
    public int uncheckdataTotal(){
        return sysUserDao.uncheckdataTotal();
    }
    public List<SysUser> getSeller(){
        Integer type = 2;
        return sysUserDao.selectByType(type);
    }
    public SysUser findOne(int uid){
        return sysUserDao.selectByPrimaryKey(uid);
    }
    public int  updateSysUser(SysUser sysUser){
        return sysUserDao.updateByPrimaryKeySelective(sysUser);
    }
    public int getTotal(){
        return sysUserDao.getTotal();
    }
    public List<SysUser> findAll(int page,int size){
        Map<String, Object> param = new HashMap<>();
        param.put("page", page);
        param.put("size", size);
        return sysUserDao.findAll(param);
    }
    public int getTotalVip(){
        return sysUserDao.getTotalVip();
    }
    public List<SysUser> findAllVip(int page,int size,String uid){
        Map<String, Object> param = new HashMap<>();
        param.put("page", page);
        param.put("search", uid);
        param.put("size", size);
        return sysUserDao.findAllVip(param);
    }
    public List<SysUser> uncheckdata(int page,int size){
        Map<String, Object> param = new HashMap<>();
        param.put("page", page);
        param.put("size", size);
        return sysUserDao.uncheckdata(param);
    }

    public List<SysUser> getSysUserByPage(int page, int pageSize) {
        page = (page - 1) * pageSize;
        return sysUserDao.findAllByPage(page, pageSize);
    }

    /**
     * 判断试客心在 是否是vip
     * @param sysUser
     * @return
     */
    public boolean isVipBuyer(SysUser sysUser) {
        List<VipUser> vipUsers = vipUserService.getVipUserByUid(sysUser.getUid());
        boolean isVip = false;
        for (VipUser vipUser : vipUsers) {
            String vip_endtime = vipUser.getVipEndTime();
            String now = StringTools.dateToString(new Date(), "yyyy-MM-dd");
            if (vip_endtime.compareTo(now) > 0) {
                isVip = true;
                break;
            }
        }
        return isVip;
    }
}
