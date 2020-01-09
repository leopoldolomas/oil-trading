package com.leo.oiltrading.domain.service.transaction;

import java.util.Collection;

import com.leo.oiltrading.domain.model.Entity;
import com.leo.oiltrading.domain.model.Transaction;

public interface TransactionService {
	  public void add(Transaction transaction) throws Exception;
	  @SuppressWarnings("rawtypes")
	  public Entity findById(Long id) throws Exception;
	  // TODO findByType? BUY/SELL
	  public Collection<Transaction> getAll() throws Exception;
	  public double calculateVolumeWeightedOilPrice() throws Exception;
}
