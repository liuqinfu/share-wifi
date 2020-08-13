package com.aether.sharezuul.filter;

import com.aether.sharecommon.utils.HttpUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.micrometer.core.instrument.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;

@Slf4j
@Configuration
public class PostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        InputStream responseDataStream = ctx.getResponseDataStream();
        String responseData ="";
        try {
            responseData = IOUtils.toString(responseDataStream);
            responseData = new String(responseData.getBytes("utf-8"), Charset.forName("iso8859-1"));
            ctx.setResponseBody(responseData);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("response  {}--------IP: {}---------response: {}", getRequestInfo(request, false), HttpUtil.getIP(request), responseData);
        return null;
    }

    private String getRequestInfo(HttpServletRequest request, boolean requestDetails) {
        StringBuffer sb = new StringBuffer();
        sb.append(request.getMethod()).append(" ");
        sb.append(request.getRequestURI());
        if (requestDetails) {
            Enumeration<String> e = request.getParameterNames();
            sb.append(" ").append("{");
            int i = 0;
            while (e.hasMoreElements()) {
                String name = e.nextElement();
                String val = request.getParameter(name);

                if (val != null && !val.isEmpty()) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(name).append(": ").append(val);

                    i++;
                }
            }
            sb.append("}");
        }

        return sb.toString();
    }
}
