package com.leo.oiltrading.domain.repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.leo.oiltrading.domain.model.OilType;
import com.leo.oiltrading.domain.model.OilType.Type;

@Repository("oilTypeRepository")
public class InMemOilTypeRepository implements OilTypeRepository<OilType, String> {
	
	private final static Map<String, OilType> entities;
	
	static {
		entities = new ConcurrentHashMap<>();
		entities.put("AAC", new OilType("AAC", "AAC", Type.STANDARD, 1,  0, 42));
		entities.put("REW", new OilType("REW", "REW", Type.STANDARD, 7,  0, 47));
		entities.put("BWO", new OilType("BWO", "BWO", Type.STANDARD, 17, 0, 61));
		entities.put("TIM", new OilType("TIM", "TIM", Type.PREMIUM,  5,  7, 111));
		entities.put("QFC", new OilType("QFC", "QFC", Type.STANDARD, 22, 0, 123));
	}

	@Override
	public void add(OilType entity) {
		entities.putIfAbsent(entity.getId(), entity);
	}

	@Override
	public void remove(String id) {
		if (entities.containsKey(id)) {
			entities.remove(id);
		}
	}

	@Override
	public void update(OilType entity) {
		if (entities.containsKey(entity.getId())) {
			entities.put(entity.getId(), entity);
		}
	}

	@Override
	public boolean contains(String id) {
		return entities.containsKey(id);
	}

	@Override
	public Optional<OilType> get(String name) {
		return entities.containsKey(name) ? Optional.of(entities.get(name)) : Optional.empty();
	}

	@Override
	public Collection<OilType> getAll() {
		return entities.values();
	}
}
