package demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /*
            Before Spring Boot 3 we used this approch
               1) extends  WebSecurityConfigurerAdapter in our class

                2)  the method void configure(AuthenticationManagerBuilder auth) for Authentication
                  about Authentication(who are you)
                  In the authentication process, the identity of users are checked for providing the access to the system.
                  Authentication by userid and password

                3) void configure(HttpSecurity http)
                   Authorization (what permission do you have)
            public class SecurityConfig extends WebSecurityConfigurerAdapter{

            }
     */


    /*
            antMatcher -> requestMatcher
         after the Spring version 3
         1)not required to exdents other class
         2) only create the method
     */
   // Authentication
    @Bean
    public UserDetailsService userDetailsService( PasswordEncoder encoder){
//        UserDetails admin=User.withUsername("Basant")
//                .password(encoder.encode("123"))
//                .roles("ADMIN")
//                .build();
//
//
//        UserDetails normal=User.withUsername("vicky")
//                .password(encoder.encode("123"))
//                .roles("USER")
//                .build();
//        UserDetails staff=User.withUsername("vicky1")
//                .password(encoder.encode("123"))
//                .roles("STAFF")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin,normal,staff);
        return null;
    }



    // Authorization
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http.csrf().disable().authorizeHttpRequests().
                requestMatchers("/products/welcome").permitAll()
                .and().authorizeHttpRequests().requestMatchers("/products/**")
                .authenticated().and().formLogin().and().build();

    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
