package example.spring.boot.mybatis.plus.xml.mulidatasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "example.spring.boot.mybatis.plus.xml.mulidatasource.mapper.mysql", sqlSessionTemplateRef  = "mySqlSessionTemplate")
public class DataSourceMySqlConfig {

    @Bean(name = "myDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource myDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置文件中指明的xml位置会失效用此方式代替，具体原因未知
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "mySqlSessionFactory")
    public SqlSessionFactory mySqlSessionFactory(@Qualifier("myDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/mysql/*.xml"));
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "myTransactionManager")
    public DataSourceTransactionManager myTransactionManager(@Qualifier("myDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mySqlSessionTemplate")
    public SqlSessionTemplate mySqlSessionTemplate(@Qualifier("mySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
