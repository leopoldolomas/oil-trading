package com.leo.oiltrading.domain.repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.leo.oiltrading.domain.model.Transaction;

@Repository("transactionRepository")
public class InMemTransactionRepository implements TransactionRepository<Transaction, Long> {
	
	private final Map<Long, Transaction> entities = new ConcurrentHashMap<>();

	@Override
	public boolean contains(Long id) {
		return entities.containsKey(id);
	}

	@Override
	public Optional<Transaction> get(Long id) {
		return entities.containsKey(id) ? Optional.of(entities.get(id)) : Optional.empty();
	}

	@Override
	public Collection<Transaction> getAll() {
		return entities.values();
	}

	@Override
	public void add(Transaction entity) {
		entities.putIfAbsent(entity.getId(), entity);
	}

	@Override
	public void remove(Long id) {
		throw new UnsupportedOperationException("remove operation not allowed in this repository type");
	}

	@Override
	public void update(Transaction entity) {
		throw new UnsupportedOperationException("update operation not allowed in this repository type");
	}
}
