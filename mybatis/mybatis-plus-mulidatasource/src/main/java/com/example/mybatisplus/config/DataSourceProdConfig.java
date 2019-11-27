package com.example.mybatisplus.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.mybatisplus.mapper.prod", sqlSessionTemplateRef = "prodSqlSessionTemplate")
public class DataSourceProdConfig {

    @Bean(name = "prodSqlSessionTemplate")
    public SqlSessionTemplate prodSqlSessionTemplate(@Qualifier("prodSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "prodSqlSessionFactory")
    public SqlSessionFactory prodSqlSessionFactory(@Qualifier("prodDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/prod/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "prodDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.prod")
    public DataSource prodDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "prodTransactionManager")
    public DataSourceTransactionManager prodTransactionManager(@Qualifier("prodDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
