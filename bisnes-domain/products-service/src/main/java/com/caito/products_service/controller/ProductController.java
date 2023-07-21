package com.caito.products_service.controller;

import com.caito.products_service.models.dto.ProductRequest;
import com.caito.products_service.models.dto.ProductResponse;
import com.caito.products_service.service.contract.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/micro-app/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @Operation(summary = "servicio guardar productos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest dto){
        log.info("<<< ****  endpoint guardar productos  *** >>>>");
        ProductResponse response = productService.addProduct(dto);
        if (response == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "servicio buscar producto por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id){
        log.info("<<< *** endpoint buscar producto por id ***>>>");
        ProductResponse response = productService.getProduct(id);
        if (response == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "servicio buscar todos los producto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<List<ProductResponse>> getAll(){
        log.info("<<< *** endpoint buscar todos los productos *** >>>");
        List<ProductResponse> response = productService.getAll();
        if (response == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(response);
    }
}
