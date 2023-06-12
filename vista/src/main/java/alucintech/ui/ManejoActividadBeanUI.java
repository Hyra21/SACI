package alucintech.ui;

import alucintech.entidad.Actividad;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import alucintech.entidad.Evento;
import alucintech.entidad.Identificaadministrador;
import alucintech.entidad.Programaeducativo;
import alucintech.entidad.Sello;
import alucintech.helper.ManejoActividadHelper;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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
@ManagedBean(name = "ManejoActividadUI")
@SessionScoped

public class ManejoActividadBeanUI implements Serializable {

    //Objeto que se apoya al bean con los métodos que afectan a la base de datos.
    private ManejoActividadHelper manejoActividadHelper;
    //Objeto que se usará para almacenar la información de las actividades ingresadas por el usuario.
    private Actividad actividad;
    //String que almacena el correo ingresado en el login.
    private String correo;
    //Objeto con la información del administrador.
    private Identificaadministrador admin;
    //Lista de actividades que le pertenecen al administrador.
    private List<Actividad> listaActividadesAdmin;
    //Lista de actividades que se encuentran en la base de datos.
    private List<Actividad> listaActividades;
    //Lista de actividades seleccionadas por el usuario.
    private List<Actividad> listaActividadesTemp;
    //Lista de los programas educativos que se encuentran en la base de datos.
    private List<Programaeducativo> listaProgramas;
    //Lista de los códigos de los programas educativos que seleccionó el usuario.
    private Integer[] listaProgramasTemp;
    //Lista de eventos registrados en la base de datos.
    private List<Evento> listaEventos;
    //Objeto que se usará para asignar los eventos ingresados por el usuario a la actividad.
    private Evento evento;
    //Lista de los eventos guardados en la base de datos.
    private Integer eventoSeleccionado;
    //Objeto tipo Sello que se usará para almacenar la información del sello ingresada por el usuario
    private Sello sello;
    //Archivo que recibirá la imagen ingresada por el usuario.
    private UploadedFile archivoImagen;

    private LocalTime minTime;
    private LocalTime maxTime;
    private LocalTime horaInicioActividad;
    private LocalTime horaFinActividad;

    //Constructor
    public ManejoActividadBeanUI() {
        manejoActividadHelper = new ManejoActividadHelper();

    }

    /**
     * Metodo postconstructor todo lo que este dentro de este metodo sera la
     * primero que haga cuando cargue la pagina.
     */
    @PostConstruct
    public void init() {

        //Objeto actividad que recibirá los valores ingresados en la página.
        actividad = new Actividad();

        //Este string servirá para identificar al administrador de eventos mediante el correo ingresado en el lógin.
        correo = new String();

        //Lista de eventos que se encuentran en la base de datos.
        listaActividades = manejoActividadHelper.consultarActividades();

        //Lista de eventos seleccionados por el usuario.
        listaActividadesTemp = new ArrayList();

        //Lista de programas educativos que se encuentran en la base de datos.
        listaProgramas = manejoActividadHelper.consultarProgramas();

        //Lista de eventos registrados en la base de datos.
        listaEventos = manejoActividadHelper.consultaEventos();

        //Objeto tipo Evento que recibirá los valores ingresados en la página.
        evento = new Evento();

        //Objeto tipo Sello que recibirá los valores ingresados en la página.
        sello = new Sello();

        //minTime es para asignarle que solo deje elegir a partir de las 7:00 AM.
        minTime = LocalTime.of(7, 0);

        //mazTime es para asignarle que solo deje elegir hasta las 10:00 PM.
        maxTime = LocalTime.of(22, 0);

    }

