package org.pf4j.demo.boot.jarplugins;

import org.pf4j.Extension;
import org.pf4j.PluginWrapper;
import org.pf4j.demo.boot.api.Greeting;
import org.pf4j.demo.boot.util.AutowiredCheck;
import org.pf4j.demo.boot.util.ContextWrapper;
import org.pf4j.spring.SpringPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class Plugin6WithSpringPlugin extends SpringPlugin {

    public Plugin6WithSpringPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        System.out.println("Plugin6WithSpringPlugin.start()");
    }

    @Override
    public void stop() {
        System.out.println("Plugin6WithSpringPlugin.stop()");
        super.stop(); // to close applicationContext
    }

    @Override
    protected ApplicationContext createApplicationContext() {
        System.out.println("Plugin6WithSpringPlugin createApplicationContext");
        return ContextWrapper.getContext();
    }

    @Extension(ordinal = 6)
    public static class Plugin6 implements Greeting {
        @Autowired
        private AutowiredCheck autowiredCheck;

        @Override
        public String getGreeting() {
            if (autowiredCheck != null) {
                return "Plugin6 text - " + autowiredCheck.check();
            } else {
                return "Plugin6 text - autowired failed";
            }
        }
    }
}
