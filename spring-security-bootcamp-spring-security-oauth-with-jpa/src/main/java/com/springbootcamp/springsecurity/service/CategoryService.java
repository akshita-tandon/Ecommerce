package com.springbootcamp.springsecurity.service;

import com.springbootcamp.springsecurity.dto.*;
import com.springbootcamp.springsecurity.entity.Category;
import com.springbootcamp.springsecurity.entity.CategoryMetadataField;
import com.springbootcamp.springsecurity.entity.CategoryMetadataFieldValue;
import com.springbootcamp.springsecurity.entity.Product;
import com.springbootcamp.springsecurity.exception.*;
import com.springbootcamp.springsecurity.repository.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMetadataFieldRepository categoryMetadataFieldRepository;

    @Autowired
    CategoryMetadataFieldValueRepository categoryMetadataFieldValueRepository;

    @Autowired
    ProductVariationRepository productVariationRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;


    public boolean addCategory(String name, Long parent_id) {
        if (name != null && parent_id != null) {
            Category categoryByName = categoryRepository.findByName(name);
            if (categoryByName == null) {
                Category category = categoryRepository.findById(parent_id).orElse(null);
                Category childCategory = new Category();
                childCategory.setName(name);
                childCategory.setParentCategory(category);
                Set<Category> parent = new HashSet<>();
                parent.add(childCategory);
                childCategory.setChildCategory(parent);
                categoryRepository.save(childCategory);
                return true;
            } else
                throw new CategoryAlreadyExistsException("Category is already present in database");

        } else if (name != null || parent_id != null) {
            Category categoryByName = categoryRepository.findByName(name);
            if (categoryByName == null) {
                Category category = new Category();
                category.setName(name);
                categoryRepository.save(category);
                return true;
            } else
                throw new ParentCategoryAlreadyExistsException("Parent Category is already present in database");
        }
        return false;
    }

    public CategoryResponseDTO viewCategory(Long category_id) {

        Category category = categoryRepository.findById(category_id).orElse(null);
        if (category == null) {
            throw new CategoryNotFoundException("There is no record with this category");
        } else {
            ModelMapper modelMapper = new ModelMapper();
            CategoryResponseDTO categoryResponseDTO = modelMapper.map(category, CategoryResponseDTO.class);
            List<Category> nextChildCategoryList=categoryRepository.fetchNextChild(category.getCategoryId());
            categoryResponseDTO.setNextCategory(nextChildCategoryList);
            return categoryResponseDTO;
        }
    }

    public Page<CategoryResponseDTO> allCategories(Pageable pageable) {

        pageable= PageRequest.of(0,10, Sort.Direction.ASC,"categoryId");
       
        Page<Category> categories =categoryRepository.findAll(pageable);
        ModelMapper modelMapper = new ModelMapper();
        Type typeList = new TypeToken<Page<CategoryResponseDTO>>() {
        }.getType();
        Page<CategoryResponseDTO> categoryDTOList = modelMapper.map(categories, typeList);
// List<CategoryResponseDto> categoryResponseDto=categories.getContent().stream().map(category -> toCategoryResponseDto(category)).collect(Collectors.toList());
        return categoryDTOList;
    }

