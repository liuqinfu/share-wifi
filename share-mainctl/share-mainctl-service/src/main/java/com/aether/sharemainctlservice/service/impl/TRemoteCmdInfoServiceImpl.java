package com.aether.sharemainctlservice.service.impl;

import com.aether.sharemainctlservice.dao.TRemoteCmdInfoDao;
import com.aether.sharemainctlservice.entity.TRemoteCmdInfo;
import com.aether.sharemainctlservice.service.TRemoteCmdInfoService;
import com.aether.sharemainctlservice.vo.TRemoteCmdRequestVo;
import com.aether.sharemainctlservice.vo.TRemoteCmdResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author luojianye
 * @description 远程指令集服务接口的实现类
 * @date 2020/8/20 16:39
 */
@Slf4j
@Service
public class TRemoteCmdInfoServiceImpl implements TRemoteCmdInfoService {

    @Autowired
    private TRemoteCmdInfoDao tRemoteCmdInfoDao;

    @Override
    public List<TRemoteCmdResponseVo> findRemoteCmdsByCondition(TRemoteCmdRequestVo vo) {
        log.info(" TRemoteCmdInfoServiceImpl findRemoteCmdsByCondition TRemoteCmdRequestVo vo={}", vo);
        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("deviceBand", vo.getDeviceBand());
        conditionMap.put("sysVersion", vo.getSysVersion());
        conditionMap.put("uiVersion", vo.getUiVersion());
        conditionMap.put("offset", vo.getOffset());
        conditionMap.put("limit", vo.getLimit());
        log.info(" TRemoteCmdInfoServiceImpl findRemoteCmdsByCondition Map<String, Object> conditionMap={}", conditionMap);
        List<TRemoteCmdResponseVo> resultList = new ArrayList<>();
        List<TRemoteCmdInfo> remoteCmdList = tRemoteCmdInfoDao.findRemoteCmdsByCondition(conditionMap);
        if(!CollectionUtils.isEmpty(remoteCmdList)){
            remoteCmdList.stream().forEach(item -> {
                TRemoteCmdResponseVo respVo = new TRemoteCmdResponseVo();
                respVo.setDeviceBand(item.getDeviceBand());
                respVo.setSysVersion(item.getSysVersion());
                respVo.setUiVersion(item.getUiVersion());
                respVo.setRemoteCmds(item.getRemoteCmds());
                resultList.add(respVo);
            });
        }
        return resultList;
    }

}
