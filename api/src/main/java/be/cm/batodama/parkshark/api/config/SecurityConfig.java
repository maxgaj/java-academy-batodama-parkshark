package be.cm.batodama.parkshark.api.config;

import be.cm.batodama.parkshark.api.security.ParksharkAccessDeniedHandler;
import be.cm.batodama.parkshark.api.security.ParksharkAuthenticationProvider;
import be.cm.batodama.parkshark.api.security.ParksharkEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final ParksharkEntryPoint parksharkEntryPoint;
    private final ParksharkAuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(ParksharkEntryPoint parksharkEntryPoint, ParksharkAuthenticationProvider authenticationProvider) {
        this.parksharkEntryPoint = parksharkEntryPoint;
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .and()
                .httpBasic()
                    .authenticationEntryPoint(parksharkEntryPoint)
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new ParksharkAccessDeniedHandler();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
}
