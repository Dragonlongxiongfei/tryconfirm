package com.chenyou.admin.cache;

/**
 * Created by Shell Li on 2018/1/2.
 */
public class CacheKeys {

    /** 任务列表 **/
    public final static String GD_TASK = "gd_task_list";
    /** 试客淘宝试用任务列表(存放的是用户的task id 列表) **/
    public final static String TAOBAO_TASK_USER_LIST = "taobao_task_list:uid:%s";
    /** 试客京东试用任务列表(存放的是用户的task id 列表)  **/
    public final static String JD_TASK_USER_LIST = "JD_task_list:uid:%s";

    /** 商品列表 **/
    public final static String GD_GOODS_LIST = "gd_goods_list";
    /**商家绑定的店铺列表**/
    public final static String SELLER_STORE_LIST = "seller_store_list:uid:%s";
    /**试客可提现的资金**/
    public final static String USER_PULL_TASK = "user_pull_task:uid:%d";
    /** 用户淘宝买号 **/
    public final static String BUY_ACCOUNT_TAOBAO = "taobao_user_buy_account";
    /** 用户京东买号 **/
    public final static String BUY_ACCOUNT_JINGDONG = "jingdong_user_buy_account";

}
