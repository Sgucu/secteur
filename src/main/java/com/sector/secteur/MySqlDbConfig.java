package com.sector.secteur;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "oraEntityManagerFactory",
        transactionManagerRef = "oraTransactionManager",
        basePackages = {"com.sector.secteur.repository.ora"}) // OracleRepository location
public class MySqlDbConfig {
/**
 * https://docs.spring.io/spring-boot/docs/2.1.18.RELEASE/reference/html/howto-data-access.html
 * https://www.youtube.com/watch?v=xlWwMSu5I70
 * **/

    @Bean(name = "oraDataSource")
    @ConfigurationProperties(prefix = "spring.ds-mysql")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }
    /*
    * Spring Boot also provides a utility builder class, called DataSourceBuilder, that can be used to create one of the standard data sources (if it is on the classpath).
    * The builder can detect the one to use based on what’s available on the classpath.
    *
    * It also auto-detects the driver based on the JDBC URL.  - it takes driver from spring.ds-oracle.jdbc-url in application.properties
    *
    * However, there is a catch. Because the actual type of the connection pool is not exposed, no keys are generated in the metadata for your
    * custom DataSource and no completion is available in your IDE (because the DataSource interface exposes no properties). Also, if you happen to have Hikari on the classpath,
    * this basic setup does not work, because Hikari has no url property (but does have a jdbcUrl property). In that case, you must rewrite your configuration as follows:
    *
    * app.datasource.url=jdbc:mysql://localhost/test    -- >   app.datasource.jdbc-url=jdbc:mysql://localhost/test
    * You can fix that by forcing the connection pool to use and return a dedicated implementation rather than DataSource.
    * You cannot change the implementation at runtime, but the list of options will be explicit.
    *
    * The following example shows how create a HikariDataSource with DataSourceBuilder:
    *
    *       return DataSourceBuilder.create().type(HikariDataSource.class).build();
    * */



//    @Bean(name = "oraDataSource")
//    @ConfigurationProperties(prefix = "oracle.datasource")
//    public DataSourceProperties dataSourceProperties(){
//        return new DataSourceProperties();
//    }

    @Bean(name = "oraEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean oraEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("oraDataSource") DataSource dataSource){

        return builder
                .dataSource(dataSource)
                .packages("com.sector.secteur.model.ora")      //Oracle Model location
                .persistenceUnit("SecteurO")        // Oracle Model class
                .build();

    }


    /*
    * Even if the default EntityManagerFactory works fine, you need to define a new one, otherwise the presence of the second bean of that type switches off the default.
    * You can use the EntityManagerBuilder provided by Spring Boot to help you to create one.
    * Alternatively, you can use the LocalContainerEntityManagerFactoryBean directly from Spring ORM, as shown in the following example:
    *
    *
    * */



    @Bean(name = "oraTransactionManager")
    public PlatformTransactionManager oraTransactionManager(
            @Qualifier("oraEntityManagerFactory") EntityManagerFactory oraEntityManagerFactory
    ){
        return new JpaTransactionManager(oraEntityManagerFactory);
    }



}



/*
*
* Spring @Configuration annotation helps in Spring annotation based configuration.
* @Configuration annotation indicates that a class declares one or more @Bean methods and may be processed by the Spring
* container to generate bean definitions and service requests for those beans at runtime.
*
*
* Use @Configuration annotation on top of any class to declare that this class provides one or more
* @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.
*
* */


/*
* @Bean
* A method-level annotation used to declare a spring bean. When configuration execute annotated method, it registers the return value as a bean within a BeanFactory.
* By default, the bean name will be the same as the method name. To customize the bean name, use its ‘name’ or ‘value’ attribute.
*
*
* Spring @Bean annotation tells that a method produces a bean to be managed by the Spring container.
* It is a method-level annotation.
* During Java configuration (@Configuration), the method is executed and its return value is registered as a bean within a BeanFactory.
*
*
* */


/*
* Spring Boot also provides a utility builder class, called DataSourceBuilder, that can be used to create one of the standard data sources (if it is on the classpath).
* The builder can detect the one to use based on what’s available on the classpath. It also auto-detects the driver based on the JDBC URL.
* https://docs.spring.io/spring-boot/docs/2.1.18.RELEASE/reference/html/howto-data-access.html
* */



/*
* Spring Boot tries to guess the location of your @Repository definitions, based on the @EnableAutoConfiguration it finds.
* To get more control, use the @EnableJpaRepositories annotation (from Spring Data JPA).
*/





/*
* When you create a bean for LocalContainerEntityManagerFactoryBean yourself, any customization that was applied during the creation of the auto-configured
* LocalContainerEntityManagerFactoryBean is lost. For example, in case of Hibernate, any properties under the spring.jpa.hibernate prefix will not be automatically
* applied to your LocalContainerEntityManagerFactoryBean.
*
* If you were relying on these properties for configuring things like the naming strategy or the DDL mode, you will need to explicitly configure that when
* creating the LocalContainerEntityManagerFactoryBean bean. On the other hand, properties that get applied to the auto-configured EntityManagerFactoryBuilder,
* which are specified via spring.jpa.properties,
* will automatically be applied, provided you use the auto-configured EntityManagerFactoryBuilder to build the LocalContainerEntityManagerFactoryBean bean.
*
* */

/*
*
* Basically JPA specification defines two types of entity managers. They are :
*
* i) Application-Managed : Application Managed entity manager means "Entity Managers are created and managed by merely the application ( i.e. our code )" .
* ii) Container Managed : Container Managed entity manager means "Entity Managers are created and managed by merely the J2EE container
* ( i.e. our code doesn't directly manages instead entity managers are created and managed by container , and our code gets EM's through some way like using JNDI ).
*
*  Note : Created and Managed (above) means "opening , closing and involving entity manager in transactions"
*
*  LocalContainerEntityManagerFactoryBean - container managed
*  LocalEntityManagerFactoryBean - application managed
*
* A Big Note : For spring based applications, the difference is not much. Spring only plays roles
* ( as container if you configure LocalContainerEntityManagerFactoryBean and as application if you configure LocalEntityManagerFactoryBean)
*
* */




















