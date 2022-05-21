package com.carCenter.dao;

import java.util.List;

import com.carCenter.model.TipoDocumento;

public interface ITipoDocumentoDAO {

	/**
	 * Metodo que consulta los tipos de documentos de la base de datos
	 * 
	 * @return La lista de todos los documentos configurados
	 */
	List<TipoDocumento> buscarTipoDocumentos();

}
