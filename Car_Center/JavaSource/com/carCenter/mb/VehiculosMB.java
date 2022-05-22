package com.carCenter.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import com.carCenter.logica.VehiculoSvc;
import com.carCenter.model.Cliente;
import com.carCenter.model.Vehiculo;

@ManagedBean(name = "vehiculos")
@SessionScoped
public class VehiculosMB {
	
	VehiculoSvc svc = new VehiculoSvc();
	Cliente usr = new Cliente();
	Vehiculo actual = new Vehiculo();
	List<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
	
	public VehiculosMB() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		usr = (Cliente) session.getAttribute("cliente");
		
		cargarListaVehiculos();
	}
	
	public void cargarListaVehiculos() {
		listaVehiculos = svc.listaVehiculosPorCliente(usr.getIdCliente());
	}
	
	public String editarVehiculo(Vehiculo v) {
		actual = v;
		
		return "formularioVehiculos";
	}
	
	public String nuevoVehiculo() {
		return "formularioVehiculos";
	}
	
	public void validarDatos() {
		if(actual.getMarca() == null || actual.getMarca().isEmpty() || actual.getMarca().trim().isEmpty()) {
			PrimeFaces.current().executeScript("Swal.fire('Atención', 'Debe ingresar la marca del vehiculo!', 'warning')");
			return;
		}
		if(actual.getModelo() == null || actual.getModelo().isEmpty() || actual.getModelo().trim().isEmpty()) {
			PrimeFaces.current().executeScript("Swal.fire('Atención', 'Debe ingresar el modelo del vehiculo!', 'warning')");
			return;
		}
		if(actual.getReferencia() == null || actual.getReferencia().isEmpty() || actual.getReferencia().trim().isEmpty()) {
			PrimeFaces.current().executeScript("Swal.fire('Atención', 'Debe ingresar la referencia del vehiculo!', 'warning')");
			return;
		}
		if(actual.getPlaca() == null || actual.getPlaca().isEmpty() || actual.getPlaca().trim().isEmpty()) {
			PrimeFaces.current().executeScript("Swal.fire('Atención', 'Debe ingresar la marca del vehiculo!', 'warning')");
			return;
		}
		
		guardarVehiculo();
	}
	
	public String guardarVehiculo() {
		boolean creado = false;
		if(actual.getIdVehiculo() == 0) {
			actual.setIdCliente(usr);
			creado = svc.crearVehiculo(actual);
			if(creado) {
				actual = new Vehiculo();
				PrimeFaces.current().executeScript("Swal.fire('Atención', 'Se registro el vehiculo de manera exitosa', 'success')");
				cargarListaVehiculos();
				return "vehiculos";
			} else {
				PrimeFaces.current().executeScript("Swal.fire('Error', 'Ha ocurrido un error al intentar registrar el vehiculo', 'error')");
			}
		} else {
			creado = svc.editarVehiculo(actual);
			if(creado) {
				actual = new Vehiculo();
				PrimeFaces.current().executeScript("Swal.fire('Atención', 'Se actualizo el vehiculo de manera exitosa', 'success')");
				cargarListaVehiculos();
				return "vehiculos";
			} else {
				PrimeFaces.current().executeScript("Swal.fire('Error', 'Ha ocurrido un error al intentar actualizar el vehiculo', 'error')");
			}
		}
		
		return "";
	}

	public Vehiculo getActual() {
		return actual;
	}

	public void setActual(Vehiculo actual) {
		this.actual = actual;
	}

	public List<Vehiculo> getListaVehiculos() {
		return listaVehiculos;
	}

	public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
	}

}
