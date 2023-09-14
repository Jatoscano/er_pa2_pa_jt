package com.example.demo.uce.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.uce.edu.repository.MateriaRepository;
import com.example.demo.uce.edu.repository.model.Materia;

@Service
public class MateriaServiceImpl implements MateriaService{

	@Autowired
	private MateriaRepository materiaRepository;
	
	@Override
	public void registrar(Materia materia) {
		
		this.materiaRepository.insertar(materia);
		
		//Encerar la variable numeroEstudiante
		this.materiaRepository.insertar(materia);
		
		if(materia.getNumeroEstudiante() == null) {
			
			materia.setNumeroEstudiante(0);
			
			this.materiaRepository.actualizar(materia);
		}
	}

	@Override
	public void guardar(Materia materia) {
		
		this.materiaRepository.actualizar(materia);
	}

	@Override
	public Materia buscar(Integer id) {
		
		return this.materiaRepository.seleccionar(id);
	}

	@Override
	public void borrar(Integer id) {
		
		this.materiaRepository.eliminar(id);
	}

	@Override
	public List<Materia> reporteMaterias() {
		
		return this.materiaRepository.listaMaterias();
	}
	
}
