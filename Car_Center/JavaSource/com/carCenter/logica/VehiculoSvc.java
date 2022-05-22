package com.carCenter.logica;

import java.util.List;

import com.carCenter.dao.impl.VehiculoDAOHibernate;
import com.carCenter.model.Vehiculo;

public class VehiculoSvc {
	
	private VehiculoDAOHibernate dao;
	
	public VehiculoSvc() {
		dao = new VehiculoDAOHibernate();
	}
	
	public boolean crearVehiculo(Vehiculo vehiculoACrear){
			return dao.crearVehiculo(vehiculoACrear);
	}
	
	public List<Vehiculo> listaVehiculosPorCliente(int idCliente) {
		return dao.buscarVehiculosCliente(idCliente);
	}
	
	public boolean editarVehiculo(Vehiculo vehiculoAEditar) {
		return dao.editarVehiculo(vehiculoAEditar);
	}
	
	public boolean eliminarVehiculo(Vehiculo vehiculoAEliminar) {
		return dao.eliminarVehiculo(vehiculoAEliminar);
	}

}
