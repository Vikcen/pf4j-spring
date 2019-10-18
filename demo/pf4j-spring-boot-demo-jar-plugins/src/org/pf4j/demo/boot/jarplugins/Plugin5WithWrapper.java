package org.pf4j.demo.boot.jarplugins;

import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;
import org.pf4j.demo.boot.api.Greeting;
import org.pf4j.demo.boot.util.AutowiredCheck;
import org.pf4j.demo.boot.util.ContextWrapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Plugin5WithWrapper extends Plugin {

    public Plugin5WithWrapper(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension(ordinal = 5)
    public static class Plugin5 implements Greeting {
        @Autowired
        private AutowiredCheck autowiredCheck;

        @Override
        public String getGreeting() {
            if (autowiredCheck != null) {
                return "Plugin5 text - " + autowiredCheck.check();
            } else {
                if (ContextWrapper.getContext() != null) {
                    autowiredCheck = ContextWrapper.getContext().getBean(AutowiredCheck.class);
                    return "Plugin5 text - " + autowiredCheck.check();
                }
                return "Plugin5 text - autowired failed";
            }
        }
    }
}
