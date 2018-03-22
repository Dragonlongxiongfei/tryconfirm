package com.chenyou.admin.controller;

import com.chenyou.admin.service.SellerStoreService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by amoslong on 2017/12/11.
 *
 * 店铺管理
 */
@Controller
@RequestMapping("store")
public class SellerStoreController {

    final static Logger LOG = LoggerFactory.getLogger(SellerStoreController.class);

    @Autowired
    private SellerStoreService sellerStoreService;

    @RequestMapping(value = "list",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView list(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/seller/storelist");
        String storeId = request.getParameter("storeId"),withInvalid = request.getParameter("with-invalid"),uId = request.getParameter("uId");
        mav.addObject("storeId", StringUtils.defaultIfEmpty(storeId,""));
        mav.addObject("uId", StringUtils.defaultIfEmpty(uId,""));
        mav.addObject("withInvalid", StringUtils.defaultIfEmpty(withInvalid,""));
        return mav;
    }

    @RequestMapping(value = "data",method = RequestMethod.GET)
    @ResponseBody
    public String getSeller(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size, HttpServletRequest request){
        String jsonResult = "";
        try {
            jsonResult = sellerStoreService.page(request,page,size);
        } catch (Exception e) {
            LOG.error("storedata error: ", e);
            JSONObject err = new JSONObject();
            err.put("result", e.getMessage());
            jsonResult = err.toString();
        } finally {
            return jsonResult;
        }
    }

    @RequestMapping(value = "sellerstore",method = RequestMethod.GET)
    @ResponseBody
    public String sellerstore(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size, HttpServletRequest request){
        String jsonResult = "";
        try {
            jsonResult = null;
        } catch (Exception e) {
            LOG.error("sellerdata error: ", e);
            JSONObject err = new JSONObject();
            err.put("result", e.getMessage());
            jsonResult = err.toString();
        } finally {
            return jsonResult;
        }
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public String update( HttpServletRequest request){
        String jsonResult = "";
        try {
            String storeId = request.getParameter("storeId");
            String uid = request.getParameter("uid");
            sellerStoreService.update(storeId,uid);
            jsonResult = "删除商铺成功"+storeId;
        } catch (Exception e) {
            LOG.error("sellerdata error: ", e);
            JSONObject err = new JSONObject();
            err.put("result", e.getMessage());
            jsonResult = err.toString();
        }
            return jsonResult;
    }
}
