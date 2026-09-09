package com.mitocode.controller;

import com.mitocode.dto.ProductDTO;
import com.mitocode.model.Product;
import com.mitocode.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService service;
    private final ModelMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<ProductDTO> list = service.findAll().stream().map(this::toDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id){
        Product obj = service.findById(id);
        return ResponseEntity.ok(toDTO(obj));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductDTO dto){
        Product obj = service.save(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(obj)); //201 CREATED
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Integer id, @Valid @RequestBody ProductDTO dto) throws Exception {
        Product obj = service.update(id, toEntity(dto));
        return ResponseEntity.ok(toDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build(); //204 NO CONTENT
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String name){
        List<ProductDTO> list = service.getProductsByCategory(name).stream().map(this::toDTO).toList();
        return ResponseEntity.ok(list);
    }

    private ProductDTO toDTO(Product obj){
        return productMapper.map(obj, ProductDTO.class);
    }

    private Product toEntity(ProductDTO dto){
        return productMapper.map(dto, Product.class);
    }

}
