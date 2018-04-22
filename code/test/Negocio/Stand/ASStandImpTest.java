package Negocio.Stand;

import Exceptions.ASException;
import Exceptions.DAOException;
import Integracion.Asignacion.DAOAsignacion;
import Integracion.Asignacion.DAOAsignacionImp;
import Integracion.Feria.DAOFeria;
import Integracion.Feria.DAOFeriaImp;
import Integracion.Pabellon.DAOPabellonImp;
import Integracion.Participacion.DAOParticipacion;
import Integracion.Participacion.DAOParticipacionImp;
import Integracion.Participante.DAOParticipanteImp;
import Integracion.Stand.DAOStand;
import Integracion.Stand.DAOStandImp;
import Negocio.Asignacion.ASAsignacionImp;
import Negocio.Asignacion.IFDAOAsignacion;
import Negocio.Asignacion.Tasignacion;
import Negocio.Feria.ASFeriaImp;
import Negocio.Pabellon.ASPabellonImp;
import Negocio.Feria.Tferia;
import Negocio.Pabellon.Tpabellon;
import Negocio.Participacion.ASParticipacionImp;
import Negocio.Participacion.Tparticipacion;
import Negocio.Participante.ASParticipanteImp;
import Negocio.Participante.Tparticipante;
import Negocio.Participante.TparticipanteInternacional;
import Negocio.Participante.TparticipanteNacional;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


import java.sql.SQLException;
import java.util.Date;

public class ASStandImpTest {

    // Tstand para probar
    private static Tstand tstandTest1 = new Tstand(1,1,1, 0, 0, 0,true);
    private static Tstand tstandTest2 = new Tstand(2,1,1, 2, 2, 2,true);
    // Tferia para probar
    private static Tferia tferiaTest1 = new Tferia("FITUR","Feria internacional turismo",new java.sql.Date(117,0,4),new java.sql.Date(117,0,4),true);
    private static Tferia tferiaTest2 = new Tferia("VINECT","Feria internacional vinos",new java.sql.Date(117,9,28),new java.sql.Date(117,10,4),true);
    // Tparticipante para probar
    private static TparticipanteNacional tparticipanteTest1 = new TparticipanteNacional("IBM", 778778778, true, "ALBACETE");
    private static TparticipanteInternacional tparticipanteTest2 = new TparticipanteInternacional("GAMBAS VEGANAS", 887887887, true, "CATALONYA");
    // Tpabellon para probar
    private static Tpabellon tpabellonTest1 = new Tpabellon(1, 0,  0,true);
    private static Tpabellon tpabellonTest2 = new Tpabellon(2, 2,  2,true);
    // Tasignacion para probar
    private static Tasignacion tasignacionTest1 = new Tasignacion(1, 1, 1, 1,true);
    private static Tasignacion tasignacionTest2 = new Tasignacion(2, 2, 2,2, true);
    // Tparticipacion para probar
    private static Tparticipacion tparticipacionTest1 = new Tparticipacion(1, 1, 1,true);
    private static Tparticipacion tparticipacionTest2 = new Tparticipacion(2, 2, 2, true);

    @Before
    public void setUp() throws Exception {
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll();
        // Borra todas las tuplas en la tabla 'stand' de la db
        DAOStandImp dao = new DAOStandImp();
        dao.deleteAll();
        // Creacion de 2 tFeria para soportar el uso de asignaciones
        DAOFeriaImp daoFeriaImp = new DAOFeriaImp();
        daoFeriaImp.deleteAll();
        daoFeriaImp.create(tferiaTest1);
        daoFeriaImp.create(tferiaTest2);
        // Creacion de 2 tParticipante para soportar el uso de asignaciones
        DAOParticipanteImp daoParticipanteImp = new DAOParticipanteImp();
        daoParticipanteImp.deleteAll();
        daoParticipanteImp.create(tparticipanteTest1);
        daoParticipanteImp.create(tparticipanteTest2);
        // Creacion de 2 tPabellon para soportar el uso de asignaciones
        DAOPabellonImp daoPabellonImp = new DAOPabellonImp();
        daoPabellonImp.deleteAll();
        daoPabellonImp.create(tpabellonTest1);
        daoPabellonImp.create(tpabellonTest2);
        // Creacion de 2 tParticipaciones para soportar el uso de asignaciones
        DAOParticipacion daoParticipacion = new DAOParticipacionImp();
        daoParticipacion.deleteAll();
        daoParticipacion.create(tparticipacionTest1);
        daoParticipacion.create(tparticipacionTest2);
        // Creacion de 2 tAsignaciones para soportar el uso de asignaciones
        DAOAsignacion daoAsignacion = new DAOAsignacionImp();
        daoAsignacion.deleteAll();
        daoAsignacion.create(tasignacionTest1);
        daoAsignacion.create(tasignacionTest2);

    }

