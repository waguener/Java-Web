package curso.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;
	
	@Override //Configura as solicitações de acesso por Http
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf()
		.disable()             //Desativa as configurações padrão de memória
		.authorizeRequests()   //Permite Restringir acessos
		.antMatchers(HttpMethod.GET, "/").permitAll()  //Qualquer usuário acessa a página inicial
		.anyRequest().authenticated()
		.and().formLogin().permitAll() //Permite qualquer usuário
		.loginPage("/login")
		.defaultSuccessUrl("/cadastropessoa")
		.failureUrl("/login?error=true")
		.and().logout().logoutSuccessUrl("/login")
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override //Cria autenticação do usuário com banco de dados ou em memoria
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(implementacaoUserDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
		
		
	}
	
	@Override //Ignora url especificas
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/materialize/**");
	}
}
