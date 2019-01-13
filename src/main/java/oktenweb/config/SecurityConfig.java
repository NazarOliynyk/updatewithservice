package oktenweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   // securityPart1InMemoryUser

     @Override
         protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         // {noop} means non-coded
         // {noop}pass and it wants the whole sentence to log in
             auth.inMemoryAuthentication().withUser("user").password("{noop}pass").roles("USER");
          // user can not upload objects, only ADMIN
             auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
     }

     //securityPart2ConfigureHttp

     @Override
     protected void configure(HttpSecurity http) throws Exception {
         http
                 .authorizeRequests()
                 .antMatchers( "/home", "/login").permitAll()
                 .anyRequest().authenticated()
                 .antMatchers("/admin/**").hasRole("ADMIN")
                 .antMatchers(HttpMethod.POST, "/upload").hasRole("ADMIN")
                 .and()
                 .formLogin()
//                 .loginPage("/login")
//                 .successForwardUrl("/successURL")//handle with post mapping in controller
//                 .failureUrl("/login?error").permitAll()
//                 .permitAll()
                 .and()
                 .logout()
                 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                 .logoutSuccessUrl("/login")
                 .permitAll();
     }
}
