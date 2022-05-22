package com.carCenter.dao;

import java.util.List;

import com.carCenter.model.Vehiculo;

public interface IVehiculoDAO {

	/**
	 * Metodo que crea un vehiculo
	 * 
	 * @param vehiculoACrear
	 * @return true si el vehiculo fue creado, false en el caso contrario
	 */
	boolean crearVehiculo(Vehiculo vehiculoACrear);

	/**
	 * Metodo que lista los vehiculos de un cliente
	 * 
	 * @param idCliente
	 * @return
	 */
	List<Vehiculo> buscarVehiculosCliente(int idCliente);

	/**
	 * Metodo que editar la informacion de un vehiculo
	 * 
	 * @param vehiculoAEditar
	 * @return true si se edito el vehiculo, false en el caso contrario
	 */
	boolean editarVehiculo(Vehiculo vehiculoAEditar);

	/**
	 * Metodo que elimina un vehiculo
	 * 
	 * @param idVehiculo
	 * @return true si el vehiculo fue eliminado, false en el caso contrario
	 */
	boolean eliminarVehiculo(Vehiculo vehiculoAEliminar);

}
