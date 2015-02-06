/*
 * #%L
 * jVoiD Open Platform
 * %%
 * Copyright 2014-2015 Schogini Systems Pvt Ltd (http://www.schogini.com)
 * Project Website: http://www.jvoid.com
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
 package com.jvoid.products.product.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jvoid.products.product.model.Categories;
import com.jvoid.products.product.model.CategoryEntityValues;
import com.jvoid.products.product.model.CategoryMaster;
import com.jvoid.products.product.model.CategoryProducts;
import com.jvoid.products.product.model.Entities;
import com.jvoid.products.utils.Utilities;


public class CategoryProductsMasterService {
	
	@Autowired
	private EntitiesService entitiesService;
	
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private CategoryProductsService categoryProductsService;
	
	@Autowired
	private CategoryEntityValuesService categoryEntityValuesService;
	
	public void setEntitiesService(EntitiesService entitiesService) {
		this.entitiesService = entitiesService;
	}
	
	public void setCategoriesService(CategoriesService categoriesService) {
		this.categoriesService = categoriesService;
	}
	
	public void setCategoryProductsService(CategoryProductsService categoryProductsService) {
		this.categoryProductsService = categoryProductsService;
	}
	
	public void setCategoryEntityValuesService(CategoryEntityValuesService categoryEntityValuesService) {
		this.categoryEntityValuesService = categoryEntityValuesService;
	}
	
	public int updateCategory(CategoryMaster categoryMaster) {
	
		int categoryId = categoryMaster.getId();
		Categories categoryBeforeUpdate = categoriesService.getCategoriesById(categoryId);
		if ( null != categoryBeforeUpdate ) {
			String categoryName = categoryMaster.getCategoryName();
			Categories category = new Categories();
			category.setId(categoryId);
			category.setParentId(categoryMaster.getParentId());
			category.setLevel(categoryMaster.getLevel());
			category.setPosition(categoryMaster.getPosition());
			category.setCreatedOn(categoryBeforeUpdate.getCreatedOn());
			category.setUpdatedOn(Utilities.getCurrentDateTime());
			category.setPath(categoryBeforeUpdate.getPath());
			this.categoriesService.updateCategories(category);
			updateCategoryName(categoryId, categoryName);
			updateAllChildrenCountAndPath();
		}else{
			categoryId = 0;
		}
		return categoryId;
	}
	
	public void deleteProductFromCategory(int categoryId, int productId) {
		CategoryProducts categoryProduct = this.categoryProductsService.getCategoryProductByCategoryIdAndProductId(categoryId, productId);
		this.categoryProductsService.removeCategoryProducts(categoryProduct.getId());
	}
		
	public int addProductToCategory(int categoryId, int productId) {
		CategoryProducts categoryProduct = new CategoryProducts();
		categoryProduct.setProductId(productId);
		categoryProduct.setCategoryId(categoryId);
		this.categoryProductsService.addCategoryProducts(categoryProduct);
		int categoryProductId = categoryProduct.getId();
		return categoryProductId;
	}

	
	public void updateCategoryName(int catId, String categoryName) {
		int attrId = getAttributeIdByNameAndType("category_name", "Category");
		CategoryEntityValues catAttributeValue = this.categoryEntityValuesService.getCategoryAttributesByCategoryId(catId);
		catAttributeValue.setValue(categoryName);
		this.categoryEntityValuesService.updateCategoryAttributeValues(catAttributeValue);
		
	}
	
	public int addCategory(CategoryMaster categoryMaster){
		int id = 0;
		
		Categories category = new Categories();
		category.setParentId(categoryMaster.getParentId());
		category.setLevel(categoryMaster.getLevel());
		category.setPosition(categoryMaster.getPosition());
		category.setCreatedOn(Utilities.getCurrentDateTime());
		category.setUpdatedOn(Utilities.getCurrentDateTime());
		this.categoriesService.addCategories(category);
		id = category.getId();
		
		int childrenCount = getChildrenCount(id);
		String path = getPath(id);
		//Update Category with path and children count
		category.setId(id);
		category.setChildrenCount(childrenCount);
		category.setPath(path);
		this.categoriesService.updateCategories(category);
		updateAllChildrenCountAndPath();
		if ( insertCategoryName(id, categoryMaster.getCategoryName()) > 0){
			return id;
		}else{
			return 0;
		}
		
	}
	
	public void updateParentCategoryChildrenCount(int cid){
		Categories categories = this.categoriesService.getCategoriesById(cid);
		int parentId = categories.getParentId();
		System.out.println("ParentID:"+parentId);
		int childrenCount = getChildrenCount(parentId);
		
		Categories parentCategory = this.categoriesService.getCategoriesById(parentId);
		parentCategory.setChildrenCount(childrenCount);
		this.categoriesService.updateCategories(parentCategory);
	}
	
	public int insertCategoryName(int catId, String categoryName) {
		int attrId = getAttributeIdByNameAndType("category_name", "Category");
		CategoryEntityValues catAttributeValue = new CategoryEntityValues();
		catAttributeValue.setAttributeId(attrId);
		catAttributeValue.setCategoryId(catId);
		catAttributeValue.setLanguage("enUS");
		catAttributeValue.setValue(categoryName);
		this.categoryEntityValuesService.addCategoryAttributeValues(catAttributeValue);
		int catAttrId = catAttributeValue.getId();
		return catAttrId;
	}
	
	public int getAttributeIdByNameAndType(String catName, String type){
		int attrId = 0;
		Entities attributesList = this.entitiesService.getAttributesByNameAndType(catName, type);
		attrId = attributesList.getId();
		return attrId;
	}
	public String getPath(int id) {
		
		String path = "";
		//System.out.println("PID(getPath):"+id);
		ArrayList<Integer> parentIdList = new ArrayList<>();
		Categories cat = this.categoriesService.getCategoriesById(id);
		parentIdList.add(id);
		int parentId = id;
		while ( (parentId = getParentId(parentId)) > 0 ) {
			if ( parentId > 0 )
				parentIdList.add(parentId);
		}
		Collections.reverse(parentIdList);
		for(int i=0; i<parentIdList.size(); i++){
			path = path + parentIdList.get(i).intValue()+"/";
		}
				
		return path;
	}
	
	public int getParentId(int id){
		int parentId = 0;
		Categories category = this.categoriesService.getCategoriesById(id);
		parentId = category.getParentId();
		return parentId;
	}
	
		
	
	/**
	 * Function to update all childrenCount and Path
	 */
	public void updateAllChildrenCountAndPath() {
		List<Categories> categoryList = this.categoriesService.listCategories();
		for(int i=0; i<categoryList.size(); i++) {
			Categories category = categoryList.get(i);
			int catId = category.getId();
			int childrenCount = getChildrenCount(catId);
			category.setChildrenCount(childrenCount);
			String categoryPath = getPath(catId);
			category.setPath(categoryPath);
			category.setCreatedOn(category.getCreatedOn());
			category.setUpdatedOn(category.getUpdatedOn());
			category.setId(category.getId());
			category.setPosition(category.getPosition());
			category.setLevel(category.getLevel());
			this.categoriesService.updateCategories(category);
		}
	}
	
	public int getChildrenCount(int id) {
		int count = 0;
		List<Categories> categories = this.categoriesService.listCategories();
		for(int i=0; i<categories.size(); i++) {
			if ( categories.get(i).getParentId() == id ) {
				count++;
			}
		}		
		return count;
	}
	
	public CategoryMaster getCategoryById(int id) {
		
		CategoryMaster categoryMaster = new CategoryMaster();
		//List<Categories> categoryList = new ArrayList<>();
		//categoryList = this.categoriesService.listCategories();
		Categories category = this.categoriesService.getCategoriesById(id);
		
		int categoryId = category.getId();
		String createdOn = category.getCreatedOn();
		String updatedOn = category.getUpdatedOn();
		int level = category.getLevel();
		int position = category.getPosition();
		int parentId = category.getParentId();
			
		String categoryName = getCategoryNameById(categoryId);
		
		categoryMaster.setCategoryName(categoryName);
		categoryMaster.setLevel(level);
		categoryMaster.setParentId(parentId);
		categoryMaster.setPosition(position);
		categoryMaster.setParentId(parentId);
		
		return categoryMaster;
	}
	
	public int removeCategory(int categoryId) {
		int status = 1;
		Categories category = this.categoriesService.getCategoriesById(categoryId);
		if ( null != category ) {
			int childrenCount = category.getChildrenCount();
			if ( childrenCount > 0 ) {
				status = 0;
			} else {
				CategoryEntityValues categoryAttrValues = this.categoryEntityValuesService.getCategoryAttributesByCategoryId(categoryId);
				this.categoryEntityValuesService.removeCategoryAttributeValues(categoryAttrValues.getId());
				this.categoriesService.removeCategories(categoryId);
				int parentId = category.getParentId();
				
				if ( parentId > 0 ){
					decreaseChildrenCount(category.getParentId());
				}
			}
		}else{
			status = 0;
		}
		return status;
	}
	
	public void decreaseChildrenCount(int categoryId) {
		Categories category = this.categoriesService.getCategoriesById(categoryId);
		int childrenCount = category.getChildrenCount() - 1;
		category.setChildrenCount(childrenCount);
		this.categoriesService.updateCategories(category);
	}
	
	public void isCategoryParent(int categoryId) {
		int count = getChildrenCount(categoryId);
	}
	
	public List<CategoryMaster> getAllCategories() {
		List<CategoryMaster> categoryMasterList = new ArrayList<>();
		List<Categories> categoryList = new ArrayList<>();
		categoryList = this.categoriesService.listCategories();
		for(int i=0; i<categoryList.size(); i++) {
			Categories category = categoryList.get(i);
			int categoryId = category.getId();
			String createdOn = category.getCreatedOn();
			String updatedOn = category.getUpdatedOn();
			int level = category.getLevel();
			int position = category.getPosition();
			int parentId = category.getParentId();
			System.out.println("CATEGORY ID:"+categoryId);
			String categoryName = getCategoryNameById(categoryId);
			
			CategoryMaster categoryMaster = new CategoryMaster();
			categoryMaster.setId(categoryId);
			categoryMaster.setCategoryName(categoryName);
			categoryMaster.setLevel(level);
			categoryMaster.setParentId(parentId);
			categoryMaster.setPosition(position);
			
			categoryMasterList.add(categoryMaster);
		}
		return categoryMasterList;
	}
	
	public String getCategoryNameById(int id){
		String categoryName = "";
		int attrId = getAttributeIdByNameAndType("category_name", "Category");
		CategoryEntityValues categoryAttributeValues = this.categoryEntityValuesService.getCategoryAttributesByCategoryId(id);		
		categoryName = categoryAttributeValues.getValue();
		return categoryName;
	}

	
}