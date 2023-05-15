/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.List;
import alucintech.DAO.ActividadDAO;
import alucintech.DAO.AlumnoDAO;
import alucintech.entidad.Alumno;
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
import alucintech.entidad.Usuarios;

/**
 *
 * @author lukki
 */
public class test {

    public static void main(String[] args) {

        UsuariosDAO user = new UsuariosDAO();
        Usuarios obj = new Usuarios();
        
        //user.save(obj);
        List<Usuarios> listaUsuarios = new ArrayList();
        
        listaUsuarios = user.findAll();
        
        for(Usuarios u : listaUsuarios){
            System.out.println("Correo: " + u.getCorreo());
        }
    }
}
