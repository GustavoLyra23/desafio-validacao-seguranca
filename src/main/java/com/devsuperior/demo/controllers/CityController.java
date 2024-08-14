package com.devsuperior.demo.controllers;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.dto.CityRequestDto;
import com.devsuperior.demo.services.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;


    @GetMapping
    public ResponseEntity<Page<CityDTO>> findAll(Pageable pageable) {
        var cityPage = cityService.findAll(pageable);
        return ResponseEntity.ok(cityPage);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CityDTO> insert(@Valid @RequestBody CityRequestDto cityRequestDto) {
        var cityDto = cityService.insert(cityRequestDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cityDto.getId()).toUri();
        return ResponseEntity.created(uri).body(cityDto);
    }


}
