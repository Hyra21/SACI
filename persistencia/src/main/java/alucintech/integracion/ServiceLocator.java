/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.integracion;

import alucintech.DAO.ActividadDAO;
import alucintech.DAO.AlumnoDAO;
import alucintech.DAO.CarnetDAO;
import alucintech.DAO.EventoDAO;
import alucintech.DAO.FacultadDAO;
import alucintech.DAO.IdentificaadministradorDAO;
import alucintech.DAO.IdentificaalumnoDAO;
import alucintech.DAO.IdentificacoordinadorDAO;
import alucintech.DAO.IdentificavsDAO;
import alucintech.DAO.ProgramaeducativoDAO;
import alucintech.DAO.SelloDAO;
import alucintech.DAO.TieneselloDAO;
import alucintech.DAO.TieneselloPKDAO;
import alucintech.DAO.UsuariosDAO;


/**
 *
 * @author total
 */
public class ServiceLocator {
    
    private static ActividadDAO actividadDAO;
    private static AlumnoDAO alumnoDAO;
    private static CarnetDAO carnetDAO;
    private static EventoDAO eventoDAO;
    private static FacultadDAO facultadDAO;
    private static IdentificaadministradorDAO identificaAdministradorDAO;
    private static IdentificaalumnoDAO identificaAlumnoDAO;
    private static IdentificacoordinadorDAO identificaCoordinadorDAO;
    private static IdentificavsDAO identificaVSDAO;
    private static ProgramaeducativoDAO programaEducativoDAO;
    private static SelloDAO selloDAO;
    private static TieneselloDAO tieneSelloDAO;
    private static TieneselloPKDAO tieneSelloPKDAO;
    private static UsuariosDAO usuariosDAO;
    
    /**
     * se crea las instancias si estas no existen
     * @return 
     */
    
    public static ActividadDAO getInstanceActividadDAO(){
        if(actividadDAO == null){
            actividadDAO = new ActividadDAO();
            return actividadDAO;
        } else{
            return actividadDAO;
        }
    }
    
    public static AlumnoDAO getInstanceAlumnoDAO(){
        if(alumnoDAO == null){
            alumnoDAO = new AlumnoDAO();
            return alumnoDAO;
        } else{
            return alumnoDAO;
        }
    }
    
    public static CarnetDAO getInstanceCarnetDAO(){
        if(carnetDAO == null){
            carnetDAO = new CarnetDAO();
            return carnetDAO;
        } else{
            return carnetDAO;
        }
    }
    
    public static EventoDAO getInstanceEventoDAO(){
        if(eventoDAO == null){
            eventoDAO = new EventoDAO();
            return eventoDAO;
        } else{
            return eventoDAO;
        }
    }
    
    public static FacultadDAO getInstanceFacultadDAO(){
        if(facultadDAO == null){
            facultadDAO = new FacultadDAO();
            return facultadDAO;
        } else{
            return facultadDAO;
        }
    }
    
    public static IdentificaadministradorDAO getInstanceIdentificaadministradorDAO(){
        if(identificaAdministradorDAO == null){
            identificaAdministradorDAO = new IdentificaadministradorDAO();
            return identificaAdministradorDAO;
        } else{
            return identificaAdministradorDAO;
        }
    }
    
    public static IdentificaalumnoDAO getInstanceIdentificaalumnoDAO(){
        if(identificaAlumnoDAO == null){
            identificaAlumnoDAO = new IdentificaalumnoDAO();
            return identificaAlumnoDAO;
        } else{
            return identificaAlumnoDAO;
        }
    }
    
    public static IdentificacoordinadorDAO getInstanceIdentificacoordinadorDAO(){
        if(identificaCoordinadorDAO == null){
            identificaCoordinadorDAO = new IdentificacoordinadorDAO();
            return identificaCoordinadorDAO;
        } else{
            return identificaCoordinadorDAO;
        }
    }
    
    public static IdentificavsDAO getInstanceIdentificavsDAO(){
        if(identificaCoordinadorDAO == null){
            identificaVSDAO = new IdentificavsDAO();
            return identificaVSDAO;
        } else{
            return identificaVSDAO;
        }
    }
    
    public static ProgramaeducativoDAO getInstanceProgramaeducativoDAO(){
        if(programaEducativoDAO == null){
            programaEducativoDAO = new ProgramaeducativoDAO();
            return programaEducativoDAO;
        } else{
            return programaEducativoDAO;
        }
    }
    
    public static SelloDAO getInstanceSelloDAO(){
        if(selloDAO == null){
            selloDAO = new SelloDAO();
            return selloDAO;
        } else{
            return selloDAO;
        }
    }
    
    public static TieneselloDAO getInstanceTieneselloDAO(){
        if(tieneSelloDAO == null){
            tieneSelloDAO = new TieneselloDAO();
            return tieneSelloDAO;
        } else{
            return tieneSelloDAO;
        }
    }
    
    public static TieneselloPKDAO getInstanceTieneselloPKDAO(){
        if(tieneSelloPKDAO == null){
            tieneSelloPKDAO = new TieneselloPKDAO();
            return tieneSelloPKDAO;
        } else{
            return tieneSelloPKDAO;
        }
    }
    
    public static UsuariosDAO getInstanceUsuariosDAO(){
        if(usuariosDAO == null){
            usuariosDAO = new UsuariosDAO();
            return usuariosDAO;
        } else{
            return usuariosDAO;
        }
    }
}
