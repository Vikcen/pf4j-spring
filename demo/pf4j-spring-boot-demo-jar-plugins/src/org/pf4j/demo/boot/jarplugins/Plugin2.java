package org.pf4j.demo.boot.jarplugins;

import org.pf4j.Extension;
import org.pf4j.demo.boot.api.Greeting;
import org.pf4j.demo.boot.util.AutowiredCheck;
import org.pf4j.demo.boot.util.ContextWrapper;
import org.springframework.beans.factory.annotation.Autowired;

@Extension(ordinal = 2)
public class Plugin2 implements Greeting {
    @Autowired
    private AutowiredCheck autowiredCheck;

    @Override
    public String getGreeting() {
        if (autowiredCheck != null) {
            return "Plugin2 text - " + autowiredCheck.check();
        } else {
            if (ContextWrapper.getContext() != null) {
                autowiredCheck = ContextWrapper.getContext().getBean(AutowiredCheck.class);
                return "Plugin2 text - " + autowiredCheck.check();
            }
            return "Plugin2 text - autowired failed";
        }
    }
}
