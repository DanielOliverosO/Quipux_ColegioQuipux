package Escuela;

import java.util.ArrayList;
import lombok.Data;

@Data
public abstract class Clases {
    private ArrayList<Apuntes> apuntesClase = new ArrayList(); // Apuntes de clase

    public void darClase(){
        System.out.println("Temarios");
    }


}
