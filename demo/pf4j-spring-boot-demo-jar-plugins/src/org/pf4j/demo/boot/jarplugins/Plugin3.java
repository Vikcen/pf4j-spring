package org.pf4j.demo.boot.jarplugins;

import org.pf4j.Extension;
import org.pf4j.demo.boot.BasePlugin;
import org.pf4j.demo.boot.api.Greeting;
import org.pf4j.demo.boot.util.AutowiredCheck;
import org.springframework.beans.factory.annotation.Autowired;

@Extension(ordinal = 3)
public class Plugin3 extends BasePlugin implements Greeting {
    @Autowired
    private AutowiredCheck autowiredCheck;

    @Override
    public String getGreeting() {
        if (autowiredCheck != null) {
            return "Plugin3 text - " + autowiredCheck.check();
        } else {
            return "Plugin3 text - autowired failed";
        }
    }
}
