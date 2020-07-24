package com.springbootcamp.springsecurity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootcamp.springsecurity.dto.*;
import com.springbootcamp.springsecurity.entity.OrderProduct;
import com.springbootcamp.springsecurity.exception.*;
import com.springbootcamp.springsecurity.service.ProductService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-ADD-PRODUCT-BY-SELLER///////////////////////////////////////////
    @PostMapping("/add/product")
    public ResponseEntity<String> addProduct(Principal principal, @Valid @RequestBody ProductRequestDTO productRequestDTO) {

        if (productService.addProduct(principal, productRequestDTO))
            return new ResponseEntity<String>("Product is added to the database", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Product is not added to the database", HttpStatus.EXPECTATION_FAILED);


    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-VIEW-PRODUCT-BY-SELLER///////////////////////////////////////////
    @GetMapping("/view/product")
    public ProductResponseDTO viewProduct(Principal principal, @RequestParam Long productId) {

        return productService.viewProduct(principal, productId);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-VIEW-ALL--PRODUCTS-BY-SELLER///////////////////////////////////////////
    @GetMapping("/view/allproduct")
    public Page<ProductPagingResponseDto> viewAllProducts(Principal principal, Pageable pageable) {

        return productService.viewAllProduct(principal, pageable);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-DELETE-PRODUCT-BY-SELLER///////////////////////////////////////////
    @DeleteMapping("/delete/product")
    public ResponseEntity<String> deleteProduct(Principal principal, @RequestParam Long productId) {

        if (productService.deleteProduct(principal, productId))
            return new ResponseEntity<String>("Product is deleted from the database", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Product is not deleted to the database", HttpStatus.EXPECTATION_FAILED);


    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-UPDATE-PRODUCT-BY-SELLER///////////////////////////////////////////
    @PutMapping("/update/product")
    public ResponseEntity<String> updateProduct(Principal principal, @RequestBody ProductUpdateRequestDTO productUpdateRequestDTO) {

        if (productService.updateProduct(principal, productUpdateRequestDTO))
            return new ResponseEntity<String>("Product is updated from the database", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Product is not updated to the database", HttpStatus.EXPECTATION_FAILED);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-ACTIVATE-PRODUCT-BY-ADMIN///////////////////////////////////////////
    @PutMapping("/activate/product")
    public ResponseEntity<String> activateProduct(@RequestParam @Valid Long productId) {

        if (productService.activateProduct(productId))
            return new ResponseEntity<String>("Product is activated", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Product is not activated", HttpStatus.EXPECTATION_FAILED);


    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-DEACTIVATE-PRODUCT-BY-ADMIN///////////////////////////////////////////
    @PutMapping("/deactivate/product")
    public ResponseEntity<String> deactivateProduct(@RequestParam @Valid Long productId) {

        if (productService.deactivateProduct(productId))
            return new ResponseEntity<String>("Product is de-activated", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Product is not de-activated", HttpStatus.EXPECTATION_FAILED);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-ADD-PRODUCT-VARIATION-BY-SELLER///////////////////////////////////////////
    @PostMapping("/add/productVariation")
    public ResponseEntity<String> addProductVariation(@RequestBody MultipartFile image,Principal principal,String productVariationRequestDTO) throws JSONException, MetadataFieldValueNotFoundException, IOException {

        ObjectMapper objectMapper=new ObjectMapper();
        ProductVariationRequestDTO productVariationRequestDTO1=objectMapper.readValue(productVariationRequestDTO,ProductVariationRequestDTO.class);
        if (productService.addProductVariation(image,principal, productVariationRequestDTO1)) {
            return new ResponseEntity<String>("Product Variation is added for the product", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Product Variation is not added for the product", HttpStatus.EXPECTATION_FAILED);

        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////TO-VIEW-PRODUCT-VARIATION-BY-SELLER///////////////////////////////////////////
    @GetMapping("/view/product/variation")
    public ProductVariationResponseDTO viewProductVariation(Principal principal, @RequestParam Long productVariationId) throws ProductVariationNotFoundException {

        return productService.viewProductVariation(principal, productVariationId);

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////TO-VIEW-ALL-PRODUCT-VARIATION-BY-SELLER///////////////////////////////////////////
    @GetMapping("/view/all/product/variations")
    public Page<AllProductVariationResponseDTO> viewAllProductVariations(Principal principal, @RequestParam Long productId, Pageable pageable) {

        return productService.viewAllProductVariations(principal, productId, pageable);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////TO-UPDATE-PRODUCT-VARIATION-BY-SELLER///////////////////////////////////////////
    @PutMapping("/update/product/variation")
    public ResponseEntity<String> updateProductVariation(Principal principal, @RequestBody UpdateVariationDTO updateVariationDTO) throws IOException, MetadataFieldValueNotFoundException {

        if(productService.updateProductVariation(principal,updateVariationDTO))
          return new ResponseEntity<String>("Product Variation is updated", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Product Variation is not updated", HttpStatus.EXPECTATION_FAILED);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////TO-VIEW-PRODUCT-BY-BUYER///////////////////////////////////////////
    @GetMapping("/buyer/view/product")
    public BuyerProductResponseDTO viewProductBuyer(@RequestParam Long productId) {
        return productService.viewProductBuyer(productId);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////TO-VIEW-ALL-PRODUCT-BY-BUYER///////////////////////////////////////////
    @GetMapping("/buyer/view/allproduct")
    public Page<BuyerProductResponseDTO> viewAllProductBuyer(@RequestParam Long categoryId) {
        return productService.viewAllProductBuyer(categoryId);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////TO-VIEW-PRODUCT-BY-ADMIN///////////////////////////////////////////
    @GetMapping("/admin/view/product")
    public BuyerProductResponseDTO viewProductAdmin(@RequestParam Long productId) {
        return productService.viewProductAdmin(productId);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////TO-VIEW-ALL-PRODUCT-BY-ADMIN//////////////////////////////////////
    @GetMapping("/admin/view/allproduct")
    public Page<BuyerProductResponseDTO> viewAllProductAdmin(Pageable pageable) {

        return productService.viewAllProductAdmin(pageable);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////TO-VIEW-SIMILAR-PRODUCT-BY-BUYER//////////////////////////////////////////
    @GetMapping("/buyer/similarproducts")
    public Page<BuyerProductResponseDTO> viewSimilarProductsBuyer(@RequestParam Long productId, Pageable pageable) {
        return productService.viewSimilarProductsBuyer(productId, pageable);

    }

    @PostMapping("/addtocart")
    public ResponseEntity<String> addToCart(Principal principal,@RequestBody  AddToCartRequestDTO addToCartRequestDTO){
        if(productService.addToCart(principal,addToCartRequestDTO)){
            return new ResponseEntity<>("Product is added to cart",HttpStatus.OK);
        }else
            return  new ResponseEntity<>("Sorry..Product can't be added to cart",HttpStatus.EXPECTATION_FAILED);

    }

    @PutMapping("/movetowishlist")
    public ResponseEntity<String> addToWishList(@RequestParam Long buyerId,@RequestParam Long productVariationId){
        if(productService.moveToWishList(buyerId,productVariationId)){
            return new ResponseEntity<>("Product is moved to wishlist",HttpStatus.OK);
        }else
            return  new ResponseEntity<>("Sorry..Product can't move to wishlist",HttpStatus.EXPECTATION_FAILED);

    }

    @PutMapping("/movetocart")
    public ResponseEntity<String> moveToCart(Principal principal,@RequestParam Long productVariationId){
        if(productService.moveToCart(principal,productVariationId)){
            return new ResponseEntity<>("Product is moved to cart",HttpStatus.OK);
        }else
            return  new ResponseEntity<>("Sorry..Product can't move to cart",HttpStatus.EXPECTATION_FAILED);

    }

    @PostMapping("/addtowishlist")
    public ResponseEntity<String> addToWishList(Principal principal,@RequestParam Long productVariationId){
        if(productService.addToWishList(principal,productVariationId)){
            return new ResponseEntity<>("Product is added to wishlist",HttpStatus.OK);
        }else
            return  new ResponseEntity<>("Sorry..Product can't be added to wishlist",HttpStatus.EXPECTATION_FAILED);

    }

    @DeleteMapping("/removefromcart")
    public ResponseEntity<String> deleteItem(@RequestParam Long buyerId,@RequestParam Long productVariationId){
        if(productService.deleteItem(buyerId,productVariationId)){
            return new ResponseEntity<>("Product is deleted from wishlist",HttpStatus.OK);
        }else
            return  new ResponseEntity<>("Sorry..Product isn't deleted from wishlist",HttpStatus.EXPECTATION_FAILED);

    }

    @GetMapping("/getcart")
    public List<CartResponseDTO> getItemsFromCart(Principal principal){
        return productService.getItemsFromCart(principal);
    }

    @GetMapping("/getwishlist")
    public List<CartResponseDTO> getItemsFromWishList(Principal principal){
        return productService.getItemsFromWishList(principal);
    }

    @PutMapping("/updatecart")
    public ResponseEntity<String> updateQuantityFromCart(@RequestParam Long buyerId,@RequestParam Long productVariationId,@RequestParam Integer quantity){
        if(productService.updateQuantityFromCart(buyerId,productVariationId,quantity))
            return new ResponseEntity<>("Quantity is updated from cart",HttpStatus.OK);
        else
            return new ResponseEntity<>("Quantity is not updated from cart",HttpStatus.EXPECTATION_FAILED);


    }

    @PostMapping("/ordernow")
    public ResponseEntity<String> orderNowForSingleProduct(Principal principal,@RequestParam Long productVariationId,@RequestParam Integer quantity,@RequestParam Long addressId){
        if(productService.orderNowForSingleProduct(principal,productVariationId,quantity,addressId))
            return new ResponseEntity<>("Order is placed",HttpStatus.OK);
        else
            return new ResponseEntity<>("Order is not placed",HttpStatus.EXPECTATION_FAILED);
    }

    @PostMapping("/ordernowfromcart")
    public ResponseEntity<String> orderNowForCart(Principal principal,@RequestParam Long addressId){
        if(productService.orderNowForCart(principal,addressId))
            return new ResponseEntity<>("Order is placed",HttpStatus.OK);
        else
            return new ResponseEntity<>("Order is not placed",HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/removeorder")
    public ResponseEntity<String> removeOrder(@RequestParam Long orderProductId){
        if(productService.removeOrder(orderProductId))
            return new ResponseEntity<>("Order is deleted",HttpStatus.OK);
        else
            return new ResponseEntity<>("Order is not deleted",HttpStatus.EXPECTATION_FAILED);
    }


    @GetMapping("/seller/orders")
    public List<OrderProductSellerResponseDTO> viewOrdersBySellers(Principal principal){
        return productService.viewOrdersBySellers(principal);
    }


    @GetMapping("/buyer/orders")
    public List<OrderBuyerResponseDTO> viewOrdersByBuyers(Principal principal){
        return productService.viewOrdersByBuyers(principal);
    }

    @PutMapping("seller/orderchangestatus")
    public ResponseEntity<String> confirmOrder(@RequestParam Long orderStatusId,@RequestParam String change){
        if(productService.confirmOrRejectOrder(orderStatusId,change))
            return new ResponseEntity<>("Order status changed",HttpStatus.OK);
        else
            return new ResponseEntity<>("Order status confirmed",HttpStatus.EXPECTATION_FAILED);

    }

}

