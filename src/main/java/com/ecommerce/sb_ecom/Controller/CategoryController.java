package com.ecommerce.sb_ecom.Controller;

import com.ecommerce.sb_ecom.Model.Category;
import com.ecommerce.sb_ecom.Service.CategoryService;
import com.ecommerce.sb_ecom.payload.CategoryDTO;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


//    @GetMapping("/public/categories")
    @RequestMapping(value = "/public/categories" ,method = RequestMethod.GET)
    public ResponseEntity<CategoryResponse> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        System.out.println(categoryDTO.getCategoryName());
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO),HttpStatus.CREATED);
    }

    @DeleteMapping("admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long categoryId){
//        categoryService.deleteCategory(categoryId);
//        try {
//            return new ResponseEntity<>(categoryService.deleteCategory(categoryId), HttpStatus.OK);
//        }catch (ResponseStatusException e){
////            return new ResponseEntity<>("Resource not found",HttpStatus.NOT_FOUND);
//            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
//        }
        String status = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(status,HttpStatus.OK);
    }

    @PutMapping("public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,
                                                 @PathVariable Long categoryId){
//        try {
//            Category savedCategory = categoryService.updateCategory(category,categoryId);
//            return new ResponseEntity<>("Category with Category Id: " + categoryId + " is updated", HttpStatus.OK);
//        }catch (ResponseStatusException e){
////            return new ResponseEntity<>("Resource not found",HttpStatus.NOT_FOUND);
//            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
//        }
        Category savedCategory = categoryService.updateCategory(category,categoryId);
        return new ResponseEntity<>("Category with Category Id: " + categoryId + " is updated",HttpStatus.OK);
    }
}
