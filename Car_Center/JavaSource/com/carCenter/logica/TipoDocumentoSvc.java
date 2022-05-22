package com.carCenter.logica;

import java.util.List;

import com.carCenter.dao.impl.TipoDocumentoDAOHibernate;
import com.carCenter.model.TipoDocumento;

public class TipoDocumentoSvc {
	
	private TipoDocumentoDAOHibernate dao;
	
	public TipoDocumentoSvc() {
		dao = new TipoDocumentoDAOHibernate();
	}
	
	public List<TipoDocumento> buscarTipoDocumentos() {
		return dao.buscarTipoDocumentos();
	}

}
