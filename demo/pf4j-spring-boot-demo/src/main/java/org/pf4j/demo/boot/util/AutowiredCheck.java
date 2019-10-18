package org.pf4j.demo.boot.util;

import org.springframework.stereotype.Component;

@Component
public class AutowiredCheck {
    public String check() {
        return "AutowiredCheck OK";
    }
}
