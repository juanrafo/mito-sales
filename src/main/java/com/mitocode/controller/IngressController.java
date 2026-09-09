package com.mitocode.controller;

import com.mitocode.dto.IngressDTO;
import com.mitocode.model.Ingress;
import com.mitocode.service.IIngressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/ingresses")
@RequiredArgsConstructor
public class IngressController {

    private final IIngressService service;
    private final ModelMapper defaultMapper;

    @GetMapping
    public ResponseEntity<List<IngressDTO>> findAll(){
        List<IngressDTO> list = service.findAll().stream().map(this::toDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngressDTO> findById(@PathVariable Integer id){
        Ingress obj = service.findById(id);
        return ResponseEntity.ok(toDTO(obj));
    }

    @PostMapping
    public ResponseEntity<IngressDTO> save(@Valid @RequestBody IngressDTO dto){
        Ingress obj = service.save(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(obj)); //201 CREATED
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngressDTO> update(@PathVariable Integer id, @Valid @RequestBody IngressDTO dto) throws Exception {
        Ingress obj = service.update(id, toEntity(dto));
        return ResponseEntity.ok(toDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build(); //204 NO CONTENT
    }

    private IngressDTO toDTO(Ingress obj){
        return defaultMapper.map(obj, IngressDTO.class);
    }

    private Ingress toEntity(IngressDTO dto){
        return defaultMapper.map(dto, Ingress.class);
    }

}
