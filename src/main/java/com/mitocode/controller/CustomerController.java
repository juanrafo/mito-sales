package com.mitocode.controller;

import com.mitocode.dto.CustomerDTO;
import com.mitocode.model.Customer;
import com.mitocode.service.ICustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    private final ICustomerService service;
    private final ModelMapper mapper;

    public CustomerController(ICustomerService service, @Qualifier("defaultMapper") ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(this::toDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(toDTO(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> save(@Valid @RequestBody CustomerDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(service.save(toEntity(dto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable Integer id, @Valid @RequestBody CustomerDTO dto)
            throws Exception {
        return ResponseEntity.ok(toDTO(service.update(id, toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private CustomerDTO toDTO(Customer customer) {
        return mapper.map(customer, CustomerDTO.class);
    }

    private Customer toEntity(CustomerDTO dto) {
        return mapper.map(dto, Customer.class);
    }
}
