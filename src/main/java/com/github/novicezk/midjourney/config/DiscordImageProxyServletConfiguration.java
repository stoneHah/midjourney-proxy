package com.github.novicezk.midjourney.config;

import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

//@Configuration
public class DiscordImageProxyServletConfiguration {
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new ProxyServlet(), "/discord-images/*");
        servletRegistrationBean.addInitParameter(ProxyServlet.P_TARGET_URI, "https://cdn.discordapp.com");
        return servletRegistrationBean;
    }
}
