package com.ecommerce.sb_ecom.Service;

import com.ecommerce.sb_ecom.Model.Category;
import com.ecommerce.sb_ecom.exceptions.APIException;
import com.ecommerce.sb_ecom.exceptions.ResourceNotFoundException;
import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import com.ecommerce.sb_ecom.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryId);

        Category savedCategory = savedCategoryOptional
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;




//        List<Category> categories = categoryRepository.findAll();
//        Optional<Category> optionalCategory = categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryId))
//                        .findFirst();
//        if (optionalCategory.isPresent()){
//            Category existingCategory = optionalCategory.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//            Category savedcategory = categoryRepository.save(existingCategory);
//            return savedcategory;
//        }else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Id Not Found");
//        }
    }

    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()){
            throw new APIException("No category added yet!!");
        }
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category,CategoryDTO.class))
                .toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
//        category.setCategoryId(Id++);
//        Mapping DTO to Category
        Category category = modelMapper.map(categoryDTO,Category.class);
        Category categoryFromDB = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryFromDB != null)
            throw new APIException("Category with name " + category.getCategoryName() + " already exists!!!");
        Category savedCategory = categoryRepository.save(category);
        savedCategory.setCategoryName(categoryDTO.getCategoryName());
        return modelMapper.map(savedCategory,CategoryDTO.class);

    }

    @Override
    public String deleteCategory(Long categoryId) {

        Optional<Category> deletedCategoryOptional = categoryRepository.findById(categoryId);

        Category category = deletedCategoryOptional
                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
//                        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));


        categoryRepository.delete(category);
        return "Category with Category Id : " + categoryId + " deleted successfully";
    }
//        List<Category> categories = categoryRepository.findAll();
//        Category category = categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst()
//                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));
//        categoryRepository.delete(category);
//        return "Category with categoryId: " + categoryId + " deleted successfully";
//        }
    }

