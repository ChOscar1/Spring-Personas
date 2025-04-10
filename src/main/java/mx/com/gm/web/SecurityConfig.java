package mx.com.gm.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public InMemoryUserDetailsManager  configure(){
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("123").roles("ADMIN", "USER").build();
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("123").roles("USER", "USER").build();
        return new InMemoryUserDetailsManager(admin,user);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/editar/**", "/agregar/**", "/eliminar")
                        .hasRole("ADMIN")
                        .requestMatchers("/")
                        .hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())  // para que aparezca el formulario de login por defecto
                .exceptionHandling(exception ->
                        exception.accessDeniedPage("/errores/403"))
                .build();
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/errores/403").setViewName("errores/403");
    }

}
