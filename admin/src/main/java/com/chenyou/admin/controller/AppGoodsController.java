package com.chenyou.admin.controller;

import com.chenyou.admin.Utils.ApplicationConstant;
import com.chenyou.admin.Utils.StringTools;
import com.chenyou.admin.domain.GdGoods;
import com.chenyou.admin.service.GdGoodsService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by amoslong on 2017/12/11.
 *
 *  商 品 管 理
 */
@Controller
@RequestMapping("task")
public class AppGoodsController {

    final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GdGoodsService goodsService;

    /**
     * 页面查询入口
     * @param request
     * @return
     */
    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = "list")
    public ModelAndView list(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/seller/goodslist");
        String goodsId = request.getParameter("goodsId"),uid = request.getParameter("uid");
        String[] withInvalids = request.getParameterValues("with-Invalid");
        String orderName = request.getParameter("sortName");
        String withInvalid = null;
        String[] platforms = request.getParameterValues("platform");
        String platform = null;
        if(withInvalids!=null) {
            withInvalid = Arrays.toString(withInvalids).replace("[", "(").replace("]", ")");
            if (withInvalid.contains("1"))
                withInvalid = withInvalid.replace("1", "'1'");
            else
                withInvalid = withInvalid.replace("2", "'2'");
        }
        if(platforms!=null){
            platform = Arrays.toString(platforms).replace("[","(").replace("]",")");
            if(platform.contains("1"))
                platform = platform.replace("1","'1'");
            if(platform.contains("0"))
                platform = platform.replace("0","'0'");
        }
        String terminalType = request.getParameter("terminalType");
        modelAndView.addObject("terminalType",terminalType);
        modelAndView.addObject("goodsId", StringUtils.defaultIfEmpty(goodsId ,""));
        modelAndView.addObject("uid", StringUtils.defaultIfEmpty(uid ,""));
        modelAndView.addObject("withInvalid", StringUtils.defaultIfEmpty(withInvalid ,""));
        modelAndView.addObject("platform", StringUtils.defaultIfEmpty(platform ,""));
        return modelAndView;
    }

    @RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value = "productslist")
    public ModelAndView productsList(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/seller/products");
        String goodsId = request.getParameter("goodsId"),uid = request.getParameter("uid"),goodsName = request.getParameter("goodsName");
        String withInvalid = "(1)";
        String[] platforms = request.getParameterValues("platform");
        String platform = null;
        if(platforms!=null){
            platform = Arrays.toString(platforms).replace("[","(").replace("]",")");
            if(platform.contains("1"))
                platform = platform.replace("1","'1'");
            if(platform.contains("0"))
                platform = platform.replace("0","'0'");
        }
        String terminalType = request.getParameter("terminalType");
        modelAndView.addObject("terminalType",terminalType);
        modelAndView.addObject("goodsId", StringUtils.defaultIfEmpty(goodsId ,""));
        modelAndView.addObject("uid", StringUtils.defaultIfEmpty(uid ,""));
        modelAndView.addObject("withInvalid", StringUtils.defaultIfEmpty(withInvalid ,""));
        modelAndView.addObject("platform", StringUtils.defaultIfEmpty(platform ,""));
        modelAndView.addObject("goodsName", StringUtils.defaultIfEmpty(goodsName ,""));
        return modelAndView;
    }
    /**
     * 获取未审核数据
     * @param request
     * @param page
     * @param size
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "goodsdata",method = RequestMethod.GET)
    public String getGoodsData(HttpServletRequest request,@RequestParam(defaultValue = "0", required = false) int page,
                               @RequestParam(defaultValue = "50", required = false) int size) {
        String jsonResult = "";
        try {
            jsonResult = goodsService.getUncheckGoods(request,page, size);
        } catch (Exception e) {
            LOG.error("goodsdata error: ", e);
            JSONObject err = new JSONObject();
            err.put("result", e.getMessage());
            jsonResult = err.toString();
        } finally {
            return jsonResult;
        }
    }

    /**
     * 获取商品数据
     * @param request
     * @param page
     * @param size
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "gooddata",method = RequestMethod.GET)
    public String getProducts(HttpServletRequest request,@RequestParam(defaultValue = "0", required = false) int page,
                               @RequestParam(defaultValue = "50", required = false) int size) {
        String jsonResult = "";
        try {
            jsonResult = goodsService.getcheckGoods(request,page, size);
        } catch (Exception e) {
            LOG.error("goodsdata error: ", e);
            JSONObject err = new JSONObject();
            err.put("result", e.getMessage());
            jsonResult = err.toString();
        } finally {
            return jsonResult;
        }
    }

    /**
     * 审核是否通过
     * @param request
     * @return
     */
    @RequestMapping(method= RequestMethod.POST,value = "update")
    @ResponseBody
    public String update(HttpServletRequest request,int gid, int passFlag,int uid){
        String result = "审核成功";
        try {
            String unpassReason = request.getParameter("unpassReason");
            unpassReason = StringUtils.defaultIfEmpty(unpassReason,"");
            goodsService.update(gid,passFlag,uid,unpassReason);
        }catch (Exception e){
            result = "商品链接错误";
            LOG.error(e.getMessage());
        }
        return result;
    }
    @RequestMapping(method= RequestMethod.POST,value = "dataedit")
    public String dataedit(HttpServletRequest request, @RequestParam("searchResultImg") MultipartFile file1){
        String result = "编辑成功";
        try {
            String goodsId = request.getParameter("id");
            String goodsName = request.getParameter("goodsName");
            String storeName = request.getParameter("storeName");
            String goodsOrder = request.getParameter("goodsOrder");
            String platform = request.getParameter("platform");
            String goodsNum = request.getParameter("goodsNum");
            String prizeNum = request.getParameter("prizeNum");
            String applyNum = request.getParameter("applyNum");
            String invalidFlag = request.getParameter("invalidFlag");
            GdGoods gdGoods = new GdGoods();
            if(StringUtils.isNotEmpty(file1.getOriginalFilename())) {
                String original1 = file1.getOriginalFilename();
                System.out.println(original1.length());
                int index = original1.lastIndexOf(".");
                String extend1 = original1.substring(index);
                String path1 = System.currentTimeMillis() + RandomUtils.nextInt(10000) + extend1;
                String storeDirectory = ApplicationConstant.CDN_URL + StringTools.dateToString(new Date(), "yyyyMMdd");
                File directory = new File(storeDirectory);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                ProcessBuilder processBuilder = null;
                processBuilder = new ProcessBuilder("chmod", "-R", "755", directory.toString() + "/");
                if (!file1.isEmpty()) {
                    File lightImgFile = new File(storeDirectory + "/" + path1);
                    file1.transferTo(lightImgFile);
                    processBuilder.start();
                }
                if (!StringUtils.isEmpty(path1)) {
                    gdGoods.setSearchResultImg(ApplicationConstant.IMG_URL + "/" + StringTools.dateToString(new Date(), "yyyyMMdd") + "/" + path1.trim());
                }
            }
            gdGoods.setGoodsId(Integer.parseInt(goodsId));
            gdGoods.setStoreName(storeName);
            gdGoods.setGoodsName(goodsName);
            gdGoods.setGoodsOrder(Integer.parseInt(goodsOrder));
            gdGoods.setInvalidFlag(Integer.parseInt(invalidFlag));
            gdGoods.setPlatform(Integer.parseInt(platform));
            gdGoods.setGoodsNum(Integer.parseInt(goodsNum));
            gdGoods.setPrizeNum(Integer.parseInt(prizeNum));
            gdGoods.setApplyNum(Integer.parseInt(applyNum));
            goodsService.updateGoodsById(gdGoods);
        }catch (Exception e){
            result = "编辑失败";
            LOG.error(e.getMessage());
        }
        return "redirect:/task/productslist";
    }
    @RequestMapping(method= RequestMethod.POST,value = "delete")
    @ResponseBody
    public String delete(int gid){
        String result = "删除成功";
        try {
            goodsService.delete(gid);
        }catch (Exception e){
            result = "删除失败";
            LOG.error(e.getMessage());
        }
        return result;
    }

    @RequestMapping(method= RequestMethod.GET,value = "update")
    @ResponseBody
    public ModelAndView findOne(int gid){
        ModelAndView modelAndView = new ModelAndView("");
        try {
            GdGoods gdGoods = goodsService.findOne(gid);
            modelAndView.addObject("gdGoods",gdGoods);
        }catch (Exception e){
            LOG.error(e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/data", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String page (HttpServletRequest request,@RequestParam(defaultValue = "0", required = false) int page,
                        @RequestParam(defaultValue = "50", required = false) int size){
        String result = "";
        try {
            result = goodsService.getAllGoods(request,page,size);
        }catch (Exception e){
            JSONObject msg = new JSONObject();
            msg.put("error", e.getMessage());
            result = msg.toString();
            LOG.error("gddata error");
        }
        return result;
    }
}
