package vn.javisco.agency.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.javisco.agency.services.BaseService;

import java.util.List;


public abstract class BaseController<M> {

    private final BaseService<M> service;

    public BaseController(BaseService<M> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<M>> get() {
        List<M> list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<M> detail(@PathVariable("id") int id) {
        M m = service.findById(id);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<M> create(@RequestBody M request) {
        M m = service.save(request);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<M> update(@RequestBody M model, @PathVariable("id") int id) {
        M m = service.update(model, id);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        service.deleteById(id);
    }
}
