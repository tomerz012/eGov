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
package org.egov.commons.dao;

import org.egov.commons.Vouchermis;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class VouchermisHibernateDAO  {
    @Transactional
    public Vouchermis update(final Vouchermis entity) {
        getCurrentSession().update(entity);
        return entity;
    }

    @Transactional
    public Vouchermis create(final Vouchermis entity) {
        getCurrentSession().persist(entity);
        return entity;
    }

    @Transactional
    public void delete(Vouchermis entity) {
        getCurrentSession().delete(entity);
    }

    public Vouchermis findById(Number id, boolean lock) {
        return (Vouchermis) getCurrentSession().load(Vouchermis.class, id);
    }

    public List<Vouchermis> findAll() {
        return (List<Vouchermis>) getCurrentSession().createCriteria(Vouchermis.class).list();
    }

    @PersistenceContext
    private EntityManager entityManager;

    
    public Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }


    public Vouchermis getVouchermisByVhId(final Integer vhId) {
        final Query qry = getCurrentSession().createQuery("from Vouchermis where voucherheaderid =:vhId");
        qry.setInteger("vhId", vhId);
        return (Vouchermis) qry.uniqueResult();
    }

}