package com.chenyou.admin.common.prize;

import com.chenyou.admin.Utils.StringTools;
import com.chenyou.admin.Utils.XuanWuSmsUtil;
import com.chenyou.admin.common.redis.GdTaskRedis;
import com.chenyou.admin.domain.GdGoods;
import com.chenyou.admin.domain.GdTask;
import com.chenyou.admin.domain.SysUser;
import com.chenyou.admin.domain.SysUserRate;
import com.chenyou.admin.service.GdGoodsService;
import com.chenyou.admin.service.GdTaskService;
import com.chenyou.admin.service.SysUserRateService;
import com.chenyou.admin.service.SysUserService;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Shell Li on 2018/2/2.
 */
@Component
public class SysPrizeService {

    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GdTaskService gdTaskService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRateService sysUserRateService;
    @Autowired
    private GdGoodsService gdGoodsService;
    @Autowired
    private GdTaskRedis gdTaskRedis;
    @Autowired
    private XuanWuSmsUtil xuanWuSmsUtil;

    @Scheduled(cron = "0 31 18 * * ?")
    public void CalculatePrizeRate() {
        log.info("calculate prize rate start ...");
        int page = 1;
        int pageSize = 100;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        DecimalFormat df = new DecimalFormat("0.00");
        while(true) {
            List<SysUser> sysUsers = sysUserService.getSysUserByPage(page, pageSize);
            if (sysUsers.size() == 0) {
                break;
            }

            List<SysUserRate> operateList = new ArrayList<>();
            /** 遍历所有用户、 计算中奖权重 **/
            for (SysUser sysUser : sysUsers) {
                if (sysUser.getUserType() == 2)
                    continue;
                float prizeRate = 0f;
                /** VIP会员身份： 权重15% **/
                boolean isVip = sysUserService.isVipBuyer(sysUser);
                if (isVip) {
                    log.info("uid :" + sysUser.getUid() + "is vip!!! prize rate add 15");
                    prizeRate += 15;
                }
                /** 30天每日任务完成度+30天内登录网站申请总天数：权重15% + 10% **/
                log.info("task user apply times before 30 days start");
                List<String> judgeList = new ArrayList<>();
                float taskApplyRate = 25f;
                List<GdTask> userTaskList = gdTaskService.getUserGdTaskList(sysUser.getUid());

                String dateBefore30 = StringTools.dateToString(calendar.getTime(), "yyyy-MM-dd");
                for (GdTask gdTask : userTaskList) {
                    if (gdTask.getAddTime().compareTo(dateBefore30) > 0) {
                        if (!judgeList.contains(gdTask.getAddTime())) {
                            judgeList.add(gdTask.getAddTime());
                        }
                    }
                }
                float applyRateInOneMonth = (float) judgeList.size() / 30;
                // 30天内申请任务数 权重最后结果
                float taskApplyRateNew = taskApplyRate * Float.valueOf(df.format(applyRateInOneMonth));
                prizeRate = prizeRate + taskApplyRateNew;
                log.info("uid :" + sysUser.getUid() + " taskApplyRate !!! prize rate add " + taskApplyRateNew);
                judgeList.clear();
                /** 30天内试用完成率 + 30天内商家给与评论评分 ：权重20 **/
                float taskFinishRate = 20f;
                for (GdTask gdTask : userTaskList) {
                    if (gdTask.getAddTime().compareTo(dateBefore30) < 0)
                        continue;
                    if (!StringUtils.isEmpty(gdTask.getTaskApproveTime()) && gdTask.getTaskStatus() == 6) {
                        if (!judgeList.contains(gdTask.getAddTime())) {
                            judgeList.add(gdTask.getAddTime());
                        }
                    }
                }
                float finishRateInOneMonth = (float) judgeList.size() / 30;
                // 30内完成任务书 权重最后结果
                float taskFinishRateNew = taskFinishRate * Float.valueOf(df.format(finishRateInOneMonth));
                prizeRate = prizeRate + taskFinishRateNew;
                judgeList.clear();
                /** 30天内同一商品连续申请3天：权重10 **/
                float threeDayApplyRate = 10f;
                for (GdTask gdTask : userTaskList) {
                    if (gdTask.getAddTime().compareTo(dateBefore30) < 0)
                        continue;
                    if (!StringUtils.isEmpty(gdTask.getFirstDayEnd()) && !StringUtils.isEmpty(gdTask.getSecondDayEnd())
                            && !StringUtils.isEmpty(gdTask.getThirdDayEnd())) {
                        if (!judgeList.contains(gdTask.getAddTime())) {
                            judgeList.add(gdTask.getAddTime());
                        }
                    }
                }
                // 30天内同一商品连续申请3天 权重最后结果
                float threeDayApplyTimeRate = (float) judgeList.size() / 30;
                float threeDayApplyRateNew = threeDayApplyRate * Float.valueOf(df.format(threeDayApplyTimeRate));
                prizeRate = prizeRate + threeDayApplyRateNew;
                judgeList.clear();

                /** 设置每个用户中奖率 **/
                SysUserRate sysUserRate = new SysUserRate();
                sysUserRate.setUid(sysUser.getUid());
                sysUserRate.setPrizeRate(Float.valueOf(df.format(prizeRate)));
                operateList.add(sysUserRate);
            }
            sysUserRateService.updateUserRate(operateList);
            page++;
        }

        log.info("calculate prize rate end ...");
    }

