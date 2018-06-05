package br.com.guilinssolution.pettingCore.model;

import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails extends UsurEntity implements UserDetails {

    public CustomUserDetails(final UsurEntity usurEntity) {
        super(usurEntity.getIdUsur(), usurEntity.getNameUsur(),
                usurEntity.getCpfUsur(), usurEntity.getAddressUsur(),
                usurEntity.getCityUsur(), usurEntity.getEmailUsur(),
                usurEntity.getPasswordUsur(), usurEntity.getStateUsur(),
                usurEntity.getCellphoneUsur(), usurEntity.getPhoneUsur(),
                usurEntity.getImageUsur());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
