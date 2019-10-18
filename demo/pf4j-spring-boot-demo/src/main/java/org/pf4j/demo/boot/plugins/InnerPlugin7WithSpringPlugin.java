package org.pf4j.demo.boot.plugins;

import org.pf4j.Extension;
import org.pf4j.PluginWrapper;
import org.pf4j.demo.boot.api.Greeting;
import org.pf4j.demo.boot.util.AutowiredCheck;
import org.pf4j.demo.boot.util.ContextWrapper;
import org.pf4j.spring.SpringPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class InnerPlugin7WithSpringPlugin extends SpringPlugin {

    public InnerPlugin7WithSpringPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        System.out.println("InnerPlugin7WithSpringPlugin.start()");
    }

    @Override
    public void stop() {
        System.out.println("InnerPlugin7WithSpringPlugin.stop()");
        super.stop(); // to close applicationContext
    }

    @Override
    protected ApplicationContext createApplicationContext() {
        System.out.println("InnerPlugin7WithSpringPlugin createApplicationContext");
        return ContextWrapper.getContext();
    }

    @Extension(ordinal = 17)
    public static class Plugin7 implements Greeting {
        @Autowired
        private AutowiredCheck autowiredCheck;

        @Override
        public String getGreeting() {
            if (autowiredCheck != null) {
                return "Inner Plugin7 text - " + autowiredCheck.check();
            } else {
                if (ContextWrapper.getContext() != null) {
                    autowiredCheck = ContextWrapper.getContext().getBean(AutowiredCheck.class);
                    return "Inner Plugin7 text - " + autowiredCheck.check();
                }
                return "Inner Plugin7 text - autowired failed";
            }
        }

    }
}
