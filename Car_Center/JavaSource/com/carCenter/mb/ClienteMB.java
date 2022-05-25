package com.carCenter.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.PrimeFaces;

import com.carCenter.logica.ClienteSvc;
import com.carCenter.logica.TipoDocumentoSvc;
import com.carCenter.model.Cliente;
import com.carCenter.model.TipoDocumento;

@ManagedBean(name = "clienteUI")
@SessionScoped
public class ClienteMB {
	
	private ClienteSvc svc = new ClienteSvc();
	private TipoDocumentoSvc tipoDocSvc = new TipoDocumentoSvc();
	private Cliente nuevo = new Cliente();
	private Cliente actual = new Cliente();
	private String contrasena = "";
	private List<TipoDocumento> tiposDocumento;
	
	public ClienteMB() {
		tiposDocumento = tipoDocSvc.buscarTipoDocumentos();
	}
	
	public String crearUsuario() {
		Cliente buscar = svc.buscarClientePorCorreo(nuevo.getCorreo());
		if(buscar != null) {
			PrimeFaces.current().executeScript("Swal.fire('Atención!', 'Ya existe un usuario con el correo ingresado, por favor inicie sesion!', 'warning');");
			return "";
		}
		buscar = svc.buscarClientePorDocumento(nuevo.getDocumento());
		if(buscar != null) {
			PrimeFaces.current().executeScript("Swal.fire('Atención!', 'Ya existe un usuario con el numero de documento ingresado!', 'warning');");
			return "";
		}
		
		if(buscar == null) {
			contrasena = DigestUtils.sha1Hex(contrasena);
			nuevo.setContrasena(contrasena);
			nuevo.setIdTipoDocumento(tiposDocumento.get(0));
			
			boolean creado = svc.crearCliente(nuevo);
			if(creado) {
				PrimeFaces.current().executeScript("Swal.fire('Operacion exitosa!', 'Se logro registrar exitosamente!', 'success');");
				nuevo = new Cliente();
				
				contrasena = "";
				return "/login.jsf";
			}
		}
		contrasena = "";
		return "";
	}
	
	public void validarDatosInicio() {
		if(actual.getCorreo() == null || actual.getCorreo().isEmpty() || actual.getCorreo().trim().isEmpty()) {
			PrimeFaces.current().executeScript("Swal.fire('Atención!', 'Debe ingresar un correo', 'warning');");
			return;
		}
		
		if(contrasena == null || contrasena.isEmpty() || contrasena.trim().isEmpty()) {
			PrimeFaces.current().executeScript("Swal.fire('Atención!', 'Debe ingresar una contraseña!', 'warning');");
			return;
		}
		iniciarSesion();
	}
	
	public String iniciarSesion() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Cliente buscar = svc.buscarClientePorCorreo(actual.getCorreo());
		String encript = DigestUtils.sha1Hex(contrasena);
		
		if(buscar == null) {
			PrimeFaces.current().executeScript("Swal.fire('Atención!', 'No se encontro el correo ingresa, por favor registrese!', 'warning');");
			return "";
		}
		
		if(!buscar.getContrasena().equals(encript)) {
			PrimeFaces.current().executeScript("Swal.fire('Atención!', 'La contraseña es incorrecta!', 'warning');");
			buscar = new Cliente();
			return "";
		} else {
			session.setAttribute("cliente", buscar);
			PrimeFaces.current().executeScript("Swal.fire('Operacion exitosamente!', 'Se logro iniciar sesión!', 'success');");
			return "/vehiculos.jsf";
		}
	}
	
	public String cerrarSesion() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
		return "/login.jsf";
	}

	public Cliente getNuevo() {
		return nuevo;
	}

	public void setNuevo(Cliente nuevo) {
		this.nuevo = nuevo;
	}

	public Cliente getActual() {
		return actual;
	}

	public void setActual(Cliente actual) {
		this.actual = actual;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public List<TipoDocumento> getTiposDocumento() {
		return tiposDocumento;
	}

	public void setTiposDocumento(List<TipoDocumento> tiposDocumento) {
		this.tiposDocumento = tiposDocumento;
	}

}
