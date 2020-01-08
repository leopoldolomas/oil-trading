package com.leo.oiltrading.domain.service.oiltype;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.oiltrading.domain.model.Entity;
import com.leo.oiltrading.domain.model.OilType;
import com.leo.oiltrading.domain.repository.OilTypeRepository;
import com.leo.oiltrading.domain.service.BaseService;

@Service("oilTypeService")
public class OilTypeServiceImpl extends BaseService<OilType, String> implements OilTypeService {
	
	private OilTypeRepository<OilType, String> repository;

	@Autowired
	OilTypeServiceImpl(OilTypeRepository<OilType, String> repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public void update(OilType oilType) throws Exception {
		repository.update(oilType);
	}

	@Override
	public void delete(String id) throws Exception {
		repository.remove(id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Entity findById(String id) throws Exception {
		return repository.get(id).get();
	}

	@Override
	public Collection<OilType> findByName(String name) throws Exception {
		return repository.getAll().stream().filter(
				e -> e.getId().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
	}
}
