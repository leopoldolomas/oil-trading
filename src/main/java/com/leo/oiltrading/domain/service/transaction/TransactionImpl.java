package com.leo.oiltrading.domain.service.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.oiltrading.domain.model.Entity;
import com.leo.oiltrading.domain.model.Transaction;
import com.leo.oiltrading.domain.repository.TransactionRepository;
import com.leo.oiltrading.domain.service.BaseService;

@Service("transactionService")
public class TransactionImpl extends BaseService<Transaction, Long> implements TransactionService {
	
	TransactionRepository<Transaction, Long> _repository;

	@Autowired
	protected TransactionImpl(TransactionRepository<Transaction, Long> repository) {
		super(repository);
		this._repository = repository;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Entity findById(Long id) throws Exception {
		return _repository.get(id).orElse(null);
	}

	@Override
	public double calculateVolumeWeightedOilPrice() throws Exception {
		if (getAll().isEmpty()) {
			return 0.0;
		}
		
		double sumPriceTimesQty = 0;
		double sumQty = 0;
		
		for (var t : getAll()) {
			sumPriceTimesQty += t.getPrice() * t.getQty();
			sumQty += t.getQty();
		}
		
		return sumPriceTimesQty / sumQty;
	}
}