    /**
     * 每天10:00 的定时抽奖
     */
    @Scheduled(cron = "0 09 10 * * ?")
    private void FirstLottery() {
        log.info("lottery1 start...");
        int page = 1;
        int pageSize = 50;
        Calendar now = Calendar.getInstance();
        String prizeNow = StringTools.dateToString(now.getTime(), "yyyy-MM-dd 10:30:00");
        now.add(Calendar.DATE, -1);
        String prizeLastNight = StringTools.dateToString(now.getTime(), "yyyy-MM-dd 22:00:00");
        while (true) {
            // 待抽奖商品
            List<GdGoods> lotteryList = gdGoodsService.getGdgoodsPagable(page, pageSize);
            if (lotteryList.size() == 0) {
                break;
            }

            for (GdGoods gdGoods : lotteryList) {
                try {
                    log.info("goods_id :" + gdGoods.getGoodsId() + " lottery.... ");
                    // uid为5，6 ,65发布的商品都是假商品,全部不能中奖
                    if (gdGoods.getUid() == 5 || gdGoods.getUid() == 6 || gdGoods.getUid() == 65) {
                        List<GdTask> unprizeTaskList = gdTaskService.getLotteryTasks(gdGoods.getGoodsId());
                        for (GdTask gdTask : unprizeTaskList) {
                            gdTask.setPrizeFlag(2);gdTaskService.update(gdTask);
                            gdTaskRedis.editOne(gdTask);
                        }
                        continue;
                    }
                    if (gdGoods.getGoodsNum() <= gdGoods.getPrizeNum()){
                        log.info("goods_id:["+ gdGoods.getGoodsId() + "] 试用份数已经不够");
                        continue;
                    }

                    // 本商品的待抽奖任务
                    List<GdTask> prizeTaskList = gdTaskService.getLotteryTasks(gdGoods.getGoodsId());
                    if (prizeTaskList == null || prizeTaskList.size() == 0) {
                        log.info("goods_id:[" + gdGoods.getGoodsId() + "] 该商品没有待抽奖的task......");
                        continue;
                    }
                    // 本次抽取prizeNum个中奖名额
                    int limitLotteryNum = gdGoods.getLimitPerDay();
                    int prizeNum = 1 + RandomUtils.nextInt(limitLotteryNum);

                    // 中奖的任务列表
                    List<GdTask> luckList = new ArrayList<>();
                    for (GdTask gdTask : prizeTaskList) {
                        // 只对昨天晚上到今天早上提交的申请，进行抽奖
                        if ((gdTask.getFirstDayEnd() != null && gdTask.getFirstDayEnd().compareTo(prizeLastNight) >= 0 && gdTask.getFirstDayEnd().compareTo(prizeNow) <= 0)
                                || (gdTask.getSecondDayEnd() != null && gdTask.getSecondDayEnd().compareTo(prizeLastNight) >= 0 && gdTask.getSecondDayEnd().compareTo(prizeNow) <= 0)
                                || (gdTask.getThirdDayEnd() != null && gdTask.getThirdDayEnd().compareTo(prizeLastNight) >= 0 && gdTask.getThirdDayEnd().compareTo(prizeNow) <= 0)) {
                            // 中奖的任务列表luckList.size() > 本次抽取数prizeNum， 则停止抽奖
                            if (luckList.size() == prizeNum) {
                                log.info("goods_id[" + gdGoods.getGoodsId() + "] 抽奖名额为:"+ prizeNum + "次， 已经抽到" + luckList.size() + "个名额... continue");
                                continue;
                            }

                            // 若待抽奖的任务数小于 <= 此次抽奖的数， 则全部中奖
                            if (prizeTaskList.size() <= prizeNum) {
                                gdTask.setTaskStatus(3);
                                gdTask.setPrizeFlag(1);
                                gdTask.setPrizeTime(StringTools.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
                                luckList.add(gdTask);
                                log.info("task:[" + gdTask.getTaskId() + "] 中奖了...");
                            } else {
                                long applyUID = gdTask.getUid();
                                SysUserRate sysUserRate = sysUserRateService.getOne(applyUID);
                                // 根据用户的中奖权重去设置是否中奖
                                int random = RandomUtils.nextInt(100);
                                if (sysUserRate.getPrizeRate() >= random) {
                                    gdTask.setTaskStatus(3);
                                    gdTask.setPrizeFlag(1);
                                    gdTask.setPrizeTime(StringTools.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
                                    // 领奖时间设置后续第3天的21:30:00
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.add(Calendar.DATE, 3);
                                    gdTask.setTouchPrizeTime(StringTools.dateToString(calendar.getTime(), "yyyy-MM-dd 21:30:00"));
                                    luckList.add(gdTask);
                                    log.info("task:[" + gdTask.getTaskId() + "] 中奖了...");
                                }else{
                                    gdTask.setPrizeFlag(2);gdTaskService.update(gdTask);
                                    gdTaskRedis.editOne(gdTask);
                                }
                            }
                        }
                    }
                    // 到此，若一个也没有抽到，则一直循环抽取 prizeNum 个名额为止
                    if (luckList.size() == 0) {
                        while (true) {
                            if (luckList.size() == prizeNum || luckList.size() == prizeTaskList.size()) {
                                break;
                            }
                            for (GdTask gdTask : prizeTaskList) {
                                long applyUID = gdTask.getUid();
                                SysUserRate sysUserRate = sysUserRateService.getOne(applyUID);
                                // 根据用户的中奖权重去设置是否中奖
                                int random = RandomUtils.nextInt(100);
                                if (sysUserRate.getPrizeRate() >= random) {
                                    gdTask.setTaskStatus(3);
                                    gdTask.setPrizeFlag(1);
                                    gdTask.setPrizeTime(StringTools.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
                                    // 领奖时间设置后续第3天的21:30:00
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.add(Calendar.DATE, 3);
                                    gdTask.setTouchPrizeTime(StringTools.dateToString(calendar.getTime(), "yyyy-MM-dd 21:30:00"));
                                    luckList.add(gdTask);
                                    log.info("task:[" + gdTask.getTaskId() + "] 中奖了...");
                                }else{
                                    gdTask.setPrizeFlag(2);gdTaskService.update(gdTask);
                                    gdTaskRedis.editOne(gdTask);
                                }
                            }
                        }
                    }

                    if (luckList != null && luckList.size() > 0) {
                        // 更新该商品的中奖人数,DB 以及 redis
                        gdGoodsService.addGoodsPrizeNum(gdGoods.getGoodsId(), luckList.size());
                        // 记录10:00 抽奖份数, 表：gd_prize_num
                        gdGoodsService.firstLotteryGoodsNum(gdGoods.getGoodsId(), luckList.size());
                        // 更新prizeTaskList中中奖的任务, DB 以及 redis
                        gdTaskService.batchUpdateLuckyTasks(luckList);
                        xuanWuSmsUtil.doSendEmg(luckList);
                    }
                } catch (Exception e) {
                    log.error("goods_id[" + gdGoods.getGoodsId() + "] 抽奖发生错误", e);
                }
            }

            page++;
        }

        log.info("lottery1 end...");
    }

    /**
     * 晚上第二次抽奖
     */
//    @Scheduled(cron = "0 0 22 * * ?")
    private void SecondLottery() {
        log.info("lottery2 start...");
        int page = 1;
        int pageSize = 50;
        Calendar now = Calendar.getInstance();
        String prizeLastTime = StringTools.dateToString(now.getTime(), "yyyy-MM-dd 10:00:00");
        String prizeNow = StringTools.dateToString(now.getTime(), "yyyy-MM-dd 22:00:00");
        while (true) {
            // 待抽奖商品
            List<GdGoods> lotteryList = gdGoodsService.getGdgoodsPagable(page, pageSize);
            if (lotteryList.size() == 0) {
                break;
            }

            for (GdGoods gdGoods : lotteryList) {
                try {
                    log.info("goods_id :" + gdGoods.getGoodsId() + " lottery.... ");
                    // uid为5，6 发布的商品都是假商品
                    if (gdGoods.getUid() == 5 || gdGoods.getUid() == 6)
                        continue;
                    if (gdGoods.getGoodsNum() <= gdGoods.getPrizeNum()){
                        log.info("goods_id:["+ gdGoods.getGoodsId() + "] 试用份数已经不够");
                        continue;
                    }

                    // 本商品的待抽奖任务
                    List<GdTask> prizeTaskList = gdTaskService.getLotteryTasks(gdGoods.getGoodsId());
                    if (prizeTaskList == null || prizeTaskList.size() == 0) {
                        log.info("goods_id:[" + gdGoods.getGoodsId() + "] 该商品没有待抽奖的task......");
                        continue;
                    }
                    // 查看上次中奖该商品抽取了多少个名额，本次抽取prizeNum个中奖名额
                    Integer tempLastLotteryNum = gdGoodsService.lastLotterNum(gdGoods.getGoodsId());
                    Integer lastLotteryNum = tempLastLotteryNum == null ? 0 : tempLastLotteryNum;
                    int limitLotteryNum = gdGoods.getLimitPerDay();
                    int prizeNum = limitLotteryNum - lastLotteryNum;

                    // 中奖的任务列表
                    List<GdTask> luckList = new ArrayList<>();
                    for (GdTask gdTask : prizeTaskList) {
                        // 只对昨天晚上到今天早上提交的申请，进行抽奖
                        if ((gdTask.getFirstDayEnd() != null && gdTask.getFirstDayEnd().compareTo(prizeLastTime) >= 0 && gdTask.getFirstDayEnd().compareTo(prizeNow) <= 0)
                                || (gdTask.getSecondDayEnd() != null && gdTask.getSecondDayEnd().compareTo(prizeLastTime) >= 0 && gdTask.getSecondDayEnd().compareTo(prizeNow) <= 0)
                                || (gdTask.getThirdDayEnd() != null && gdTask.getThirdDayEnd().compareTo(prizeLastTime) >= 0 && gdTask.getThirdDayEnd().compareTo(prizeNow) <= 0)) {
                            // 中奖的任务列表luckList.size() > 本次抽取数prizeNum， 则停止抽奖
                            if (luckList.size() == prizeNum) {
                                log.info("goods_id[" + gdGoods.getGoodsId() + "] 抽奖名额为:"+ prizeNum + "次， 已经抽到" + luckList.size() + "个名额... continue");
                                continue;
                            }

                            // 若待抽奖的任务数小于 <= 此次抽奖的数， 则全部中奖
                            if (prizeTaskList.size() <= prizeNum) {
                                gdTask.setTaskStatus(3);
                                gdTask.setPrizeFlag(1);
                                gdTask.setPrizeTime(StringTools.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
                                // 领奖时间设置后续第3天的21:30:00
                                Calendar calendar = Calendar.getInstance();
                                calendar.add(Calendar.DATE, 3);
                                gdTask.setTouchPrizeTime(StringTools.dateToString(calendar.getTime(), "yyyy-MM-dd 21:30:00"));
                                luckList.add(gdTask);
                                log.info("task:[" + gdTask.getTaskId() + "] 中奖了...");
                            } else {
                                long applyUID = gdTask.getUid();
                                SysUserRate sysUserRate = sysUserRateService.getOne(applyUID);
                                // 根据用户的中奖权重去设置是否中奖
                                int random = RandomUtils.nextInt(100);
                                if (sysUserRate.getPrizeRate() >= random) {
                                    gdTask.setTaskStatus(3);
                                    gdTask.setPrizeFlag(1);
                                    gdTask.setPrizeTime(StringTools.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
                                    luckList.add(gdTask);
                                    log.info("task:[" + gdTask.getTaskId() + "] 中奖了...");
                                }else{
                                    gdTask.setPrizeFlag(2);
                                    gdTaskService.update(gdTask);
                                    gdTaskRedis.editOne(gdTask);
                                }
                            }
                        }
                    }
                    // 到此，若一个也没有抽到，则一直循环抽取 prizeNum 个名额为止
                    if (luckList.size() == 0) {
                        while (true) {
                            if (luckList.size() == prizeNum || luckList.size() == prizeTaskList.size()) {
                                break;
                            }
                            for (GdTask gdTask : prizeTaskList) {
                                long applyUID = gdTask.getUid();
                                SysUserRate sysUserRate = sysUserRateService.getOne(applyUID);
                                // 根据用户的中奖权重去设置是否中奖
                                int random = RandomUtils.nextInt(100);
                                if (sysUserRate.getPrizeRate() >= random) {
                                    gdTask.setTaskStatus(3);
                                    gdTask.setPrizeFlag(1);
                                    gdTask.setPrizeTime(StringTools.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
                                    // 领奖时间设置后续第3天的21:30:00
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.add(Calendar.DATE, 3);
                                    gdTask.setTouchPrizeTime(StringTools.dateToString(calendar.getTime(), "yyyy-MM-dd 21:30:00"));
                                    log.info("task:[" + gdTask.getTaskId() + "] 中奖了...");
                                    luckList.add(gdTask);
                                }else{
                                    gdTask.setPrizeFlag(2);
                                    gdTaskService.update(gdTask);
                                    gdTaskRedis.editOne(gdTask);
                                }
                            }
                        }
                    }

                    // 更新该商品的中奖人数,DB 以及 redis
                    // 更新prizeTaskList中中奖的任务, DB 以及 redis
                    // 给中奖用户发站内消息
                    if (luckList.size() != 0) {
                        gdGoodsService.addGoodsPrizeNum(gdGoods.getGoodsId(), luckList.size());
                        gdTaskService.batchUpdateLuckyTasks(luckList);
                        xuanWuSmsUtil.doSendEmg(luckList);
                    }
                } catch (Exception e) {
                    log.error("goods_id[" + gdGoods.getGoodsId() + "] 抽奖发生错误 ", e);
                }
            }

            page++;
        }

        log.info("lottery2 end...");
    }

    /**
     * 凌晨1点清空
     */
//    @Scheduled(cron = "0 20 13 * * ?")
    private void ClearPrizeNum() {
        gdGoodsService.clearTable();
    }

    public static void main (String [] args) {
//        float a = 0f;
//        int total = 30;
//        float rate = a / total;
//        DecimalFormat df = new DecimalFormat("0.00");
//        System.out.println("输出为：" + df.format(rate));
        for (int i = 0; i < 1000; i++) {
            System.out.println(RandomUtils.nextInt(5));
        }
    }


}
