package org.pf4j.demo.boot.jarplugins;

import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;
import org.pf4j.demo.boot.api.Greeting;
import org.pf4j.demo.boot.util.AutowiredCheck;
import org.springframework.beans.factory.annotation.Autowired;

public class Plugin4WithWrapper extends Plugin {
    public Plugin4WithWrapper(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension(ordinal = 4)
    public static class Plugin4 implements Greeting {
        @Autowired
        private AutowiredCheck autowiredCheck;

        @Override
        public String getGreeting() {
            if (autowiredCheck != null) {
                return "Plugin4 text - " + autowiredCheck.check();
            } else {
                return "Plugin4 text - autowired failed";
            }
        }

    }
}
