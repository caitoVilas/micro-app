package com.caito.products_service.service.contract;

import com.caito.products_service.models.dto.ProductRequest;
import com.caito.products_service.models.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest dto);
    ProductResponse getProduct(Long id);
    List<ProductResponse> getAll();
}
