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

package com.telefonica.euro_iaas.paasmanager.installator;

import java.util.List;
import java.util.Set;

import com.telefonica.euro_iaas.commons.dao.EntityNotFoundException;
import com.telefonica.euro_iaas.paasmanager.exception.ProductInstallatorException;
import com.telefonica.euro_iaas.paasmanager.installator.sdc.util.SDCUtil;
import com.telefonica.euro_iaas.paasmanager.model.Artifact;
import com.telefonica.euro_iaas.paasmanager.model.Attribute;
import com.telefonica.euro_iaas.paasmanager.model.ClaudiaData;
import com.telefonica.euro_iaas.paasmanager.model.InstallableInstance.Status;
import com.telefonica.euro_iaas.paasmanager.model.ProductInstance;
import com.telefonica.euro_iaas.paasmanager.model.ProductRelease;
import com.telefonica.euro_iaas.paasmanager.model.TierInstance;
import com.telefonica.euro_iaas.paasmanager.util.SystemPropertiesProvider;
import com.telefonica.euro_iaas.sdc.client.SDCClient;
import com.telefonica.euro_iaas.sdc.model.dto.ChefClient;

public class ProductInstallatorDummySdcImpl implements ProductInstallator {

    private SDCClient sDCClient;
    private SystemPropertiesProvider systemPropertiesProvider;
    private SDCUtil sDCUtil;

    public void installArtifact(ProductInstance productInstance, Artifact artifact) throws ProductInstallatorException {
        // TODO Auto-generated method stub

    }

    public void uninstall(ProductInstance productInstance) throws ProductInstallatorException {
        // TODO Auto-generated method stub

    }

    // //////////// I.O.C /////////////
    /**
     * @param sDCClient
     *            the sDCClient to set
     */
    public void setSDCClient(SDCClient sDCClient) {
        this.sDCClient = sDCClient;
    }

    /**
     * @param systemPropertiesProvider
     *            the systemPropertiesProvider to set
     */
    public void setSystemPropertiesProvider(SystemPropertiesProvider systemPropertiesProvider) {
        this.systemPropertiesProvider = systemPropertiesProvider;
    }

    public void setSDCUtil(SDCUtil sDCUtil) {
        this.sDCUtil = sDCUtil;
    }

    public void uninstallArtifact(ProductInstance productInstance, Artifact artifact)
            throws ProductInstallatorException {
        // TODO Auto-generated method stub

    }

    public ProductInstance install(ClaudiaData claudiaData, String envName, TierInstance tierInstance,
            ProductRelease productRelease, Set<Attribute> attributes) throws ProductInstallatorException {

        ProductInstance productInstance = new ProductInstance();
        productInstance.setStatus(Status.INSTALLED);
        productInstance.setName(tierInstance.getName() + "_" + productRelease.getProduct() + "_"
                + productRelease.getVersion());
        productInstance.setProductRelease(productRelease);
        // productInstance.setTierInstance(tierInstance);
        productInstance.setVdc(tierInstance.getVdc());
        return productInstance;

    }

    public void configure(ClaudiaData data, ProductInstance productInstance, List<Attribute> properties) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @seecom.telefonica.euro_iaas.paasmanager.installator.ProductInstallator# deleteNode(java.lang.String,
     * java.lang.String)
     */
    public void deleteNode(String vdc, String nodeName) throws ProductInstallatorException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * @see com.telefonica.euro_iaas.paasmanager.installator.ProductInstallator#loadNode(java.lang.String,
     * java.lang.String)
     */
    public ChefClient loadNode(String vdc, String nodeName) throws ProductInstallatorException, EntityNotFoundException {

        return null;
    }

}
