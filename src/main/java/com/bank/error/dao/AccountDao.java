package com.bank.error.dao;

import com.bank.error.entities.ErrorEntity;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    List<ErrorEntity> getErrorEntity();

}
