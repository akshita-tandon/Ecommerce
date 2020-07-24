package com.springbootcamp.springsecurity.controller;

import com.springbootcamp.springsecurity.dto.*;
import com.springbootcamp.springsecurity.entity.Category;
import com.springbootcamp.springsecurity.exception.CategoryNotFoundException;
import com.springbootcamp.springsecurity.repository.CategoryRepository;
import com.springbootcamp.springsecurity.repository.ProductVariationRepository;
import com.springbootcamp.springsecurity.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {


    @Autowired
    CategoryService categoryService;


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////TO-ADD-A-CATEGORY/////////////////////////////////////////////////
    @PostMapping("/addcategory")
    public ResponseEntity<String> addCategory(@Valid @RequestParam String name, @RequestParam(required = false) Long parent_id) {

        if (categoryService.addCategory(name, parent_id))
            return new ResponseEntity<String>("Category is added to the database", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Category is not added to the database", HttpStatus.NOT_ACCEPTABLE);

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////TO-VIEW-A-CATEGORY-BY-ADMIN/////////////////////////////////////////////////
    @GetMapping("/viewcategory")
    public CategoryResponseDTO viewCategory(@RequestParam Long category_id) {

        return categoryService.viewCategory(category_id);

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////TO-VIEW-ALL-CATEGORIES-BY-ADMIN/////////////////////////////////////////////////
    @GetMapping("/allcategories")
    public Page<CategoryResponseDTO> allCategories(Pageable pageable) {

        return categoryService.allCategories(pageable);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////TO-UPDATE-A-CATEGORY/////////////////////////////////////////////////
    @PatchMapping("/updatecategory")
    public ResponseEntity<String> updateCategory(@RequestParam Long category_id, @RequestParam String name) {

        if (categoryService.updateCategory(category_id, name))
            return new ResponseEntity<String>("Category is updated to the database", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Category is not updated to the database", HttpStatus.EXPECTATION_FAILED);


    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////TO-ADD-A-METADATA-FIELD/////////////////////////////////////////////////
    @PostMapping("/addmetadata")
    public ResponseEntity<String> addMetadata(@Valid @RequestParam String fieldname) {

        if (categoryService.addMetadata(fieldname))
            return new ResponseEntity<String>("Category Metadata Field is added to the database", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Category Metadata Field is not added to the database", HttpStatus.BAD_REQUEST);

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////TO-VIEW-A-METADATA-FIELD/////////////////////////////////////////////////
    @GetMapping("/viewmetadata")
    public Page<CategoryMetadataFieldResponseDTO> viewMetadataField(Pageable pageable) {

        return categoryService.viewMetadataField(pageable);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////TO-ADD-A-METADATA-FIELD-VALUE//////////////////////////////////////////////
    @PostMapping("/add/metadatavalue")
    public ResponseEntity<String> addMetadataFieldValue(@RequestBody CategoryMetadataFieldValueRequestDTO categoryMetadataFieldValueRequestDTO) {

        if (categoryService.addMetadataFieldValue(categoryMetadataFieldValueRequestDTO))
            return new ResponseEntity<String>("Category Metadata Field value is added to the database", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Category Metadata Field value is not added to the database", HttpStatus.EXPECTATION_FAILED);


    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////TO-UPDATE-A-METADATA-FIELD-VALUE///////////////////////////////////////////
    @PutMapping("/update/metadatavalue")
    public ResponseEntity<String> updateMetadataFieldValue(@RequestBody CategoryMetadataFieldValueRequestDTO categoryMetadataFieldValueRequestDTO) {

        if (categoryService.updateMetadataFieldValue(categoryMetadataFieldValueRequestDTO))
            return new ResponseEntity<String>("Category Metadata Field value is updated to the database", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Category Metadata Field value is not updated to the database", HttpStatus.EXPECTATION_FAILED);


    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////TO LIST ALL CATEGORIES BY SELLER//////////////////////////////////////////////
    @GetMapping("/seller/categories")
    public List<CategoryResponseDTO> sellerListCategories() {

        return categoryService.sellerListCategories();

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////TO LIST ALL CATEGORIES BY BUYER//////////////////////////////////////////////
    @GetMapping("buyer/allcategories")
    public List<CustomerCategoryResponseDTO> buyerAllCategories(@RequestParam(required = false) Long categoryId, Pageable pageable) {
        return categoryService.buyerAllCategories(categoryId, pageable);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////TO FILTER ACC TO A CATEGORIES BY BUYER//////////////////////////////////////////////
    @GetMapping("/buyer/filtercategory")
    public CustomerFilterCategoryResponseDTO buyerFilterCategory(@RequestParam Long categoryId) {

        return categoryService.buyerFilterCategories(categoryId);

    }
}


