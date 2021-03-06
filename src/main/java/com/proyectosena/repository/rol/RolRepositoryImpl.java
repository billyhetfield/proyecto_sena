package com.proyectosena.repository.rol;

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
public class RolRepositoryImpl implements RolRepository{
	
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
	 * Metodo de consulta para los registros de la tabla Rol por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return Rol = objeto de la case Rol que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public Rol list(Long id){
		try{
			String sql = "select "+Rol.getColumnNames()
					   + "from Rol "
					   + "where rol_rol = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(Rol.class)					
					     .setParameter("id", id);
			return (Rol)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla Rol
	 * @return Rol = coleccion de objetos de la case Rol que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<Rol> listAll(int init, int limit){
		try{
			String sql = "select "+Rol.getColumnNames()
					   + "from Rol ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(Rol.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla Rol
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from Rol ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla Rol por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return Rol = objeto de la case Rol que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public Rol update(Rol rol){
		getSession().update(rol);
		return rol;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla Rol por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return Rol = objeto de la case Rol que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(Rol rol){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla Rol
	 * @value rol_rol
	 * @value rol_nombre
	 * @return Rol = objeto de la case Rol que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public Rol insert(Rol rol){
		getSession().save(rol);	
		return rol;
	}
}
