package com.carCenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mantenimiento")
public class Mantenimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "", unique = true)
	private int idMantenimiento;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "estado")
	private String estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
	private Cliente idCliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMecanico", referencedColumnName = "idEmpleado")
	private Mecanico idMecanico;
	
	@Column(name = "estado2")
	private String estado2;
	
	@Column(name = "limitePresupuesto")
	private double limitePresupuesto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idVehiculo", referencedColumnName = "idVehiculo")
	private Vehiculo idVehiculo;

	public int getIdMantenimiento() {
		return idMantenimiento;
	}

	public void setIdMantenimiento(int idMantenimiento) {
		this.idMantenimiento = idMantenimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public Mecanico getIdMecanico() {
		return idMecanico;
	}

	public void setIdMecanico(Mecanico idMecanico) {
		this.idMecanico = idMecanico;
	}

	public String getEstado2() {
		return estado2;
	}

	public void setEstado2(String estado2) {
		this.estado2 = estado2;
	}

	public double getLimitePresupuesto() {
		return limitePresupuesto;
	}

	public void setLimitePresupuesto(double limitePresupuesto) {
		this.limitePresupuesto = limitePresupuesto;
	}

	public Vehiculo getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Vehiculo idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

}
