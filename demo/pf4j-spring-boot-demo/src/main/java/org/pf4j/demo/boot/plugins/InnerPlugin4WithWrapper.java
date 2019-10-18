package org.pf4j.demo.boot.plugins;

import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;
import org.pf4j.demo.boot.api.Greeting;
import org.pf4j.demo.boot.util.AutowiredCheck;
import org.springframework.beans.factory.annotation.Autowired;

public class InnerPlugin4WithWrapper extends Plugin {
    public InnerPlugin4WithWrapper(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension(ordinal = 14)
    public static class Plugin4 implements Greeting {
        @Autowired
        private AutowiredCheck autowiredCheck;

        @Override
        public String getGreeting() {
            if (autowiredCheck != null) {
                return "Inner Plugin4 text - " + autowiredCheck.check();
            } else {
                return "Inner Plugin4 text - autowired failed";
            }
        }

    }
}
