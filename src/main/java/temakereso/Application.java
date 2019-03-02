package temakereso;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import temakereso.entity.Account;
import temakereso.entity.Role;
import temakereso.repository.AccountRepository;
import temakereso.service.AccountService;

@SpringBootApplication
@ComponentScan({"temakereso"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Application extends SpringBootServletInitializer {

    // TODO refactor

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static Class<Application> applicationClass = Application.class;

    public static void main(String[] args) {
        SpringApplication.run(applicationClass, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    /**
     * Password grants are switched on by injecting an AuthenticationManager.
     * Here, we setup the builder so that the userDetailsService is the one we coded.
     * @param builder
     * @param repository
     * @throws Exception
     */
    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, AccountRepository repository, AccountService service) throws Exception {
        //Setup db with admin
        if (repository.count() == 0) {
            service.createAccount(new Account("admin", "leah@inf.elte.hu", "admin", "admin", Arrays.asList(new Role("ADMIN"))));
        }
        builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
    }

    /**
     * We return an instance of our CustomUserDetails.
     * @param repository
     * @return
     */
    private UserDetailsService userDetailsService(AccountRepository repository) {
        return username -> new temakereso.helper.AccountDetails(repository.findByUsername(username));
    }

    @Bean
    public static PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public static ModelMapper modelMapper() {
        return  new ModelMapper();
    }


}