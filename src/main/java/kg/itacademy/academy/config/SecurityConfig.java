package kg.itacademy.academy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/users/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/student/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/student/listName").permitAll()
                .antMatchers(HttpMethod.POST, "/api/student/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/student/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/course/**").hasRole("ADMIN")
                .and()
                .httpBasic()
                .and().logout().and().formLogin();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, status from users where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.role_name from user_role ur join users u on ur.user_id = u.id where u.username=? and status=1");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