//    private CategoryResponseDTO toAllCategoriesResponseDTO(Category category) {
//        return new CategoryResponseDTO(category.getCategoryId(),category.getName(),category.getParentCategory(),category.getCategoryMetadataFieldValue());
//    }

    public boolean updateCategory(Long category_id, String name) {
        Category categoryByName = categoryRepository.findByName(name);
        if (categoryByName != null) {
            throw new CategoryAlreadyExistsException("The category already exists in the databse");
        } else {
            Category category = categoryRepository.findById(category_id).orElse(null);
            if (category != null) {
                category.setName(name);
                categoryRepository.save(category);
                return true;
            } else
                throw new CategoryNotFoundException("There is no category with the given id");

        }
    }

    public boolean addMetadata(String fieldname) {
        CategoryMetadataField categoryMetadataField = categoryMetadataFieldRepository.findByName(fieldname);
        if (categoryMetadataField == null) {

            CategoryMetadataField newCategoryMetadataField = new CategoryMetadataField();
            newCategoryMetadataField.setName(fieldname);
            categoryMetadataFieldRepository.save(newCategoryMetadataField);
            return true;
        }
        else
            return false;
    }

    public Page<CategoryMetadataFieldResponseDTO> viewMetadataField(Pageable pageable) {
        Page<CategoryMetadataField> categoryMetadataFields = categoryMetadataFieldRepository.findAll(pageable);
        List<CategoryMetadataFieldResponseDTO> categoryMetadataFieldResponseDTOList = categoryMetadataFields.getContent().stream().map(this::toCategoryMetadataFieldResponseDto).collect(Collectors.toList());
        return new PageImpl<CategoryMetadataFieldResponseDTO>(categoryMetadataFieldResponseDTOList);

    }
    private CategoryMetadataFieldResponseDTO toCategoryMetadataFieldResponseDto(CategoryMetadataField categoryMetadataField) {
        return new CategoryMetadataFieldResponseDTO(categoryMetadataField.getCategoryMetadataFieldId(), categoryMetadataField.getName());
    }

    public boolean addMetadataFieldValue(CategoryMetadataFieldValueRequestDTO categoryMetadataFieldValueRequestDTO) {


        Category category = categoryRepository.findById(categoryMetadataFieldValueRequestDTO.getCategory_id()).orElse(null);
        Integer length=categoryMetadataFieldValueRequestDTO.countArray();
        Integer loop=0;
        while(loop<length) {
            CategoryMetadataField categoryMetadataField = categoryMetadataFieldRepository.findById(categoryMetadataFieldValueRequestDTO.getMetadata_field_id(loop)).orElse(null);

            if (category == null) {
                throw new CategoryNotFoundException("There is no category present with this id");
            }
            if (categoryMetadataField == null) {
                throw new CategoryMetadataFieldNotFoundException("There is no metadata field with this id");
            } else {

                CategoryMetadataFieldValue categoryMetadataFieldValue = new CategoryMetadataFieldValue(category, categoryMetadataField);
                categoryMetadataFieldValue.setMetadataValues(categoryMetadataFieldValueRequestDTO.getMetadatavalues(loop));
                categoryMetadataFieldValueRepository.save(categoryMetadataFieldValue);
                loop++;
            }

        }
        return true;
    }

    public boolean updateMetadataFieldValue(CategoryMetadataFieldValueRequestDTO categoryMetadataFieldValueRequestDTO) {

        Category category = categoryRepository.findById(categoryMetadataFieldValueRequestDTO.getCategory_id()).orElse(null);
        Integer length=categoryMetadataFieldValueRequestDTO.countArray();
        Integer loop=0;
        while(loop<length) {
            CategoryMetadataField categoryMetadataField = categoryMetadataFieldRepository.findById(categoryMetadataFieldValueRequestDTO.getMetadata_field_id(loop)).orElse(null);
            if (category == null) {
                throw new CategoryNotFoundException("There is no category present with this id");
            }
            if (categoryMetadataField == null) {
                throw new CategoryMetadataFieldNotFoundException("There is no metadata field with this id");
            } else {
                CategoryMetadataFieldValue valueById = categoryMetadataFieldValueRepository.findByFieldIdCategoryCategoryIdAndFieldIdCategoryMetadataFieldCategoryId(category.getCategoryId(), categoryMetadataField.getCategoryMetadataFieldId()).orElse(null);


                if (valueById == null) {
                    throw new CategoryMetadataFieldValueNotFoundException("There is no category metadata field value available in the database");
                }else{
                    List<String> values = new ArrayList<>();
                    for (String s : valueById.getMetadataValues()) {
                        values.add(s);
                    }
                    List<String> newValue = categoryMetadataFieldValueRequestDTO.getMetadatavalues(loop);
                    String addedValues = categoryMetadataFieldValueRequestDTO.convertToString(newValue);
                    values.add(addedValues);
                    valueById.setMetadataValues(values);
                    categoryMetadataFieldValueRepository.save(valueById);
                    loop++;
                }

            }

        }
        return true;
    }

    public List<CategoryResponseDTO> sellerListCategories() {
        List<Long> longList=categoryRepository.fetchLeafCategoryId();
        Iterable<Category> category=categoryRepository.findAllById(longList);
        Type typeList = new TypeToken<List<CategoryResponseDTO>>() {
        }.getType();
        ModelMapper modelMapper = new ModelMapper();
        List<CategoryResponseDTO> categoryDTOList = modelMapper.map(category, typeList);
        return categoryDTOList;
    }

    public List<CustomerCategoryResponseDTO> buyerAllCategories(Long categoryId, Pageable pageable) {

        if(categoryId!=null) {
            Category category = categoryRepository.findById(categoryId).orElse(null);
            if (category == null) {
                throw new CategoryNotFoundException("There is no category found with this id");
            } else {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
                CustomerCategoryResponseDTO customerCategoryDTO = modelMapper.map(category, CustomerCategoryResponseDTO.class);
                return Arrays.asList(customerCategoryDTO);
            }
        }else{
            List<Long> longList=categoryRepository.fetchParentId();
            Iterable<Category> categories=categoryRepository.findAllById(longList);
            Type typeList=new TypeToken<List<CustomerCategoryResponseDTO>>(){}.getType();
            List<CustomerCategoryResponseDTO> customerCategoryDTOS=modelMapper.map(categories,typeList);
            return customerCategoryDTOS;
        }
    }

    public CustomerFilterCategoryResponseDTO buyerFilterCategories(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            throw new CategoryNotFoundException("There is no category with the given id");
        } else {

            CustomerFilterCategoryResponseDTO customerFilterCategoryResponseDTO = modelMapper.map(category, CustomerFilterCategoryResponseDTO.class);
            List<String> brandList = productRepository.fetchBrandList(categoryId);
            Float minPrice=productVariationRepository.getMinprice(categoryId);
            Float maxPrice=productVariationRepository.getMaxprice(categoryId);
            customerFilterCategoryResponseDTO.setMinimumPrice(minPrice);
            customerFilterCategoryResponseDTO.setMaximumPrice(maxPrice);
            customerFilterCategoryResponseDTO.setBrands(brandList);
            List<Object> metadata = productVariationRepository.fetchMetadata();
            System.out.println(metadata);
            return customerFilterCategoryResponseDTO;
        }
    }
}
