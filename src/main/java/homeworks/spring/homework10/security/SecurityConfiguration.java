package homeworks.spring.homework10.security;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;

//
//@Configuration
//@EnableMethodSecurity(securedEnabled = true)
//@EnableWebSecurity
//public class SecurityConfiguration {
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .authorizeHttpRequests(configurer -> configurer
//                        .requestMatchers("/ui/issues/**").hasAuthority("admin")
//                        .requestMatchers("/ui/reader/**").hasAnyAuthority("reader", "admin")
//                        .requestMatchers("/ui/books/**").authenticated()
//                        .requestMatchers("/book/**").permitAll()
//                        .requestMatchers("/issue/**").permitAll()
//                        .requestMatchers("/reader/**").permitAll()
//                        .anyRequest().denyAll()
//                )
//                .formLogin(Customizer.withDefaults())
//                .build();
//    }
//}
