package be.cm.batodama.parkshark.api.security;

import be.cm.batodama.parkshark.domain.base_user.BaseUser;
import be.cm.batodama.parkshark.domain.base_user.BaseUserRepository;
import be.cm.batodama.parkshark.domain.base_user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class ParksharkAuthenticationProvider implements AuthenticationProvider {

    private final BaseUserRepository baseUserRepository;

    @Autowired
    public ParksharkAuthenticationProvider(BaseUserRepository baseUserRepository) {
        this.baseUserRepository = baseUserRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        List<BaseUser> baseUsers = baseUserRepository.findByUsernameAndPassword(authentication.getPrincipal().toString(), authentication.getCredentials().toString());
        if (baseUsers.size() < 1) {
            throw new BadCredentialsException("Those credentials are invalid.");
        }
        if (baseUsers.size() > 1){
            throw new BadCredentialsException("Those credentials are not unique.");
        }
        BaseUser authenticatedUser = baseUsers.get(0);
        return new UsernamePasswordAuthenticationToken(
                authenticatedUser.getUsername(),
                authenticatedUser.getPassword(),
                roleToGrantedAuthority(authenticatedUser.getRole()));
    }

    private Collection<? extends GrantedAuthority> roleToGrantedAuthority(Role role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
