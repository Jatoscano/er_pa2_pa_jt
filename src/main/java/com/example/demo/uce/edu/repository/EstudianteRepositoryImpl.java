package com.example.demo.uce.edu.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.uce.edu.repository.model.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EstudianteRepositoryImpl implements  EstudianteRepository{

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insertar(Estudiante estudiante) {
		
		this.entityManager.persist(estudiante);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		
		this.entityManager.merge(estudiante);
	}

	@Override
	public Estudiante seleccionar(Integer id) {
		
		return this.entityManager.find(Estudiante.class, id);
	}

	@Override
	public void eliminar(Integer id) {
		
		this.entityManager.remove(this.seleccionar(id));
	}

	

	@Override
	public Estudiante seleccionarEstudiante(String cedula) {
		//SQL
		//SELECT * FROM estudiante e WHERE e.estu_cedula = "" 
																		
		//JPQL
		//Se mantienen las palabras reservadas 
		//SELECT e FROM Estudiante e WHERE e.codigo = :""
		TypedQuery<Estudiante> typedQuery = this.entityManager.createQuery("SELECT e FROM Estudiante e WHERE "
														   + "e.cedula = :datoCedula", Estudiante.class);
		typedQuery.setParameter("datoCedula", cedula);
		return typedQuery.getSingleResult();
	}

	@Override
	public List<Estudiante> listaEstudiantes() {
		
		TypedQuery<Estudiante> typedQuery = this.entityManager.createQuery("SELECT e FROM Estudiante e"
																		  , Estudiante.class);
		return typedQuery.getResultList();
	}
	
	

}
