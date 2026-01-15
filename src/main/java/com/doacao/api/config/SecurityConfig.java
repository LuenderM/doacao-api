package com.doacao.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer; // <--- IMPORTANTE: Esse import é novo
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. NOVO: Habilita o CORS para aceitar o Lovable (lê a config do outro arquivo)
            .cors(Customizer.withDefaults())
            
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/webhook/**").permitAll()
                
                // 2. MANTIDO: Suas configurações para os arquivos estáticos (não mexi em nada aqui)
                .requestMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/*.html", "/*.js", "/*.css").permitAll()
                
                .requestMatchers("/financeiro/**", "/doacoes/**").authenticated()
                .anyRequest().authenticated()
            )
            // 3. ATUALIZADO: Sintaxe mais moderna para o Login (o seu lambda vazio funcionava, esse é o padrão novo)
            .httpBasic(Customizer.withDefaults()); 

        return http.build();
    }
}
