/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.service.impl;

import com.moneyhelper.model.Category;
import com.moneyhelper.repository.CategoryRepository;
import com.moneyhelper.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category applyCategory(final String categoryName) {
        return categoryRepository.findByNameIgnoreCase(categoryName)
                .orElse(categoryRepository.save(new Category(categoryName)));
    }

}
