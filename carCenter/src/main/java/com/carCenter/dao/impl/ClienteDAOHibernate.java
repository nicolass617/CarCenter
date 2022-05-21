package com.carCenter.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.carCenter.dao.IClienteDAO;
import com.carCenter.model.Cliente;
import com.carCenter.model.TipoDocumento;
import com.carCenter.model.persistence.SessionHibernate;

public class ClienteDAOHibernate implements IClienteDAO {

	@Override
	public boolean crearCliente(Cliente clienteACrear) {
		Session session = null;
		try {
			session = SessionHibernate.getSf().getCurrentSession();
			session.beginTransaction();
			session.persist(clienteACrear);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Cliente buscarClientePorDocumento(String documento) {
		Cliente clienteEncontrado = new Cliente();
		try {
			String hql = "SELECT c FROM cliente c WHERE c.documento = :documento";
			Session session = SessionHibernate.getSf().getCurrentSession();
			session.beginTransaction();
			
			Query<Cliente> query = session.createQuery(hql);
			query.setParameter("documento", documento);
			clienteEncontrado = query.getSingleResult();
			
			session.getTransaction().commit();
			session.close();
			
			return clienteEncontrado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Cliente buscarClientePorCorreo(String correo) {
		Cliente clienteEncontrado = new Cliente();
		try {
			String hql = "SELECT c FROM Cliente c WHERE c.correo = :correo";
			Session session = SessionHibernate.getSf().getCurrentSession();
			session.beginTransaction();
			
			Query<Cliente> query = session.createQuery(hql);
			query.setParameter("correo", correo);
			clienteEncontrado = query.getSingleResult();
			
			session.getTransaction().commit();
			session.close();
			
			return clienteEncontrado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean editarCliente(Cliente clienteAEditar) {
		Session session = null;
		try {
			session = SessionHibernate.getSf().getCurrentSession();
			session.beginTransaction();
			session.update(clienteAEditar);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return false;
	}

	@Override
	public boolean eliminarCliente(Cliente clienteAEliminar) {
		Session session = null;
		try {
			session = SessionHibernate.getSf().getCurrentSession();
			session.beginTransaction();
			session.delete(clienteAEliminar);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return false;
	}
	
//	public static void main(String[] args) {
//		Cliente c = new Cliente();
//		ClienteDAOHibernate cdao = new ClienteDAOHibernate();
//		
//		TipoDocumento td = new TipoDocumento();
//		td.setIdTipoDocumento(1);
//		
//		c.setPrimerNombre("Nicolas");
//		c.setPrimerApellido("Avila");
//		c.setCelular("3152603968");
//		c.setContrasena("123456");
//		c.setCorreo("nicolas@mail.com");
//		c.setIdTipoDocumento(td);
//		c.setDocumento("1001330618");
//		c.setDireccion("cra 62 #161a-09");
//		
//		boolean b = cdao.crearCliente(c);
//		
//		System.out.println(String.valueOf(b));
//		
//		Cliente buscar = cdao.buscarClientePorCorreo("nicolas@mail.com");
//		
//		System.out.println(buscar.getPrimerNombre() + " " + buscar.getPrimerApellido());
//		
//		c.setCelular("3615684651");
//		
//		boolean editar = cdao.editarCliente(c);
//		
//		System.out.println(String.valueOf(editar));
//		
//		boolean eliminar = cdao.eliminarCliente(c);
//		
//		System.out.println(String.valueOf(eliminar));
//	}

}
