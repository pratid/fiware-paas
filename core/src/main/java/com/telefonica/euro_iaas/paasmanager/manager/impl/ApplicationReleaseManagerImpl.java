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

package com.telefonica.euro_iaas.paasmanager.manager.impl;

import java.util.List;

import com.telefonica.euro_iaas.commons.dao.EntityNotFoundException;
import com.telefonica.euro_iaas.paasmanager.dao.ApplicationReleaseDao;
import com.telefonica.euro_iaas.paasmanager.manager.ApplicationReleaseManager;
import com.telefonica.euro_iaas.paasmanager.model.ApplicationRelease;
import com.telefonica.euro_iaas.paasmanager.model.searchcriteria.ApplicationReleaseSearchCriteria;

/**
 * @author jesus.movilla
 */
public class ApplicationReleaseManagerImpl implements ApplicationReleaseManager {

    private ApplicationReleaseDao applicationReleaseDao;

    /*
     * (non-Javadoc)
     * @see com.telefonica.euro_iaas.paasmanager.manager.ApplicationReleaseManager #load(java.lang.String)
     */
    public ApplicationRelease load(String name) throws EntityNotFoundException {
        return applicationReleaseDao.load(name);
    }

    /*
     * (non-Javadoc)
     * @see com.telefonica.euro_iaas.paasmanager.manager.ApplicationReleaseManager #findAll()
     */
    public List<ApplicationRelease> findAll() {
        return applicationReleaseDao.findAll();
    }

    public List<ApplicationRelease> findByCriteria(ApplicationReleaseSearchCriteria criteria) {
        return applicationReleaseDao.findByCriteria(criteria);
    }

    /**
     * @param productDao
     *            the applicationDao to set
     */
    public void setApplicationReleaseDao(ApplicationReleaseDao applicationReleaseDao) {
        this.applicationReleaseDao = applicationReleaseDao;
    }

}
