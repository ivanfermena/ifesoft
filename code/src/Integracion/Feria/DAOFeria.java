package Integracion.Feria;

import Exceptions.DAOException;
import Negocio.Feria.Tferia;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public interface DAOFeria {
	Integer create(Tferia tferia) throws SQLException, DAOException;
	Collection<Tferia> readAll() throws DAOException;
	Tferia readByName(String name) throws DAOException;
	Tferia readById(Integer id) throws DAOException;
	Collection<Tferia> readByDates(Date initDate, Date endDate) throws DAOException;
	Integer update(Tferia tferia) throws DAOException;
	boolean delete (Integer id) throws DAOException;
	void deleteAll() throws DAOException;
}
