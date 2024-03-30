package com.example.Sales.Management.System.service;

import com.example.Sales.Management.System.dto.ProductDto;
import com.example.Sales.Management.System.entity.Product;
import com.example.Sales.Management.System.exception.RecordNotFoundException;
import com.example.Sales.Management.System.mapper.ProductMapper;
import com.example.Sales.Management.System.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;


@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    MessageSource messageSource;


    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    private boolean isUpdated(Object data) {
        if (data != null && data.toString().trim().length() > 0) return true;
        return false;
    }
    public ProductDto findOne(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            String [] para = {id.toString()};
            throw new RecordNotFoundException(messageSource.getMessage("record.notfound", para, LocaleContextHolder.getLocale()));
        }
        return productMapper.toDto(productRepository.findById(id).get());
    }
    public Long createProduct(ProductDto productDto) {
        try {
            Product product = productRepository.save(productMapper.toEntity(productDto));
            if (product != null) {
                log.info("Product :"+ product.getName() + " created successfully");
                return product.getId();
            }
        } catch (Exception e) {
            log.error("Error while creating product: {}", e.getMessage());
        }
        throw new RuntimeException("Failed to create product.");
    }

    // update as patch
    public Long updateProduct(Long id, ProductDto productDto) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) {
            String [] para = {id.toString()};
            throw new RecordNotFoundException(messageSource.getMessage("record.notfound", para, LocaleContextHolder.getLocale()));
        }
        try {
            Product product = productOptional.get();
            if(isUpdated(productDto.getCategory()))
                product.setCategory(productDto.getCategory());
            if(isUpdated(productDto.getName()))
                product.setName(productDto.getName());
            if(isUpdated(productDto.getDescription()))
                product.setDescription(productDto.getDescription());
            if(isUpdated(productDto.getPrice()))
                product.setPrice(productDto.getPrice());
            if(isUpdated(productDto.getQuantity()))
                product.setAvailableQuantity(productDto.getQuantity());
            productRepository.save(product);
            log.info("Product with ID :"+ id + " saved successfully");
            return product.getId();
        }catch (Exception e){
            log.error("Error while updating product: {}", e.getMessage());
            throw new RuntimeException("Faild to update product !");
        }
    }

    public boolean deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            String[] para = {id.toString()};
            throw new RecordNotFoundException(messageSource.getMessage("record.notfound", para, LocaleContextHolder.getLocale()));
        }
        productRepository.deleteById(id);
        log.info("Product with ID :"+ id + " has deleted successfully");
        return true;
    }
}
