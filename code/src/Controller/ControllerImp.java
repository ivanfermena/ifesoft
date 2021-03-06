package Controller;

import Negocio.Asignacion.ASAsignacion;
import Negocio.Asignacion.IFASAsignacion;
import Negocio.Asignacion.Tasignacion;
import Negocio.Feria.ASFeria;
import Negocio.Feria.IFASFeria;
import Negocio.Feria.Tferia;
import Negocio.Pabellon.ASPabellon;
import Negocio.Pabellon.IFASPabellon;
import Negocio.Pabellon.Tpabellon;
import Negocio.Participacion.ASParticipacion;
import Negocio.Participacion.IFASParticipacion;
import Negocio.Participacion.Tparticipacion;
import Negocio.Participante.ASParticipante;
import Negocio.Participante.IFASParticipante;
import Negocio.Participante.Tparticipante;
import Negocio.Stand.ASStand;
import Negocio.Stand.IFASStand;
import Negocio.Stand.Tstand;
import Presentacion.Create_Modify.IFCreateModifyForm;
import Presentacion.Drop.IFDrop;
import Presentacion.Home.IFHome;
import Presentacion.Shows.IFViewList;
import Presentacion.Utils.ActionHelp;
import Presentacion.Events.Event;
import Presentacion.Events.EventGUI;

import javax.swing.*;
import java.util.Collection;

public class ControllerImp extends Controller {

    private ASFeria asFair;
    private ASAsignacion asAssignation;
    private ASPabellon asPavilion;
    private ASStand asStand;
    private ASParticipante asClient;
    private ASParticipacion asParticipation;
    private JTextField textID;

    public ControllerImp(){
        this.asFair = IFASFeria.getInstance().generateASferia();
        this.asAssignation = IFASAsignacion.getInstance().generateASAsignacion();
        this.asPavilion = IFASPabellon.getInstance().generateASPabellon();
        this.asStand = IFASStand.getInstance().generateASStand();
        this.asClient = IFASParticipante.getInstance().generateASParticipante();
        this.asParticipation = IFASParticipacion.getInstance().generateASParticipacion();
    }

