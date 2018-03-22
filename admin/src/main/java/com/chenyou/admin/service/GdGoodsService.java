package com.chenyou.admin.service;

import com.chenyou.admin.Utils.RedisUtils;
import com.chenyou.admin.Utils.ToolUtil;
import com.chenyou.admin.Utils.UrlParamUtils;
import com.chenyou.admin.cache.CacheKeys;
import com.chenyou.admin.dao.FnIORecordDao;
import com.chenyou.admin.dao.GdGoodsDao;
import com.chenyou.admin.dao.SellerDao;
import com.chenyou.admin.domain.GdGoods;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amoslong on 2017/12/2.
 *
 * 发布的商品
 */
@Service
public class GdGoodsService {
    @Autowired
    private GdGoodsDao gdGoodsDao;

    @Autowired
    private SellerDao sellerDao;

    @Autowired
    private FnIORecordDao fnIORecordDao;
    @Autowired
    private RedisUtils<GdGoods> redisUtils;


    public String getUncheckGoods(HttpServletRequest request, int page, int size){
            String result = "";
            Map<String, Object> searchParam = new HashMap<>();
            Map<String, String[]> requestMap = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : requestMap.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                if ("page".equals(key) || "size".equals(key))
                    continue;
                if (value.length == 1) {
                    searchParam.put(key, value[0]);
                } else {
                    searchParam.put(key, value);
                }
            }
            searchParam.put("page", page * size);
            searchParam.put("size", size);

            List<GdGoods> gdGoods = gdGoodsDao.selectByStatus(searchParam);
            int total = gdGoodsDao.getUncheckTotal(searchParam);
            if (gdGoods != null && gdGoods.size() > 0) {
                result = ToolUtil.tableFormat(gdGoods, total);
            }

