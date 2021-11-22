package com.sector.secteur;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
public class OraDbConfig {


    @Bean(name = "oraDataSource")
    @ConfigurationProperties(prefix = "spring.ds-oracle")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }


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


    @Bean(name = "oraTransactionManager")
    public PlatformTransactionManager oraTransactionManager(
            @Qualifier("oraEntityManagerFactory") EntityManagerFactory oraEntityManagerFactory
    ){
        return new JpaTransactionManager(oraEntityManagerFactory);
    }



}