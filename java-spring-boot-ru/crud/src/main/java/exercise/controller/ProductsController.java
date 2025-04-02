package exercise.controller;

import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ConstraintViolationException;
import exercise.mapper.ProductMapper;
import exercise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path = "")
    public List<ProductDTO> index() {
        return productRepository.findAll().stream().map(product -> productMapper.map(product)).toList();
    }

    @GetMapping(path = "/{id}")
    public ProductDTO show(@PathVariable long id) {
        var product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("product with id " + " not found"));
        return productMapper.map(product);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@Valid @RequestBody ProductCreateDTO data) {
        var product = productMapper.map(data);
        var category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(() ->
                new ConstraintViolationException("category not found"));
        category.getProducts().add(product);
        categoryRepository.save(category);
        productRepository.save(product);
        return productMapper.map(product);
    }

    @PutMapping(path = "/{id}")
    public ProductDTO update(@Valid @RequestBody ProductUpdateDTO data, @PathVariable long id) {
        var product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("product with id " + id + " not found"));
        var category = categoryRepository.findById(data.getCategoryId().get()).orElseThrow(() ->
                new ResourceNotFoundException("category with id " + data.getCategoryId() + "not found"));
        productMapper.update(data, product);
        product.setCategory(category);
        productRepository.save(product);
        return productMapper.map(product);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable long id) {
        var product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("product with id " + id + " not found"));
        productRepository.delete(product);
    }
    // END
}
