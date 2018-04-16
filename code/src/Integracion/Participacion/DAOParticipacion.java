package Integracion.Participacion;

import Exceptions.DAOException;
import Negocio.Participacion.Tparticipacion;

import java.sql.SQLException;
import java.util.Collection;

public interface DAOParticipacion {
	boolean create(Tparticipacion tParticipacion) throws SQLException, DAOException;
	Collection<Tparticipacion> readAll() throws DAOException;
	Collection<Tparticipacion> readByFairName(String nombre) throws DAOException;
	Collection<Tparticipacion> readByClientName(String nombre) throws DAOException;
	Collection<Tparticipacion> readByFairId(Integer id) throws DAOException;
	Collection<Tparticipacion> readByClientId(Integer id) throws DAOException;
	boolean update(Tparticipacion tParticipacion) throws DAOException;
	boolean delete(Integer fair_id, Integer pavilion_id, Integer stand_id) throws DAOException;
	void deleteAll() throws DAOException;
}
