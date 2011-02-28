package com.gydoc.site;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 */
public class ApplicationInit implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        FMManager.init(servletContextEvent.getServletContext(), "WEB-INF/templates");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        
    }

}
