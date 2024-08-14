package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.dto.CityRequestDto;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public Page<CityDTO> findAll(Pageable pageable) {
        Pageable sortedByName = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(), Sort.by("name"));
        return repository.findAll(sortedByName).map(CityDTO::new);
    }

    @Transactional
    public CityDTO insert(CityRequestDto cityRequestDto) {
        City city = new City();
        city.setName(cityRequestDto.name());
        city = repository.save(city);
        return new CityDTO(city);
    }
}
