package temakereso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import temakereso.service.implementation.AccountDetailsService;

@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AccountDetailsService accountDetailsService;

	@Configuration
	@Order(1)
	public class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.authenticationProvider(userAuthenticationProvider())
				.antMatcher("/api/**")
				.authorizeRequests()
				.anyRequest().permitAll();
		}
	}
	
	@Configuration		
	public class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.exceptionHandling().accessDeniedPage("/accessdenied")
				.and()
				.authenticationProvider(userAuthenticationProvider())
				.authorizeRequests()
				.antMatchers(
						"/",
						"/registration",
						"/forms/{\\d+}", // TODO check if only numbers are good!
						"/forms",
						"/topics/{\\d+}",
						"/css/**",
						"/img/**",
						"/js/**",
						"/fonts/**",
						"/perform-login").permitAll()
				.anyRequest().fullyAuthenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/perform-login")
				.defaultSuccessUrl("/")
				.failureUrl("/login?error=true")
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll()
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")  
				.and()
				.csrf();
		}

	}	

	@Bean
	public AuthenticationProvider userAuthenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(accountDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

}