package com.ecommerce.controller;

import com.ecommerce.ApiUrls;
import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(ApiUrls.VERSION_1 + ApiUrls.ROOT_URL_PRODUCTS)
public class ProductRestController {
    private  static final Logger logger = (Logger) LoggerFactory.getLogger(ProductRestController.class);

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Product> productList = productService.findAll();
        return ResponseEntity.ok(productList);
    }

}
