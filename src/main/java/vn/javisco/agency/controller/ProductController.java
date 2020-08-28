package vn.javisco.agency.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.javisco.agency.entity.Product;
import vn.javisco.agency.request.ProductRequest;
import vn.javisco.agency.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService service) {
        this.productService = service;
    }

    @GetMapping
    public ResponseEntity<List<Product>> get() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> detail(@PathVariable("id") int id) {
        Product product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductRequest request) {
        Product product = productService.create(request);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody ProductRequest product, @PathVariable("id") int id) {
        Product newProduct = productService.update(product, id);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        productService.deleteById(id);
    }
}
