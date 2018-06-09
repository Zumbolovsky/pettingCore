package br.com.guilinssolution.pettingCore.security.filter;

import br.com.guilinssolution.pettingCore.exception.ApplicationException;
import br.com.guilinssolution.pettingCore.model.CustomUserDetails;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import br.com.guilinssolution.pettingCore.repositories.UsurRepository;
import br.com.guilinssolution.pettingCore.services.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static br.com.guilinssolution.pettingCore.security.constraints.SecurityConstraints.HEADER_STRING;
import static br.com.guilinssolution.pettingCore.security.constraints.SecurityConstraints.SECRET;

public class JWTAthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final UsurRepository usurRepository;

    private final MessageService message;

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    public JWTAthenticationFilter(AuthenticationManager authenticationManager, UsurRepository usurRepository, MessageService message) {
        this.authenticationManager = authenticationManager;
        this.usurRepository = usurRepository;
        this.message = message;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            final byte[] content = IOUtils.toByteArray(request.getReader(), "UTF-8");
            String jsonString = new String(content, StandardCharsets.UTF_8);
            jsonString = jsonString.replace("username", "emailUsur").replace("password", "passwordUsur");
            UsurEntity credentials = new ObjectMapper().readValue(jsonString, UsurEntity.class);

            return this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmailUsur(), credentials.getPasswordUsur(), new ArrayList<>()));
        } catch (IOException e) {
            throw new ApplicationException(this.message.getMessage("invalid.auth"), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(this.usurRepository.findByEmail(((CustomUserDetails) authResult.getPrincipal()).getUsername()).getIdUsur().toString())
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        response.addHeader(HEADER_STRING, token);
    }
}
