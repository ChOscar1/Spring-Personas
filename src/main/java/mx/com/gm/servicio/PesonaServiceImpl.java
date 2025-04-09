package mx.com.gm.servicio;

import mx.com.gm.dao.IPersonaDao;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PesonaServiceImpl implements PersonaService {

    @Autowired
    private IPersonaDao personaDao; //para conectar la capa de servicio con la capa de datos

    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
       return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Long idPersona) {
        personaDao.deleteById(idPersona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
}
