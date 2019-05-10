package com.bank.error.dao.impl;

import com.bank.error.core.annotation.RealTransaction;
import com.bank.error.core.annotation.ServiceBean;
import com.bank.error.dao.AbstractDao;
import com.bank.error.dao.AccountDao;
import com.bank.error.entities.ErrorEntity;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ServiceBean("accountDao")
public class AccountDaoImpl extends AbstractDao implements AccountDao {

    @Override
    @RealTransaction
    public List<ErrorEntity> getErrorEntity() {
        Session session = getSessionFactory();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ErrorEntity> query = builder.createQuery(ErrorEntity.class);
        Root<ErrorEntity> root = query.from(ErrorEntity.class);
        query.select(root).where(builder.equal(root.get("success"), false));
        return session.createQuery(query).getResultList();
    }
}
