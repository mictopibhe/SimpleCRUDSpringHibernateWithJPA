package pl.davidduke.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement
public class HibernateConfig {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(new String[]{"pl.davidduke.library.models"});
        sessionFactoryBean.setHibernateProperties(hibernateProperties());

        return sessionFactoryBean;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getRequiredProperty("database.jdbc.driverName"));
        dataSource.setUrl(environment.getRequiredProperty("database.jdbc.connectionUrl"));
        dataSource.setUsername(environment.getRequiredProperty("database.jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("database.jdbc.password"));

        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.showSql"));

//        properties.put("hibernate.c3p0.min_size", environment.getRequiredProperty("hibernate.c3p0.minimumSize"));
//        properties.put("hibernate.c3p0.max_size", environment.getRequiredProperty("hibernate.c3p0.maximumSize"));
//        properties.put("hibernate.c3p0.timeout", environment.getRequiredProperty("hibernate.c3p0.timeOut"));
//        properties.put("hibernate.c3p0.max_statements", environment.getRequiredProperty("hibernate.c3p0.maxStatements"));

        return properties;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();

        transactionManager.setSessionFactory(sessionFactoryBean().getObject());
        return transactionManager;
    }
}
