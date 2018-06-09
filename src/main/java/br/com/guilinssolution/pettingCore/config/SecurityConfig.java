package br.com.guilinssolution.pettingCore.config;

import br.com.guilinssolution.pettingCore.repositories.UsurRepository;
import br.com.guilinssolution.pettingCore.security.filter.JWTAthenticationFilter;
import br.com.guilinssolution.pettingCore.security.filter.JWTAuthorizationFilter;
import br.com.guilinssolution.pettingCore.services.MessageService;
import br.com.guilinssolution.pettingCore.services.UsurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import static br.com.guilinssolution.pettingCore.security.constraints.SecurityConstraints.SIGN_UP_URL;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsurService usurService;

    private final UsurRepository usurRepository;

    private final MessageService message;

    @Autowired
    public SecurityConfig(UsurService usurService, UsurRepository usurRepository, MessageService message) {
        this.usurService = usurService;
        this.usurRepository = usurRepository;
        this.message = message;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .regexMatchers(".*/secured/.*").authenticated()
                .and()
                .addFilter(new JWTAthenticationFilter(authenticationManager(), this.usurRepository, this.message))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.usurService).passwordEncoder(getPasswordEncoder());
    }

    private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return charSequence.toString().equals(s);
            }
        };
    }
}
