package com.chenyou.admin.service;

import com.chenyou.admin.Utils.ToolUtil;
import com.chenyou.admin.dao.BuyerDao;
import com.chenyou.admin.dao.CoupDao;
import com.chenyou.admin.dao.CoupRecordDao;
import com.chenyou.admin.domain.Coup;
import com.chenyou.admin.domain.CoupRecord;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.codehaus.groovy.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Shell Li on 2017/12/16.
 */
@Service
public class CoupService {
    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CoupDao coupDao;
    @Autowired
    private CoupRecordDao coupRecordDao;
    @Autowired
    private BuyerDao buyerDao;

    public String getListService(HttpServletRequest request, int page, int size){
        String result = null;
        HashMap<String, Object> param = new HashMap<>();
        param.put("page", page * size);
        param.put("size", size);
        List<Coup> list = coupDao.findAllByPage(param);
        int total = coupDao.findTotal();
        result = ToolUtil.tableFormat(list, total);
        return result;
    }

    public Coup getOne(long id){
        return coupDao.findOne(id);
    }

    @Transactional
    public Map<String, Object> sendCoupToPerson(long coupID, long uid, String comment) {
        Map<String, Object> result = new HashMap<>();

        Coup coup = getOne(coupID);
        if (coup.getCoupNum() - coup.getCoupUsed() < 1) {
            result.put("msg", "券目前数量不够了，请及时添加券数量");
            return result;
        }
        CoupRecord coupRecord = new CoupRecord();
        coupRecord = (CoupRecord) JSONObject.toBean(JSONObject.fromObject(coup), CoupRecord.class);

        coupRecord.setUid(uid);
        coupRecord.setCoupID(coupID);
        coupRecord.setComment(comment);
        Calendar calendar  = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date coupGetDate = calendar.getTime();
        String coupGetTime = sdf.format(coupGetDate);
        coupRecord.setCoupGetTime(coupGetTime);

        // 券有效时间
        calendar.add(Calendar.HOUR, Long.valueOf(coup.getCoupTimeLimit()).intValue());
        Date coupEndDate = calendar.getTime();
        String coupEndTime = sdf.format(coupEndDate);
        coupRecord.setCoupEndTime(coupEndTime);

        // 默认发放券为可用状态
        coupRecord.setStatus(0);
        coup.setCoupUsed(coup.getCoupUsed() + 1);
        coupDao.updateOne(coup);
        coupRecordDao.insertOne(coupRecord);

        return result;
    }

    public Map<String, Object> sendCoupToGroup(long coupID, String group, String comment) {
        Map<String, Object> result = new HashMap<>();
        Coup coup = coupDao.findOne(coupID);

        Map<String, Object> param = new HashMap<>();
        List<String> uidList = null;
        // 所有人 group == 'all'
        if ("all".equals(group)) {
            uidList = buyerDao.getBuyerUidList(param);
        }
        // 非vip用户
        if ("noVip".equals(group)) {
            param.put("vipLevel", 1);
            uidList = buyerDao.getBuyerUidList(param);
        }
        // 普通vip
        if ("vip".equals(group)) {
            param.put("vipLevel", 2);
            uidList = buyerDao.getBuyerUidList(param);
        }
        // 超级vip
        if ("superVip".equals(group)) {
            param.put("vipLevel", 3);
            uidList = buyerDao.getBuyerUidList(param);
        }
        if (uidList != null && uidList.size() > 0 ){
            if (coup.getCoupNum() - coup.getCoupUsed() < uidList.size()) {
                result.put("msg", "券数量不够");
                return result;
            }
            List<CoupRecord> sendCoups = new ArrayList<>();
            Calendar calendar  = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date coupGetDate = calendar.getTime();
            String coupGetTime = sdf.format(coupGetDate);
            // 券有效时间
            calendar.add(Calendar.HOUR, Long.valueOf(coup.getCoupTimeLimit()).intValue());
            Date coupEndDate = calendar.getTime();
            String coupEndTime = sdf.format(coupEndDate);

            for (String uid : uidList) {
                CoupRecord coupRecord = new CoupRecord();
                coupRecord = (CoupRecord) JSONObject.toBean(JSONObject.fromObject(coup), CoupRecord.class);
                coupRecord.setUid(Long.valueOf(uid));
                coupRecord.setComment(comment);
                coupRecord.setCoupGetTime(coupGetTime);
                coupRecord.setCoupEndTime(coupEndTime);
                coupRecord.setStatus(0);
                sendCoups.add(coupRecord);
            }
            coup.setCoupUsed(coup.getCoupUsed() + uidList.size());
            coupDao.updateOne(coup);
            coupRecordDao.insertList(sendCoups);
        }

        return result;
    }

    // 根据组和用户ID来发放券
    public Map<String, Object> sendCoups(String coupID, String sendGroup, String sendPerson, String comment){
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(sendGroup) && StringUtils.isEmpty(sendPerson)) {
            map.put("msg", "请输入需要发送的用户组或者用户id");
            return map;
        }
        if (!StringUtils.isEmpty(sendGroup) && !StringUtils.isEmpty(sendPerson)) {
            map.put("msg", "发送的用户组和用户id，两者只能选一个");
            return map;
        }
        if (!StringUtils.isEmpty(sendGroup)) {
            return sendCoupToGroup(Long.parseLong(coupID), sendGroup, comment);
        }
        if (!StringUtils.isEmpty(sendPerson)) {
            return sendCoupToPerson(Long.parseLong(coupID), Long.parseLong(sendPerson), comment);
        }

        return map;
    }


    @Transactional
    public void editCoup(Coup coup){
        coupDao.updateOne(coup);
    }

    @Transactional
    public void addCoup(Coup coup){
        coupDao.insertOne(coup);
    }

}
