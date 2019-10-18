package org.pf4j.demo.boot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pf4j.demo.boot.util.ContextWrapper;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

/**
 * The class has inspired on {@link org.springframework.web.context.support.SpringBeanAutowiringSupport}
 * The main modification is to use {@link ContextWrapper} to get the application context in the method
 * {@link #processInjectionBasedOnCurrentContext(Object) processInjectionBasedOnCurrentContext}.
 */
public abstract class OKMSpringBeanAutowiringSupport {
    private static final Log logger = LogFactory.getLog(OKMSpringBeanAutowiringSupport.class);

    /**
     * This constructor performs injection on this instance,
     * based on the current web application context.
     * <p>
     * Intended for use as a base class.
     *
     * @see #processInjectionBasedOnCurrentContext
     */
    public OKMSpringBeanAutowiringSupport() {
        processInjectionBasedOnCurrentContext(this);
    }

    /**
     * Process {@code @Autowired} injection for the given target object,
     * based on the current web application context.
     * <p>
     * Intended for use as a delegate.
     *
     * @param target the target object to process
     * @see org.springframework.web.context.ContextLoader#getCurrentWebApplicationContext()
     */
    public static void processInjectionBasedOnCurrentContext(Object target) {
        Assert.notNull(target, "Target object must not be null");
        AutowireCapableBeanFactory acbf = null;
        WebApplicationContext cc = ContextLoader.getCurrentWebApplicationContext();

        if (cc != null) {
            acbf = cc.getAutowireCapableBeanFactory();
        } else {
            // To solve start up issue when the bean PluginContextWrapper still has not been initialized
            if (ContextWrapper.getContext() != null) {
                acbf = ContextWrapper.getContext().getAutowireCapableBeanFactory();
            } else {
                logger.error("@Autowired tags into " + ClassUtils.getShortName(target.getClass())
                    + " can not be initized in the application startup by this mechanism");
            }
        }

        if (acbf != null) {
            AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
            bpp.setBeanFactory(acbf);
            bpp.processInjection(target);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Current WebApplicationContext is not available for processing of "
                    + ClassUtils.getShortName(target.getClass()) + ": "
                    + "Make sure this class gets constructed in a Spring web application. Proceeding without injection.");
            }
        }
    }

    /**
     * Process {@code @Autowired} injection for the given target object,
     * based on the current root web application context as stored in the ServletContext.
     * <p>
     * Intended for use as a delegate.
     *
     * @param target the target object to process
     * @param servletContext the ServletContext to find the Spring web application context in
     * @see WebApplicationContextUtils#getWebApplicationContext(javax.servlet.ServletContext)
     */
    public static void processInjectionBasedOnServletContext(Object target, ServletContext servletContext) {
        Assert.notNull(target, "Target object must not be null");
        WebApplicationContext cc = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        bpp.setBeanFactory(cc.getAutowireCapableBeanFactory());
        bpp.processInjection(target);
    }
}
