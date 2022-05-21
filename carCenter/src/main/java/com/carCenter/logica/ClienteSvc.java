package com.carCenter.logica;

import com.carCenter.dao.impl.ClienteDAOHibernate;
import com.carCenter.model.Cliente;

public class ClienteSvc {
	
	private ClienteDAOHibernate dao;
	
	public ClienteSvc() {
		dao = new ClienteDAOHibernate();
	}
	
	public boolean crearCliente(Cliente clienteACrear) {
		return dao.crearCliente(clienteACrear);
	}
	
	public Cliente buscarClientePorDocumento(String documento) {
		return dao.buscarClientePorDocumento(documento);
	}
	
	public Cliente buscarClientePorCorreo(String correo) {
		return dao.buscarClientePorCorreo(correo);
	}
	
	public boolean editarCliente(Cliente clienteAEditar) {
		return dao.editarCliente(clienteAEditar);
	}
	
	public boolean eliminarCliente(Cliente clienteAEliminar) {
		return dao.eliminarCliente(clienteAEliminar);
	}

}