    //---------------------------------------------TEST CREATE---------------------------------------------------------------

    //Comprobamos que no se puede crear un stand con datos incorrectos
    @Test(expected = ASException.class)
    public void createStandIncorrect() throws ASException, DAOException, SQLException {
        ASStandImp asStand = new ASStandImp();
        int idStand = -1;

        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        //Le pasamos una participacion incorrecta o total m2 incorrectos
        Tstand tStand = new Tstand(idStand, -1, -1, 223344, 200, -1, false);
        asStand.create(tStand);
    }

    //Comprobamos que no se puede crear un stand con una participacion incorrecta(null)
    @Test(expected = ASException.class)
    public void createStandIncorrectParticipation() throws ASException, DAOException, SQLException {
        ASStandImp asStand = new ASStandImp();

        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion no valido.
        Tstand tStand = new Tstand(1, 1, -1, 223344, 200, 20, false);
        asStand.create(tStand);
    }

    //Comprobamos que se puede crear un stand correctamente
    @Test(expected = ASException.class)
    public void createStandCorrectly() throws ASException, SQLException, DAOException {
        ASStandImp asStand = new ASStandImp();
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        ASAsignacionImp asAsignation = new ASAsignacionImp();
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        daoAsignacion.deleteAll(); //Vaciamos la bbdd de asignaciones
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands

        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        asStand.create(tStand);
    }


    //-----------------------------------------------------------------------------------------------------------------------

    //--------------------------------------------TEST DROP-------------------------------------------------------------------

    //iNTENTAMOS BORRAR UN STAND INEXISTENTE EN LA BBDD
    @Test(expected = ASException.class)
    public void dropNotExist() throws ASException, DAOException, SQLException {
        int idStand = 223344;
        ASStandImp asStand = new ASStandImp();
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands

        Tstand tStand = new Tstand(idStand, 223344, 223344, 223344, 200, 20, false);
        asStand.drop(tStand);
    }

    //Borramos un stand correctamente
    @Test(expected = ASException.class)
    public void dropStandCorrectly() throws ASException, SQLException, DAOException {
        ASStandImp asStand = new ASStandImp();
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        ASAsignacionImp asAsignation = new ASAsignacionImp();
        DAOAsignacion daoAsignacion = IFDAOAsignacion.getInstance().generateDAOasignacion();
        daoAsignacion.deleteAll(); //Vaciamos la bbdd de asignaciones
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();

        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands

        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        idStand = asStand.create(tStand);

        Tstand tStand2 = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);


