package com.qimou.sb.web.config;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.jolbox.bonecp.BoneCPDataSource;


//https://blog.csdn.net/netdevgirl/article/details/51498626

//@EnableAutoConfiguration
//@EnableTransactionManagement//加上这个注解，使得支持事务
@Configuration
@MapperScan("com.qimou.sb.web.mapper")
@PropertySource(value= {"classpath:jdbc.properties"},ignoreResourceNotFound = true)
public class MyBatisConfig  {
   
	
	@Value("${jdbc.url}")
    private String jdbcUrl;
    
    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    
    @Value("${jdbc.username}")
    private String jdbcUsername;
    
    @Value("${jdbc.password}")
    private String jdbcPassword;
    
    //这里配置数据源就不用application.properties中的配置了
    @Bean(destroyMethod = "close")
    public BoneCPDataSource boneCPDataSource(){
        BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
        //数据库驱动
        boneCPDataSource.setDriverClass(driverClassName);
        //相应驱动的jdbcURL
        boneCPDataSource.setJdbcUrl(jdbcUrl);
        //数据库的用户名
        boneCPDataSource.setUsername(jdbcUsername);
        //数据库的密码
        boneCPDataSource.setPassword(jdbcPassword);
        //检查数据库连接池中空闲连接的间隔时间，单位是分，默认值：240，如果要取消设置为0
        boneCPDataSource.setIdleConnectionTestPeriodInMinutes(60);
        //连接池中未使用的链接最大存活时间，单位是分，默认值60，如果要永远存活设置为0【生产环境中次属性设置为0】
        boneCPDataSource.setIdleMaxAgeInMinutes(30);
        //每个分区最大连接数
        boneCPDataSource.setMaxConnectionsPerPartition(100);
        //每个分区最小的连接数吧
        boneCPDataSource.setMinConnectionsPerPartition(5);
        //设置分区个数，默认是1
        boneCPDataSource.setPartitionCount(2);
        return boneCPDataSource;
    }
    
	//创建SqlSessionFactory  
//	@Bean  
//	public SqlSessionFactory sqlSessionFactoryBean() throws Exception{     
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();    
//		sqlSessionFactoryBean.setDataSource(dataSource());     
//		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();     
//		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));     
//		return sqlSessionFactoryBean.getObject();  
//	}   
	
	//创建事物管理器  
//	@Bean  
//	public PlatformTransactionManager transactionManager() {    
//		return new DataSourceTransactionManager(dataSource());  
//	}

	//创建数据源  
//	@Bean  
//	@ConfigurationProperties(prefix = "spring.datasource")//指定数据源的前缀 ,在application.properties文件中指定  
//	public DataSource dataSource() {    
//		return new DataSource();  
//	}
    
    /*@Bean
    @ConditionalOnMissingBean //当容器里没有指定的Bean的情况下创建该对象
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
       
        // 设置别名包
        sqlSessionFactoryBean.setTypeAliasesPackage("com.qimou.sb.web.entity");
        
        return sqlSessionFactoryBean;
    }*/
   
}