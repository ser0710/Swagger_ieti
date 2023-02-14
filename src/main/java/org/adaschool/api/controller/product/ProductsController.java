package org.adaschool.api.controller.product;

import jakarta.servlet.Servlet;
import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;
import org.adaschool.api.service.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/products/")
public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(@Autowired ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        //TODO implement this method
        Product newProduct = productsService.save(product);
        URI createdProductUri = URI.create("");
        return ResponseEntity.created(createdProductUri).body(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        //TODO implement this method
        return ResponseEntity.ok(productsService.all());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") String id) {
        //TODO implement this method
        Product optionalProduct = productsService.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return ResponseEntity.ok(optionalProduct);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDto productDto, @PathVariable("id") String id) {
        //TODO implement this method
        Product optionalProduct = productsService.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        Product productUp = productsService.update(productDto, id);
        productsService.save(optionalProduct);
        return ResponseEntity.ok(productUp);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        //TODO implement this method
        Product optionalProduct = productsService.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
