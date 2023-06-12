package alucintech.ui;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import alucintech.entidad.Evento;
import alucintech.entidad.Facultad;
import alucintech.entidad.Identificaadministrador;
import alucintech.helper.ManejoEventoHelper;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

/**
 * Clase que se encargará de ejecutar las acciones requeridas por el archivo
 * ManejoEventos.xhtml.
 *
 * @author 980014102
 */
//Nombre por el cual la clase será reconocida en el archivo ManejoEventos.xhtml.
@ManagedBean(name = "ManejoEventoUI")
@SessionScoped

public class ManejoEventoBeanUI implements Serializable {

    //Objeto que se apoya al bean con los métodos que afectan a la base de datos.
    private ManejoEventoHelper manejoEventoHelper;
    //Objeto que se usará para almacenar la información de los eventos ingresados por el usuario.
    private Evento evento;
    //String que almacena el correo ingresado en el login.
    private String correo;
    //Objeto con la información del administrador.
    private Identificaadministrador admin;
    //Lista de eventos que le pertenecen al administrador.
    private List<Evento> listaEventosAdmin;
    //Lista de eventos que se encuentran en la base de datos.
    private List<Evento> listaEventos;
    //Lista de eventos seleccionados por el usuario.
    private List<Evento> listaEventosTemp;
    //Lista de IDs de las facultades seleccionadas por el usuario.
    private Integer[] idFacultades;
    //Lista de facultades almacenadas en la base de datos.
    private List<Facultad> facultadesEvento;
    //Archivo que recibirá la imagen ingresada por el usuario.
    private UploadedFile archivoImagen;
    
    private LocalTime time8;
    private LocalTime time9;
    private LocalTime minTime;
    private LocalTime maxTime;

//    private boolean mostrarForm;
//    private boolean mostrarForm2;
    
    //Constructor
    public ManejoEventoBeanUI() {
        manejoEventoHelper = new ManejoEventoHelper();
//        mostrarForm = true;
//        mostrarForm2 = false;
    }

    /**
     * Metodo postconstructor todo lo que este dentro de este metodo sera la
     * primero que haga cuando cargue la pagina.
     */
    @PostConstruct
    public void init() {

        //Objeto evento que recibirá los valores ingresados en la página.
        evento = new Evento();

        //Este string servirá para identificar al administrador de eventos mediante el correo ingresado en el lógin.
        correo = new String();

        //Lista de eventos que se encuentran en la base de datos.
        listaEventos = manejoEventoHelper.listaEventos();

        //Lista de eventos seleccionados por el usuario.
        listaEventosTemp = new ArrayList();

        //Lista de facultades existentes en la base de datos.
        facultadesEvento = manejoEventoHelper.obtenerFacultades();
        
        //Variables para el componente datePicker que servirá para obtener el horario.
        
        //minTime es para asignarle que solo deje elegir a partir de las 7:00 AM.
        minTime = LocalTime.of(7, 0);
        //mazTime es para asignarle que solo deje elegir hasta las 10:00 PM.
        maxTime = LocalTime.of(22, 0);

    }

//    public void setMostrarForm(boolean mostrarForm) {
//        this.mostrarForm = mostrarForm;
//    }
    
//    public void mostrarForm(ActionEvent event) {
//        mostrarForm = true;
//        mostrarForm2 = false;
//        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form");
//        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form2");
//    }
//
//    public void mostrarForm2(ActionEvent event) {
//        mostrarForm = false;
//        mostrarForm2 = true;
//        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form");
//        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form2");
//    }
    
