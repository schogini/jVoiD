/*
 * #%L
 * jVoiD Open Platform
 * %%
 * Copyright (C) 2014 - 2015 jVoiD
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
 package com.jvoid.customers.customer.service;

import java.util.List;

import com.jvoid.customers.customer.model.Entities;

/**
 * Service class for Entities 
 * 
 * @author Shajir K, Rajeev Pillai
 * @version 1.0
 */

public interface EntitiesService {

	public void addAttributes(Entities p);
	public void updateAttributes(Entities p);
	public List<Entities> listAttributes();
	public Entities getAttributesById(int id);
	public void removeAttributes(int id);
	public List<Entities> getAttributesByType(String type);
}
