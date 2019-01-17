package oktenweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   // securityPart1InMemoryUser

//     @Override
//         protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         // {noop} means non-coded
//         // {noop}pass and it wants the whole sentence to log in
//             auth.inMemoryAuthentication().withUser("user").password("{noop}pass").roles("USER");
//          // user can not upload objects, only ADMIN
//             auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
//     }

     //securityPart2ConfigureHttp

     @Override
     protected void configure(HttpSecurity http) throws Exception {
         http
                 .authorizeRequests()
                 .antMatchers( "/home", "/login").permitAll()
                 .antMatchers(HttpMethod.POST, "/saveUser").permitAll()
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

        @Autowired
        @Qualifier("userDetailsServiceImpl")
        // could be @Qualifier("xxx") from UserDetailsServiceImpl
        private UserDetailsService userDetailsService;
     @Bean
         public PasswordEncoder passwordEncoder() {
             return new BCryptPasswordEncoder();
         }

         @Bean
         public DaoAuthenticationProvider authenticationProvider() {
             DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
             provider.setUserDetailsService(userDetailsService);// service which finds users
             provider.setPasswordEncoder(passwordEncoder());// encodes passwords
             return provider;
         }

        // securityPart7
          private InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryConfigurer() {
                  return new InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>();
              }

              // it allows to get in with admin and {noop}admin by default in case you forgot your data
              @Autowired
              public void configureGlobal(AuthenticationManagerBuilder auth,
                                          AuthenticationProvider provider) throws Exception {
                  inMemoryConfigurer()
                          .withUser("admin")
                          .password("{noop}admin")
                          .authorities("ADMIN")
                          .and()
                          .configure(auth);
                  auth.authenticationProvider(provider);

              }
}
