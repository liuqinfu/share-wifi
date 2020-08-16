package com.aether.sharemainctlservice.entity;

import com.aether.sharecommon.utils.StringUtil;
import io.swagger.annotations.ApiParam;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 终端设备连接与断开热点的事件记录(TWifiEvent)实体类
 *
 * @author makejava
 * @since 2020-08-16 13:26:19
 */
public class TWifiEvent implements Serializable {
    private static final long serialVersionUID = 559359466029420368L;

    private String id = StringUtil.get32GUID();
    /**
     * 设备唯一标识
     */
    @NotNull(message = "设备唯一标识不能为空")
    @ApiParam(name = "设备唯一标识",required = true)
    private String deviceId;
    /**
     * 热点唯一标识
     */
    @ApiParam(name = "热点唯一标识",required = false)
    private String bssid;
    /**
     * 热点名称
     */
    @NotNull(message = "热点名称不能为空")
    @ApiParam(name = "热点名称",required = true)
    private String ssid;
    /**
     * 事件类型  con:连接  discon:断开
     */
    @NotNull(message = "事件类型不能为空")
    @ApiParam(name = "事件类型",required = true)
    private String event;
    /**
     * 事件发生时间
     */
    @NotNull(message = "事件发生时间不能为空")
    @ApiParam(name = "事件发生时间",required = true)
    private Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}