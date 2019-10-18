package org.pf4j.demo.boot.jarplugins;

import org.pf4j.Extension;
import org.pf4j.demo.boot.api.Greeting;
import org.pf4j.demo.boot.util.BeanCheck;
import org.springframework.beans.factory.annotation.Autowired;

@Extension(ordinal = 8)
public class Plugin8 implements Greeting {
    @Autowired
    private BeanCheck beanCheck;

    @Override
    public String getGreeting() {
        if (beanCheck != null) {
            return "Plugin8 text - " + beanCheck.check();
        } else {
            return "Plugin8 text - autowired failed";
        }
    }
}
