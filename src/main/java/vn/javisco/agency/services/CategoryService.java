package vn.javisco.agency.services;

import org.springframework.stereotype.Service;
import vn.javisco.agency.entity.Category;
import vn.javisco.agency.repository.CategoryRepository;

@Service
public class CategoryService extends BaseService<Category> {

    protected CategoryService(CategoryRepository repository) {
        super(repository);
    }
}