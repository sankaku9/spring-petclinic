package org.springframework.samples.petclinic.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "users")
public class User {

	@Id
	private String username;

	private String password;

	private boolean enabled;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	private Set<Authority> authorities;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", enabled=" + enabled
				+ ", authorities=" + authorities + '}';
	}

}
