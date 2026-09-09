package com.mitocode.repo;

import com.mitocode.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepo extends IGenericRepo<Category, Integer> {

    //public Category getCategoryFromDB(Integer id);
    //DerivedQuery
    //SELECT * FROM Category c WHERE c.name = ?
    List<Category> findByName(String name);
    List<Category> findByNameIgnoreCase(String name);
    //SELECT * FROM Category c WHERE c.name LIKE %?%
    List<Category> findByNameLikeIgnoreCase(String name);
    //%XYZ% -> findByNameContains
    //%XYZ -> findByNameStartsWith
    //XYZ% -> findByNameEndsWith
    List<Category> findByNameAndEnabled(String name, boolean enabled);
    List<Category> findByNameOrEnabled(String name, boolean enabled);
    List<Category> findByEnabled(boolean enabled);
    List<Category> findByEnabledTrue();
    List<Category> findByEnabledFalse();
    Optional<Category> findOneByName(String name);
    List<Category> findTop3ByOrderByNameAsc();
    List<Category> findByNameIs(String name);
    List<Category> findByNameIsNot(String name);
    List<Category> findByNameIsNull();
    List<Category> findByNameIsNotNull();
    List<Category> findByIdCategoryLessThan(Integer idCategory);
    List<Category> findByIdCategoryLessThanEqual(Integer idCategory);
    List<Category> findByIdCategoryGreaterThan(Integer idCategory);
    List<Category> findByIdCategoryGreaterThanEqual(Integer idCategory);
    List<Category> findByIdCategoryBetween(Integer start, Integer end);
    List<Category> findByNameOrderByNameAsc(String name);
    List<Category> findByNameOrderByNameDesc(String name);

    // Más ejemplos de findBy con operadores adicionales
    List<Category> findByNameContains(String name);
    List<Category> findByNameStartsWith(String name);
    List<Category> findByNameEndsWith(String name);
    List<Category> findByIdCategoryIn(List<Integer> ids);
    List<Category> findByIdCategoryNotIn(List<Integer> ids);
    List<Category> findByNameNotContains(String name);

    // findBy con múltiples condiciones y ordenamiento
    List<Category> findByEnabledAndNameOrderByNameAsc(boolean enabled, String name);
    List<Category> findByEnabledOrNameContainsOrderByIdCategoryDesc(boolean enabled, String name);
    List<Category> findByIdCategoryBetweenAndEnabledOrderByNameAsc(Integer start, Integer end, boolean enabled);

    // findBy con parámetros de paginación (límite)
    List<Category> findTop5ByEnabledOrderByNameAsc(boolean enabled);
    List<Category> findFirst10ByOrderByIdCategoryDesc();

    // deleteBy con diferentes condiciones
    Integer deleteByName(String name);
    Integer deleteByEnabled(boolean enabled);
    Integer deleteByIdCategoryGreaterThan(Integer idCategory);
    Integer deleteByIdCategoryLessThanEqual(Integer idCategory);
    Integer deleteByNameContains(String name);
    Integer deleteByEnabledFalse();

    // countBy con diferentes condiciones
    Long countByEnabled(boolean enabled);
    Long countByName(String name);
    Long countByNameContains(String name);
    Long countByEnabledTrue();
    Long countByEnabledFalse();
    Long countByIdCategoryGreaterThan(Integer idCategory);
    Long countByNameAndEnabled(String name, boolean enabled);
    Long countByNameOrEnabled(String name, boolean enabled);

    //JQPL: Java Persistence Query Language
    @Query("FROM Category c WHERE c.name = :name AND c.description LIKE %:desc%")
    List<Category> getNameDescription1(String name, @Param("desc") String desc);

    @Query("SELECT new com.mitocode.model.Category (c.name, c.enabled) FROM Category c WHERE c.name = :name AND c.description LIKE %:desc%")
    List<Category> getNameDescription2(String name, @Param("desc") String desc);

    //SQL: Native Query
    @Query(value = "SELECT * FROM category c WHERE c.name = :name", nativeQuery = true)
    List<Category> getNameSQL(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE category c SET c.name = :name WHERE c.id_category = :idCategory", nativeQuery = true)
    long updateNames(String name, String idCategory);

}
