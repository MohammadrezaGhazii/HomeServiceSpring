package ir.ghazi.service_managment.config;

import ir.ghazi.service_managment.base.exception.NotFoundException;
import ir.ghazi.service_managment.service.AdminService;
import ir.ghazi.service_managment.service.ClientService;
import ir.ghazi.service_managment.service.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AdminService adminService;

    private final ClientService clientService;

    private final SpecialistService specialistService;

    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(s -> s.anyRequest().permitAll()).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(username -> {
                    try {
                        return clientService.emailOptional(username)
                                .orElseThrow(() -> new NotFoundException("Client not found"));
                    } catch (NotFoundException e) {
                        // Try next service
                    }
                    try {
                        return specialistService.emailOptional(username)
                                .orElseThrow(() -> new NotFoundException("Specialist not found"));
                    } catch (NotFoundException e) {
                        // Try next service
                    }
                    return adminService.emailOptional(username)
                            .orElseThrow(() -> new NotFoundException("Admin not found"));
                })
                .passwordEncoder(passwordEncoder);
    }

    //    @Autowired
//    public void configureBuildClient(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(username -> clientService.emailOptional(username)
//                        .orElseThrow(() -> new NotFoundException("Not found !!!")))
//                .passwordEncoder(passwordEncoder);
//
//    }
//    @Autowired
//    public void configureBuildSpecialist(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(username -> specialistService.emailOptional(username)
//                        .orElseThrow(() -> new NotFoundException("Not found !!!")))
//                .passwordEncoder(passwordEncoder);
//
//    }
//    @Autowired
//    public void configureBuildAdmin(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(username -> adminService.emailOptional(username)
//                        .orElseThrow(() -> new NotFoundException("Not found !!!")))
//                .passwordEncoder(passwordEncoder);
//
//    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
