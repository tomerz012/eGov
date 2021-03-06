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

package org.egov.pgr.web.controller.reports;

import org.apache.commons.io.IOUtils;
import org.egov.pgr.service.reports.ComplaintTypeWiseReportService;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.egov.infra.utils.JsonUtils.toJSON;

@Controller
@RequestMapping("/report")
public class ComplaintTypeWiseReportController {

    @Autowired
    private ComplaintTypeWiseReportService complaintTypeReportService;

    @ModelAttribute
    public void getReportHelper(Model model) {
        model.addAttribute("reportHelper", new ReportHelper());
    }

    @GetMapping("complaintTypeReport")
    public String searchAgeingReportByBoundaryForm() {
        return "complaintTypeReport-search";
    }

    @GetMapping("complaintTypeReport/resultList-update")
    @ResponseBody
    public void springPaginationDataTablesUpdate(@RequestParam String complaintType,
                                                 @RequestParam String complaintTypeWithStatus, @RequestParam String status,
                                                 @RequestParam String complaintDateType, @RequestParam DateTime fromDate,
                                                 @RequestParam DateTime toDate, HttpServletResponse response)
            throws IOException {
        SQLQuery complaintTypeReportQuery;
        List<DrillDownReportResult> complaintTypeReportResult;
        String result;
        if (complaintTypeWithStatus != null && status != null && !"".equals(complaintTypeWithStatus)
                && !"".equals(status)) {

            complaintTypeReportQuery = complaintTypeReportService.getComplaintTypeWiseReportQuery(fromDate,
                    toDate, complaintDateType, complaintTypeWithStatus, status);
            complaintTypeReportQuery.setResultTransformer(Transformers.aliasToBean(DrillDownReportResult.class));
            complaintTypeReportResult = complaintTypeReportQuery.list();
            result = new StringBuilder("{ \"data\":").append(toJSON(complaintTypeReportResult, DrillDownReportResult.class,
                    DrillDownReportWithcompTypeAdaptor.class)).append("}")
                    .toString();

        } else {
            complaintTypeReportQuery = complaintTypeReportService.getComplaintTypeWiseReportQuery(fromDate,
                    toDate, complaintType, complaintDateType);
            complaintTypeReportQuery.setResultTransformer(Transformers.aliasToBean(DrillDownReportResult.class));
            complaintTypeReportResult = complaintTypeReportQuery.list();
            result = new StringBuilder("{ \"data\":").append(toJSON(complaintTypeReportResult, DrillDownReportResult.class,
                    DrillDownReportHelperAdaptor.class)).append("}")
                    .toString();
        }

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        IOUtils.write(result, response.getWriter());

    }
}
