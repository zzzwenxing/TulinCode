package com.tuling.handler;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by smlz on 2019/3/19.
 */
@Component
public class TulingErrorAttribute extends DefaultErrorAttributes {

    public Map<String, Object> getErrorAttributes(WebRequest webRequest,
                                                  boolean includeStackTrace) {
        Map<String, Object> retInfo = super.getErrorAttributes(webRequest,includeStackTrace);
        Map<String,Object> ext = (Map<String, Object>) webRequest.getAttribute("ext",0);
        retInfo.put("company","tuling");
        retInfo.put("ext",ext);
        return retInfo;
    }
}
