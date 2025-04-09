package mx.com.gm.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data //Notacion de lombok, no tiene nada que ver con spring boot, es para crear de manera automatica constructor y get y set
@Entity
@Table(name = "persona") //nombre de la tabla en BD, no es necesario agregarlo pero evita problemas en linux o mac
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
}
