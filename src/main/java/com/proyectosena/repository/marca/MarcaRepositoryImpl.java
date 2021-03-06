package com.proyectosena.repository.marca;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		proyectosena  
  */                          

import java.util.List;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MarcaRepositoryImpl implements MarcaRepository{
	
	@Autowired
	private SessionFactory sessionFactory;  	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla Marca por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return Marca = objeto de la case Marca que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public Marca list(Long id){
		try{
			String sql = "select "+Marca.getColumnNames()
					   + "from Marca "
					   + "where marca_marca = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(Marca.class)					
					     .setParameter("id", id);
			return (Marca)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla Marca
	 * @return Marca = coleccion de objetos de la case Marca que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<Marca> listAll(int init, int limit){
		try{
			String sql = "select "+Marca.getColumnNames()
					   + "from Marca ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(Marca.class);
						 
			if(init==0 && limit!=0){
				query.setFirstResult(init);			
				query.setMaxResults(limit);
			}
					     
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}	
	
	/**
	 * Metodo de consulta para el conteo de los registros de la tabla Marca
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from Marca ";
						
			Query query = getSession().createQuery(sql);
	        
			Iterator it = query.list().iterator();
	        Long ret = new Long(0);
	        
	        if (it != null)
		        if (it.hasNext()){
		        	ret = (Long) it.next();
		        }
	        
			return ret.intValue();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Metodo para actualizar los datos de un registro de la tabla Marca por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return Marca = objeto de la case Marca que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public Marca update(Marca marca){
		getSession().update(marca);
		return marca;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla Marca por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return Marca = objeto de la case Marca que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(Marca marca){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla Marca
	 * @value marca_marca
	 * @value marca_descri
	 * @return Marca = objeto de la case Marca que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public Marca insert(Marca marca){
		getSession().save(marca);	
		return marca;
	}
}
