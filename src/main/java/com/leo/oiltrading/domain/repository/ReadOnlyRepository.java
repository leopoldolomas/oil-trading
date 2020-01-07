package com.leo.oiltrading.domain.repository;

import java.util.Collection;
import java.util.Optional;

public interface ReadOnlyRepository<TE, T> {
	boolean contains(T id);
	Optional<TE> get(T name);
	Collection<TE> getAll();
}
