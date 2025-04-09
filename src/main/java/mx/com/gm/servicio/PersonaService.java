package mx.com.gm.servicio;

import mx.com.gm.domain.Persona;

import java.util.List;

public interface PersonaService {
    public List<Persona> listarPersonas();
    public void guardar(Persona persona);
    public void eliminar(Long idPersona);
    public Persona encontrarPersona(Persona persona);

}
