package org.pf4j.demo.boot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ContextHelper {
    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void init() {
        System.out.println("Initializing ContextWrapper");
        ContextWrapper.setContext(context);
    }
}
