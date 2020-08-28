package com.aether.xxljob.admin.core.alarm;


import com.aether.xxljob.admin.core.model.XxlJobInfo;
import com.aether.xxljob.admin.core.model.XxlJobLog;

/**
 * @author xuxueli 2020-01-19
 */
public interface JobAlarm {

    /**
     * job alarm
     *
     * @param info
     * @param jobLog
     * @return
     */
    public boolean doAlarm(XxlJobInfo info, XxlJobLog jobLog);

}
