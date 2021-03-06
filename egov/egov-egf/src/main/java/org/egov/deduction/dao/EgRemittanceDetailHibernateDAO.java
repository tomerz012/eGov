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



package org.egov.deduction.dao;

import org.egov.deduction.model.EgRemittance;
import org.egov.deduction.model.EgRemittanceDetail;
import org.egov.deduction.model.EgRemittanceGldtl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * TODO Brief Description of the purpose of the class/interface
 *
 * @author Sathish
 * @version 1.00
 */
@Transactional(readOnly = true)
public class EgRemittanceDetailHibernateDAO {
    @Transactional
    public EgRemittanceDetail update(final EgRemittanceDetail entity) {
        getCurrentSession().update(entity);
        return entity;
    }

    @Transactional
    public EgRemittanceDetail create(final EgRemittanceDetail entity) {
        getCurrentSession().persist(entity);
        return entity;
    }

    @Transactional
    public void delete(EgRemittanceDetail entity) {
        getCurrentSession().delete(entity);
    }

    public EgRemittanceDetail findById(Number id, boolean lock) {
        return (EgRemittanceDetail) getCurrentSession().load(EgRemittanceDetail.class, id);
    }

    public List<EgRemittanceDetail> findAll() {
        return (List<EgRemittanceDetail>) getCurrentSession().createCriteria(EgRemittanceDetail.class).list();
    }

    @PersistenceContext
    private EntityManager entityManager;

    public Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

   

    public List<EgRemittanceDetail> getEgRemittanceDetailByEgRmt(final EgRemittance egRmt) {
        final Query qry = getCurrentSession().createQuery("from EgRemittanceDetail erd where erd.egRemittance =:egRmt");
        qry.setEntity("egRmt", egRmt);
        return qry.list();
    }

    public EgRemittanceDetail getEgRemittanceDetailFilterBy(final EgRemittance egRmt, final EgRemittanceGldtl egRmtGldtl) {
        final Query qry = getCurrentSession().createQuery(
                "from EgRemittanceDetail erd where erd.egRemittance =:egRmt and erd.egRemittanceGldtl =:egRmtGldtl");
        qry.setEntity("egRmt", egRmt);
        qry.setEntity("egRmtGldtl", egRmtGldtl);
        return (EgRemittanceDetail) qry.uniqueResult();
    }
}