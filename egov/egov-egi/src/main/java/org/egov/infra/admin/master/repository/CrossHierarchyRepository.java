/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 * accountability and the service delivery of the government  organizations.
 *
 *  Copyright (C) 2016  eGovernments Foundation
 *
 *  The updated version of eGov suite of products as by eGovernments Foundation
 *  is available at http://www.egovernments.org
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see http://www.gnu.org/licenses/ or
 *  http://www.gnu.org/licenses/gpl.html .
 *
 *  In addition to the terms of the GPL license to be adhered to in using this
 *  program, the following additional terms are to be complied with:
 *
 *      1) All versions of this program, verbatim or modified must carry this
 *         Legal Notice.
 *
 *      2) Any misrepresentation of the origin of the material is prohibited. It
 *         is required that all modified versions of this material be marked in
 *         reasonable ways as different from the original version.
 *
 *      3) This license does not grant any rights to any user of the program
 *         with regards to rights under trademark law for use of the trade names
 *         or trademarks of eGovernments Foundation.
 *
 *  In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */

package org.egov.infra.admin.master.repository;

import org.egov.infra.admin.master.entity.Boundary;
import org.egov.infra.admin.master.entity.BoundaryType;
import org.egov.infra.admin.master.entity.CrossHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrossHierarchyRepository extends JpaRepository<CrossHierarchy, Long> {

    List<Boundary> findByParentAndChildBoundaryType(Boundary boundary, BoundaryType boundaryType);

    @Query("select ch from CrossHierarchy ch where UPPER(ch.childType.name)= UPPER(:boundaryTypeName)"
            + " and UPPER(ch.childType.hierarchyType.name) =UPPER(:hierarchyTypeName) and "
            + " UPPER(ch.parentType.hierarchyType.name) = UPPER(:parenthierarchyTypeName) and"
            + " UPPER(ch.child.name) like UPPER(:name) order by ch.child.name")
    List<CrossHierarchy> findActiveBoundariesByNameAndBndryTypeNameAndHierarchyTypeName(
            @Param("boundaryTypeName") String boundaryTypeName, @Param("hierarchyTypeName") String hierarchyTypeName,
            @Param("parenthierarchyTypeName") String parenthierarchyTypeName, @Param("name") String name);

    @Query("select ch.child from CrossHierarchy ch where UPPER(ch.childType.name)= UPPER(:boundaryTypeName) and UPPER(ch.childType.hierarchyType.name) =UPPER(:hierarchyTypeName)")
    List<Boundary> findChildBoundariesNameAndBndryTypeAndHierarchyType(
            @Param("boundaryTypeName") String boundaryTypeName, @Param("hierarchyTypeName") String hierarchyTypeName);

    @Query("select ch.child from CrossHierarchy ch where ch.parent.id= :id")
    List<Boundary> findActiveBoundariesById(@Param("id") Long id);

    @Query("select ch.parent from CrossHierarchy ch where ch.child.id= :childId and ch.parentType.id=:parentTypeId")
    List<Boundary> findParentBoundaryByChildBoundaryAndParentBoundaryType(@Param("childId") Long childId,
            @Param("parentTypeId") Long parentTypeId);

    List<CrossHierarchy> findByParentTypeAndChildType(BoundaryType parentType, BoundaryType childType);

    @Query("select ch.child from CrossHierarchy ch where UPPER(ch.parentType.name)= UPPER(:boundaryTypeName) and UPPER(ch.parentType.hierarchyType.name) =UPPER(:hierarchyTypeName) and UPPER(ch.parent.name) = UPPER(:boundaryName)")
    List<Boundary> findChildBoundariesByParentBoundary(@Param("boundaryTypeName") String boundaryTypeName,
            @Param("hierarchyTypeName") String hierarchyTypeName, @Param("boundaryName") String boundaryName);

    @Query("select ch from CrossHierarchy ch where ch.parent.id= :parentId and ch.child.id= :childId ")
    CrossHierarchy findBoundariesByParentAndChildBoundary(@Param("parentId") Long parentId,
            @Param("childId") Long childId);
}