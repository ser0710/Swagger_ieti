package org.adaschool.api.service.product;

import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductsServiceMap implements ProductsService {

    Map<String, Product> productDB = new HashMap<>();
    @Override
    public Product save(Product product) {
        if(findById(product.getId()).isEmpty()){
            productDB.put(product.getId(), product);
        }
        return productDB.get(product.getId());
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(productDB.get(id));
    }

    @Override
    public List<Product> all() {
        return new ArrayList<>(productDB.values());
    }

    @Override
    public void deleteById(String id) {
        productDB.remove(id);
    }

    @Override
    public Product update(ProductDto productDto, String productId) {
        Product product = productDB.get(productId);
        product.update(productDto);
        productDB.replace(productId, product);
        return productDB.get(productId);
    }
}
