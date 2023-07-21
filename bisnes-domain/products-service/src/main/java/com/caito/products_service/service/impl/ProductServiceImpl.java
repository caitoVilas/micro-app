package com.caito.products_service.service.impl;

import com.caito.products_service.models.dto.ProductRequest;
import com.caito.products_service.models.dto.ProductResponse;
import com.caito.products_service.models.entity.Product;
import com.caito.products_service.repository.ProductRepository;
import com.caito.products_service.service.contract.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public ProductResponse addProduct(ProductRequest dto) {
        log.info("----> inicio servicio guardar producto");
        if (productRepository.existsByName(dto.getName())){
            log.error("##### error ya existe un producto con ese nombre");
            return null;
        }
        log.info("----> gurdando producto...");
        return this.mapToDTO(productRepository.save(this.mapToEntity(dto)));
    }

    @Override
    public ProductResponse getProduct(Long id) {
        log.info("----> inicio servicio buscar producto por id");
        log.info("----> buscando producto...");
        Product product = productRepository.findById(id).orElse(null);
        if (product == null){
            log.error("#### producto no encontrado");
            return null;
        }
        return this.mapToDTO(product);
    }

    @Override
    public List<ProductResponse> getAll() {
        log.info("----> inicio servicio buscar productos");
        log.info("----> buscando productos....");
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()){
            log.error("#### no se encontraron productos");
            return null;
        }
        return products.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private ProductResponse mapToDTO(Product request){
        return ProductResponse.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }

    private Product mapToEntity(ProductRequest request){
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }
}
