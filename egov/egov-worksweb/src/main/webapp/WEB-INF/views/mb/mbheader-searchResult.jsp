<%--
  ~ eGov suite of products aim to improve the internal efficiency,transparency,
  ~    accountability and the service delivery of the government  organizations.
  ~
  ~     Copyright (C) <2015>  eGovernments Foundation
  ~
  ~     The updated version of eGov suite of products as by eGovernments Foundation
  ~     is available at http://www.egovernments.org
  ~
  ~     This program is free software: you can redistribute it and/or modify
  ~     it under the terms of the GNU General Public License as published by
  ~     the Free Software Foundation, either version 3 of the License, or
  ~     any later version.
  ~
  ~     This program is distributed in the hope that it will be useful,
  ~     but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~     GNU General Public License for more details.
  ~
  ~     You should have received a copy of the GNU General Public License
  ~     along with this program. If not, see http://www.gnu.org/licenses/ or
  ~     http://www.gnu.org/licenses/gpl.html .
  ~
  ~     In addition to the terms of the GPL license to be adhered to in using this
  ~     program, the following additional terms are to be complied with:
  ~
  ~         1) All versions of this program, verbatim or modified must carry this
  ~            Legal Notice.
  ~
  ~         2) Any misrepresentation of the origin of the material is prohibited. It
  ~            is required that all modified versions of this material be marked in
  ~            reasonable ways as different from the original version.
  ~
  ~         3) This license does not grant any rights to any user of the program
  ~            with regards to rights under trademark law for use of the trade names
  ~            or trademarks of eGovernments Foundation.
  ~
  ~   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
  --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="display-hide report-section">
	<div class="table-header text-left">
		<spring:message code="title.mbheader.search" />
	</div>
	<div class="form-group report-table-container">
		<table class="table table-bordered table-hover" id="resultTable">
			<thead>
				<tr id="mbheaderRow">
					<th><spring:message code="lbl.slno" /></th>
					<th><spring:message code="lbl.estimatenumber" /></th>
					<th><spring:message code="lbl.loanumber" /></th>
					<th class="departmentcolumn"><spring:message code="lbl.department" /></th>
					<th><spring:message code="lbl.contractor.name" /></th>
					<th><spring:message code="lbl.agreement.amount" /></th> 
					<th><spring:message code="lbl.mb.referencenumberdate" /></th>
					<th><spring:message code="lbl.mb.pagenumber" /></th>
					<th><spring:message code="lbl.mbamount" /></th> 
					<th class="statuscolumn"><spring:message code="lbl.status" /></th>
					<th class="createdbycolumn"><spring:message code="lbl.createdby" /></th>
					<th><spring:message code="lbl.currentowner" /></th>
					<th><spring:message code="lbl.view" /></th>
				</tr>
			</thead>
		</table>
	</div>
</div>