    /**
     * Método que que modifica registros de los eventos.
     *
     * @throws IOException
     */
    public void modificarEvento() throws IOException {
        //Arreglo de enteros que almacena los errores encontrados en la validación del evento.
        int[] errores = new int[3];

        //Variable que indica si un error ha sido encontrado.
        boolean error = false;

        //Aquí se llena el arreglo dependiendo de los errores encontrados. 
        errores = manejoEventoHelper.validarEvento(evento);

        //La variable admin almacena la información del administrador dueño del correo que se envía como parámetro.
        admin = manejoEventoHelper.identificarAdmin(correo);

        //Se obtiene la lista de eventos que le pertenecen al administrador que se envía como parámetro, que luego se usará para validar si intenta modificar eventos ajenos.
        listaEventosAdmin = manejoEventoHelper.listaEventoAdmin(admin);

        //Este if se encarga de cambiar la variable "error" a true en caso de que el arreglo se haya llenado, indicando que existe un error.
        if (errores[0] == 1 || errores[1] == 1) {
            error = true;

        }

        //Este booleano válida si el administrador intenta modificar un evento que no haya creado.
        boolean modificacionIlegal = true;

        //Este for each se encarga de ir comparando los eventos del administrador con el que intenta modificar comparando el correo de su administrador.
        for (Evento evAdmin : listaEventosAdmin) {
            if (evAdmin.getNumEmpleadoAdministradorEvento().getCorreoAdministrador().getCorreo().equalsIgnoreCase(this.evento.getNumEmpleadoAdministradorEvento().getCorreoAdministrador().getCorreo())) {
                modificacionIlegal = false;
            }
        }

        if (modificacionIlegal) {
            //Se cambia y muestra el mensaje que aparecerá en caso de que se identifique un borrado ilegal.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Solo puede modificar eventos creados por usted"));
            PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        } else {

            //Este if valida si se lleva a cabo la modificación o se muestra algún mensaje de error tomando en cuenta la variable "error".
            if (error == false) {
                //Método que modifica al evento en la base de datos.
                manejoEventoHelper.modificarEvento(evento);
                manejoEventoHelper.asignarFacultadesElegidas(idFacultades, evento);
                //Actualiza la lista de eventos para que se aprecien los cambios en la consulta.
                actualizarListaEventos();

                //Se cambia y muestra el mensaje que aparecerá al terminar la modificación.
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Evento actualizado", ""));
                PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-products");

            } else {
                if (errores[0] == 1 && errores[1] == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El evento ya existe y la fecha no está dentro del rango permitido:", "Intente de nuevo"));
                }
                if (errores[0] == 1 && errores[1] == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La fecha no está dentro del rango permitido:", "Intente de nuevo"));
                }
                if (errores[0] == 0 && errores[1] == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El evento ya existe", "Intente de nuevo"));
                }
            }
        }
    }

    /**
     * Método que registra los eventos en la base de datos.
     *
     * @throws IOException
     */
    public void registroEvento() throws IOException {
        //Este if verifica si lo que se intenta hacer es un registro o modificación al revisar si el evento ya tiene un ID asignado.
        if (evento.getIdEvento() == null) {
            //Arreglo de enteros que almacena los errores encontrados en la validación del evento
            int[] errores = new int[3];

            //Variable que indica si un error ha sido encontrado.
            boolean error = false;

            //La variable admin almacena la información del administrador dueño del correo que se envía como parámetro
            admin = manejoEventoHelper.identificarAdmin(correo);

            //Se obtiene la lista de eventos almacenados en la base de datos que luego se usará para validar si existen eventos registrados.
            listaEventos = manejoEventoHelper.listaEventos();

            //Se asigna el administrador que registra el evento
            evento.setNumEmpleadoAdministradorEvento(admin);

            //Se cambia el estado del evento
            evento.setEstadoEvento("Postulado");
            
            //Aquí se llena el arreglo dependiendo de los errores encontrados. 
            errores = manejoEventoHelper.validarEvento(evento);

            //Se revisan los errores encontrados
            if (errores[0] == 1 || errores[1] == 1) {
                error = true;

            }

            if (error == false) {
                //Si no hay eventos se asigna el id 1, en caso contrario se asigna un id con un valor 1 unidad mayor que la id anterior
                if (listaEventos.isEmpty()) {
                    evento.setIdEvento(1);
                } else {
                    evento.setIdEvento(listaEventos.get(listaEventos.size() - 1).getIdEvento() + 1);
                }

                //Se registra el evento
                manejoEventoHelper.registroEvento(evento);
                manejoEventoHelper.asignarFacultadesElegidas(idFacultades, evento);

                //Se actualiza la lista de los eventos para que se vea reflejada en la consulta
                actualizarListaEventos();

                //Limpiar las facultades seleccionadas, esto sirve para evitar que nuevos registros o modificaciones aparezcan con facultades ya seleccionadas
                for (int j = 0; j < idFacultades.length; j++) {
                    idFacultades[j] = 0;
                }

                //Se cambia y muestra el mensaje que aparecerá al terminar el registro
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Evento registrado", ""));
                PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-products");

                //Dependiendo del tipo de error, se determina la alerta.
            } else {
                if (errores[0] == 1 && errores[1] == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El evento ya existe y la fecha no está dentro del rango permitido:", "Intente de nuevo"));
                }
                if (errores[0] == 1 && errores[1] == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La fecha no está dentro del rango permitido:", "Intente de nuevo"));
                }
                if (errores[0] == 0 && errores[1] == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El evento ya existe", "Intente de nuevo"));
                }
            }

        } else {
            //En caso de detectar una modificación se ejecuta el cuadro de confirmación que acepta o cancela la modificación.
            PrimeFaces.current().executeScript("PF('manageProductDialog3').show()");
        }

    }

