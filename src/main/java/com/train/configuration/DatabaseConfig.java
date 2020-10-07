package com.train.configuration;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@ComponentScan("com.train")
@PropertySource("classpath:META-INF/application.properties")
@EnableJpaRepositories("com.train.repository")
public class DatabaseConfig {

    @Value("${javax.persistence.jdbc.driver}")
    private  String PROP_DATABASE_DRIVER;
    @Value("${javax.persistence.jdbc.password}")
    private String PROP_DATABASE_PASSWORD;
    @Value("${javax.persistence.jdbc.url}")
    private String PROP_DATABASE_URL;
    @Value("${javax.persistence.jdbc.user}")
    private String PROP_DATABASE_USERNAME;
    @Value("${hibernate.dialect}")
    private String PROP_HIBERNATE_DIALECT;
    @Value("${packages.scan}")
    private String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN;
    @Value("${hibernate.hbm2ddl.auto}")
    private String PROP_HIBERNATE_HBM2DDL_AUTO;


    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(PROP_DATABASE_DRIVER);
        dataSource.setUrl(PROP_DATABASE_URL);
        dataSource.setUsername(PROP_DATABASE_USERNAME);
        dataSource.setPassword(PROP_DATABASE_PASSWORD);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    private Properties getHibernateProperties() {

        Properties properties = new Properties();

        properties.put(PROP_HIBERNATE_DIALECT,PROP_HIBERNATE_DIALECT);
        properties.put(PROP_HIBERNATE_HBM2DDL_AUTO,PROP_HIBERNATE_HBM2DDL_AUTO);

        return properties;
    }
}

