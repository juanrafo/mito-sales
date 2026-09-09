package com.mitocode.controller;

import com.mitocode.dto.CategoryDTO;
import com.mitocode.dto.CategoryRecord;
import com.mitocode.model.Category;
import com.mitocode.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    //@Autowired
    private final ICategoryService service;
    private final ModelMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        //List<CategoryDTO> list = service.findAll().stream().map(e -> new CategoryDTO(e.getIdCategory(), e.getName(), e.getDescription(), e.isEnabled())).toList();
        //List<CategoryRecord> list = service.findAll().stream().map(e -> new CategoryRecord(e.getIdCategory(), e.getName(), e.getDescription(), e.isEnabled())).toList();
        //List<CategoryDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, CategoryDTO.class)).toList();
        List<CategoryDTO> list = service.findAll().stream().map(this::toDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Integer id){
        Category obj = service.findById(id);
        return ResponseEntity.ok(toDTO(obj));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@Valid @RequestBody CategoryDTO dto){
        Category obj = service.save(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(obj)); //201 CREATED
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Integer id, @Valid @RequestBody CategoryDTO dto) throws Exception {
        //dto.setIdCategory(id);
        Category obj = service.update(id, toEntity(dto));
        return ResponseEntity.ok(toDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build(); //204 NO CONTENT
    }

    //queries
    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<CategoryDTO>> findByName(@PathVariable String name){
        List<CategoryDTO> list = service.findByName(name).stream().map(this::toDTO).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/find/name/and/{name}/{enabled}")
    public ResponseEntity<List<CategoryDTO>> findByNameAndEnabled(@PathVariable String name, @PathVariable boolean enabled) {
        List<CategoryDTO> list = service.findByNameAndEnabled(name, enabled).stream().map(this::toDTO).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/find/name/desc1")
    public ResponseEntity<List<CategoryDTO>> getNameDescription1(@RequestParam String name, @RequestParam String desc) {
        List<CategoryDTO> list = service.getNameDescription1(name, desc).stream().map(this::toDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/find/name/desc2")
    public ResponseEntity<List<CategoryDTO>> getNameDescription2(@RequestParam String name, @RequestParam String desc) {
        List<CategoryDTO> list = service.getNameDescription2(name, desc).stream().map(this::toDTO).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/find/name/sql")
    public ResponseEntity<List<CategoryDTO>> getNameSQL(@RequestParam String name) {
        List<CategoryDTO> list = service.getNameSQL(name).stream().map(this::toDTO).toList();
        return ResponseEntity.ok(list);
    }

    private CategoryDTO toDTO(Category obj){
        return categoryMapper.map(obj, CategoryDTO.class);
    }

    private Category toEntity(CategoryDTO dto){
        return categoryMapper.map(dto, Category.class);
    }

    /*public CategoryController(ICategoryService service) {
        this.service = service;
    }*/

    /*@GetMapping
    public Category getCategory(){
        //service = new CategoryService();
        return service.validAndGet(0);
    }*/

}
