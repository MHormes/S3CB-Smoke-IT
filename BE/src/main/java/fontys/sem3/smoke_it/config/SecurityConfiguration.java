package fontys.sem3.smoke_it.config;

import fontys.sem3.smoke_it.filter.JWTAuthenticationFilter;
import fontys.sem3.smoke_it.filter.JWTAuthorizationFilter;
import fontys.sem3.smoke_it.service.AuthenticationUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationUserDetailService authenticationUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));


        http.cors().configurationSource(request -> corsConfiguration).and().csrf().disable().authorizeRequests()
                .antMatchers("/h2-ui/**").permitAll()
                //configuration of boxes end points
                .antMatchers("/boxes/").permitAll()
                .antMatchers("/boxes/{id}").permitAll()
                .antMatchers("/boxes/{id}/price/").permitAll()
                .antMatchers("/boxes/sort").permitAll()
                .antMatchers("/boxes/create").hasAnyAuthority("ADMIN")
                .antMatchers("/boxes/update").hasAnyAuthority("ADMIN")
                .antMatchers("/boxes/delete/{id}").hasAnyAuthority("ADMIN")
                //configuration of orders endpoints
                .antMatchers("/subscriptions/create/").permitAll()
                .antMatchers("/subscriptions/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/subscriptions/grouped").hasAnyAuthority("ADMIN")
                .antMatchers("/subscriptions/grouped/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/subscriptions/orders/send/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/subscriptions/orders/pack/{id}").hasAnyAuthority("ADMIN")
                //configuration of user endpoints
                .antMatchers(HttpMethod.POST, AuthenticationConfigConstants.SIGN_UP_URL).permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .clearAuthentication(true).invalidateHttpSession(true)
                .logoutSuccessUrl("/test")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)).permitAll()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationUserDetailService).passwordEncoder(bCryptPasswordEncoder);
    }
}
