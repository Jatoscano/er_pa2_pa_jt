package com.example.demo.uce.edu.repository.model.dto;

import java.time.LocalDateTime;

public class MatriculaDTO {

	private String cedula;
	private String apellido;
	private String nombre;
	private String codigo;
	private LocalDateTime fecha;
	
	public MatriculaDTO() {
		
	}
	
	public MatriculaDTO(String cedula, String apellido, String nombre, String codigo,  LocalDateTime fecha) {
		
		this.cedula = cedula;
		this.apellido = apellido;
		this.nombre = nombre;
		this.codigo = codigo;
		this.fecha = fecha;
	}

		
	@Override
	public String toString() {
		return "MatriculaDTO [cedula=" + cedula + ", apellido=" + apellido + ", nombre=" + nombre + ", codigo=" + codigo
				+ ", fecha=" + fecha + "]";
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
}
