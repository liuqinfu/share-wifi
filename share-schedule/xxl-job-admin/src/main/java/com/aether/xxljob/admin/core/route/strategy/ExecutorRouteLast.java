package com.aether.xxljob.admin.core.route.strategy;


import com.aether.xxljob.admin.core.route.ExecutorRouter;
import com.aether.xxljob.core.biz.model.ReturnT;
import com.aether.xxljob.core.biz.model.TriggerParam;

import java.util.List;

/**
 * Created by xuxueli on 17/3/10.
 */
public class ExecutorRouteLast extends ExecutorRouter {

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        return new ReturnT<String>(addressList.get(addressList.size()-1));
    }

}
