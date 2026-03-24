package backend.bookstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import backend.bookstore.domain.AppUser;



@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig  {
	
	// using lambdas 
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
      http
      .authorizeHttpRequests(authorize -> authorize
        	.requestMatchers("/css/**").permitAll() // Enable css when logged out
            .requestMatchers("/css/**").permitAll()// Login ja signup näkyvät kaikille
            .requestMatchers("/login", "/signup", "/saveuser").permitAll() // Delete vain adminille
            .requestMatchers("/books/delete/**").hasAuthority("ADMIN") // Kaikki muut URL:t näkyvät kaikille kirjautuneille
        	.anyRequest().authenticated()
      )
      .formLogin(formlogin -> formlogin
          .loginPage("/login")
          .defaultSuccessUrl("/booklist", true)
          .permitAll()
      )
      .logout(logout -> logout
          .permitAll()
      );
      return http.build();
    }
    
     @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

   /* @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList<>();

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user1 = User
        		.withUsername("user")
        		.password(passwordEncoder.encode("user"))
        		.roles("USER")
        		.build();

        users.add(user1);

        UserDetails user2 = User
        		.withUsername("admin")
        		.password(passwordEncoder.encode("admin"))
        		.roles("USER", "ADMIN")
        		.build();

    	users.add(user2);

        return new InMemoryUserDetailsManager(users);
    }*/



