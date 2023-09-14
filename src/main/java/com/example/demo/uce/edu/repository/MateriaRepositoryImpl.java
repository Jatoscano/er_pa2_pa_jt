package com.example.demo.uce.edu.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.uce.edu.repository.model.Materia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class MateriaRepositoryImpl implements MateriaRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void insertar(Materia materia) {
		
		this.entityManager.persist(materia);
	}

	@Override
	public void actualizar(Materia materia) {
		
		this.entityManager.merge(materia);
	}

	@Override
	public Materia seleccionar(Integer id) {
		
		return this.entityManager.find(Materia.class, id);
	}

	@Override
	public void eliminar(Integer id) {
		
		this.entityManager.remove(this.seleccionar(id));
	}
	

	@Override
	public Materia seleccionarMateria(String codigo) {
		//SQL
		//SELECT * FROM materia ma WHERE ma.mate_codigo = "" 
																		
		//JPQL
		//Se mantienen las palabras reservadas 
		//SELECT ma FROM Materia ma WHERE ma.codigo = :""
		TypedQuery<Materia> typedQuery = this.entityManager.createQuery("SELECT ma FROM Materia ma WHERE "
												   + "ma.codigo = : datoCodigo", Materia.class);
		typedQuery.setParameter("datoCodigo", codigo);
		return typedQuery.getSingleResult();
	}

	@Override
	public List<Materia> listaMaterias() {
		TypedQuery<Materia> typedQuery = this.entityManager.createQuery("SELECT ma FROM Materia ma"
				  													   , Materia.class);
         return typedQuery.getResultList();
	}
	
	
}
