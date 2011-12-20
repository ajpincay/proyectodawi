package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import entidades.Asignatura;
import entidades.Grados;
import entidades.Persona;
import entidades.Seccionprogramada;

public class AsignaturaJPADAO implements AsignaturaDAO{
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public void setEntityManagerFactory(EntityManagerFactory emf){
		this.emf = emf;
	}
	
	
	@SuppressWarnings("rawtypes")
	public ArrayList<Asignatura> obtenerTodos() throws Exception{
		
		em = emf.createEntityManager();
		
		ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
		
		List lista = em.createQuery("SELECT a FROM Asignatura a where a.strEstado = 'Habilitado'").getResultList();
		
		if (lista.size()>0) {
			for (int i = 0; i < lista.size(); i++) {
				asignaturas.add((Asignatura)lista.get(i));
			}
		}
		
		for (Asignatura x : asignaturas) {
			if(x.getIntGrado() > 0 && x.getIntGrado() <=6){
				x.setStrNivel("Primario");
			}else{
				x.setStrNivel("Secundario");
			}
		}
		
		return asignaturas;
		
	}
	
	public ArrayList<Grados> obtenerGradoPrimaria() throws Exception {
		
		ArrayList<Grados> gradosPrimaria = new ArrayList<Grados>();
		
		for (int i = 1; i < 7; i++) {
			Grados objGrado =  new Grados();
			
			objGrado.setId(i);
			objGrado.setValor(i + "�");
			
			gradosPrimaria.add(objGrado);
		}
		
		return gradosPrimaria;
		
	}
	
    public ArrayList<Grados> obtenerGradoSecundaria() throws Exception {
		
		ArrayList<Grados> gradosSecundaria = new ArrayList<Grados>();
		
		for (int i = 7; i < 12; i++) {
			Grados objGrado =  new Grados();
			
			objGrado.setId(i);
			objGrado.setValor(i + "�");
			
			gradosSecundaria.add(objGrado);
		}
		
		return gradosSecundaria;
		
	}
	
	
	public void actualizar(Asignatura asignatura) throws Exception{
		
		System.out.println(asignatura.getIntCodigoAsignatura());
		System.out.println(asignatura.getStrNombreAsignatura());
		
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Asignatura entidadAsignatura = em.find(Asignatura.class, asignatura.getIntCodigoAsignatura());
		
		entidadAsignatura.setStrNombreAsignatura(asignatura.getStrNombreAsignatura());
		entidadAsignatura.setIntGrado(asignatura.getIntGrado());
		
		em.merge(entidadAsignatura);
		em.flush();
		
		em.getTransaction().commit();
		em.close();
	}
	
	public Asignatura obtenerAsignatura(Asignatura asignatura) throws Exception {
		
		em = emf.createEntityManager();
		
		Asignatura entidadAsignatura = (Asignatura)em.find(Asignatura.class,asignatura.getIntCodigoAsignatura());
	
		return entidadAsignatura;
	}
	
	public Asignatura consultarAsignatura(Asignatura asignatura) throws Exception {
		em=emf.createEntityManager();
		Query q =  em.createQuery("SELECT a FROM Asignatura a WHERE a.strNombreAsignatura=?1");
		q.setParameter(1, asignatura.getStrNombreAsignatura());
		
		try {
			Asignatura entidadAsignatura =(Asignatura)q.getSingleResult();
			em.close();
			if(entidadAsignatura!=null)
				return entidadAsignatura;
			else
				return null;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public void insertar(Asignatura asignatura) throws Exception {
		
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		asignatura.setStrEstado("Habilitado");
		
		em.persist(asignatura);
		em.flush();
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void inhabilitar(Asignatura asignatura) throws Exception {
		
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Asignatura entidadAsignatura = em.find(Asignatura.class, asignatura.getIntCodigoAsignatura());
		
		entidadAsignatura.setStrNombreAsignatura(asignatura.getStrNombreAsignatura());
		entidadAsignatura.setStrEstado("Deshabilitado");
		
		em.merge(entidadAsignatura);
		em.flush();
		
		em.getTransaction().commit();
		em.close();
	}
	
	
	@SuppressWarnings("rawtypes")
	public ArrayList<Asignatura> obtenerAsignaturasXGrado(Seccionprogramada seccion) throws Exception{
		
		em = emf.createEntityManager();
		
		ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
		
		Query q = em.createQuery("SELECT a FROM Asignatura a " +
				                    "WHERE a.intGrado=?1 " +
				                    "AND a.strEstado = 'Habilitado'");
		q.setParameter(1, seccion.getIntGrado());
		List lista = q.getResultList();
		
		if (lista.size()>0) {
			for (int i = 0; i < lista.size(); i++) {
				asignaturas.add((Asignatura)lista.get(i));
			}
		}
		
		return asignaturas;
		
	}
}
