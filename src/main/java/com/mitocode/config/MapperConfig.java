package com.mitocode.config;

import com.mitocode.dto.CategoryDTO;
import com.mitocode.model.Category;
import com.mitocode.model.Sale;
import com.mitocode.dto.SaleDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.Converter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper defaultMapper() {
        return new ModelMapper();
    }

    @Bean
    public ModelMapper categoryMapper() {
        ModelMapper mapper = new ModelMapper();

        //LECTURA
        mapper.createTypeMap(Category.class, CategoryDTO.class)
                .addMapping(Category::getName, CategoryDTO::setNameofCategory);

        //ESCRITURA
        mapper.createTypeMap(CategoryDTO.class, Category.class)
                .addMapping(CategoryDTO::getNameofCategory, Category::setName);

        return mapper;
    }

    @Bean
    public ModelMapper productMapper() {
        ModelMapper mapper = new ModelMapper();

        //Product's own fields (category, name, price, description, enabled) match ProductDTO 1:1,
        //but the nested Category <-> CategoryDTO pair needs the same explicit mapping as categoryMapper,
        //since this is a separate ModelMapper instance.
        mapper.createTypeMap(Category.class, CategoryDTO.class)
                .addMapping(Category::getName, CategoryDTO::setNameofCategory);

        mapper.createTypeMap(CategoryDTO.class, Category.class)
                .addMapping(CategoryDTO::getNameofCategory, Category::setName);

        return mapper;
    }

    @Bean
    public ModelMapper saleMapper() {
        ModelMapper mapper = new ModelMapper();

        //Needed for the nested chain Sale.details[].product.category <-> SaleDetailDTO.product.category
        mapper.createTypeMap(Category.class, CategoryDTO.class)
                .addMapping(Category::getName, CategoryDTO::setNameofCategory);

        mapper.createTypeMap(CategoryDTO.class, Category.class)
                .addMapping(CategoryDTO::getNameofCategory, Category::setName);

        //Sale.details is the owning side's inverse (mappedBy = "sale"); each SaleDetail must carry
        //a back-reference to its parent Sale or the id_sale FK column would be inserted as null.
        mapper.createTypeMap(SaleDTO.class, Sale.class)
                .setPostConverter(saleBackReferenceConverter());

        return mapper;
    }

    private Converter<SaleDTO, Sale> saleBackReferenceConverter() {
        return ctx -> {
            Sale sale = ctx.getDestination();
            if (sale.getDetails() != null) {
                sale.getDetails().forEach(detail -> detail.setSale(sale));
            }
            return sale;
        };
    }
}
