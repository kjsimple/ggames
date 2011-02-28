<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.gydoc.site.FMManager" %>
<%@page import="freemarker.template.Configuration" %>
<%@page import="freemarker.template.Template" %>
<%@page import="freemarker.template.TemplateException" %>
<%@page import="javax.servlet.ServletException" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.HashMap" %>

<%
    Configuration cfg = FMManager.getCfg();
    Template t = cfg.getTemplate("siteMain.ftl");
    Map root = new HashMap();
    root.put("_title", "网站模板");
    try {
        t.process(root, out);
    } catch (TemplateException e) {
        throw new ServletException("Freemarker error in site.jsp", e);
    }
%>
