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
@MapperScan(basePackages = "com.example.mybatisplus.mapper.dev", sqlSessionTemplateRef = "devSqlSessionTemplate")
public class DataSourceDevConfig {

    @Bean(name = "devSqlSessionTemplate")
    public SqlSessionTemplate devSqlSessionTemplate(@Qualifier("devSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "devSqlSessionFactory")
    public SqlSessionFactory devSqlSessionFactory(@Qualifier("devDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/dev/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "devDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dev")
    public DataSource devDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "devTransactionManager")
    public DataSourceTransactionManager devTransactionManager(@Qualifier("devDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}