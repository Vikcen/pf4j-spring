package org.pf4j.demo.boot.plugins;

import org.pf4j.Extension;
import org.pf4j.demo.boot.api.Greeting;
import org.pf4j.demo.boot.util.AutowiredCheck;
import org.springframework.beans.factory.annotation.Autowired;

@Extension(ordinal = 11)
public class InnerPlugin1 implements Greeting {
    @Autowired
    private AutowiredCheck autowiredCheck;

    @Override
    public String getGreeting() {
        if (autowiredCheck != null) {
            return "Inner Plugin1 text - " + autowiredCheck.check();
        } else {
            return "Inner Plugin1 text - autowired failed";
        }
    }
}
