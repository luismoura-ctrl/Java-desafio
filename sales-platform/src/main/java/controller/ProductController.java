package controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import entity.Product;
import repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	 @Autowired
	    private ProductRepository productRepository;

	    // LISTAR TODOS OS PRODUTOS
	    @GetMapping
	    public List<Product> getAllProducts() {
	        return productRepository.findAll();
	    }

	    // BUSCAR PRODUTO POR ID
	    @GetMapping("/{id}")
	    public Optional<Product> getProductById(@PathVariable Long id) {
	        return productRepository.findById(id);
	    }

	    // CRIAR PRODUTO
	    @PostMapping
	    public Product createProduct(@RequestBody Product product) {
	        return productRepository.save(product);
	    }

	    // ATUALIZAR PRODUTO
	    @PutMapping("/{id}")
	    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {

	        Product product = productRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

	        product.setName(productDetails.getName());
	        product.setPrice(productDetails.getPrice());
	        product.setDescription(productDetails.getDescription());

	        return productRepository.save(product);
	    }

	    // DELETAR PRODUTO
	    @DeleteMapping("/{id}")
	    public void deleteProduct(@PathVariable Long id) {
	        productRepository.deleteById(id);
	    }

}