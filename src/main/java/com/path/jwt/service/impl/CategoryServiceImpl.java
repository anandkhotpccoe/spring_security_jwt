package com.path.jwt.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.path.jwt.entity.Category;
import com.path.jwt.exception.CategoryFetchException;
import com.path.jwt.repository.CategoryRepository;
import com.path.jwt.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Iterable<Category> getAll()
	{
		Iterable<Category> result = categoryRepository.findAll();
        return result;
	}
	
	
	public Category add(Category category)
	{
		return categoryRepository.save(category);
	}
	
	
	public Category update(Category category)
	{
		return categoryRepository.save(category);
	}
	
	public Category get(Long id)
	{
		
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			return category.get();
			
		}
		else {
			throw new CategoryFetchException("Problem in Fetching Category ID: " + id);
		}
		
	}
	
	public void delete(Long id)
	{
		categoryRepository.deleteById(id);
	}
	
	

}