    /**
     * Este método determina el texto que aparecerá en el boton para
     * eliminaciones multiples.
     *
     * @return
     */
    public String getMensajeBotonEliminar() {
        if (hayEventosSeleccionados()) {
            int size = this.listaEventosTemp.size();
            return size > 1 ? size + " Eventos seleccionados" : "1 Evento seleccionado";
        }

        return "Eliminación múltiple";
    }

    /**
     * Método que valida si hay eventos seleccionados, con el fin de cambiar el
     * estado del boton de eliminación múltiple.
     *
     * @return
     */
    public boolean hayEventosSeleccionados() {
        return this.listaEventosTemp != null && !this.listaEventosTemp.isEmpty();
    }

    /**
     * Método encargado de eliminar el evento.
     */
    public void eliminarEvento() {

        //Objeto identificaadmin para asignarle un administrador de eventos al objeto evento.
        admin = manejoEventoHelper.identificarAdmin(correo);
        //Lista de eventos creados por el administrador de eventos.
        listaEventosAdmin = manejoEventoHelper.listaEventoAdmin(admin);

        //Este booleano válida si el administrador intenta eliminar un evento que no haya creado.
        boolean borradoIlegal = true;

        //Este for each se encarga de ir comparando los eventos del administrador con el que intenta eliminar comparando el correo de su administrador.
        for (Evento evAdmin : listaEventosAdmin) {
            if (evAdmin.getNumEmpleadoAdministradorEvento().getCorreoAdministrador().getCorreo().equalsIgnoreCase(this.evento.getNumEmpleadoAdministradorEvento().getCorreoAdministrador().getCorreo())) {
                borradoIlegal = false;
            }
        }

        if (borradoIlegal) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Solo se pueden eliminar eventos creados por usted:", "Seleccione eventos propios"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        } else {
            //Método que elimina al evento en la base de datos.
            manejoEventoHelper.eliminarEvento(evento);
            //Actualiza la lista de eventos para que se aprecien los cambios en la consulta.
            actualizarListaEventos();
            //Se refrescan los datos del objeto evento.
            evento = null;

            //Se cambia y muestra el mensaje que aparecerá al terminar la eliminación.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Evento eliminado", ""));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        }
    }

