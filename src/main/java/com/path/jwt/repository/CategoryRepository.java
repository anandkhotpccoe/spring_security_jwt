/**
 * 
 */
package com.path.jwt.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.path.jwt.entity.Category;


/**
 * @author anand
 *
 */
public interface CategoryRepository extends CrudRepository<Category, Long>{
	
	
	List<Category> findByName(String name);

}
