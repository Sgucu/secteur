package com.sector.secteur;

import org.springframework.beans.factory.annotation.Qualifier;
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
@EnableJpaRepositories(entityManagerFactoryRef = "posEntityManagerFactory", basePackages = {"com.sector.secteur.repository.pos"})
public class PosDbConfig {

    @Primary
    @Bean(name = "posDataSource")
    @ConfigurationProperties(prefix = "spring.ds-postgresql")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

//    @Bean(name = "oraDataSource")
//    @ConfigurationProperties(prefix = "oracle.datasource")
//    public DataSourceProperties dataSourceProperties(){
//        return new DataSourceProperties();
//    }


    @Primary
    @Bean(name = "posEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean posEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("posDataSource") DataSource posDataSource){
        return builder
                .dataSource(posDataSource)
                .packages("com.sector.secteur.model.pos")
                .persistenceUnit("SecteurP")
                .build();
    }




    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager posTransactionManager(
            @Qualifier("posEntityManagerFactory") EntityManagerFactory posEntityManagerFactory
    ){
        return new JpaTransactionManager(posEntityManagerFactory);
    }


}



