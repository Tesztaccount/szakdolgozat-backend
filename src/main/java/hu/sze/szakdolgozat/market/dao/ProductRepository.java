package hu.sze.szakdolgozat.market.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;


import hu.sze.szakdolgozat.market.entity.Product;
@Repository
@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Integer>{

    Page<Product> findByCategory(@RequestParam("category") String category, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE productname like %:productname% or details like %:details%")
    Page<Product> customSearch(@Param("productname") String productname, @Param("details") String details, Pageable pageable);

    // @Query("Select p.firstname, p.lastname, pr.productname, pr.category, pr.unit, pr.price, pr.details, pr.image From Product pr WHERE category like :category && pr.products")
    // List<ProductResponse> customProductList();
    // p.products pr   
    // @Query(value = "SELECT p.firstname, p.lastname, pr.id, pr.productname, pr.category, pr.unit, pr.price, pr.details, pr.image " + 
    //     "FROM products as pr " + 
    //     "INNER JOIN producers as p on pr.producer_id=p.id "+
    //     "WHERE pr.category = :category", nativeQuery = true)
    // List<ProductResponse> customFindByCategory(@Param("category") String category);

}

