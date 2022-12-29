package dev.mvc.team4_v2sbm3c;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ckfinder.connector.ConnectorServlet;

import dev.mvc.tool.Download;

// ?λΈλ¦Ώ? ?±λ‘ν¨.
@Configuration
public class ServletRegister {
  @Bean
  public ServletRegistrationBean getServletRegistrationBean() {

      // dir: ??Ό?΄ ???₯??΄ ?? ?΄?, filename: ?€?  ?λ²μ ???₯? ??Όλͺ?, downname: ?¬?©??κ²? ???₯?  ??Όλͺ?
      // urlPatterns: /download?dir=/contents/storage&filename=winter_1.jpg&downname=winter.jpg
      // urlPatterns: /download?dir=/attachfile/storage&filename=winter_1.jpg&downname=winter.jpg
      ServletRegistrationBean registrationBean = new ServletRegistrationBean(new Download());
      registrationBean.addUrlMappings("/download"); // ? κ·? μ£Όμ

      return registrationBean;
  }
  
  // CKEditor ?λΈλ¦Ώ ?±λ‘?
  @Bean
  public ServletRegistrationBean<HttpServlet> getConnectorServlet()  {
    
    ServletRegistrationBean<HttpServlet> registrationBean = new ServletRegistrationBean<HttpServlet>(new ConnectorServlet());
    registrationBean.addUrlMappings("/ckfinder/core/connector/java/connector.java"); // ? κ·? μ£Όμ
    Map<String, String> params = new HashMap<String, String>();
    params.put("XMLConfig", "/WEB-INF/ckfinder-config.xml");
    params.put("debug", "false");
    registrationBean.setInitParameters(params);
    
    registrationBean.setLoadOnStartup(1);

    return registrationBean;
  }
  
}