    /**
     * Método que elimina la lista de eventos enviados como parámetro de la base
     * de datos.- Se utiiza para eliminar eventos cancelados.
     *
     * @param eventos
     */
    public void eliminarListaEventos() {
        boolean borradoIlegal = true;

        //Objeto identificaadmin para asignarle un administrador de eventos al objeto evento.
        admin = manejoEventoHelper.identificarAdmin(correo);

        //Lista de eventos creados por el administrador de eventos.
        listaEventosAdmin = manejoEventoHelper.listaEventoAdmin(admin);

        for (Evento evTemp : listaEventosTemp) {
            borradoIlegal = true;
            for (Evento evAdmin : listaEventosAdmin) {
                if (evAdmin.getNumEmpleadoAdministradorEvento().getCorreoAdministrador().getCorreo().equalsIgnoreCase(evTemp.getNumEmpleadoAdministradorEvento().getCorreoAdministrador().getCorreo())) {
                    borradoIlegal = false;
                }
            }
        }

        if (borradoIlegal) {
            //Se cambia y muestra el mensaje que aparecerá en caso de que se identifique un borrado ilegal.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Solo se pueden eliminar eventos creados por usted:", "Seleccione eventos propios"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        } else {
            //Método que elimina los eventos seleccionados de la base de datos.
            manejoEventoHelper.eliminarListaEventos(listaEventosTemp);

            //Actualiza la lista de eventos para que se aprecien los cambios en la consulta
            actualizarListaEventos();

            //Refresca la lista de los eventos seleccionados.
            listaEventosTemp = null;

            //Se cambia y muestra el mensaje que aparecerá al terminar el registro
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eventos eliminados", ""));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
            PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
        }
    }

    /**
     * Método que reinicia los valores del objeto evento. Este método se activa
     * al cargar la página otra vez
     */
    public void actualizarDatosEvento() {
        //Actualiza los datos del atributo evento, Se usa para iniciar los registros con un objeto vacío.
        this.evento = new Evento();
    }

    /**
     * Método que actualiza la lista de eventos.
     */
    public void actualizarListaEventos() {
        //Actualiza la lista de eventos, se usa para actualizar los datos de la consulta.
        listaEventos = manejoEventoHelper.listaEventos();
    }

    /**
     * Este método se usa para almacenar la imagen proporcionada por el usuario
     * en el objeto evento.
     *
     * @param event
     */
    public void subirArchivoImagen(FileUploadEvent event) {
        //Se recibe el dato tipo File.
        archivoImagen = event.getFile();

        if (archivoImagen != null) {
            //Se almacena en "evento" obteniendo sus datos en forma de byte[].
            evento.setImagenEvento(archivoImagen.getContent());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado la imágen:", "" + archivoImagen.getFileName()));
        }
    }

    /**
     * Este método se encarga de obtener la imágen almacenada en el objeto
     * "evento" en formato Base64 para que pueda ser mostrado.
     *
     * @param eventoImagen
     * @return
     */
    public String getImagenDatos(byte[] eventoImagen) {
        if (eventoImagen == null) {
            return null;
        } else {
            //Aquí se hace la transformación de byte [] a Base 64.
            String imagenBase64 = Base64.getEncoder().encodeToString(eventoImagen);
            return imagenBase64;
        }

    }

// Getters y setters    
    public List<Evento> getListaEventosTemp() {
        return listaEventosTemp;
    }

    public void setListaEventosTemp(List<Evento> listaEventosTemp) {
        this.listaEventosTemp = listaEventosTemp;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Evento> getListaEventosAdmin() {
        return listaEventosAdmin;
    }

    public void setListaEventosAdmin(List<Evento> listaEventosAdmin) {
        this.listaEventosAdmin = listaEventosAdmin;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    public List<Facultad> getFacultadesEvento() {
        return facultadesEvento;
    }

    public void setFacultadesEvento(List<Facultad> facultadesEvento) {
        this.facultadesEvento = facultadesEvento;
    }

    public UploadedFile getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(UploadedFile archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

    public LocalTime getTime8() {
        return time8;
    }

    public void setTime8(LocalTime time8) {
        this.time8 = time8;
    }
    
    public LocalTime getTime9() {
        return time9;
    }

    public void setTime9(LocalTime time9) {
        this.time8 = time9;
    }
    
    public LocalTime getMinTime() {
        return minTime;
    }

    public void setMinTime(LocalTime minTime) {
        this.minTime = minTime;
    }

    public LocalTime getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(LocalTime maxTime) {
        this.maxTime = maxTime;
    }
    
    
//    public boolean isMostrarForm() {
//        return mostrarForm;
//    }
//    
//    public boolean isMostrarForm2() {
//        return mostrarForm2;
//    }

    public Integer[] getIdFacultades() {
        return idFacultades;
    }

    public void setIdFacultades(Integer[] idFacultades) {
        this.idFacultades = idFacultades;
    }
}
