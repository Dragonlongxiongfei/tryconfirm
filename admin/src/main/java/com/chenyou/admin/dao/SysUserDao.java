package com.chenyou.admin.dao;

import com.chenyou.admin.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserDao {
    int deleteByPrimaryKey(Integer uid);

    int insert(SysUser record);

    int getTotal();

    int getTotalVip();

    int uncheckdataTotal();

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    int updateFrozen(Map<String, Object> map);

    List<SysUser> selectByType(int type);

    List<SysUser> unchecklist();

    List<SysUser> findAll(Map<String, Object> param);

    List<SysUser> findAllVip(Map<String, Object> param);

    List<SysUser> uncheckdata(Map<String, Object> param);

    List<SysUser> findAllByPage(@Param("page") int page, @Param("pageSize") int pageSize);

}