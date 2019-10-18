package org.pf4j.demo.boot.plugins;

import org.pf4j.Extension;
import org.pf4j.demo.boot.api.Greeting;
import org.pf4j.demo.boot.util.AutowiredCheck;
import org.pf4j.demo.boot.util.ContextWrapper;
import org.springframework.beans.factory.annotation.Autowired;

@Extension(ordinal = 12)
public class InnerPlugin2 implements Greeting {
    @Autowired
    private AutowiredCheck autowiredCheck;

    @Override
    public String getGreeting() {
        if (autowiredCheck != null) {
            return "Inner Plugin2 text - " + autowiredCheck.check();
        } else {
            if (ContextWrapper.getContext() != null) {
                autowiredCheck = ContextWrapper.getContext().getBean(AutowiredCheck.class);
                return "Inner Plugin2 text - " + autowiredCheck.check();
            }
            return "Inner Plugin2 text - autowired failed";
        }
    }
}
