

package com.travel.role.global.auth.token;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.travel.role.domain.user.entity.User;
import com.travel.role.global.auth.entity.AuthInfo;

public class UserPrincipal implements UserDetails, OAuth2User {

	private Long id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private Map<String, Object> attributes;

	public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrincipal create(AuthInfo authInfo) {
		List<SimpleGrantedAuthority> authorities = Collections.singletonList(
			new SimpleGrantedAuthority(authInfo.getRole().getRoleValue()));

		User user = authInfo.getUser();
		return new UserPrincipal(
			user.getId(),
			user.getEmail(),
			user.getPassword(),
			authorities
		);
	}


	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
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
	@Override
	public String getName() {
		return null;
	}
}
