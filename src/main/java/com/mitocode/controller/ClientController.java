package com.mitocode.controller;

import com.mitocode.dto.ClientDTO;
import com.mitocode.model.Client;
import com.mitocode.service.IClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService service;
    private final ModelMapper defaultMapper;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<ClientDTO> list = service.findAll().stream().map(this::toDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Integer id){
        Client obj = service.findById(id);
        return ResponseEntity.ok(toDTO(obj));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> save(@Valid @RequestBody ClientDTO dto){
        Client obj = service.save(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(obj)); //201 CREATED
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Integer id, @Valid @RequestBody ClientDTO dto) throws Exception {
        Client obj = service.update(id, toEntity(dto));
        return ResponseEntity.ok(toDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build(); //204 NO CONTENT
    }

    private ClientDTO toDTO(Client obj){
        return defaultMapper.map(obj, ClientDTO.class);
    }

    private Client toEntity(ClientDTO dto){
        return defaultMapper.map(dto, Client.class);
    }

}
