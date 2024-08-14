package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    public Page<CityDTO> findAll(Pageable pageable) {
        Pageable sortedByName = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(), Sort.by("name"));
        return repository.findAll(sortedByName).map(CityDTO::new);
    }


}
