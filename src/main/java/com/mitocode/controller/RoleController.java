package com.mitocode.controller;

import com.mitocode.dto.RoleDTO;
import com.mitocode.model.Role;
import com.mitocode.service.IRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService service;
    private final ModelMapper defaultMapper;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAll(){
        List<RoleDTO> list = service.findAll().stream().map(this::toDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable Integer id){
        Role obj = service.findById(id);
        return ResponseEntity.ok(toDTO(obj));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> save(@Valid @RequestBody RoleDTO dto){
        Role obj = service.save(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(obj)); //201 CREATED
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable Integer id, @Valid @RequestBody RoleDTO dto) throws Exception {
        Role obj = service.update(id, toEntity(dto));
        return ResponseEntity.ok(toDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build(); //204 NO CONTENT
    }

    private RoleDTO toDTO(Role obj){
        return defaultMapper.map(obj, RoleDTO.class);
    }

    private Role toEntity(RoleDTO dto){
        return defaultMapper.map(dto, Role.class);
    }

}
