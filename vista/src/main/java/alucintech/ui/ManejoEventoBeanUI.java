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
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import org.primefaces.PrimeFaces;
import org.primefaces.context.PrimeFacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.file.UploadedFile;

/**
 * Clase que se encargará de ejecutar las acciones requeridas por el archivo
 * ManejoEventos.xhtml
 *
 * @author 980014102
 */
//Nombre por el cual la clase será reconocida en el archivo ManejoEventos.xhtml
@ManagedBean(name = "ManejoEventoUI")
@SessionScoped

public class ManejoEventoBeanUI implements Serializable {

    private ManejoEventoHelper manejoEventoHelper;
    private Evento evento;
    private String correo;
    private Identificaadministrador admin;
    private List<Evento> listaEventosAdmin;
    private List<Evento> listaEventos;
    private List<Evento> listaEventosTemp;
    private List<Facultad> facultadesEventoTemp;
    private List<Facultad> facultadesEvento;
    private String fecha;
    private Calendar cal;
    private UploadedFile archivoImagen;

    //Constructor
    public ManejoEventoBeanUI() {
        manejoEventoHelper = new ManejoEventoHelper();
    }

    /**
     * Metodo postconstructor todo lo que este dentro de este metodo sera la
     * primero que haga cuando cargue la pagina
     */
    @PostConstruct
    public void init() {

        //Objeto evento que recibirá los valores ingresados en la página.
        evento = new Evento();

        //Este string servirá para identificar al administrador de eventos mediante el correo ingresado en el lógin
        correo = new String();

        //Lista de eventos que se encuentran en la base de datos.
        listaEventos = manejoEventoHelper.listaEventos();

        //Lista de eventos seleccionados por el usuario.
        listaEventosTemp = new ArrayList();

        //Lista de facultades seleccionadas por el usuario.
        facultadesEventoTemp = new ArrayList();

        //Lista de facultades existentes en la base de datos.
        facultadesEvento = manejoEventoHelper.obtenerFacultades();

        //Se obtiene la fecha actual para establecer los ciclos escolares que aparecerán en los nuevos registros
        String fecha = new String();

    }