    /**
     * Método que procesa el registrar las actividades en la base de datos.
     *
     * @throws IOException
     */
    public void registroActividad() throws IOException {
        //Este if verifica si lo que se intenta hacer es un registro o modificación al revisar si el evento ya tiene un ID asignado.
        if (actividad.getIdActividad() == null) {
            //Arreglo de enteros que almacena los errores encontrados en la validación del evento
            int[] errores = new int[3];

            //Variable que indica si un error ha sido encontrado.
            boolean error = false;

            //Variable que indica si ya se terminó de subir la actividad.
            boolean errorP = false;

            //La variable admin almacena la información del administrador dueño del correo que se envía como parámetro
            admin = manejoActividadHelper.identificarAdmin(correo);

            //Se obtiene la lista de eventos almacenados en la base de datos que luego se usará para validar si existen eventos registrados.
            listaActividades = manejoActividadHelper.consultarActividades();

            //Se asigna el administrador que registra el evento
            actividad.setNumEmpleadoAdministradorActividad(admin);

            //Se cambia el estado de la actividad.
            actividad.setEstadoActividad("Postulada");

            //Se busca el evento seleccionado mediante su ID.
            evento = manejoActividadHelper.obtenerEvento(eventoSeleccionado);

            //Se asigna el evento seleccionado a la actividad.
            actividad.setIdEvento(evento);

            //Aquí se llena el arreglo dependiendo de los errores encontrados. 
            errores = manejoActividadHelper.validarActividad(actividad);

            // Convertir LocalTime a LocalDateTime
            LocalDate localDate = LocalDate.now();
            LocalDateTime localDateTime = LocalDateTime.of(localDate, horaInicioActividad);
            LocalDateTime localDateTime2 = LocalDateTime.of(localDate, horaFinActividad);

            // Convertir LocalDateTime a Date y se asignan las horas seleccionadas a la actividad
            actividad.setHorarioInicioActividad(actividad.getHorarioInicioActividad().from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
            actividad.setHorarioFinActividad(actividad.getHorarioFinActividad().from(localDateTime2.atZone(ZoneId.systemDefault()).toInstant()));

            //Se revisan los errores encontrados
            if (errores[0] == 1 || errores[1] == 1 || errores[2] == 1) {
                error = true;
                System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            }

            if (error == false) {
                //Si no hay actividades se asigna el id 100, en caso contrario se asigna un id con un valor 1 unidad mayor que la id anterior
                if (listaActividades.isEmpty()) {
                    actividad.setIdActividad(100);
                } else {
                    actividad.setIdActividad(listaActividades.get(listaActividades.size() - 1).getIdActividad() + 1);
                }

                if (!manejoActividadHelper.validarProgramasEducativos(eventoSeleccionado, listaProgramasTemp)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Los programas educativos no concuerdan con la facultad del evento", "Intente de nuevo"));
                    actividad.setIdActividad(null);
                } else {
                    //Se registra la actividad.
                    manejoActividadHelper.registrarActividad(actividad);

                    manejoActividadHelper.asignarProgramasElegidos(listaProgramasTemp, actividad);

                    sello.setIdActividad(actividad);

                    List<Sello> listaSellos = manejoActividadHelper.consultarSellos();

                    //Si no hay sellos se asigna el id 1, en caso contrario se asigna un id con un valor 1 unidad mayor que la id anterior
                    if (listaActividades.isEmpty()) {
                        sello.setIdSello(1);
                    } else {
                        sello.setIdSello(listaSellos.get(listaSellos.size() - 1).getIdSello() + 1);
                    }

                    List<Sello> sellos = new ArrayList();

                    sellos.add(sello);

                    actividad.setSelloList(sellos);

                    sello = new Sello();
                    //Se actualiza la lista de las actividades para que se vea reflejada en la consulta
                    actualizarListaActividades();

                    //Se cambia y muestra el mensaje que aparecerá al terminar el registro
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actividad registrada", ""));
                    PrimeFaces.current().executeScript("PF('manageProductDialog2').hide()");
                    PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
                }
                //Dependiendo del tipo de error, se determina la alerta.
            } else {
                if (errores[0] == 1 && errores[1] == 1 && errores[2] == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El evento ya existe, la fecha no está dentro del rango permitido y el ponente ya tiene ese horario asignado en otra actividad:", "Intente de nuevo"));
                }
                if (errores[0] == 1 && errores[1] == 1 && errores[2] == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El evento ya existe y la fecha no está dentro del rango permitido:", "Ingrese otros datos"));
                }
                if (errores[0] == 1 && errores[1] == 0 && errores[2] == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La fecha no está dentro del rango permitido:", "Ingrese otra fecha"));
                }
                if (errores[0] == 0 && errores[1] == 1 && errores[2] == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El evento ya existe", ""));
                }
                if (errores[0] == 0 && errores[1] == 1 && errores[2] == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El evento ya existe y el ponente ya tiene ese horario asignado en otra actividad:", "Intente otros datos"));
                }
                if (errores[0] == 0 && errores[1] == 0 && errores[2] == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El ponente ya tiene ese horario asignado en otra actividad:", "Ingrese otra fecha"));
                }
            }

        } else {
            //En caso de detectar una modificación se ejecuta el cuadro de confirmación que acepta o cancela la modificación.
            PrimeFaces.current().executeScript("PF('manageProductDialog3').show()");
        }
    }

    /**
     * Método que procesa el modificar los registros de las actividades.
     *
     * @throws IOException
     */
    public void modificacionActividad() {
        //Arreglo de enteros que almacena los errores encontrados en la validación del evento.
        int[] errores = new int[3];

        //Variable que indica si un error ha sido encontrado.
        boolean error = false;

        //error provocado por elegir una facultad.
        //Aquí se llena el arreglo dependiendo de los errores encontrados. 
        errores = manejoActividadHelper.validarActividad(actividad);

        //La variable admin almacena la información del administrador dueño del correo que se envía como parámetro.
        admin = manejoActividadHelper.identificarAdmin(correo);

        //Se obtiene la lista de eventos que le pertenecen al administrador que se envía como parámetro, que luego se usará para validar si intenta modificar eventos ajenos.
        listaActividadesAdmin = manejoActividadHelper.listaActividadesAdmin(admin);

        // Convertir LocalTime a LocalDateTime
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, horaInicioActividad);
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, horaFinActividad);

