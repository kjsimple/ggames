package com.gydoc.site;

import freemarker.template.Configuration;
import javax.servlet.ServletContext;

/**
 *
 */
public class FMManager {

    private static Configuration cfg;

    private FMManager() {
        
    }

    public static void init(ServletContext sc, String path) {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(sc, path);
    }

    public static Configuration getCfg() {
        return cfg;
    }

}
