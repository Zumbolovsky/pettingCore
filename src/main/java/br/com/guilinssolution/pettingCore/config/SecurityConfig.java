package br.com.guilinssolution.pettingCore.config;

import br.com.guilinssolution.pettingCore.security.filter.JWTAthenticationFilter;
import br.com.guilinssolution.pettingCore.security.filter.JWTAuthorizationFilter;
import br.com.guilinssolution.pettingCore.services.MessageService;
import br.com.guilinssolution.pettingCore.services.UsurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static br.com.guilinssolution.pettingCore.security.constraints.SecurityConstraints.SIGN_UP_URL;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsurService usurService;

    private final BCryptPasswordEncoder encoder;

    private final MessageService message;

    @Autowired
    public SecurityConfig(UsurService usurService, BCryptPasswordEncoder encoder, MessageService message) {
        this.usurService = usurService;
        this.encoder = encoder;
        this.message = message;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAthenticationFilter(authenticationManager(), this.message))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.usurService).passwordEncoder(this.encoder);
    }
}
