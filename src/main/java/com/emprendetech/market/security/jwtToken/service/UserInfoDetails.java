package com.emprendetech.market.security.jwtToken.service;
import com.emprendetech.market.entitys.Usuario;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class UserInfoDetails implements UserDetails {

	private static final Log LOG = LogFactory.getLog(UserInfoDetails.class);

    private static final long serialVersionUID = 1L;
	private String name;
    private String password;

    // Constructor que toma un objeto UserInfo y lo convierte en UserDetails
    public UserInfoDetails(Usuario userInfo) {
        name = userInfo.getNombreusuario();
        password = userInfo.getContrasena();

		LOG.info("llegue a UserInfoDetails ");

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    // Métodos de estado de la cuenta (siempre devuelven true en este ejemplo)
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
