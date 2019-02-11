package com.gemepro.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Druid配置
 *
 * @author fwq
 * @email 1576034877@qq.com
 * @date 2017-04-21 0:00
 */
@Configuration
@MapperScan(basePackages = "com.gemepro.api.dao", sqlSessionTemplateRef = "dataskySqlSessionTemplate")
public class DruidConfig {

    private Logger logger = LoggerFactory.getLogger(DruidConfig.class);

    @Value("${spring.datasource.url:#{null}}")
    private String dbUrl;
    @Value("${spring.datasource.username: #{null}}")
    private String username;
    @Value("${spring.datasource.password:#{null}}")
    private String password;
    @Value("${spring.datasource.driverClassName:#{null}}")
    private String driverClassName;
    @Value("${spring.datasource.initialSize:#{null}}")
    private Integer initialSize;
    @Value("${spring.datasource.minIdle:#{null}}")
    private Integer minIdle;
    @Value("${spring.datasource.maxActive:#{null}}")
    private Integer maxActive;
    @Value("${spring.datasource.maxWait:#{null}}")
    private Integer maxWait;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis:#{null}}")
    private Integer timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis:#{null}}")
    private Integer minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery:#{null}}")
    private String validationQuery;
    @Value("${spring.datasource.testWhileIdle:#{null}}")
    private Boolean testWhileIdle;
    @Value("${spring.datasource.testOnBorrow:#{null}}")
    private Boolean testOnBorrow;
    @Value("${spring.datasource.testOnReturn:#{null}}")
    private Boolean testOnReturn;
    @Value("${spring.datasource.poolPreparedStatements:#{null}}")
    private Boolean poolPreparedStatements;
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize:#{null}}")
    private Integer maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.filters:#{null}}")
    private String filters;
    @Value("{spring.datasource.connectionProperties:#{null}}")
    private String connectionProperties;


    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;
    @Value("${mybatis.mapper-locations}")
    private String mapperLocation;
    @Value("${mybatis.configuration.mapUnderscoreToCamelCase}")
    private String mapUnderscoreToCamelCase;
    @Value("${mybatis.configuration.useColumnLabel}")
    private String useColumnLabel;
    @Value("${mybatis.configuration.useGeneratedKeys}")
    private String useGeneratedKeys;

    @Bean(name = "dataskyDatasource")     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource

    public DataSource dataskyDatasource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        //configuration
        if (initialSize != null) {
            datasource.setInitialSize(initialSize);
        }
        if (minIdle != null) {
            datasource.setMinIdle(minIdle);
        }
        if (maxActive != null) {
            datasource.setMaxActive(maxActive);
        }
        if (maxWait != null) {
            datasource.setMaxWait(maxWait);
        }
        if (timeBetweenEvictionRunsMillis != null) {
            datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        }
        if (minEvictableIdleTimeMillis != null) {
            datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        }
        if (validationQuery != null) {
            datasource.setValidationQuery(validationQuery);
        }
        if (testWhileIdle != null) {
            datasource.setTestWhileIdle(testWhileIdle);
        }
        if (testOnBorrow != null) {
            datasource.setTestOnBorrow(testOnBorrow);
        }
        if (testOnReturn != null) {
            datasource.setTestOnReturn(testOnReturn);
        }
        if (poolPreparedStatements != null) {
            datasource.setPoolPreparedStatements(poolPreparedStatements);
        }
        if (maxPoolPreparedStatementPerConnectionSize != null) {
            datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        }

        if (connectionProperties != null) {
            datasource.setConnectionProperties(connectionProperties);
        }

        List<Filter> filters = new ArrayList<>();
        filters.add(statFilter());
        filters.add(wallFilter());
        datasource.setProxyFilters(filters);

        return datasource;
    }


    @Bean(name = "datskyTransactionManagerate")
    @Primary
    public DataSourceTransactionManager rdsTransactionManager(@Qualifier("dataskyDatasource") DataSource dataskyDatasource) throws SQLException {

        return new DataSourceTransactionManager(dataskyDatasource());

    }

    @Bean(name = "dataskySessionFactory")
    public SqlSessionFactory firstSqlSessionFactory(@Qualifier("dataskyDatasource") DataSource dataskyDatasource) throws Exception {

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        sessionFactory.setDataSource(dataskyDatasource);

        sessionFactory.setTypeAliasesPackage(typeAliasesPackage);

        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()

                .getResources(mapperLocation));

        sessionFactory.setConfiguration(configuration());

        return sessionFactory.getObject();

    }


    @Bean(name = "dataskySqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("dataskySessionFactory") SqlSessionFactory firstSqlSessionFactory) throws Exception {

        return new SqlSessionTemplate(firstSqlSessionFactory);

    }


    private org.apache.ibatis.session.Configuration configuration() {

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();

        configuration.setCacheEnabled(true);

        configuration.setJdbcTypeForNull(JdbcType.NULL);

        configuration.setMapUnderscoreToCamelCase(Boolean.parseBoolean(mapUnderscoreToCamelCase));

        configuration.setUseColumnLabel(Boolean.parseBoolean(useColumnLabel));

        configuration.setUseGeneratedKeys(Boolean.parseBoolean(useGeneratedKeys));

        return configuration;

    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        return servletRegistrationBean;
    }

    @Bean
    public StatFilter statFilter() {
        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        statFilter.setSlowSqlMillis(1000);

        return statFilter;
    }

    @Bean
    public WallFilter wallFilter() {
        WallFilter wallFilter = new WallFilter();

        //允许执行多条SQL
        WallConfig config = new WallConfig();
        config.setMultiStatementAllow(true);
        wallFilter.setConfig(config);

        return wallFilter;
    }
}
