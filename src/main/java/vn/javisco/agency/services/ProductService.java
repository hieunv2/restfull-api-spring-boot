package vn.javisco.agency.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import vn.javisco.agency.entity.Category;
import vn.javisco.agency.entity.Product;
import vn.javisco.agency.exceptions.NotFoundException;
import vn.javisco.agency.repository.CategoryRepository;
import vn.javisco.agency.repository.ProductRepository;
import vn.javisco.agency.request.ProductRequest;

@Service
public class ProductService extends BaseService<Product> {

    private final ProductRepository productCategory;
    private final CategoryRepository categoryRepository;

    protected ProductService(ProductRepository repository, CategoryRepository categoryRepository) {
        super(repository);
        this.productCategory = repository;
        this.categoryRepository = categoryRepository;
    }

    public Product create(ProductRequest request) {
        int categoryId = request.getCategoryId();
        Category category = categoryRepository.findById(categoryId);
        if (category == null) {
            throw new NotFoundException("Can not find category");
        }
        Product product = new Product();
        BeanUtils.copyProperties(request, product);
        product.setCategory(category);
        return productCategory.saveAndFlush(product);
    }

    public Product update(ProductRequest request, int id) {
        Product oldProduct = productCategory.findById(id);
        if (oldProduct == null) throw new NotFoundException("Cant not find product");
        int categoryId = request.getCategoryId();
        Category category = categoryRepository.findById(categoryId);
        if (category == null) {
            throw new NotFoundException("Cant not find category");
        }
        Product product = new Product();
        BeanUtils.copyProperties(request, product, "id", "created_at", "updated_at");
        product.setCategory(category);
        return productCategory.saveAndFlush(product);
    }

}