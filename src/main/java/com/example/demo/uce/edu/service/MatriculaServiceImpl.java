package com.example.demo.uce.edu.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.uce.edu.repository.EstudianteRepository;
import com.example.demo.uce.edu.repository.MateriaRepository;
import com.example.demo.uce.edu.repository.MatriculaRepository;
import com.example.demo.uce.edu.repository.model.Estudiante;
import com.example.demo.uce.edu.repository.model.Materia;
import com.example.demo.uce.edu.repository.model.Matricula;
import com.example.demo.uce.edu.repository.model.dto.MatriculaDTO;

@Service
public class MatriculaServiceImpl implements MatriculaService{

	@Autowired
	private MatriculaRepository matriculaRepository;
	
	@Autowired
	private EstudianteRepository estudianteRepository;
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	@Override
	public void registrar(Matricula matricula) {
		
		this.matriculaRepository.insertar(matricula);
	}

	@Override
	public void guardar(Matricula matricula) {
		
		this.matriculaRepository.actualizar(matricula);
	}

	@Override
	public Matricula buscar(Integer id) {
		
		return this.matriculaRepository.seleccionar(id);
	}

	@Override
	public void borrar(Integer id) {
		
		this.matriculaRepository.eliminar(id);
	}

	
	@Override
	public void registrarMatricula(String codigo, String cedula) {
		//1. Creamos un Estudiante
		Estudiante estudiante = this.estudianteRepository.seleccionarEstudiante(cedula);
				
		//2. Creamos una materia
		Materia materia = this.materiaRepository.seleccionarMateria(codigo);
				
		if(materia.getNumeroEstudiante() == 0) {
					
			//2.1 Creamos una variable para la operacion matematica
			Integer suma = 0;
			suma += suma + 1;
			//2.2 Cambiamos el numero de estudiantes que tiene la materia
			materia.setNumeroEstudiante(suma);
					
			//2.3 Actualizamos la materia
			this.materiaRepository.actualizar(materia);
			}
				
			else {
			//2.4 Creamos una variable para la operacion matematica
			Integer suma = materia.getNumeroEstudiante() + 1;
			//2.5 Cambiamos el numero de estudiantes que tiene la materia
			materia.setNumeroEstudiante(suma);
					
			//2.6 Actualizamos la materia
			this.materiaRepository.actualizar(materia);
			}
			
			//3. Creamos la matricula 
			Matricula matricula = new Matricula();
			matricula.setFecha(LocalDateTime.now());
			matricula.setEstudiante(estudiante);
			matricula.setMateria(materia);
				
			this.registrar(matricula);
		
	}

	@Override
	public List<MatriculaDTO> reporteMatriculas(String cedula, String apellido, String nombre, String codigo,
			LocalDateTime fecha) {
		
		return this.matriculaRepository.listaMatriculas(cedula, apellido, nombre, codigo, fecha);
	}
}
