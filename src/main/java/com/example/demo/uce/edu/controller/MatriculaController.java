package com.example.demo.uce.edu.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.uce.edu.repository.model.Estudiante;
import com.example.demo.uce.edu.repository.model.Materia;
import com.example.demo.uce.edu.repository.model.Matricula;
import com.example.demo.uce.edu.repository.model.dto.MatriculaDTO;
import com.example.demo.uce.edu.service.EstudianteService;
import com.example.demo.uce.edu.service.MateriaService;
import com.example.demo.uce.edu.service.MatriculaService;

@Controller
@RequestMapping("/servicios")
public class MatriculaController {

	@Autowired
	private EstudianteService estudianteService;
	
	@Autowired
	private MateriaService materiaService;
	
	@Autowired
	private MatriculaService matriculaService;
	
	//Estudiantes - Vistas
	
	//Registro de un Estudiante
	@PostMapping("/registrarEstudiante")
	public String registrarEstudiante(Estudiante estudiante) {
			
		this.estudianteService.registrar(estudiante);
			
		return "redirect:/servicios/nuevoEstudiante";
	}
		
	// http://localhost:8080/uce/servicios/nuevoEstudiante
	@GetMapping("/nuevoEstudiante")
	public String nuevoEstudiante(Model model, Estudiante estudiante) {
			
		model.addAttribute("estudiante", estudiante);
			
		return "vistaNuevoEstudiante";
		}
		
		//Busqueda del Estudiante
		// http://localhost:8080/uce/servicios/busquedaEstudiante/1
		@GetMapping("/busquedaEstudiante/{idEstudiante}")
		public String busquedaEstudiante(@PathVariable("idEstudiante") Integer id, Model model) {
			
			Estudiante estudiante = this.estudianteService.buscar(id);
			model.addAttribute("estudiante",estudiante);
			
			return "vistaActualizarEstudiante";
		}
		
		//Acualizacion del Estudiante
		// http://localhost:8080/uce/servicios/actualizarEstudiante
		@PutMapping("/actualizarEstudiante/{idEstudiante}")
		public String actualizarEstudiante(@PathVariable("idEstudiante") Integer id, Estudiante estudiante) {
			
			this.estudianteService.guardar(estudiante);
			
			return "redirect:/servicios/reporteEstudiantes";
		}
		
		//Borrar un Estudiante
		// http://localhost:8080/uce/servicios/borrarEstudiante
		@DeleteMapping("/borrarEstudiante/{idEstudiante}")
		public String borrarEstudiante(@PathVariable("idEstudiante") Integer id) {
			
			this.estudianteService.borrar(id);
			
			return "redirect:/servicios/reporteEstudiantes";
		}
		
		//Reporte de Estudiantes
		// http://localhost:8080/uce/servicios/reporteEstudiantes
		@GetMapping("/reporteEstudiantes")
		public String reporteEstudiantes(Model model) {
			List<Estudiante> reporte = this.estudianteService.reporteEstudiantes();
			model.addAttribute("servicios", reporte);
			return "vistaListadoEstudiantes";
		}
		
		//Materia - Vistas
		
		//Registro de una Materia
		@PostMapping("/registrarMateria")
		public String registrarMateria(Materia materia) {
				
			this.materiaService.registrar(materia);
				
			return "redirect:/servicios/nuevaMateria";
		}
			
		// http://localhost:8080/uce/servicios/nuevaMateria
		@GetMapping("/nuevaMateria")
		public String nuevaMateria(Model model, Materia materia) {
				
			model.addAttribute("materia", materia);
				
			return "vistaNuevaMateria";
		}
			
		//Busqueda de Materias
		// http://localhost:8080/uce/servicios/busquedaMateria/1
		@GetMapping("/busquedaMateria/{idMateria}")
		public String busquedaMateria(@PathVariable("idMateria") Integer id, Model model) {
				
			Materia materia = this.materiaService.buscar(id);
			model.addAttribute("materia", materia);
				
			return "vistaActualizarMateria";
		}
			
		//Acualizacion de la Materia
		// http://localhost:8080/uce/servicios/actualizarMateria
		@PutMapping("/actualizarMateria/{idMateria}")
		public String actualizarMateria(@PathVariable("idMateria") Integer id, Materia materia) {
				
			this.materiaService.guardar(materia);
				
			return "redirect:/servicios/reporteMaterias";
		}
			
		//Borrar una Materia
		// http://localhost:8080/uce/servicios/borrarMateria
		@DeleteMapping("/borrarMateria/{idMateria}")
		public String borrarMateria(@PathVariable("idMateria") Integer id) {
				
			this.materiaService.borrar(id);
				
			return "redirect:/servicios/reporteMaterias";
		}
			
		//Reporte de Materias
		// http://localhost:8080/uce/servicios/reporteMaterias
		@GetMapping("/reporteMaterias")
		public String reporteMaterias(Model model) {
			
			List<Materia> reporte = this.materiaService.reporteMaterias();
			model.addAttribute("servicios", reporte);
			
			return "vistaListadoMaterias";
		}
		
		//Matriculas - Vistas
		
		//Registro de una Matricula
		@PostMapping("/registrarMatricula")
		public String registrarMatricula(String codigo, String cedula) {
			
			this.matriculaService.registrarMatricula(codigo, cedula);
			
			return "redirect:/servicios/reporteMatriculas";
		}
		
		// http://localhost:8080/uce/servicios/nuevaMatricula
		@GetMapping("/nuevaMatricula")
		public String nuevaMatricula(Model model, Matricula matricula) {
			
			model.addAttribute("matricula", matricula);
			
			return "vistaNuevaMatricula";
		}
		
		//Reporte de Matriculas
		
		// http://localhost:8080/uce/servicios/reporteMatriculas
		@GetMapping("/reporteMatriculas")
		public String reporteMatriculas(Model model, String cedula, String apellido, String nombre, 
										String codigo, LocalDateTime fecha) {
			
			List<MatriculaDTO> lista = this.matriculaService.reporteMatriculas(cedula, apellido, nombre, codigo, fecha);
			model.addAttribute("servicios", lista);
			
			return "vistaListadoMatriculas";
		}		
	
}

