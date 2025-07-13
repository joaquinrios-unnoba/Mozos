package mozos_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth 
                .requestMatchers("/login", "/inicio", "/", "/visitante/**", "/css/**").permitAll() // Público
                .requestMatchers("/administrador/**").hasRole("ADMIN") // Solo admin
                .requestMatchers("/registrados/**").hasAnyRole("USER", "ADMIN") // Usuario registrado o admin
                .anyRequest().authenticated() // Otras rutas necesitan autenticación
            )
            .formLogin(login -> login
                .loginPage("/login") // Página personalizada para login
                .defaultSuccessUrl("/inicio", true) // Redirigir a /inicio por defecto si no hay otra URL guardada
                .permitAll()
            )
            .logout(logout -> logout
            .logoutSuccessUrl("/inicio") // Página tras logout
            .permitAll()); // Logout público
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