        // Convertir LocalDateTime a Date y se asignan las horas seleccionadas a la actividad.
        actividad.setHorarioInicioActividad(actividad.getHorarioInicioActividad().from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
        actividad.setHorarioFinActividad(actividad.getHorarioFinActividad().from(localDateTime2.atZone(ZoneId.systemDefault()).toInstant()));

        //Este if se encarga de cambiar la variable "error" a true en caso de que el arreglo se haya llenado, indicando que existe un error.
        if (errores[0] == 1 || errores[1] == 1 || errores[2] == 1) {
            error = true;

        }

        //Este booleano válida si el administrador intenta modificar un evento que no haya creado.
        boolean modificacionIlegal = true;

        //Este for each se encarga de ir comparando los eventos del administrador con el que intenta modificar comparando el correo de su administrador.
        for (Actividad actAdmin : listaActividadesAdmin) {
            if (actAdmin.getNumEmpleadoAdministradorActividad().getCorreoAdministrador().getCorreo().equalsIgnoreCase(this.actividad.getNumEmpleadoAdministradorActividad().getCorreoAdministrador().getCorreo())) {
                modificacionIlegal = false;
            }
        }

        if (modificacionIlegal) {
            //Se cambia y muestra el mensaje que aparecerá en caso de que se identifique un borrado ilegal.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Solo puede modificar actividades creadas por usted"));
            PrimeFaces.current().executeScript("PF('manageProductDialog2').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        } else {

            //Este if valida si se lleva a cabo la modificación o se muestra algún mensaje de error tomando en cuenta la variable "error".
            if (error == false) {
                //Método que modifica al evento en la base de datos.
                if (!manejoActividadHelper.validarProgramasEducativos(eventoSeleccionado, listaProgramasTemp)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Los programas educativos no concuerdan con la facultad del evento", "Intente de nuevo"));
                } else {
                    manejoActividadHelper.asignarProgramasElegidos(listaProgramasTemp, actividad);
                    manejoActividadHelper.modificarActividad(actividad);
                    manejoActividadHelper.actualizarSello(sello);
                    //Se actualiza la lista de las actividades para que se vea reflejada en la consulta
                    actualizarListaActividades();

                    //Se cambia y muestra el mensaje que aparecerá al terminar la modificación.
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actividad actualizada", ""));
                    PrimeFaces.current().executeScript("PF('manageProductDialog2').hide()");
                    PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
                }

            } else {
                if (errores[0] == 1 && errores[1] == 1 && errores[2] == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La actividad ya existe, la fecha no está dentro del rango permitido y el ponente ya tiene ese horario asignado en otra actividad:", "Intente de nuevo"));
                }
                if (errores[0] == 1 && errores[1] == 1 && errores[2] == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La actividad ya existe y la fecha no está dentro del rango permitido:", "Ingrese otros datos"));
                }
                if (errores[0] == 1 && errores[1] == 0 && errores[2] == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La fecha no está dentro del rango permitido:", "Ingrese otra fecha"));
                }
                if (errores[0] == 0 && errores[1] == 1 && errores[2] == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La actividad ya existe", ""));
                }
                if (errores[0] == 0 && errores[1] == 1 && errores[2] == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La actividad ya existe y el ponente ya tiene ese horario asignado en otra actividad:", "Intente otros datos"));
                }
                if (errores[0] == 0 && errores[1] == 0 && errores[2] == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El ponente ya tiene ese horario asignado en otra actividad:", "Ingrese otra fecha"));
                }
            }



        }
    }

    /**
     * Método que procesa la eliminación particular de una actividad.
     */
    public void eliminarActividad() {
        //Objeto identificaadmin para asignarle un administrador de eventos al objeto evento.
        admin = manejoActividadHelper.identificarAdmin(correo);
        //Lista de eventos creados por el administrador de eventos.
        listaActividadesAdmin = manejoActividadHelper.listaActividadesAdmin(admin);

        //Este booleano válida si el administrador intenta eliminar un evento que no haya creado.
        boolean borradoIlegal = true;

        //Este for each se encarga de ir comparando los eventos del administrador con el que intenta eliminar comparando el correo de su administrador.
        for (Actividad actAdmin : listaActividadesAdmin) {
            if (actAdmin.getNumEmpleadoAdministradorActividad().getCorreoAdministrador().getCorreo().equalsIgnoreCase(this.actividad.getNumEmpleadoAdministradorActividad().getCorreoAdministrador().getCorreo())) {
                borradoIlegal = false;
            }
        }

        if (borradoIlegal) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Solo se pueden eliminar actividades creadas por usted:", "Seleccione actividades propias"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        } else {
            //Método que elimina la actividad de la base de datos.
            manejoActividadHelper.eliminarActividad(actividad);
            //Actualiza la lista de actividades para que se aprecien los cambios en la consulta.
            actualizarListaActividades();
            //Se refrescan los datos del objeto actividad.
            actividad = null;

            //Se cambia y muestra el mensaje que aparecerá al terminar la eliminación.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actividad eliminada", ""));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        }
    }

    /**
     * Método que procesa la eliminación de varias actividades.
     */
    public void eliminarListaActividades() {
        boolean borradoIlegal = true;

        //Objeto identificaadmin para asignarle un administrador de eventos al objeto evento.
        admin = manejoActividadHelper.identificarAdmin(correo);

        //Lista de eventos creados por el administrador de eventos.
        listaActividadesAdmin = manejoActividadHelper.listaActividadesAdmin(admin);

        for (Actividad actTemp : listaActividadesTemp) {
            borradoIlegal = true;
            for (Actividad actAdmin : listaActividadesAdmin) {
                if (actAdmin.getNumEmpleadoAdministradorActividad().getCorreoAdministrador().getCorreo().equalsIgnoreCase(this.actividad.getNumEmpleadoAdministradorActividad().getCorreoAdministrador().getCorreo())) {
                    borradoIlegal = false;
                }
            }
        }

        if (borradoIlegal) {
            //Se cambia y muestra el mensaje que aparecerá en caso de que se identifique un borrado ilegal.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Solo se pueden eliminar actividades creadas por usted:", "Seleccione actividades propias"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        } else {
            //Método que elimina los eventos seleccionados de la base de datos.
            manejoActividadHelper.eliminarListaActividades(listaActividadesTemp);

            //Actualiza la lista de eventos para que se aprecien los cambios en la consulta
            actualizarListaActividades();

            //Refresca la lista de los eventos seleccionados.
            listaActividadesTemp = null;

            //Se cambia y muestra el mensaje que aparecerá al terminar el registro
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actividades eliminadas", ""));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
            PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
        }
    }

    /**
     * Método que se encarga de guardar las imágenes que suba el usuario en la
     * actividad.
     *
     * @param event
     */
    public void subirArchivoImagenActividad(FileUploadEvent event) {
        //Se recibe el dato tipo File.
        archivoImagen = event.getFile();

        if (archivoImagen != null) {
            //Se almacena en "actividad" obteniendo sus datos en forma de byte[].
            actividad.setImagenActividad(archivoImagen.getContent());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado la imágen:", "" + archivoImagen.getFileName()));
        }
    }

    /**
     * Método que se encarga de guardar las imágenes que suba el usuario en el
     * objeto "sello".
     *
     * @param event
     */
    public void subirArchivoImagenSello(FileUploadEvent event) {
        //Se recibe el dato tipo File.
        archivoImagen = event.getFile();

        if (archivoImagen != null) {
            //Se almacena en "actividad" obteniendo sus datos en forma de byte[].
            sello.setImagenSello(archivoImagen.getContent());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado la imágen:", "" + archivoImagen.getFileName()));
        }
    }

    /**
     * Método que convierte las imágenes a Base64 para que se puedan mostrar en
     * la página, se utiliza para mostrar las imágenes de las actividades y
     * sellos.
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

    /**
     * Método que determina si hay eventos seleccionados actualmente por el
     * usuario, se usa para saber si se tiene que cambiar el texto del botón de
     * eliminación múltiple.
     *
     * @return
     */
    public boolean hayActividadesSeleccionadas() {
        return this.listaActividadesTemp != null && !this.listaActividadesTemp.isEmpty();
    }

    /**
     * Método que determina el mensaje que aparecerá en el botón de eliminación
     * múltiple.
     *
     * @return
     */
    public String getMensajeBotonEliminar() {
        if (hayActividadesSeleccionadas()) {
            int size = this.listaActividadesTemp.size();
            return size > 1 ? size + " Actividades seleccionadas" : "1 actividad seleccionada";
        }

        return "Eliminación múltiple";
    }

    /**
     * Método que actualiza la lista de actividades con las que se encuentran en
     * la base de datos, se utiliza en caso de realizar cambios en la misma y se
     * necesiten los datos nuevos.
     *
     */
    public void actualizarListaActividades() {
        //Actualiza la lista de eventos, se usa para actualizar los datos de la consulta.
        listaActividades = manejoActividadHelper.consultarActividades();
    }

    /**
     * Método que reinicia el objeto "actividad"
     *
     */
    public void actualizarDatosActividad() {
        this.actividad = new Actividad();

    }

    public void actualizarListaEventos() {
        listaEventos = manejoActividadHelper.consultaEventos();
    }

    /**
     * Método que obtiene el sello del evento seleccionado.
     *
     * @return
     */
    public void obtenerSello() {
        for (Sello s : manejoActividadHelper.consultarSellos()) {
            if (s.getIdActividad().getIdActividad() == actividad.getIdActividad()) {
                this.sello = s;
            }
        }
    }

    //Getters y setters
    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Actividad> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(List<Actividad> listaActividades) {
        this.listaActividades = listaActividades;
    }

    public List<Actividad> getListaActividadesTemp() {
        return listaActividadesTemp;
    }

    public void setListaActividadesTemp(List<Actividad> listaActividadesTemp) {
        this.listaActividadesTemp = listaActividadesTemp;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Sello getSello() {
        return sello;
    }

    public void setSello(Sello sello) {
        this.sello = sello;
    }

    public UploadedFile getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(UploadedFile archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

    public List<Programaeducativo> getListaProgramas() {
        return listaProgramas;
    }

    public void setListaProgramas(List<Programaeducativo> listaProgramas) {
        this.listaProgramas = listaProgramas;
    }

    public Integer[] getListaProgramasTemp() {
        return listaProgramasTemp;
    }

    public void setListaProgramasTemp(Integer[] listaProgramasTemp) {
        this.listaProgramasTemp = listaProgramasTemp;
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

    public LocalTime getHoraInicioActividad() {
        return horaInicioActividad;
    }

    public void setHoraInicioActividad(LocalTime horaInicioActividad) {
        this.horaInicioActividad = horaInicioActividad;
    }

    public LocalTime getHoraFinActividad() {
        return horaFinActividad;
    }

    public void setHoraFinActividad(LocalTime horaFinActividad) {
        this.horaFinActividad = horaFinActividad;
    }

    public Integer getEventoSeleccionado() {
        return eventoSeleccionado;
    }

    public void setEventoSeleccionado(Integer EventoSeleccionado) {
        this.eventoSeleccionado = EventoSeleccionado;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

}
