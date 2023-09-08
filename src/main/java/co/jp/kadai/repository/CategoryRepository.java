package co.jp.kadai.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import co.jp.kadai.entity.Category;

@Mapper
public interface CategoryRepository {

	void saveCategory(Category category);

	List<Category> getCategory();

	Category getLastCategoryId();

}
