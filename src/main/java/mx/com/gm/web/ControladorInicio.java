package mx.com.gm.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j //Nos da acceso a la variable log para agregar msj
public class ControladorInicio {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/") // Una peticion get http
    public String inicio(Model model, @AuthenticationPrincipal User user){ //con la clase model agregamos la informacion que queremos compartir con la vista
        Iterable<Persona> personas = personaService.listarPersonas();
        log.info("Ejecutando el controlador Spring MVC");
        log.info("usuario que hizo loggin: " + user);
        model.addAttribute("personas", personas);
        return "index"; //Thymeleaf usa archivos con extension .html por default
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        if(errores.hasErrors()){
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam("idPersona") Long idPersona){
        personaService.eliminar(idPersona);
        return "redirect:/";
    }


}
