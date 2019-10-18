package org.pf4j.demo.boot;

public class BasePlugin  {

    public BasePlugin() {
        OKMSpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
}
