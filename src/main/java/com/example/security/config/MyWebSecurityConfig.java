package com.example.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	/*@Bean
	public UserDetailsService userDetailsService() {
		
		List<UserDetails> users= new ArrayList<>();
		
		users.add(User.withDefaultPasswordEncoder().username("user").password("pass").roles("USER").build());
		users.add(User.withDefaultPasswordEncoder().username("admin").password("pass").roles("User", "ADMIN").build());
		return new InMemoryUserDetailsManager(users);
	}*/
    
	
	//for authentication of api
	@SuppressWarnings("deprecation")
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return provider;
	}
	
	//for authorization of api
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		     .csrf().disable()
		     .authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
		     .antMatchers("/covid/hospitalAdmin/**").hasRole("HOSPITALADMIN")
		     .antMatchers("/**").permitAll()
		     .and().formLogin()
		     .loginPage("/login")
		  .and()
		     .logout().logoutUrl("/logout");
	}
	 
}
