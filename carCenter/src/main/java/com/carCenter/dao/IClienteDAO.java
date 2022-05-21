package com.carCenter.dao;

import com.carCenter.model.Cliente;

public interface IClienteDAO {
	
	
	/**
	 * Metodo para crear un cliente
	 * @param clienteACrear
	 * @return true si el cliente fue creado, false en el caso contrario
	 */
	boolean crearCliente(Cliente clienteACrear);
	
	
	/**
	 * Busca un cliente por su numero de documento
	 * @param documento
	 * @return retorna un objeto de cliente encontrado
	 */
	Cliente buscarClientePorDocumento(String documento);
	
	
	/**
	 * Metodo que busaca un cliente por el correo
	 * @param correo
	 * @return objeto de cliente encontrado
	 */
	Cliente buscarClientePorCorreo(String correo);
	
	/**
	 * Metodo para editar la informacion de un cliente
	 * @param clienteAEditar
	 * @return true si el cliente fue modificado, false en el caso contrario
	 */
	boolean editarCliente(Cliente clienteAEditar);
	
	
	/**
	 * Metodo que elimina un cliente de la base de datos
	 * @param clienteAEliminar
	 * @return true si se elimino el cliente, false en el caso contario
	 */
	boolean eliminarCliente(Cliente clienteAEliminar);

}
