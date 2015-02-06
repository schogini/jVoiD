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
 package com.jvoid.persistence.persistenceapi;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

/**
 * Generic DAO class
 * 
 * @author Shajir
 * @version 1.0
 */
public interface GenericDAO<T, ID extends Serializable> {

    /**
     * Find the object through primary key
     * @param id The primary key of the entity
     * @param lock  Choose whether to useLockMode.UPGRADE
     * @return the object if found
     */
    T findById(ID id, boolean lock);

    /**
     * Find all the entities of <T>
     * @return List of entity objects
     */
    List<T> findAll();
    
    /**
     * Find all the entities of <T> by criterion
     * @return List of entity objects
     */
    List<T> findAllByCriteria(Criterion criterion);
    

    /**
     * Given an example, find out the objects of the same type
     * @param exampleInstance example instance
     * @param excludeProperty 
     * @return
     */
    List<T> findByExample(T exampleInstance, String... excludeProperty);

    /**
     * Make object persistent
     * @param entity target object
     * @return persistent object
     */
    T saveOrUpdate(T entity);

    /**
     * Make Object detached from persistance context
     * @param entity the target object
     * @return detached object
     */
    void removeEntity(T entity);

    /**
     * Clear synchronization between persistent context and database
     */
    void flush();

    /**
     * Detach all objects from persistent context
     */
    void clear();
}
