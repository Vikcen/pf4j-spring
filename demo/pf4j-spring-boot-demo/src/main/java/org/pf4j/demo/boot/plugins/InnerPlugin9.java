package org.pf4j.demo.boot.plugins;

import org.pf4j.Extension;
import org.pf4j.demo.boot.api.Greeting;

@Extension(ordinal = 19)
public class InnerPlugin9 implements Greeting {
    
    @Override
    public String getGreeting() {
    	return "Inner Plugin9 text - OK";
    }
}
