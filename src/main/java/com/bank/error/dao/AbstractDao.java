package com.bank.error.dao;

import com.bank.error.core.HibernateConfiguration;
import org.hibernate.Session;

public class AbstractDao {

    protected Session getSessionFactory(){
        return HibernateConfiguration.getSession().getCurrentSession();
    }

}
