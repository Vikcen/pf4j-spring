package org.pf4j.demo.boot.util;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

public class ContextWrapper {
    private static ApplicationContext context;

    public static void setContext(ServletContext sc) {
        // Ensure only it is initialized one time
        if (context == null) {
            context = WebApplicationContextUtils.getWebApplicationContext(sc);
        }
    }

    public static void setContext(ApplicationContext ac) {
        // Ensure only it is initialized one time
        if (context == null) {
            context = ac;
        }
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
