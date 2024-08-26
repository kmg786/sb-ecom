package com.ecommerce.sb_ecom.repositories;

import com.ecommerce.sb_ecom.Model.Category;
import com.ecommerce.sb_ecom.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(String categoryName);

}
