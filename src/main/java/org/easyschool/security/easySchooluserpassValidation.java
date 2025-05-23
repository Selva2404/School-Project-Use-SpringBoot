package org.easyschool.security;

import lombok.extern.slf4j.Slf4j;
import org.easyschool.Model.Roles;
import org.easyschool.Model.person;
import org.easyschool.Repository.personRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class easySchooluserpassValidation implements AuthenticationProvider
{
    @Autowired
    private personRepository personRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email= authentication.getName();
        String password = authentication.getCredentials().toString();
        person person=personRepository.readByMailid(email);
        log.info(person.toString());
        log.info("possword=> "+password+" "+email);
        if(null != password && person.getPerson_id()>0 && password.equals(person.getPwd())){
            return new UsernamePasswordAuthenticationToken(person.getName(), password, getGarantedautherities(person.getRole()));
        }else {
            throw  new BadCredentialsException("invalid user name or password");
        }
    }
    private List<GrantedAuthority> getGarantedautherities(Roles role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()) );
        return authorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