    /**
     * Método que que actualiza el registro del evento.
     *
     * @throws IOException
     */
    public void modificarEvento() throws IOException {
        //Arreglo de enteros que almacena los errores encontrados en la validación del evento
        int[] errores = new int[3];

        //Variable que indica si un error ha sido encontrado.
        boolean error = false;

        //Aquí se llena el arreglo dependiendo de los errores encontrados. 
        errores = manejoEventoHelper.validarEvento(evento);

        //Las facultades seleccionadas se asignan al objeto evento.
        evento.setFacultadList(facultadesEventoTemp);

        admin = manejoEventoHelper.identificarAdmin(correo);
        listaEventosAdmin = manejoEventoHelper.listaEventoAdmin(admin);

        //Este if se encarga de cambiar la variable "error" a true en caso de que el arreglo se haya llenado.
        if (errores[0] == 1 || errores[1] == 1) {
            error = true;

        }
        boolean modificacionIlegal = true;
        for (Evento evAdmin : listaEventosAdmin) {
            if (evAdmin.getNumEmpleadoAdministradorEvento().getCorreoAdministrador().getCorreo().equalsIgnoreCase(this.evento.getNumEmpleadoAdministradorEvento().getCorreoAdministrador().getCorreo())) {
                modificacionIlegal = false;
            }
        }

        if (modificacionIlegal) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Solo puede modificar eventos creados por usted"));
            PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        } else {

            //Este if valida si se lleva a cabo la modificación o se muestra algún mensaje de error tomando en cuenta la variable "error"
            if (error == false) {
                
                manejoEventoHelper.modificarEvento(evento);
                actualizarListaEventos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Evento actualizado",""));
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

    public void registroEvento() throws IOException {

        if (evento.getIdEvento() == null) {
            int[] errores = new int[3];
            boolean error = false;
            admin = manejoEventoHelper.identificarAdmin(correo);
            listaEventos = manejoEventoHelper.listaEventos();

            evento.setNumEmpleadoAdministradorEvento(admin);
            evento.setEstadoEvento("Postulado");
            evento.setFacultadList(facultadesEventoTemp);

            errores = manejoEventoHelper.validarEvento(evento);

            if (errores[0] == 1 || errores[1] == 1) {
                error = true;

            }

            if (error == false) {
                if (listaEventos.isEmpty()) {
                    evento.setIdEvento(1);
                } else {
                    evento.setIdEvento(listaEventos.get(listaEventos.size() - 1).getIdEvento() + 1);
                }
                manejoEventoHelper.registroEvento(evento);
                actualizarListaEventos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Evento registrado",""));
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

        } else {
            PrimeFaces.current().executeScript("PF('manageProductDialog3').show()");
        }

    }

    public String getMensajeBotonEliminar() {
        if (hayEventosSeleccionados()) {
            int size = this.listaEventosTemp.size();
            return size > 1 ? size + " Eventos seleccionados" : "1 Evento seleccionado";
        }

        return "Eliminar";
    }

    public boolean hayEventosSeleccionados() {
        return this.listaEventosTemp != null && !this.listaEventosTemp.isEmpty();
    }

    public void onItemUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage();
        msg.setSummary("Item unselected: " + event.getObject().toString());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Método encargado de eliminar el evento.
     */
    public void eliminarEvento() {

        //Objeto identificaadmin para asignarle un administrador de eventos al objeto evento.
        admin = manejoEventoHelper.identificarAdmin(correo);
        //Lista de eventos creados por el administrador de eventos.
        listaEventosAdmin = manejoEventoHelper.listaEventoAdmin(admin);

        this.evento = manejoEventoHelper.eventoSeleccionado(this.evento.getIdEvento());
        boolean borradoIlegal = true;
        for (Evento evAdmin : listaEventosAdmin) {
            if (evAdmin.getNumEmpleadoAdministradorEvento().getCorreoAdministrador().getCorreo().equalsIgnoreCase(this.evento.getNumEmpleadoAdministradorEvento().getCorreoAdministrador().getCorreo())) {
                borradoIlegal = false;
            }
        }

        if (borradoIlegal) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Solo se pueden eliminar eventos creados por usted:", "Seleccione eventos propios"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        } else {
            manejoEventoHelper.eliminarEvento(evento);
            actualizarListaEventos();
            evento = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Evento eliminado",""));
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Solo se pueden eliminar eventos creados por usted:", "Seleccione eventos propios"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        } else {
            manejoEventoHelper.eliminarListaEventos(listaEventosTemp);
            actualizarListaEventos();
            listaEventosTemp = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eventos eliminados",""));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
            PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
        }
    }

    /**
     * Método que reinicia los valores del objeto evento. Este método se activa
     * al cargar la página otra vez
     */
    public void actualizarDatosEvento() {
        this.evento = new Evento();
    }

    /**
     * Método que actualiza la lista de eventos.
     */
    public void actualizarListaEventos() {
        listaEventos = manejoEventoHelper.listaEventos();
    }

    public void subirArchivoImagen(FileUploadEvent event) {
        archivoImagen = event.getFile();
        if (archivoImagen != null) {
            evento.setImagenEvento(archivoImagen.getContent());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado la imágen:", "" + archivoImagen.getFileName()));
        }
    }

    public String getImagenDatos(byte[] eventoImagen) {
        if (eventoImagen == null) {
            return null;
        } else {
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

    public List<Facultad> getFacultadesEventoTemp() {
        return facultadesEventoTemp;
    }

    public void setFacultadesEventoTemp(List<Facultad> facultadesEventoTemp) {
        this.facultadesEventoTemp = facultadesEventoTemp;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public UploadedFile getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(UploadedFile archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

}
