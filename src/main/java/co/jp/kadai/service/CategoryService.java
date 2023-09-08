package co.jp.kadai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import co.jp.kadai.dto.CategoryDto;
import co.jp.kadai.entity.Category;
import co.jp.kadai.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public void save(CategoryDto categorydto) {
		Category category = new Category();
		category.setId(Integer.valueOf(categorydto.getId()));
		category.setName(categorydto.getName());

		try {
			categoryRepository.saveCategory(category);
		} catch (Exception e) {
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}

	}
	
	public List<Category> getCategory() {
		return categoryRepository.getCategory();
	}

	public Category getCategoryWithMaxId() {
		Category category = categoryRepository.getLastCategoryId();
		if (category != null) {
			category.setId(category.getId() + 1);
		} else {
			category = new Category();
			category.setId(1);
		}

		return category;
	}

}
