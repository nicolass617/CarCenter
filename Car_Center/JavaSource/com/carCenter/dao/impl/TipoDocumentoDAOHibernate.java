package com.carCenter.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.carCenter.dao.ITipoDocumentoDAO;
import com.carCenter.model.TipoDocumento;
import com.carCenter.model.persistence.SessionHibernate;

public class TipoDocumentoDAOHibernate implements ITipoDocumentoDAO {

	@Override
	public List<TipoDocumento> buscarTipoDocumentos() {
		List<TipoDocumento> listaTiposDocumentos = new ArrayList<TipoDocumento>();
		Session session = null;
		try {
			String hql = "SELECT td FROM TipoDocumento td";
			
			session = SessionHibernate.getSf().getCurrentSession();
			session.beginTransaction();
			
			Query<TipoDocumento> query = session.createQuery(hql);
			listaTiposDocumentos = query.getResultList();
			
			session.getTransaction().commit();
			session.close();
			
			return listaTiposDocumentos;
		} catch (Exception e) {
			session.close();
			return null;
		}
	}

}
