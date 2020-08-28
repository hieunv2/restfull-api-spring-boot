package vn.javisco.agency.services;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.javisco.agency.exceptions.NotFoundException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<T> {

    private final JpaRepository<T, Serializable> repository;

    protected BaseService(JpaRepository<T, Serializable> repository) {
        this.repository = repository;
    }

    public T save(T entity) {
        return repository.saveAndFlush(entity);
    }

    public T update(T entity, Serializable id) {
        Optional<T> t = repository.findById(id);
        if (!t.isPresent()) throw new NotFoundException("NOT_FOUND");
        T updateEntity = t.get();
        BeanUtils.copyProperties(entity, updateEntity, "id", "createdAt", "updatedAt");
        return repository.saveAndFlush(updateEntity);
    }

    public <S extends T> List<S> saveAll(List<S> entities) {
        return repository.saveAll(entities);
    }

    public T findById(Serializable id) {
        Optional<T> t = repository.findById(id);
        if (t.isPresent()) return t.get();
        throw new NotFoundException("NOT_FOUND");
    }

    public boolean existsById(Serializable id) {
        return repository.existsById(id);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public List<T> findAllById(List<Serializable> ids) {
        return repository.findAllById(ids);
    }

    public long count() {
        return repository.count();
    }

    public void deleteById(Serializable id) {
        if (!existsById(id)) throw new NotFoundException("NOT_FOUND");
        repository.deleteById(id);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public void deleteAll(List<? extends T> entities) {
        repository.deleteAll(entities);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}