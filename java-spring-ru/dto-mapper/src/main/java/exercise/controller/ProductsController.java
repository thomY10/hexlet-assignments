package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @Autowired
    private ProductMapper productMapper;
    @GetMapping(path = "")
    public List<ProductDTO> index() {
        var products = productRepository.findAll();

        return products.stream()
                .map(productMapper::map)
                .toList();
    }

    @GetMapping(path = "/{id}")
    public ProductDTO show(@PathVariable long id) {

        var product =  productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        var productDTO = productMapper.map(product);
        return productDTO;
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO productData) {

        var post = productMapper.map(productData);
        productRepository.save(post);
        // Преобразование в DTO
        var postDTO = productMapper.map(post);
        return postDTO;

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProductDTO update(@RequestBody ProductUpdateDTO productData, @PathVariable Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        productMapper.update(productData, product);
        productRepository.save(product);
        var productDTO = productMapper.map(product);
        return productDTO;
    }
    // END
}
