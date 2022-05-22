package com.carCenter.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

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
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Ya existe un usuario con el correo ingresado, por favor inicie sesion", null));
			return "";
		}
		buscar = svc.buscarClientePorDocumento(nuevo.getDocumento());
		if(buscar != null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Ya existe un usuario con el numero de documento ingresado", null));
			return "";
		}
		
		if(buscar == null) {
			contrasena = DigestUtils.sha1Hex(contrasena);
			nuevo.setContrasena(contrasena);
			
			boolean creado = svc.crearCliente(nuevo);
			if(creado) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Se logro registrar exitosamente", null));
				return "";
			}
		}
		return "";
	}
	
	public void validarDatosInicio() {
		if(actual.getCorreo() == null || actual.getCorreo().isEmpty() || actual.getCorreo().trim().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar un correo", null));
			return;
		}
		
		if(actual.getContrasena() == null || actual.getContrasena().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe ingresar una contraseña", null));
			return;
		}
		iniciarSecion();
	}
	
	public String iniciarSecion() {
		Cliente buscar = svc.buscarClientePorCorreo(actual.getCorreo());
		String encript = DigestUtils.sha1Hex(contrasena);
		
		if(buscar == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe un usuario con el correo ingresado", null));
			return "";
		}
		
		if(!buscar.getContrasena().equals(encript)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "La contraseña es incorrecta", null));
			buscar = new Cliente();
			return "";
		} else {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.setAttribute("cliente", buscar);
		}
		return "";
	}
	
	public String iniciarSesion() {
		
		return "";
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

}
