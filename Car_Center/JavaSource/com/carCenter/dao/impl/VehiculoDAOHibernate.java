package com.carCenter.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.carCenter.dao.IVehiculoDAO;
import com.carCenter.model.Vehiculo;
import com.carCenter.model.persistence.SessionHibernate;

public class VehiculoDAOHibernate implements IVehiculoDAO{

	@Override
	public boolean crearVehiculo(Vehiculo vehiculoACrear) {
		Session session = null;
		try {
			session = SessionHibernate.getSf().getCurrentSession();
			session.beginTransaction();
			session.persist(vehiculoACrear);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Vehiculo> buscarVehiculosCliente(int idCliente) {
		List<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
		Session session = null;
		try {
			String hql = "SELECT v FROM Vehiculo v WHERE v.idCliente.idCliente = :idCliente";
			session = SessionHibernate.getSf().getCurrentSession();
			session.beginTransaction();
			
			Query<Vehiculo> query = session.createQuery(hql);
			query.setParameter("idCliente", idCliente);
			listaVehiculos = query.getResultList();
			
			session.getTransaction().commit();
			session.close();
			
			return listaVehiculos;
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean editarVehiculo(Vehiculo vehiculoAEditar) {
		Session session = null;
		try {
			session = SessionHibernate.getSf().getCurrentSession();
			session.beginTransaction();
			session.update(vehiculoAEditar);
			session.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean eliminarVehiculo(Vehiculo vehiculoAEliminar) {
		Session session = null;
		try {
			session = SessionHibernate.getSf().getCurrentSession();
			session.beginTransaction();
			session.delete(vehiculoAEliminar);
			session.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
