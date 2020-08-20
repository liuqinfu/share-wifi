package com.aether.sharemainctlservice.dao;

import com.aether.sharemainctlservice.entity.TRemoteCmdInfo;

import java.util.List;
import java.util.Map;

/**
 * @author luojianye
 * @description 远程指令集表（t_remotecmd_info）DAO
 * @date 2020/8/20 16:04
 */
public interface TRemoteCmdInfoDao {

    /**
     * 根据条件 查询 远程指令集
     * @param conditionMap
     * @return
     */
    List<TRemoteCmdInfo> findRemoteCmdsByCondition(Map<String, Object> conditionMap);

}
