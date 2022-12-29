package dev.mvc.team4_v2sbm3c;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")  // �꽕�젙 �뙆�씪 �쐞移�
@MapperScan(basePackages= {"dev.mvc.fcate",
                                          "dev.mvc.frcontents",
                                          "dev.mvc.member",
                                          "dev.mvc.admin",
                                          "dev.mvc.survey",
                                          "dev.mvc.surveyitem"}) // DAO interface?���? 李얜?�� �쐞移�
public class DatabaseConfiguration {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.hikari")  // �꽕�젙 �뙆�씪�쓽 �젒�몢�궗 �꽑�뼵 spring.datasource.hikari....
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }
    
    @Bean
    public DataSource dataSource() throws Exception{
        DataSource dataSource = new HikariDataSource(hikariConfig());
        System.out.println(dataSource.toString());  // �젙�긽�쟻�쑝濡� �뿰寃� �릺��?�뒗吏� �빐�떆?��붾뱶濡� �솗�씤
        return dataSource;
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // "/src/main/resources/mybatis" �뤃�뜑�쓽 �뙆�씪紐낆?�� "xml"濡� �걹�굹�뒗 �뙆�씪 留ㅽ�?
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/**/*.xml"));
        
        return sqlSessionFactoryBean.getObject();
    }
    
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}


