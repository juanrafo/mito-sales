package com.mitocode.controller;

import com.mitocode.dto.SaleDTO;
import com.mitocode.model.Sale;
import com.mitocode.service.ISaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/sales")
@RequiredArgsConstructor
public class SaleController {

    private final ISaleService service;
    private final ModelMapper saleMapper;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> findAll(){
        List<SaleDTO> list = service.findAll().stream().map(this::toDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> findById(@PathVariable Integer id){
        Sale obj = service.findById(id);
        return ResponseEntity.ok(toDTO(obj));
    }

    @PostMapping
    public ResponseEntity<SaleDTO> save(@Valid @RequestBody SaleDTO dto){
        Sale obj = service.save(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(obj)); //201 CREATED
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> update(@PathVariable Integer id, @Valid @RequestBody SaleDTO dto) throws Exception {
        Sale obj = service.update(id, toEntity(dto));
        return ResponseEntity.ok(toDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build(); //204 NO CONTENT
    }

    private SaleDTO toDTO(Sale obj){
        return saleMapper.map(obj, SaleDTO.class);
    }

    private Sale toEntity(SaleDTO dto){
        return saleMapper.map(dto, Sale.class);
    }

}
