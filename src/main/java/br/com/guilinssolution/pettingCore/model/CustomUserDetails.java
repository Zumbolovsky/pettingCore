package br.com.guilinssolution.pettingCore.model;

import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import net.bytebuddy.matcher.FilterableList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails extends UsurEntity implements UserDetails {

    public CustomUserDetails(final UsurEntity usurEntity) {
        super(usurEntity.getEmailUsur(), usurEntity.getPasswordUsur());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new FilterableList.Empty<>();
    }

    @Override
    public String getPassword() {
        return super.getPasswordUsur();
    }

    @Override
    public String getUsername() {
        return super.getEmailUsur();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