        assertTrue(asStand.drop(tStand2) > 0);
    }

    //------------------------------------------------------------------------------------------------------------------------

    //---------------------------------------------TEST MODIFY----------------------------------------------------------------

    // Superados m2
    @Test(expected = ASException.class)
    public void modifyStandm2() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        idStand = asStand.create(tStand);

        Tstand stand = new Tstand(idStand + 1, idAsignacion, idParticipacion, 223344, 200, 1500, true);
        asStand.modify(stand);
    }

    // No participacion en bbdd
    @Test(expected = ASException.class)
    public void modifyStandNoParticipation() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 30, false);
        idStand = asStand.create(tStand);

        Tstand stand = new Tstand(idStand, idAsignacion, idParticipacion + 1, 223344, 200, 20, true);
        asStand.modify(stand);
    }

    // No asignacion en bbdd
    @Test(expected = ASException.class)
    public void modifyStandNoAssignation() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion + 1, idParticipacion, 223344, 200, 20, false);
        idStand = asStand.create(tStand);
    }

    // No datos stand
    @Test(expected = ASException.class)
    public void modifyStandNoData() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        idStand = asStand.create(tStand);

        Tstand stand = new Tstand(idStand + 1, -1, -1, -1, -1, -1, true);
        asStand.modify(stand);
    }

    // No stand en bbdd
    @Test(expected = ASException.class)
    public void modifyStandWrongStand() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        idStand = asStand.create(tStand);

        Tstand stand = new Tstand(idStand + 1, idAsignacion, idParticipacion, 223344, 200, 20, true);
        asStand.modify(stand);
    }

    // No id stand inicializado
    @Test(expected = ASException.class)
    public void modifyStandNoStand() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        idStand = asStand.create(tStand);

        Tstand stand = new Tstand(-1, idAsignacion, idParticipacion, 223344, 200, 20, true);
        asStand.modify(stand);
    }

    // Chachi pistachi
    @Test
    public void modifyStand() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        idStand = asStand.create(tStand);

        tStand.setCost(tStand.getCost() * 2);
        Tstand standResult = asStand.showById(asStand.modify(tStand));

        assert (standResult.getActive() == tStand.getActive() && standResult.getAssignation_id() == tStand.getAssignation_id()
                && standResult.getCost() == tStand.getCost() && standResult.getId() == tStand.getId()
                && standResult.getNum_at_fair() == tStand.getNum_at_fair() && standResult.getParticipation_id() == tStand.getParticipation_id()
                && standResult.getTotal_m2() == tStand.getTotal_m2());
    }

    //------------------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------TEST LIST---------------------------------------------------------------

    //Comprobamos que se puede listar correctamente los stand validos
    @Test
    public void listStand() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        idStand = asStand.create(tStand);

        Tstand tStand2 = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        asStand.create(tStand2);

        //Listamos todos los stands creados
        asStand.list();
    }
    //-----------------------------------------------------------------------------------------------------------------------

    //----------------------------------------------TEST SHOWBYID----------------------------------------------------------------

    // No id
    @Test(expected = ASException.class)
    public void showNoId() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);

        asStand.showById(-1);
    }

    // Wrong id
    @Test(expected = ASException.class)
    public void showWrongId() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        idStand = asStand.create(tStand);

        asStand.showById(idStand + 1);
    }

    // No error
    @Test
    public void showById() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        idStand = asStand.create(tStand);

        asStand.showById(idStand);
    }

    //---------------------------------------------------------------------------------------------------------------------------

    //----------------------------------------------TEST SHOW BY ID PARTICIPATION---------------------------------------------------

    // No ID
    @Test(expected = ASException.class)
    public void showNoParticipationId() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        idStand = asStand.create(tStand);

        asStand.showByParticipation(-1);
    }

    // Wrong ID
    @Test(expected = ASException.class)
    public void showWrongParticipationId() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        idStand = asStand.create(tStand);

        asStand.showByParticipation(idParticipacion + 1);
    }

    // No Error
    @Test
    public void showByParticipationId() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        idStand = asStand.create(tStand);

        asStand.showByParticipation(idParticipacion);
    }

    //------------------------------------------------------------------------------------------------------------------------------

    //----------------------------------------------TEST SHOW BY ID ASSIGNATION-------------------------------------------------------

    // No ID
    @Test(expected = ASException.class)
    public void showNoAssignationId() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        idStand = asStand.create(tStand);

        asStand.showByAssignation(-1);
    }

    // Wrong ID
    @Test(expected = ASException.class)
    public void showWrongAssignationId() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Añadimos primero una feria y un pabellon a la bbdd para poder generar una asignacio

        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        idStand = asStand.create(tStand);

        asStand.showByAssignation(idParticipacion + 1);
    }

    // No Error
    @Test
    public void showByAssignationId() throws DAOException, SQLException, ASException {
        int idStand = -1, idFeria = -1, idPabellon = -1, idAsignacion = -1, idParticipacion = -1, idParticipante = -1;
        DAOStand daoStand = IFDAOStand.getInstance().generateDAOstand();
        daoStand.deleteAll(); //Vaciamos la bbdd de stands
        ASStandImp asStand = new ASStandImp();
        ASParticipanteImp asParticipante = new ASParticipanteImp();
        ASParticipacionImp asParticipacion = new ASParticipacionImp();
        ASAsignacionImp asAsignation = new ASAsignacionImp();


        //Creamos una asignacion y no la participacion asi sera esa la que este a null
        Tasignacion transferAsignation = new Tasignacion(idAsignacion, idFeria, idPabellon, 4000, 3000, false);
        idAsignacion = asAsignation.create(transferAsignation);

        //Creamos un participante
        Tparticipante tParticipante = new Tparticipante("UCM", -1, true);
        idParticipante = asParticipante.create(tParticipante);


        //Creamos la participacion
        Tparticipacion participation = new Tparticipacion(idFeria, idParticipante, false);
        idParticipacion = asParticipacion.create(participation);


        //Ahora intentamos crear un stand a partir de un id asignacion valido y un id de participacion  valido.
        Tstand tStand = new Tstand(idStand, idAsignacion, idParticipacion, 223344, 200, 20, false);
        idStand = asStand.create(tStand);

        asStand.showByAssignation(idAsignacion);
    }
    //-----------------------------------------------------------------------------------------------------------------------------
}