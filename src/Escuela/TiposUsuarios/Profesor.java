package Escuela.TiposUsuarios;
import Escuela.Horario;
import Escuela.Main;
import Escuela.Tareas;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Profesor extends Persona {
    Main main = new Main();
    private String estudios;
    private String materia;

    private List<Estudiante> estudiantes = new ArrayList<>(); // Lista de estudiantes del profesor
    private List<Tareas> tareas = new ArrayList<>(); // Tareas creadas por el profesor
    private Horario horario = new Horario(); // Horario personal del profesor

    // 1. Crea una nueva tarea y la agrega a la lista, esto se le asiganara a sus estudiantes
    public void crearTarea(){
        Tareas tarea = new Tareas();
        System.out.print("Escriba el titulo de la tarea: ");
        tarea.setTitulo(sc.nextLine());
        System.out.print("Escriba el descripcion de la tarea: ");
        tarea.setDescripcion(sc.nextLine());
        System.out.println("Escribe la fecha de entrega");
        tarea.setFechaEnt(escribirFecha()); // Metodo heredado de Persona
        tareas.add(tarea); // Se guardo en la lista
        sc.nextLine();
    }
    // 2. Muestra todas las tareas creadas por el profesor
    public void mostrarTareas(){
        try{
            for (Tareas i:tareas){
                System.out.println("Titulo: "+i.getTitulo());
                System.out.println("Descripcion: "+i.getDescripcion());
                System.out.println("Fecha de entrega: "+i.getFechaEnt()+"\n");
            }
        } catch (Exception e) {
            System.out.println("No se a ingresado ninguna tarea aun");
        }
    }
    // 3. Busca un estudiante y muestra su informacion (si existe)
    public void infoEstudiante(){
        System.out.println("Escriba el nombre del estudiantes: ");
        String nombre = sc.nextLine();
        System.out.println("Escriba el correo de la estudiante: ");
        String correo = sc.nextLine();
        boolean exist = Admin.verificarPersona(nombre, correo,"estudiante");
        if (exist){
            Estudiante estbusc = Admin.buscarEstudiante(nombre);
            System.out.println("Nombre : "+estbusc.getNombre());
            System.out.println("Apellido : "+estbusc.getApellido());
            System.out.println("Edad: "+estbusc.getEdad());
            System.out.println("Email : "+estbusc.getEmail());
            System.out.println("Telefono : "+estbusc.getTelefono());
        }else{
            System.out.println("No existe el estudiante");
        }
    }
    // 4. Permite que el profesor cree sus horarios de clase
    public void crearHorario(){
        System.out.print("Que tienes planeado hacer este lunes? ");
        horario.setLun(sc.nextLine());
        System.out.print("Que tienes planeado hacer este martes? ");
        horario.setMart(sc.nextLine());
        System.out.print("Que tienes planeado hacer este miercoles? ");
        horario.setMier(sc.nextLine());
        System.out.print("Que tienes planeado hacer este jueves? ");
        horario.setJuev(sc.nextLine());
        System.out.print("Que tienes planeado hacer este viernes? ");
        horario.setVier(sc.nextLine());
    }
    // 5. Muestra el horario actual del profesor
    public void listaHorario(){
        System.out.println("Lunes: "+horario.getLun());
        System.out.println("Martes: "+horario.getMart());
        System.out.println("Mieres: "+horario.getMier());
        System.out.println("Jueves: "+horario.getJuev());
        System.out.println("Viernes: "+horario.getVier());
    }
    // 6. Solo el profesor puede ingresar un estudiante existente a su clase
    public void ingresarEstudiante(){
        System.out.print("Escriba el nombre de la estudiante: ");
        String nomb = sc.nextLine();
        System.out.print("Escriba el correo del estudiante: ");
        String cor = sc.nextLine();
        boolean exist = Admin.verificarPersona(nomb,cor,"estudiante");
        if (exist){
            for (Estudiante i: main.getEstudiantes()){
                if (i.getNombre().equals(nomb)){
                    this.estudiantes.add(i);
                    System.out.println("El estudiante ha sido añadido a su lista");
                    int estudiante = main.getEstudiantes().indexOf(i);
                    Estudiante actEstudiante = main.getEstudiantes().get(estudiante);
                    ArrayList <String> profesEst = actEstudiante.getProfeEst();
                    profesEst.add(this.getNombre());
                    actEstudiante.setProfeEst(profesEst);
                }
            }

        }else{
            System.out.println("No existe el estudiante");
        }
    }
    // 7. Muestra  todos los estudiantes que ya pertenecen a su clase
    public void listaEstudiantes(){
        if (estudiantes.isEmpty()){
            System.out.println("No se ha ingresado ningun estudiante aun.");
        }else{
            System.out.println("Lista de estudiantes:");
            for (Estudiante i : estudiantes){
                System.out.println("Nombre: "+i.getNombre()+" \nCorreo: "+i.getEmail());
            }
        }


    }
}
