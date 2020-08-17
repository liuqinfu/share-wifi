package com.aether.sharemainctlservice.service.impl;

import com.aether.sharecommon.utils.RedisUtil;
import com.aether.sharemainctlservice.service.SDIService;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author 我走路带风
 * @since 2020/8/14 13:29
 */
@Service
@Slf4j
public class SDIServiceImpl implements SDIService {

    @Value("${sdi.redis.reg}")
    private String sdi_reg;

    @Autowired
    private RedisUtil redisUtil;

    private String hostaddr;

    {
        try {
            hostaddr = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void registrySDI(String ip, String port) {
        //将SDI信息写入redis
        SDIInfo sdiInfo = new SDIInfo(ip, port);
//        redisUtil.hset(sdi_reg,hostaddr, JSONObject.toJSONString(sdiInfo));
        redisUtil.sSet(sdi_reg, JSONObject.toJSONString(sdiInfo));
    }

    @Data
    static
    class SDIInfo{
        private String ip;
        private String port;

        public SDIInfo(String ip, String port) {
            this.ip = ip;
            this.port = port;
        }

        @Override
        public String toString() {
            return "SDIInfo{" +
                    "ip='" + ip + '\'' +
                    ", port='" + port + '\'' +
                    '}';
        }
    }
}
