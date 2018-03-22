package com.chenyou.admin.dao;

import com.chenyou.admin.domain.GdGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GdGoodsDao {

    int getUncheckTotal(Map<String, Object> params);

    int getcheckTotal(Map<String, Object> params);

    GdGoods selectByPrimaryKey(long gid);

    void deleteByPrimaryKey(long gid);

    void updateGoodsById(GdGoods gdGoods);

    List<GdGoods> selectByStatus(Map<String, Object> params);

    List<GdGoods> selectCheck(Map<String, Object> params);

    int getAllTotal(Map<String, Object> params);

    List<GdGoods> selectAll(Map<String, Object> params);

    void updateStatus(Map<String, Object> params);

    void updateGoodsCommentById(GdGoods gdGoods);

    List<GdGoods> findAllPagable(@Param("page") int page, @Param("pageSize") int pageSize);

    void updatePrizeNum(@Param("goodsID") long goodsID, @Param("prizeNum") int prizeNum);
    /** 插入第一次抽奖商品开奖的份数 **/
    void insertGdPrizeNum(@Param("goodsId") long goodsId, @Param("luckNum") int luckNum);
    /** 查询商品第一次抽了多少份 **/
    Integer getLastLotteryNum(long goodsId);
    /** 清空gd_prize_num 表 **/
    void truncatePrizeNumTable();
}