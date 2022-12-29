package dev.mvc.team4_v2sbm3c;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ckfinder.connector.ConnectorServlet;

import dev.mvc.tool.Download;

// ?„œë¸”ë¦¿?„ ?“±ë¡í•¨.
@Configuration
public class ServletRegister {
  @Bean
  public ServletRegistrationBean getServletRegistrationBean() {

      // dir: ?ŒŒ?¼?´ ???¥?˜?–´ ?ˆ?Š” ?´?”, filename: ?‹¤? œ ?„œë²„ì— ???¥?œ ?ŒŒ?¼ëª?, downname: ?‚¬?š©??—ê²? ???¥?  ?ŒŒ?¼ëª?
      // urlPatterns: /download?dir=/contents/storage&filename=winter_1.jpg&downname=winter.jpg
      // urlPatterns: /download?dir=/attachfile/storage&filename=winter_1.jpg&downname=winter.jpg
      ServletRegistrationBean registrationBean = new ServletRegistrationBean(new Download());
      registrationBean.addUrlMappings("/download"); // ? ‘ê·? ì£¼ì†Œ

      return registrationBean;
  }
  
  // CKEditor ?„œë¸”ë¦¿ ?“±ë¡?
  @Bean
  public ServletRegistrationBean<HttpServlet> getConnectorServlet()  {
    
    ServletRegistrationBean<HttpServlet> registrationBean = new ServletRegistrationBean<HttpServlet>(new ConnectorServlet());
    registrationBean.addUrlMappings("/ckfinder/core/connector/java/connector.java"); // ? ‘ê·? ì£¼ì†Œ
    Map<String, String> params = new HashMap<String, String>();
    params.put("XMLConfig", "/WEB-INF/ckfinder-config.xml");
    params.put("debug", "false");
    registrationBean.setInitParameters(params);
    
    registrationBean.setLoadOnStartup(1);

    return registrationBean;
  }
  
}


