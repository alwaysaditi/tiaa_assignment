package com.example.productsnew.rest;

import com.example.productsnew.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController implements ProductsImpl{

    private EntityManager entityManager;

    @Autowired
    ProductRestController (EntityManager theentityManager)
    {
        this.entityManager = theentityManager;
    }
    @Override
    @GetMapping("/products")
    public List<Product> findAll() {
        TypedQuery<Product> query = entityManager.createQuery("FROM Product",Product.class);

        List<Product> prodList = query.getResultList();
        return  prodList;
    }

    @Override
    @GetMapping("/products/{id}")
    public Product findById(@PathVariable int id) {
        return entityManager.find(Product.class,id);
    }

    @Override
    @Transactional
    @PostMapping("/products")
    public Product save(@RequestBody Product theProduct) { // this method is used to create a new product

        Product dBProduct = entityManager.merge(theProduct);

        return dBProduct;
    }


@PutMapping("/products")
@Transactional
public Product productUpdate(@RequestBody Product theProduct)
{
   save(theProduct);
   return theProduct;
}



    @Override
    @Transactional
    @DeleteMapping("/products/{id}")
    public void deletebyId(@PathVariable int id) {
        Product deleteProduct = entityManager.find(Product.class,id);
        entityManager.remove(deleteProduct);
    }
}
