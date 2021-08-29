package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.entites.Product;
import spring.repositories.ProductRepository;

import java.util.List;


@Component
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    public void addProduct(Product product){
        productRepository.addNewProduct(product);
    }
    public Product getById(int id) {
        return productRepository.getProductById(id);
    }

    public List<Product> getAll() {
        return productRepository.getAllProduct();
    }
}
