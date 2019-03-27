package com.flashies.demo.config.db;

import com.flashies.demo.error.SetupTechnicalException;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(annotationClass = Mapper.class, basePackages = {"com.flashies.demo"})
public class MybatisConfig implements ResourceLoaderAware {

   @Setter
   private ResourceLoader resourceLoader;

   @Autowired
   private DataSource dataSource;

   @Primary
   @Bean
   public DataSourceTransactionManager transactionManager() {
      return new DataSourceTransactionManager(dataSource);
   }

   @Bean(name = "sqlSessionFactory")
   public SqlSessionFactoryBean sqlSessionFactory() {
      Resource[] mapperLocations;

      try {
         ResourcePatternResolver resourceResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
         mapperLocations = resourceResolver.getResources("classpath*:com/flashies/demo/**/*Mapper.xml");
      } catch (IOException e) {
         throw new SetupTechnicalException("Error loading mybatis mappers");
      }

      SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
      factoryBean.setDataSource(dataSource);
      factoryBean.setConfigLocation(resourceLoader.getResource("classpath:mybatis-config.xml"));
      factoryBean.setMapperLocations(mapperLocations);

      return factoryBean;
   }

}
