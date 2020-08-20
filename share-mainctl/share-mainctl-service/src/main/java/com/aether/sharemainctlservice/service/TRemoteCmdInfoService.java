package com.aether.sharemainctlservice.service;

import com.aether.sharemainctlservice.vo.TRemoteCmdRequestVo;
import com.aether.sharemainctlservice.vo.TRemoteCmdResponseVo;

import java.util.List;

/**
 * @author luojianye
 * @description 远程指令集服务接口
 * @date 2020/8/20 16:31
 */
public interface TRemoteCmdInfoService {

    /**
     * 根据条件 查询 远程指令集
     * @param vo
     * @return
     */
    List<TRemoteCmdResponseVo> findRemoteCmdsByCondition(TRemoteCmdRequestVo vo);

}
