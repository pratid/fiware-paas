/**
 * Copyright 2014 Telefonica Investigación y Desarrollo, S.A.U <br>
 * This file is part of FI-WARE project.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License.
 * </p>
 * <p>
 * You may obtain a copy of the License at:<br>
 * <br>
 * http://www.apache.org/licenses/LICENSE-2.0
 * </p>
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * </p>
 * <p>
 * See the License for the specific language governing permissions and limitations under the License.
 * </p>
 * <p>
 * For those usages not covered by the Apache version 2.0 License please contact with opensource@tid.es
 * </p>
 */

package com.telefonica.euro_iaas.paasmanager.manager;

import java.util.List;

import com.telefonica.euro_iaas.commons.dao.EntityNotFoundException;
import com.telefonica.euro_iaas.commons.dao.InvalidEntityException;
import com.telefonica.euro_iaas.paasmanager.exception.InfrastructureException;
import com.telefonica.euro_iaas.paasmanager.exception.InvalidEnvironmentRequestException;
import com.telefonica.euro_iaas.paasmanager.model.ClaudiaData;
import com.telefonica.euro_iaas.paasmanager.model.Environment;
import com.telefonica.euro_iaas.paasmanager.model.Tier;
import com.telefonica.euro_iaas.paasmanager.model.searchcriteria.EnvironmentSearchCriteria;

public interface EnvironmentManager {

    /**
     * Create an environent
     * 
     * @param name
     * @param tiers
     * @param environmentType
     * @return the of installed product.
     */
    Environment create(ClaudiaData claudiaData, Environment environment) throws InvalidEnvironmentRequestException;

    /**
     * Updates the tier
     * 
     * @param environment
     * @param tierold
     * @param tiernew
     * @return
     * @throws EntityNotFoundException
     * @throws InvalidEntityException
     */
    Environment updateTier(Environment environment, Tier tierold, Tier tiernew) throws EntityNotFoundException,
            InvalidEntityException;

    /**
     * Destroy a previously creted environment.
     * 
     * @param environment
     *            the candidate to environment
     */
    void destroy(ClaudiaData data, Environment environment) throws InvalidEntityException, InfrastructureException, EntityNotFoundException;

    /**
     * Find the Environment using the given name.
     * 
     * @param name
     *            the name
     * @param vdc
     *            the vdc
     * @return the environment
     * @throws EntityNotFoundException
     *             if the product instance does not exists
     */
    Environment load(String name, String vdc) throws EntityNotFoundException;

    /**
     * Find the Environment using the given name.
     * 
     * @param name
     *            the name
     * @return the environment
     * @throws EntityNotFoundException
     *             if the product instance does not exists
     */
    Environment load(String name) throws EntityNotFoundException;

    /**
     * Updates the environment
     * 
     * @param name
     * @return
     * @throws EntityNotFoundException
     * @throws InvalidEntityException
     */
    Environment update(Environment name) throws EntityNotFoundException, InvalidEntityException;

    /**
     * Retrieve all Environment created in the system.
     * 
     * @return the existent environments.
     */
    List<Environment> findAll();

    /**
     * Find the environments that match with the given criteria.
     * 
     * @param criteria
     *            the search criteria
     * @return the list of elements that match with the criteria.
     */
    List<Environment> findByCriteria(EnvironmentSearchCriteria criteria);

}
