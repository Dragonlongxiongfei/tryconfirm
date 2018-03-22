package com.chenyou.admin.controller;

import com.chenyou.admin.Utils.ToolUtil;
import com.chenyou.admin.domain.SellerVip;
import com.chenyou.admin.domain.SysManage;
import com.chenyou.admin.domain.SysUser;
import com.chenyou.admin.domain.SysUserSeller;
import com.chenyou.admin.service.SellerService;
import com.chenyou.admin.service.SellerVipService;
import com.chenyou.admin.service.SysUserService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by amoslong on 2017/11/29.
 */
@RestController
@RequestMapping("seller")
public class AppSellerController {
    final static Logger LOG = LoggerFactory.getLogger(AppSellerController.class);
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private SellerVipService sellerVipService;

    /**
     * 页面控制跳转(带搜索的关键词)
     * @param request
     * @return
     */
    @RequestMapping(value = "sellerlist",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView sellerlist(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/seller/sellerlist");
        String uid = request.getParameter("uid");
        String inuid = request.getParameter("inuid");
        String[] vips  = request.getParameterValues("vipLevel");
        String vip = null;
        String withInvalid  = request.getParameter("withInvalid");
        String mobilePhone  = request.getParameter("mobilePhone");
        if (vips != null){
            vip = Arrays.asList(vips).toString().replace("[","(").replace("]", ")");
            if (vip.contains("1"))
                vip = vip.replace("1", "'1'");
            if (vip.contains("2"))
                vip = vip.replace("2", "'2'");
            if (vip.contains("3"))
                vip = vip.replace("3", "'3'");
        }
        mav.addObject("uid", StringUtils.defaultIfEmpty(uid,""));
        mav.addObject("inuid", StringUtils.defaultIfEmpty(inuid,""));
        mav.addObject("vipLevel", StringUtils.defaultIfEmpty(vip,""));
        mav.addObject("mobilePhone", StringUtils.defaultIfEmpty(mobilePhone,""));
        mav.addObject("withInvalid", StringUtils.defaultIfEmpty(withInvalid,""));
        return mav;
    }

    /**
     * 获取商家分页数据
     * @param page
     * @param size
     * @param request
     * @return
     */
    @RequestMapping(value = "seller",method = RequestMethod.GET)
    public String getSeller(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size,HttpServletRequest request){
        String jsonResult = "";
        try {
                jsonResult = sellerService.searchBuyers(request,page,size);
        } catch (Exception e) {
            LOG.error("sellerdata error: ", e);
            JSONObject err = new JSONObject();
            err.put("result", e.getMessage());
            jsonResult = err.toString();
        } finally {
            return jsonResult;
        }
    }
    @RequestMapping(value = "vipseller",method = RequestMethod.GET)
    public String getSellerViP(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size,String search){
        String jsonResult = "";
        try {
            List<SysUser> list = sysUserService.findAllVip(page,size,search);
            int total = sysUserService.getTotalVip();
            if (null != list && list.size() > 0) {
                jsonResult = ToolUtil.tableFormat(list, total);
            }
        } catch (Exception e) {
            LOG.error("vipseller error: ", e);
            JSONObject err = new JSONObject();
            err.put("result", e.getMessage());
            jsonResult = err.toString();
        } finally {
            return jsonResult;
        }
    }

    @RequestMapping(value = "vipsellerlist",method = RequestMethod.GET)
    public ModelAndView vipsellerlist(){
        ModelAndView mav = new ModelAndView("/seller/vipsellerlist");
        try {
            List<SysUser> list = sysUserService.getSeller();
            mav.addObject("pnode", list);
        } catch (Exception e) {
            LOG.error("vipsellerlist error: ", e);
        }
        return mav;
    }
    @RequestMapping(value = "uncheckdata",method = RequestMethod.GET)
    public String uncheckdata(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size){
        String jsonResult = "";
        try {
            List<SysUser> list = sysUserService.uncheckdata(page,size);
            int total = sysUserService.uncheckdataTotal();
            if (null != list && list.size() > 0) {
                jsonResult = ToolUtil.tableFormat(list, total);
            }
        } catch (Exception e) {
            LOG.error("authsdata error: ", e);
            JSONObject err = new JSONObject();
            err.put("result", e.getMessage());
            jsonResult = err.toString();
        } finally {
            return jsonResult;
        }
    }

    @RequestMapping(value = "cashlist",method = RequestMethod.GET)
    public ModelAndView cashlist(){
        ModelAndView mav = new ModelAndView("cashlist");
        try {
            List<SysUser> list = sysUserService.getSeller();
            mav.addObject("pnode", list);
        } catch (Exception e) {
            LOG.error("cashlist error: ", e);
        }
        return mav;
    }

    @RequestMapping(value = "/sellerput", method = {RequestMethod.POST})
    public String authsSave(HttpServletRequest request,SysUser sysUser) {
        String jsonResult = "success";
        Map<String, String> result = new HashMap<>();
        result.put("result", jsonResult);
        try {
            String uid = request.getParameter("uid");
            sysUser.setUid(Integer.parseInt(uid));
            sysUserService.updateSysUser(sysUser);

            jsonResult = JSONObject.fromObject(result).toString();
        } catch (Exception e) {
            result.put("result", e.getMessage());
            jsonResult = JSONObject.fromObject(result).toString();
        } finally {
            return jsonResult;
        }
    }

    /**
     * 商家的信息查询
     * @param uid
     * @return
     */
    @RequestMapping(value = "/sellerinfo", method = {RequestMethod.GET})
    public ModelAndView sellerinfo(@RequestParam("uid") String uid) {
            ModelAndView mav = new ModelAndView("/seller/sellerinfo");
            try {
                SysUserSeller sysUserSeller = sellerService.findOneBuyer(Long.valueOf(uid));
                mav.addObject("sysUserSeller", sysUserSeller);
            } catch (Exception e) {
                JSONObject msg = new JSONObject();
                msg.put("error", e.getMessage());
                LOG.error("datalist error", e);
            }
            return mav;
        }
    /**
     * 商家充值界面
     * @param uid
     * @return
     */
    @RequestMapping(value = "/chongzhi", method = {RequestMethod.GET})
    public ModelAndView chongzhi(@RequestParam("uid") String uid) {
            ModelAndView mav = new ModelAndView("/seller/chongzhi");
            try {
                SysUserSeller sysUserSeller = sellerService.findOneBuyer(Long.valueOf(uid));
                sysUserSeller.getVipLevel();
                SellerVip sellerVip = sellerVipService.findOne(sysUserSeller.getVipLevel());
                mav.addObject("sysUserSeller", sysUserSeller);
                mav.addObject("sellerVip", sellerVip);
            } catch (Exception e) {
                JSONObject msg = new JSONObject();
                msg.put("error", e.getMessage());
                LOG.error("datalist error", e);
            }
            return mav;
        }
    /**
     * 商家充值更新
     * @return
     */
    @RequestMapping(value = "/chongzhi", method = {RequestMethod.POST})
    @ResponseBody
    public String update(@RequestParam("uid") String uid,@RequestParam("price")String price,@RequestParam("xuanze")String xuanze,@RequestParam("vipEndTime")String vipEndTime, @RequestParam("payNo")String payNo,@RequestParam("payAccount")String payAccount,@RequestParam("payType")String payType,@RequestParam("payFinishTime")String payFinishTime,HttpServletRequest request) {
            String jsonResult = "success";
            Map<String, String> result = new HashMap<>();
            result.put("result", jsonResult);
            ModelAndView mav = new ModelAndView("/seller/chongzhi");
            SysUserSeller sysUserSeller = sellerService.findOneBuyer(Long.parseLong(uid));
            if("" == price){
                price = "0";
            }
            SysManage sysManage = (SysManage) request.getSession().getAttribute("userContext");
            String updateUser = sysManage.getManName();
            try {
                sellerService.saveSeller(uid,updateUser,price,xuanze,vipEndTime,payNo,payAccount,payType,payFinishTime);
                jsonResult = JSONObject.fromObject(result).toString();
            } catch (Exception e) {
                JSONObject msg = new JSONObject();
                msg.put("error", e.getMessage());
                LOG.error("datalist error", e);
                result.put("result", e.getMessage());
                jsonResult = JSONObject.fromObject(result).toString();
            }
            return jsonResult;
        }

    /**
     * 禁用 修改 保存
     * @param request
     * @return
     */
    @RequestMapping(value = "/datasave", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String datasave(HttpServletRequest request){
        String jsonResult = "success";
        Map<String, String> result = new HashMap<>();
        result.put("result", jsonResult);
        try {
            String forbiddenTime = request.getParameter("forbiddenTime"),invalidFlag = request.getParameter("invalidFlag"),forbiddenReason = request.getParameter("forbiddenReason");
            long uid = Long.valueOf(request.getParameter("uid"));
            SysUserSeller sysUserSeller = sellerService.findOneBuyer(Long.valueOf(uid));
            if (!StringUtils.isEmpty(forbiddenTime)) {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Date date=new Date();
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(date);
                rightNow.add(Calendar.DAY_OF_YEAR,Integer.parseInt(forbiddenTime));
                String str=sdf.format(rightNow.getTime());

                sysUserSeller.setForbiddenTime(str);
            }
            /*if (!StringUtils.isEmpty(invalidFlag)) {
                sysUserSeller.setInvalidFlag(Integer.valueOf(invalidFlag));
            } if (!StringUtils.isEmpty(forbiddenReason)) {
                sysUserSeller.setForbiddenReason(forbiddenReason);
            }*/
            sysUserSeller.setForbiddenReason(forbiddenReason);
            sysUserSeller.setInvalidFlag(0);
            sellerService.saveSeller(sysUserSeller);
            jsonResult = JSONObject.fromObject(result).toString();
        } catch (Exception e) {
            JSONObject msg = new JSONObject();
            msg.put("error", e.getMessage());
            LOG.error("save error", e);
            result.put("result", e.getMessage());
            jsonResult = JSONObject.fromObject(result).toString();
        }
        return jsonResult;
    }

    @RequestMapping(method= RequestMethod.POST,value = "delete")
    @ResponseBody
    public String delete(int uid){
        String result = "删除成功";
        try {
            sellerService.delete(String.valueOf(uid));
        }catch (Exception e){
            result = "删除失败";
            LOG.error(e.getMessage());
        }
        return result;
    }

}
