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
package org.egov.wtms.web.controller.application;

import org.egov.infra.reporting.engine.ReportOutput;
import org.egov.wtms.application.entity.WaterConnectionDetails;
import org.egov.wtms.application.service.ReportGenerationService;
import org.egov.wtms.application.service.WaterConnectionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/application")
public class ReconnectionAckController {

    public static final String ESTIMATION_NOTICE = "ReconnacknowlgementNotice";

    @Autowired
    private WaterConnectionDetailsService waterConnectionDetailsService;
    
    @Autowired
    private ReportGenerationService reportGenerationService;

    @RequestMapping(value = "/ReconnacknowlgementNotice", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<byte[]> generateEstimationNotice(final HttpServletRequest request,
            final HttpSession session) {
        final WaterConnectionDetails waterConnectionDetails = waterConnectionDetailsService
                .findByApplicationNumber(request.getParameter("pathVar"));
        String cityMaunicipalityName = (String)session.getAttribute("citymunicipalityname");
        String districtName = (String)session.getAttribute("districtName");
        final ReportOutput reportOutput = reportGenerationService.generateReconnectionReport(waterConnectionDetails, null, cityMaunicipalityName, districtName);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("content-disposition", "inline;filename=Re-ConnectionAcknowledgement.pdf");
        return new ResponseEntity<byte[]>(reportOutput.getReportOutputData(), headers, HttpStatus.CREATED); 
    }

    @RequestMapping(value = "/ReconnacknowlgementNotice/view/{applicationNumber}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<byte[]> viewEstimationNotice(@PathVariable final String applicationNumber,
            final HttpSession session) {
        final WaterConnectionDetails waterConnectionDetails = waterConnectionDetailsService
                .findByApplicationNumber(applicationNumber);
        String cityMaunicipalityName = (String)session.getAttribute("citymunicipalityname");
        String districtName = (String)session.getAttribute("districtName");
        final ReportOutput reportOutput = reportGenerationService.generateReconnectionReport(waterConnectionDetails, null, cityMaunicipalityName, districtName);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("content-disposition", "inline;filename=Re-ConnectionAcknowledgement.pdf");
        return new ResponseEntity<byte[]>(reportOutput.getReportOutputData(), headers, HttpStatus.CREATED); 
    }
}
