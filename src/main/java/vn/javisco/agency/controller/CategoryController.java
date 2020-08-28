package vn.javisco.agency.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.javisco.agency.entity.Category;
import vn.javisco.agency.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends BaseController<Category> {

    public CategoryController(CategoryService service) {
        super(service);
    }
}
