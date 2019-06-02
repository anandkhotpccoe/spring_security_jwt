package com.path.jwt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.path.jwt.controller.utils.Response;
import com.path.jwt.entity.Category;
import com.path.jwt.exception.CategoryDeleteException;
import com.path.jwt.repository.CategoryRepository;
import com.path.jwt.service.CategoryService;


@RestController
public class CategoryController {
	
	
	
	@Autowired
	private CategoryService categoryService;
	
	
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@GetMapping("/category")
	public ResponseEntity<Response> getAll()
	{
		System.out.print(categoryService.getAll());
		Iterable<Category> catergories = categoryService.getAll();
		System.out.print(catergories);
		return new ResponseEntity<>(Response.success(catergories), HttpStatus.OK);
	}
	
	
	@GetMapping("/category/{id}")
	public ResponseEntity<Response> get(@PathVariable Long id)
	{
		Category result = categoryService.get(id);
		return new ResponseEntity<Response>(Response.success(result),HttpStatus.OK);
	}
	
	
	
	@GetMapping("/category/search/{name}")
	public ResponseEntity<Response> get(@PathVariable String name)
	{
List<Category> result = categoryRepository.findByName(name);
		return new ResponseEntity<Response>(Response.success(result),HttpStatus.OK);
	}
	
	
	@PostMapping("/category")
	public ResponseEntity<Response> create(@RequestBody Category category)
	{
		Category result = categoryService.add(category);
		return new ResponseEntity<Response>(Response.success(result),HttpStatus.OK);
	}
	
	
	@PutMapping("/category")
	public ResponseEntity<Response> update(@RequestBody Category category)
	{
		Category result = categoryService.update(category);
		return new ResponseEntity<Response>(Response.success(result),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/category/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id)
	{
		
		
		Optional<Category> categoryOptional = categoryRepository.findById(id);
		if (categoryOptional.isPresent()) {
			categoryService.delete(id);
			return new ResponseEntity<>(Response.success(), HttpStatus.OK);
			
		} else {
			//Cutomized Error Handling
			throw new CategoryDeleteException("Can not Perform Fetch on id: " + id);
		}
		
	}
	
	
	
	@GetMapping("/categoryview")
	public ModelAndView getAllCategory()
	{
		System.out.print(categoryService.getAll());
		Iterable<Category> catergories = categoryService.getAll();
		List<Category> list = new ArrayList<>();
		for(Category category : catergories)
		{
			list.add(category);
		}
		System.out.println(list);
		System.out.print(catergories);
		return new ModelAndView("viewcategories","list",list);
	}
	
	
	

}
