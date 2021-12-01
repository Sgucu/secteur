package com.sector.secteur.repository.ora;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Qualifier("OraFileDBRepositoryImpl")
public class OraFileDBRepositoryImpl {


    @PersistenceContext
    private EntityManager entityManager;


    public OraFileDBRepositoryImpl(){

    }

    public OraFileDBRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void executeQuery(String query){


        Session currentSession = entityManager.unwrap(Session.class);


        Transaction txn = currentSession.beginTransaction();
        /*
        * if we want to use DDL operation and don't use transactions we will get :
        * TransactionRequiredException error
        * This error typically occurs when we're trying to perform a database operation that modifies the database without a transaction.
        * */


        Query sQuery = currentSession.createNativeQuery(query); // JPA uses JPQL, so if we want to use completely standard query we have to use createNativeQuery instead of createQuery
        // Query sQuery = currentSession.createQuery(query);
        sQuery.executeUpdate();

        txn.commit();

        System.out.println("test");


    }



}
