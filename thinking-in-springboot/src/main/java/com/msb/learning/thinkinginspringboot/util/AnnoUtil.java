package com.msb.learning.thinkinginspringboot.util;

import com.msb.learning.thinkinginspringboot.annotation.Sensitive;
import com.msb.learning.thinkinginspringboot.bean.Policy;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: H.K
 * @create: 2020-12-02 16:07
 */
@Component
public class AnnoUtil {
    @Sensitive
    public String testEncrypt(Policy request) {
        System.out.println("testEncrypt 业务逻辑入参 request:{}"+ request.toString());
        return null;
    }
}
