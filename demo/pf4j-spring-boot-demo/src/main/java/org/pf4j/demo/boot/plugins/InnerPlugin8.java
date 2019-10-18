package org.pf4j.demo.boot.plugins;

import org.pf4j.Extension;
import org.pf4j.demo.boot.api.Greeting;
import org.pf4j.demo.boot.util.BeanCheck;
import org.springframework.beans.factory.annotation.Autowired;

@Extension(ordinal = 18)
public class InnerPlugin8 implements Greeting {
    @Autowired
    private BeanCheck beanCheck;

    @Override
    public String getGreeting() {
        if (beanCheck != null) {
            return "Inner Plugin8 text - " + beanCheck.check();
        } else {
            return "Inner Plugin8 text - autowired failed";
        }
    }
}