    @Override
    public void execute(int event, Object data) throws Exception {

        Tferia tFair;
        Tpabellon tPavilion;
        Tparticipante tClient;
        Tasignacion tAssignation;
        Tparticipacion tParticipation;
        Tstand tStand;

        switch (event){

            /** -------- Main Views -------- */

            case Event.HOME:
                IFHome.getInstance().generateHome();
                break;

            /** -------- Views to select the different modules -------- */

            case Event.CREATE_HALF:
                IFCreateModifyForm.getInstance().generateSpecificView(event, data);
                break;
            case Event.MODIFY_HALF:
                IFCreateModifyForm.getInstance().generateSpecificView(event, data);
                break;
            case Event.SHOW_HALF:
                IFViewList.getInstance().generateSpecificView(event, data);
                break;
            case Event.DROP_HALF:
                IFDrop.getInstance().generateSpecificView(event, data);
                break;



            /** -------- Insertion forms -------- */

            case Event.INSERT_FORM_FAIR:
                IFCreateModifyForm.getInstance().generateSpecificView(event, data);
                break;
            case Event.INSERT_FORM_PAVILION:
                IFCreateModifyForm.getInstance().generateSpecificView(event, data);
                break;
            case Event.INSERT_FORM_CLIENT:
                IFCreateModifyForm.getInstance().generateSpecificView(event, data);
                break;
            case Event.INSERT_FORM_ASSIGNATION:
                IFCreateModifyForm.getInstance().generateSpecificView(event, data);
                break;
            case Event.INSERT_FORM_PARTICIPATION:
                IFCreateModifyForm.getInstance().generateSpecificView(event, data);
                break;
            case Event.INSERT_FORM_STAND:
                IFCreateModifyForm.getInstance().generateSpecificView(event, data);
                break;



            /** -------- Modification forms -------- */

            case Event.MODIFY_FORM_FAIR:
                try {
                    tFair = asFair.showById((Integer) data);
                    IFCreateModifyForm.getInstance().generateSpecificView(event, tFair);
                } catch (Exception e) { throw new Exception(e.getMessage() + ActionHelp.strHelpBasic()); }
                break;
            case Event.MODIFY_FORM_PAVILION:
                try {
                    tPavilion = asPavilion.showById((Integer) data);
                    IFCreateModifyForm.getInstance().generateSpecificView(event, tPavilion);
                } catch (Exception e) { throw new Exception(e.getMessage() + ActionHelp.strHelpBasic()); }
                break;
            case Event.MODIFY_FORM_CLIENT:
                try {
                    tClient = asClient.showById((Integer) data);
                    IFCreateModifyForm.getInstance().generateSpecificView(event, tClient);
                } catch (Exception e) { throw new Exception(e.getMessage() + ActionHelp.strHelpBasic()); }
                break;
            case Event.MODIFY_FORM_ASSIGNATION:
                try {
                    tAssignation = asAssignation.show((Integer) data);
                    IFCreateModifyForm.getInstance().generateSpecificView(event, tAssignation);
                } catch (Exception e) { throw new Exception(e.getMessage() + ActionHelp.strHelpBasic()); }
                break;
            case Event.MODIFY_FORM_PARTICIPATION:
                try {
                    tParticipation = asParticipation.show((Integer) data);
                    IFCreateModifyForm.getInstance().generateSpecificView(event, tParticipation);
                } catch (Exception e) { throw new Exception(e.getMessage() + ActionHelp.strHelpBasic()); }
                break;
            case Event.MODIFY_FORM_STAND:
                try {
                    tStand = asStand.showById((Integer) data);
                    IFCreateModifyForm.getInstance().generateSpecificView(event, tStand);
                } catch (Exception e) { throw new Exception(e.getMessage() + ActionHelp.strHelpBasic()); }
                break;



            /** -------- Inserts modules -------- */

            case Event.INSERT_FAIR:
                tFair = (Tferia) data;
                try {
                    int res = asFair.create(tFair);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_FAIR_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_FERIA_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage() + ActionHelp.strHelpBasic());
                }
                break;
            case Event.INSERT_PAVILION:
                tPavilion = (Tpabellon) data;
                try {
                    int res = asPavilion.create(tPavilion);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_PAVILION_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_PAVILION_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.INSERT_CLIENT:
                tClient = (Tparticipante) data;
                try {
                    int res = asClient.create(tClient);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_CLIENT_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_CLIENT_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.INSERT_ASSIGNATION:
                tAssignation = (Tasignacion) data;
                try {
                    int res = asAssignation.create(tAssignation);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_ASSIGNATION_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_ASSIGNATION_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;

            case Event.INSERT_PARTICIPATION:
                tParticipation = (Tparticipacion) data;
                try {
                    int res = asParticipation.create(tParticipation);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_PARTICIPATION_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_PARTICIPATION_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.INSERT_STAND:
                tStand = (Tstand) data;
                try {
                    int res = asStand.create(tStand);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_STAND_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_STAND_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;



            /** -------- Modify modules -------- */

            case Event.MODIFY_FAIR:
                tFair = (Tferia) data;
                try {
                    int res = asFair.modify(tFair);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_FERIA_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_FERIA_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.MODIFY_PAVILION:
                tPavilion = (Tpabellon) data;
                try {
                    int res = asPavilion.modify(tPavilion);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_PAVILION_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_PAVILION_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.MODIFY_CLIENT:
                tClient = (Tparticipante) data;
                try {
                    int res = asClient.modify(tClient);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CLIENT_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CLIENT_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.MODIFY_ASSIGNATION:
                tAssignation = (Tasignacion) data;
                try {
                    int res = asAssignation.modify(tAssignation);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_ASSIGNATION_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_ASSIGNATION_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.MODIFY_PARTICIPATION:
                tParticipation = (Tparticipacion) data;
                try {
                    int res = asParticipation.modify(tParticipation);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_PARTICIPATION_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_PARTICIPATION_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.MODIFY_STAND:
                tStand = (Tstand) data;
                try {
                    int res = asStand.modify(tStand);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_STAND_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_STAND_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;



            /** -------- Drops modules -------- */

            case Event.DROP_FAIR:
                try {
                    int res = asFair.drop((Integer) data);
                    if (res>0) IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_DROP_FAIR_OK, res);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_DROP_FAIR_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.DROP_PAVILION:
                try {
                    int res = asPavilion.drop((Integer) data);
                    if (res>0) IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_DROP_PAVILION_OK, res);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_DROP_PAVILION_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.DROP_CLIENT:
                try {
                    int res = asClient.drop((Integer) data);
                    if (res>0) IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_DROP_CLIENT_OK, res);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_DROP_CLIENT_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.DROP_ASSIGNATION:
                try {
                    int res = asAssignation.drop((Integer) data);
                    if (res>0) IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_DROP_ASSIGNATION_OK, res);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_DROP_ASSIGNATION_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.DROP_PARTICIPATION:
                try {
                    int res = asParticipation.drop((Integer) data);
                    if (res>0) IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_DROP_PARTICIPATION_OK, res);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_DROP_PARTICIPACION_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.DROP_STAND:
                try {
                    int res = asStand.drop((Integer) data);
                    if (res>0) IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_DROP_STAND_OK, res);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_DROP_STAND_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;


            /** -------- Individuals shows -------- */

            case Event.SHOW_FAIR_INDIVIDUAL:
                try {
                    Tferia tfair = asFair.showById((Integer)data);
                    if (tfair!=null) IFViewList.getInstance().generateSpecificView(event , tfair);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_SHOW_INDIVIDUAL_FAIR_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_PAVILION_INDIVIDUAL:
                try {
                    Tpabellon tpavilion = asPavilion.showById((Integer)data);
                    if (tpavilion!=null)
                    	IFViewList.getInstance().generateSpecificView(event , tpavilion);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_SHOW_INDIVIDUAL_PAVILION_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_CLIENT_INDIVIDUAL:
                try {
                    Tparticipante tclient = asClient.showById((Integer)data);
                    if (tclient!=null)
						IFViewList.getInstance().generateSpecificView(event , tclient);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_SHOW_INDIVIDUAL_CLIENT_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_ASSIGNATION_INDIVIDUAL:
                try {
                    Tasignacion tassignation = asAssignation.show((Integer)data);
                    if (tassignation!=null)
						IFViewList.getInstance().generateSpecificView(event , tassignation);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_SHOW_INDIVIDUAL_ASSIGNATION_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_PARTICIPATION_INDIVIDUAL:
                try {
                    Tparticipacion tparticipation = asParticipation.show((Integer)data);
                    if (tparticipation!=null)
						IFViewList.getInstance().generateSpecificView(event ,tparticipation);
					else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_SHOW_INDIVIDUAL_PARTICIPATION_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_STAND_INDIVIDUAL:
                try {
                    Tstand tstand = asStand.showById((Integer)data);
                    if (tstand!=null)
						IFViewList.getInstance().generateSpecificView(event , tstand);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_SHOW_INDIVIDUAL_STAND_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;

            /** -------- List shows -------- */

            case Event.SHOW_FAIR_LIST:
                try {
                    Collection<Tferia> collec_tferia = asFair.list();
                    if (collec_tferia !=null)
						IFViewList.getInstance().generateSpecificView(event , collec_tferia);
                    else
                    	IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_LIST_FAIR_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_FAIR_LIST_DATES:
                try {
                	Collection<Tferia> collec_tferia = asFair.listDates((Tferia) data);
                	if(collec_tferia != null)
                		IFViewList.getInstance().generateSpecificView(event ,collec_tferia);
                	else
						IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_LIST_FAIR_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_PAVILION_LIST:
                try {
                    Collection<Tpabellon> collec_tpabellon = asPavilion.list();
                    if (collec_tpabellon!=null)
						IFViewList.getInstance().generateSpecificView(event , collec_tpabellon);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_LIST_PAVILION_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_CLIENT_LIST:
                try {
                    Collection<Tparticipante> collec_tparticipante = asClient.list();
                    if (collec_tparticipante!=null)
						IFViewList.getInstance().generateSpecificView(event , collec_tparticipante);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_LIST_CLIENT_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_ASSIGANTION_FAIR:
                try {
                    Collection<Tasignacion> collec_tasignacion = asAssignation.showByIdFair((Integer)data);
                    if (collec_tasignacion!=null) IFViewList.getInstance().generateSpecificView(event , collec_tasignacion);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_LIST_ASSIGNATION_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_ASSIGNATION_PAVILION:
                try {
                    Collection<Tasignacion> collec_tasignacion = asAssignation.showByIdPavilion((Integer)data);
                    if (collec_tasignacion!=null)
						IFViewList.getInstance().generateSpecificView(event ,collec_tasignacion);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_LIST_ASSIGNATION_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_ASSIGANTION_LIST:
                try {
					Collection<Tasignacion> collec_tasignacion = asAssignation.list();
                    if (collec_tasignacion!=null)
						IFViewList.getInstance().generateSpecificView(event ,collec_tasignacion);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_LIST_ASSIGNATION_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_PARTICIPATION_FAIR:
                try {
                    Collection<Tparticipacion> collec_tparticipacion = asParticipation.showByFairId((Integer) data);
                    if (collec_tparticipacion!=null)
						IFViewList.getInstance().generateSpecificView(event ,collec_tparticipacion);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_LIST_PARTICIPATION_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_PARTICIPATION_CLIENT:
                try {
					Collection<Tparticipacion> collec_tparticipacion = asParticipation.showByClientId((Integer) data);
                    if (collec_tparticipacion!=null)
                    	IFViewList.getInstance().generateSpecificView(event ,collec_tparticipacion);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_LIST_PARTICIPATION_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_PARTICIPATION_LIST:
                try {
					Collection<Tparticipacion> collec_tparticipacion = asParticipation.list();
                    if (collec_tparticipacion!=null) IFViewList.getInstance().generateSpecificView(event ,collec_tparticipacion);
                    else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_LIST_PARTICIPATION_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_STAND_ASSIGNATION:
                try {
                	Collection<Tstand> collec_tstand = asStand.showByAssignation((Integer) data);
                	if(collec_tstand != null)
						IFViewList.getInstance().generateSpecificView(event ,collec_tstand);
					else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_LIST_STAND_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_STAND_PARTICIPATION:
                try {
					Collection<Tstand> collec_tstand = asStand.showByAssignation((Integer) data);
					if(collec_tstand != null)
                    	IFViewList.getInstance().generateSpecificView(event ,collec_tstand);
					else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_LIST_STAND_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_STAND_LIST:
                try {
					Collection<Tstand> collec_tstand = asStand.list();
					if(collec_tstand != null)
                    	IFViewList.getInstance().generateSpecificView(event ,collec_tstand);
					else IFDrop.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_LIST_STAND_FAIL, null);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
        }
    }
}

/**
 *  - Especializacion
 *  - Corregir vistas
 * */
