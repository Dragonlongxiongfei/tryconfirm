package com.chenyou.admin.controller;

import com.chenyou.admin.domain.FnIORecord;
import com.chenyou.admin.domain.FnRechargeRecord;
import com.chenyou.admin.domain.FnWithdrawalRecord;
import com.chenyou.admin.service.FinanceService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.entity.ExportParams;
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
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Shell Li on 2017/12/25.
 */
@Controller
@RequestMapping(value = "finance")
public class FinanceController {
    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FinanceService financeService;

    /**
     * 充值记录
     * @param request
     * @return
     */
    @RequestMapping(value = "/recharge_init", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView rechargeInit(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("fnrechargelist");
        String uid = request.getParameter("uid"), userAccount = request.getParameter("user-account"),
                charType = request.getParameter("char-type");

        mv.addObject("uid", StringUtils.defaultIfEmpty(uid, ""));
        mv.addObject("userAccount", StringUtils.defaultIfEmpty(userAccount, ""));
        mv.addObject("charType", StringUtils.defaultIfEmpty(charType, ""));
        return mv;
    }

    /**
     * 提现记录
     * @param request
     * @return
     */
    @RequestMapping(value = "/withdrawal_init", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView withdrawalInit(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("fnwithdrawalrecordlist");
        String uid = request.getParameter("uid"), userAccount = request.getParameter("userAccount"),
                withdrawalType = request.getParameter("withdrawalType");
        mv.addObject("uid", StringUtils.defaultIfEmpty(uid, ""));
        mv.addObject("userAccount", StringUtils.defaultIfEmpty(userAccount, ""));
        mv.addObject("withdrawalType", StringUtils.defaultIfEmpty(withdrawalType, ""));
        return mv;
    }

    /**
     * 资金明细记录
     * @param request
     * @return
     */
    @RequestMapping(value = "/io_record_init", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView ioRecordInit(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("fniorecordlist");
        String uid = request.getParameter("uid"), userAccount = request.getParameter("userAccount"),
                moneyType = request.getParameter("moneyType");

        mv.addObject("uid", StringUtils.defaultIfEmpty(uid, ""));
        mv.addObject("userAccount", StringUtils.defaultIfEmpty(userAccount, ""));
        mv.addObject("moneyType", StringUtils.defaultIfEmpty(moneyType, ""));
        return mv;
    }

    @RequestMapping(value = "/rechargeList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String rechargeList(HttpServletRequest request, @RequestParam(defaultValue = "0", required = false) int page,
               @RequestParam(defaultValue = "50") int size){
        String result = "";
        try {
            result = financeService.rechargeList(request, page, size);
        } catch (Exception e) {
            log.error("rechargeList:", e);
        }
        return result;
    }

    @RequestMapping(value = "/IORecordList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String fnIORecordList(HttpServletRequest request, int page, int size) {
        String result = "";
        try {
            result = financeService.FnIORecordList(request, page, size);
        } catch (Exception e) {
            log.error("fnIORecordList:", e);
        }
        return result;
    }

    @RequestMapping(value = "/withdrawalList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String fnWithdrawalRecordList(HttpServletRequest request, int page, int size) {
        String result = "";
        try {
            result = financeService.FnWithdrawalList(request, page, size);
        } catch (Exception e) {
            log.error("withdrawalList error", e);
        }
        return result;
    }

    @RequestMapping(value = "/recharge_record_excel", method = {RequestMethod.POST, RequestMethod.GET})
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = sdf.format(date);
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("充值记录", "UTF-8")
                + timeStamp + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");
        List<FnRechargeRecord> list = financeService.rechargeListNoPage(request);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), FnRechargeRecord.class, list);
        workbook.write(response.getOutputStream());
    }

    @RequestMapping(value = "/io_record_excel", method = {RequestMethod.POST})
    public void exportIORecordExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = sdf.format(date);
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("资金明细", "UTF-8")
                + timeStamp + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");
        List<FnIORecord> list = financeService.getFnIORecordList(request);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), FnIORecord.class, list);
        workbook.write(response.getOutputStream());
    }

    @RequestMapping(value = "/withdrawal_record_excel", method = {RequestMethod.POST})
    public void exportWithdrawalRecord(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = sdf.format(date);
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("提现记录", "UTF-8")
                + timeStamp + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");
        List<FnWithdrawalRecord> list = financeService.getWithdrawalList(request, null, null);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), FnWithdrawalRecord.class, list);
        workbook.write(response.getOutputStream());
    }


}
