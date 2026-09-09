package com.mitocode.controller;

import com.mitocode.dto.ProviderDTO;
import com.mitocode.model.Provider;
import com.mitocode.service.IProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final IProviderService service;
    private final ModelMapper defaultMapper;

    @GetMapping
    public ResponseEntity<List<ProviderDTO>> findAll(){
        List<ProviderDTO> list = service.findAll().stream().map(this::toDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> findById(@PathVariable Integer id){
        Provider obj = service.findById(id);
        return ResponseEntity.ok(toDTO(obj));
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> save(@Valid @RequestBody ProviderDTO dto){
        Provider obj = service.save(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(obj)); //201 CREATED
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderDTO> update(@PathVariable Integer id, @Valid @RequestBody ProviderDTO dto) throws Exception {
        Provider obj = service.update(id, toEntity(dto));
        return ResponseEntity.ok(toDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build(); //204 NO CONTENT
    }

    private ProviderDTO toDTO(Provider obj){
        return defaultMapper.map(obj, ProviderDTO.class);
    }

    private Provider toEntity(ProviderDTO dto){
        return defaultMapper.map(dto, Provider.class);
    }

}
