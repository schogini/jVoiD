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

import java.util.List;

import com.jvoid.products.product.model.AttributeSet;


public interface AttributeSetService {

	public void addAttributeSet(AttributeSet p);
	public void updateAttributeSet(AttributeSet p);
	public List<AttributeSet> listAttributeSets();
	public AttributeSet getAttributeSetById(int id);
	public void removeAttributeSet(int id);
	
}
