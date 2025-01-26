package com.ecommerce.controller;

import com.ecommerce.ApiUrls;
import com.ecommerce.entity.Product;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiUrls.VERSION_1 + ApiUrls.ROOT_URL_PRODUCTS)
public class ProductRestController {
    private  static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Product> productList = productService.findAll();
        return ResponseEntity.ok(productList);
    }

    @GetMapping(ApiUrls.URL_PRODUCTS_PRODUCT)
    public ResponseEntity<?> findOne(@PathVariable(value = "productId") Long id) {
        logger.info("findOne: id = {}", id);
        return ResponseEntity.ok(productService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product) {
        logger.info("save");

        product = productService.save(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location).body(product);
    }

    @DeleteMapping(ApiUrls.URL_PRODUCTS_PRODUCT)
    public ResponseEntity<?> delete(@PathVariable(value = "productId") Long id) {
        logger.info("delete: id = {}", id);

        if (!productService.isExists(id)) {
            throw new ResourceNotFoundException("");
        }

        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
