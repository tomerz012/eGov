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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="row">
	<div class="col-md-12">
		<form:form id="unitOfMeasureform" method="post" class="form-horizontal form-groups-bordered" modelAttribute="unitOfMeasure" commandName="unitOfMeasure">
			<div class="panel panel-primary" data-collapsed="0">
				<div class="panel-heading ">
					<div class="panel-title">
						<strong><spring:message code="title.uom.details"/></strong>
					</div>
				</div>
				<div class="panel-body custom-form"> 										
					<div class="form-group"> 
                    	  
                           		<c:choose>
									<c:when test="${empty unitOfMeasure.id}">
									<label class="col-sm-3 control-label text-right"><spring:message code="lbl.uom.code"/><span class="mandatory"></span></label>
                            		 <div class="col-sm-3 add-margin">
                        				<form:input type="text" cssClass="form-control patternvalidation" 
                        	  					data-pattern="alphanumericwithspacehyphenunderscore"  maxlength="25" path="code" id="unitofmeasurecode" required="required"/>
                               			<form:errors path="code" cssClass="error-msg" />
                              		 </div>
                        			<label class="col-sm-3 control-label text-right"><spring:message code="lbl.uom.name"/><span class="mandatory"></span></label>
                               			<div class="col-sm-3 add-margin">
                               			
                               			<form:input type="text" cssClass="form-control patternvalidation" 
                        	      			data-pattern="alphanumericwithspacehyphenunderscore" maxlength="49" path="description" id="classdesc" required="required"/>
                               				<form:errors path="description" cssClass="error-msg" />
                               			
                               			<form:input type="hidden" cssClass="form-control" path="id" />
                               			</div>
                               		</c:when>
                               		<c:otherwise>
                        		<label class="col-sm-3 control-label text-right"><spring:message code="lbl.uom.code"/><span class="mandatory"></span></label>
                               			 <div class="col-sm-3 add-margin">
                               			<form:input type="hidden" cssClass="form-control" path="id" />
                               			<form:input type="text" cssClass="form-control patternvalidation" 
                        	  					data-pattern="alphanumericwithspacehyphenunderscore"  maxlength="25" path="code" id="unitofmeasurecode" readonly="true" required="required"/>
                               			<form:errors path="code" cssClass="error-msg" />
                        				</div>
                        <label class="col-sm-3 control-label text-right"><spring:message code="lbl.uom.name"/><span class="mandatory"></span></label>
                               			<div class="col-sm-3 add-margin">
                               			<form:input type="text" cssClass="form-control" path="description" id="classdesc" required="required" readonly="true" />
                               			<form:errors path="description" cssClass="error-msg" />
                           				</div>
                               		</c:otherwise>
                               	</c:choose>
                        </div>    
                  	<div class="form-group"> 
                        <label class="col-sm-3 control-label text-right"><spring:message code="lbl.uom.active"/><span class="mandatory"></span></label>
                        	<div class="col-sm-1 add-margin">
								<form:checkbox path="active" id="classactive" />
								<form:errors path="active" cssClass="error-msg" />
							</div>
                    </div>
           		</div>
        	</div>
        	<div class="row">
				<div class="text-center">
					<button type="submit" class="btn btn-primary"><spring:message code="lbl.submit"/></button>
					<c:if test="${empty unitOfMeasure.id}">
						<button type="reset" class="btn btn-default"><spring:message code="lbl.reset"/></button>
					</c:if>
			        <a href="javascript:void(0)" class="btn btn-default" onclick="self.close()"><spring:message code="lbl.close"/></a>
				</div>
			</div>
		</form:form>
	</div>	
</div>
				
	


