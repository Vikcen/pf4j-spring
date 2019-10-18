package org.pf4j.demo.boot.jarplugins;

import org.pf4j.Extension;
import org.pf4j.demo.boot.api.Greeting;

@Extension(ordinal = 9)
public class Plugin9 implements Greeting {
    
    @Override
    public String getGreeting() {
    	return "Plugin9 text - OK";
    }
}
