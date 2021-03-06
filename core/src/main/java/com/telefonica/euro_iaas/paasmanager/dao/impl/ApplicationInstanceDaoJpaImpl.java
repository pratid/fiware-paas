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

package com.telefonica.euro_iaas.paasmanager.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.telefonica.euro_iaas.commons.dao.AbstractBaseDao;
import com.telefonica.euro_iaas.commons.dao.EntityNotFoundException;
import com.telefonica.euro_iaas.paasmanager.dao.ApplicationInstanceDao;
import com.telefonica.euro_iaas.paasmanager.model.ApplicationInstance;
import com.telefonica.euro_iaas.paasmanager.model.ApplicationRelease;
import com.telefonica.euro_iaas.paasmanager.model.InstallableInstance.Status;
import com.telefonica.euro_iaas.paasmanager.model.searchcriteria.ApplicationInstanceSearchCriteria;

@Transactional(propagation = Propagation.REQUIRED)
public class ApplicationInstanceDaoJpaImpl extends AbstractBaseDao<ApplicationInstance, String> implements
        ApplicationInstanceDao {

    public List<ApplicationInstance> findAll() {
        return super.findAll(ApplicationInstance.class);
    }

    public ApplicationInstance load(String arg0) throws EntityNotFoundException {
        return super.loadByField(ApplicationInstance.class, "name", arg0);
    }

    public List<ApplicationInstance> findByCriteria(ApplicationInstanceSearchCriteria criteria) {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria baseCriteria = session.createCriteria(ApplicationInstance.class);

        if (criteria.getStatus() != null && !criteria.getStatus().isEmpty()) {
            Criterion statusCr = null;
            for (Status status : criteria.getStatus()) {
                statusCr = addStatus(statusCr, status);
            }
            baseCriteria.add(statusCr);
        }

        if (!StringUtils.isEmpty(criteria.getVdc())) {
            baseCriteria.add(Restrictions.eq(ApplicationInstance.VDC_FIELD, criteria.getVdc()));
        }
        /*
         * if (!StringUtils.isEmpty(criteria.getEnvironmentInstance())) { baseCriteria
         * .add(Restrictions.eq(ApplicationInstance.ENVIRONMENT_INSTANCE_FIELD, criteria.getEnvironmentInstance())); }
         */

        List<ApplicationInstance> applicationInstances = setOptionalPagination(criteria, baseCriteria).list();

        // TODO sarroyo: try to do this filter using hibernate criteria.
        if (criteria.getApplicatonRelease() != null) {
            applicationInstances = filterByApplicationRelease(applicationInstances, criteria.getApplicatonRelease());
        }

        applicationInstances = filterByVDCandEnvironmentInstance(applicationInstances, criteria.getVdc(),
                criteria.getEnvironmentInstance());

        return applicationInstances;
    }

    /**
     * Filter the result by product instance
     * 
     * @param applications
     * @param product
     * @return
     */
    private List<ApplicationInstance> filterByApplicationRelease(List<ApplicationInstance> applicationInstances,
            ApplicationRelease applicationRelease) {
        List<ApplicationInstance> result = new ArrayList<ApplicationInstance>();
        for (ApplicationInstance applicationInstance : applicationInstances) {
            if (applicationInstance.getApplicationRelease().getId().equals(applicationRelease.getId())) {
                result.add(applicationInstance);
            }
        }
        return result;
    }

    /**
     * Filter the result by product instance
     * 
     * @param applications
     * @param product
     * @return
     */
    private List<ApplicationInstance> filterByVDCandEnvironmentInstance(List<ApplicationInstance> applicationInstances,
            String vdc, String environmentInstanceName) {
        List<ApplicationInstance> result = new ArrayList<ApplicationInstance>();
        for (ApplicationInstance applicationInstance : applicationInstances) {
            if (applicationInstance.getEnvironmentInstance().getName().equals(environmentInstanceName)
                    && applicationInstance.getVdc().equals(vdc)) {
                result.add(applicationInstance);
            }
        }
        return result;
    }

    private Criterion addStatus(Criterion statusCr, Status status) {
        SimpleExpression expression = Restrictions.eq("status", status);
        if (statusCr == null) {
            statusCr = expression;
        } else {
            statusCr = Restrictions.or(statusCr, expression);
        }
        return statusCr;
    }

}
