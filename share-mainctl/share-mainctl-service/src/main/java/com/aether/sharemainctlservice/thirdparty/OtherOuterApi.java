package com.aether.sharemainctlservice.thirdparty;

import com.dtflys.forest.annotation.DataParam;
import com.dtflys.forest.annotation.DataVariable;
import com.dtflys.forest.annotation.Request;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

/** 外部第三方api
 * @author 我走路带风
 * @since 2020/8/20 13:19
 */
public interface OtherOuterApi {

    @Request(url = "${url}",type = "POST",retryCount = 3)
    Map getGreaterWIFI(
                       @DataVariable("url")String url,
                       @DataParam("latitude") double latitude,
                       @DataParam("longitude") double longitude);
}
