/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */

package org.egov.wtms.web.controller.dashboard;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.egov.wtms.bean.dashboard.TaxPayerResponseDetails;
import org.egov.wtms.bean.dashboard.WaterChargeDashBoardRequest;
import org.egov.wtms.bean.dashboard.WaterChargeDashBoardResponse;
import org.egov.wtms.service.dashboard.WaterChargeDashboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "/public/dashboard", "/dashboard" })
public class WaterChargeCMDashboardController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WaterChargeCMDashboardController.class);

    @Autowired
    private WaterChargeDashboardService waterChargeDashboardService;

    /**
     * Provides Collection Index details across all ULBs
     *
     * @return response JSON
     * @throws IOException
     */
    @RequestMapping(value = "/waterchargecollectiondashboard", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<WaterChargeDashBoardResponse>> getCollectionDetails(
            @RequestBody final WaterChargeDashBoardRequest collectionDetailsRequest)
            throws IOException {
        final Long startTime = System.currentTimeMillis();
        final Map<String, List<WaterChargeDashBoardResponse>> collectionDetails = waterChargeDashboardService
                .getCollectionIndexDetails(collectionDetailsRequest);
        final Long timeTaken = System.currentTimeMillis() - startTime;
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("Time taken to serve collectiondashboard is (millisecs): " + timeTaken);
        return collectionDetails;
    }

    /**
     * Gives the receipts details across all ULBs
     *
     * @param collectionDetailsRequest
     * @return CollReceiptDetails
     */

    @RequestMapping(value = "/waterchargereceipttransactions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<WaterChargeDashBoardResponse>> getReceiptTransactions(
            @RequestBody final WaterChargeDashBoardRequest collectionDetailsRequest) throws IOException {
        final Long startTime = System.currentTimeMillis();
        final Map<String, List<WaterChargeDashBoardResponse>> collReceiptDetails = waterChargeDashboardService
                .getReceiptDetails(collectionDetailsRequest);
        final Long timeTaken = System.currentTimeMillis() -
                startTime;
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("Time taken to serve receipttransactions is  (millisecs): " + timeTaken);
        return collReceiptDetails;

    }

    /**
     * Returns Top Ten Tax Performers Across all ULB's
     *
     * @param collDetailsRequestStr
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/waterchargetoptentaxers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaxPayerResponseDetails getTopTenTaxProducers(@RequestBody final WaterChargeDashBoardRequest collectionDetailsRequest)
            throws IOException {
        final Long startTime = System.currentTimeMillis();
        final TaxPayerResponseDetails taxPayerDetails = waterChargeDashboardService
                .getTopTenTaxProducers(collectionDetailsRequest);
        final Long timeTaken = System.currentTimeMillis() - startTime;
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("Time taken to serve toptentaxers is :  (millisecs)" + timeTaken);
        return taxPayerDetails;
    }

    @RequestMapping(value = "/waterchargebottomtentaxers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaxPayerResponseDetails getBottomTenTaxProducers(
            @RequestBody final WaterChargeDashBoardRequest waterChargeDashBoardRequest) throws IOException {
        final Long startTime = System.currentTimeMillis();
        LOGGER.debug("CollectionDetailsRequest input : regionName = " + waterChargeDashBoardRequest.getRegionName()
                + ", districtName = " + waterChargeDashBoardRequest.getDistrictName() + ", ulbGrade = "
                + waterChargeDashBoardRequest.getUlbGrade() + ", ulbCode = " + waterChargeDashBoardRequest.getUlbCode()
                + ", fromDate = " + waterChargeDashBoardRequest.getFromDate() + ", toDate = "
                + waterChargeDashBoardRequest.getToDate() + ", type = " + waterChargeDashBoardRequest.getType());
        final TaxPayerResponseDetails taxPayerDetails = waterChargeDashboardService
                .getBottomTenTaxProducers(waterChargeDashBoardRequest);
        final Long timeTaken = System.currentTimeMillis() - startTime;
        LOGGER.debug("Time taken to serve bottomtentaxers is : " + timeTaken + " (millisecs)");
        return taxPayerDetails;
    }

}