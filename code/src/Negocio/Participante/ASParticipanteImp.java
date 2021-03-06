package Negocio.Participante;

import Exceptions.ASException;
import Integracion.Participacion.DAOParticipacion;
import Integracion.Participante.DAOParticipante;
import Integracion.Participante.IFDAOParticipante;
import Integracion.Stand.DAOStand;
import Integracion.Participacion.IFDAOParticipacion;
import Negocio.Participacion.Tparticipacion;
import Integracion.Stand.IFDAOStand;
import Negocio.Stand.Tstand;

import java.util.ArrayList;
import java.util.Collection;

public class ASParticipanteImp implements ASParticipante {
    public Integer create(Tparticipante participante) throws ASException {
        int id;
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        if (participante != null && participante.getName() != null && participante.getPhone() >= 0) {
            try {
                Tparticipante read = daoParticipante.readByName(participante.getName());
                if (read == null)
                    id = daoParticipante.create(participante);
                else {
                    if (!read.getActive() && participante.getActive() && read.getName().equals(participante.getName())) {
                        participante.setId(read.getId());
                        id = daoParticipante.update(participante);
                    } else
                        throw new ASException("ERROR: Ya existe un participante con este nombre.\n");
                }
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido los datos del participante.\n");
        return id;
    }

    public Integer drop(Integer id) throws ASException {
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        DAOParticipacion daoParticipacion = IFDAOParticipacion.getInstance().generateDAOparticipacion();

        ArrayList<Tparticipacion> listaParticipaciones;
        ArrayList<Tstand> readStandList;

        int idr;
        if (id > 0) {
            try {
                Tparticipante read = daoParticipante.readById(id);
                if (read != null && read.getActive()) {
                    read.setActive(false);
                    listaParticipaciones = (ArrayList<Tparticipacion>) daoParticipacion.readByClientId(read.getId());
                    //Numero de asignaciones que tenemos en el arraylist para poder iterar
                    for (int i = 0; i < listaParticipaciones.size(); i++) {
                        //Desactivacion de todas las participaciones que se corresponden con esa participacion
                        Tparticipacion tParticipation = listaParticipaciones.get(i);
                        tParticipation.setActive(false);
                        daoParticipacion.update(tParticipation);
                        //Desactivacion de stands referenciados en esa participacion
                        readStandList = (ArrayList<Tstand>) daoStand.readByAssignation(tParticipation.getId());
                        for (int j = 0; j < readStandList.size(); j++) {
                            Tstand tStand = readStandList.get(j);
                            tStand.setActive(false);
                            daoStand.update(tStand);
                        }
                    }
                    idr = daoParticipante.update(read);
                } else
                    throw new ASException("ERROR: El participante " + id + " no existe o ya esta desactivado.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es valido.\n");
        return idr;
    }

    public Integer modify(Tparticipante participante) throws ASException {
        int id;
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        if (participante != null && participante.getName() != null && participante.getId() > 0 && participante.getPhone() >= 0) {
            try {
                Tparticipante read = daoParticipante.readById(participante.getId());
                if (read != null) {
                    Tparticipante nameOK = daoParticipante.readByName(participante.getName());
                    if (nameOK == null || nameOK.getName().equals(read.getName())) {
                        if (!participante.getActive())
                            if (read.getActive())
                                participante.setActive(true);
                        id = daoParticipante.update(participante);
                    } else
                        throw new ASException("ERROR: El nuevo nombre para el participante " + participante.getName() + " ya esta siendo usado.\n");
                } else
                    throw new ASException("ERROR: El participante " + participante.getName() + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se han introducido datos correctos.\n");
        return id;
    }

    public Collection<Tparticipante> list() throws ASException {
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        Collection<Tparticipante> collection;
        try {
            collection = daoParticipante.readAll();
        } catch (Exception ex) {
            throw new ASException(ex.getMessage());
        }
        return collection;
    }

    public Tparticipante showByName(String name) throws ASException {
        Tparticipante part;
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        if (name != null) {
            try {
                Tparticipante read = daoParticipante.readByName(name);
                if (read != null)
                    part = read;
                else
                    throw new ASException("ERROR: El participante " + name + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: No se ha introducido ningun nombre.\n");
        return part;
    }

    public Tparticipante showById(Integer id) throws ASException {
        Tparticipante part;
        DAOParticipante daoParticipante = IFDAOParticipante.getInstance().generateDAOparticipante();
        if (id > 0) {
            try {
                Tparticipante read = daoParticipante.readById(id);
                if (read != null)
                    part = read;
                else
                    throw new ASException("ERROR: El participante " + id + " no existe.\n");
            } catch (Exception ex) {
                throw new ASException(ex.getMessage());
            }
        } else
            throw new ASException("ERROR: El ID introducido no es valido.\n");
        return part;
    }
}