            return result;
        }

        public String getcheckGoods(HttpServletRequest request, int page, int size){
            String result = "";

            Map<String, Object> searchParam = new HashMap<>();
            Map<String, String[]> requestMap = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : requestMap.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                if ("page".equals(key) || "size".equals(key))
                    continue;
                if (value.length == 1) {
                    searchParam.put(key, value[0]);
                } else {
                    searchParam.put(key, value);
                }
            }
            searchParam.put("page", page * size);
            searchParam.put("size", size);

            List<GdGoods> gdGoods = gdGoodsDao.selectCheck(searchParam);
            int total = gdGoodsDao.getcheckTotal(searchParam);
            if (gdGoods != null && gdGoods.size() > 0) {
                result = ToolUtil.tableFormat(gdGoods, total);
            }

            return result;
        }

    public String getAllGoods(HttpServletRequest request, int page, int size){
            String result = "";

            Map<String, Object> searchParam = new HashMap<>();
            Map<String, String[]> requestMap = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : requestMap.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                if ("page".equals(key) || "size".equals(key))
                    continue;
                if (value.length == 1) {
                    searchParam.put(key, value[0]);
                } else {
                    searchParam.put(key, value);
                }
            }
            searchParam.put("page", page * size);
            searchParam.put("size", size);

            List<GdGoods> gdGoods = gdGoodsDao.selectAll(searchParam);
            int total = gdGoodsDao.getAllTotal(searchParam);
            if (gdGoods != null && gdGoods.size() > 0) {
                result = ToolUtil.tableFormat(gdGoods, total);
            }

            return result;
        }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    public int update(int gid,int passFlag,int uid,String unpassReason ){
        Map<String,Object> params = new HashMap();
        params.put("gid",gid);
        params.put("passFlag",passFlag);
        params.put("unpassReason",unpassReason);
        gdGoodsDao.updateStatus(params);
        GdGoods gdGoods = findOne(gid);
        if(passFlag==0){
            String goodsComment = getGoodsComment(gdGoods.getGoodsUrl());
            if(goodsComment.equals("")){
                String i = null;
                if(i.equals(null)){
                }
            }else {
                updateGoodsCommentById(gid, goodsComment);
               /* SysUserSeller sysUserSeller = sellerDao.findOne(uid);
                BigDecimal jinbi = new BigDecimal(gdGoods.getJinbi());
                BigDecimal money_price = gdGoods.getAllMoney();
                BigDecimal danbao = gdGoods.getDanbaoMoney();
                BigDecimal sellerFrozen = jinbi.add(money_price).add(danbao);
                sysUserSeller.setSellerFrozenDeposit(sellerFrozen);
                sellerDao.updateFrozen(sysUserSeller);
                String date = DateFormatUtils.format(new Date(),"yyyy-MM-dd hh:mm:ss");
                FnIORecord fnIORecord = new FnIORecord();
                fnIORecord.setUid(sysUserSeller.getUid());
                fnIORecord.setUserAccount(sysUserSeller.getUserAccount());
                fnIORecord.setAddTime(date);
                fnIORecord.setMoney(sellerFrozen);
                fnIORecord.setComment("发布试货");
                fnIORecord.setMoneyType(2);//出账
                fnIORecord.setFreeMoney(sysUserSeller.getFreeMoney().add(sysUserSeller.getAvailableDeposit()));
                fnIORecordDao.insertSelective(fnIORecord);*/
            }
        }
        return 1;
    }

    public GdGoods findOne(int gid){
        return gdGoodsDao.selectByPrimaryKey(gid);
    }
    public void delete(int gid){
        gdGoodsDao.deleteByPrimaryKey(gid);
    }

    public void updateGoodsById(GdGoods gdGoods){
        gdGoodsDao.updateGoodsById(gdGoods);
        boolean ex = redisUtils.hexists(CacheKeys.GD_GOODS_LIST,String.valueOf(gdGoods.getGoodsId()));
        if(ex){
            redisUtils.delMapKey(CacheKeys.GD_GOODS_LIST,String.valueOf(gdGoods.getGoodsId()));
        }
        redisUtils.hset(CacheKeys.GD_GOODS_LIST,String.valueOf(gdGoods.getGoodsId()),gdGoodsDao.selectByPrimaryKey(gdGoods.getGoodsId()));
    }
    public String getGoodsComment(String url){
        String text ;
        String urlAPI = "http://www.ahatao.com/propic/?nd=1&id=";
        Map<String, String> mapRequest = UrlParamUtils.uRLRequest(url);
        String id = mapRequest.get("id");
        Document document ;
        try {
            document = Jsoup.connect(urlAPI + id).get();
            Elements els = document.getElementsByTag("textarea");
            text = els.text();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return text;
    }
    public void updateGoodsCommentById(int id,String goodsComment){
        GdGoods gdGoods = new GdGoods();
        gdGoods.setGoodsId(id);
        gdGoods.setGoodsComment(goodsComment);
        gdGoodsDao.updateGoodsCommentById(gdGoods);
    }

    /**
     * 查询所有商品翻页查询
     * @param page
     * @param pageSize
     * @return
     */
    public List<GdGoods> getGdgoodsPagable(int page, int pageSize) {
        page = (page - 1) * pageSize;
        return gdGoodsDao.findAllPagable(page, pageSize);
    }

    /**
     * 增加商品抽奖的中奖人数
     * @param goodsId
     * @param increaseNum
     */
    public void addGoodsPrizeNum(long goodsId,int increaseNum) {
        gdGoodsDao.updatePrizeNum(goodsId, increaseNum);
        // 存入缓存
        String goodsJson = redisUtils.hget(CacheKeys.GD_GOODS_LIST, String.valueOf(goodsId));
        GdGoods gdGoods = (GdGoods) JSONObject.toBean(JSONObject.fromObject(goodsJson), GdGoods.class);
        if (gdGoods == null) return;
        gdGoods.setPrizeNum(gdGoods.getPrizeNum() + increaseNum);
        redisUtils.hset(CacheKeys.GD_GOODS_LIST, String.valueOf(gdGoods.getGoodsId()), gdGoods);
    }

    /**
     * 插入每天第一次抽奖商品--已经抽到的数量
     * @param goodsId
     * @param luckNum
     */
    public void firstLotteryGoodsNum(long goodsId, int luckNum) {
        gdGoodsDao.insertGdPrizeNum(goodsId, luckNum);
    }

    /**
     * 查询每天第一次抽奖（10:00）抽了多少份数
     * @param goodsId
     * @return
     */
    public Integer lastLotterNum(long goodsId) {
        return gdGoodsDao.getLastLotteryNum(goodsId);
    }

    /**
     * 清空表gd_prize_num
     */
    public void clearTable(){
        gdGoodsDao.truncatePrizeNumTable();
    }
}
