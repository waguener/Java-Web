package br.com.escolamusica.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import br.com.escolamusica.model.Usuario;
import br.com.escolamusica.service.UsuarioService;

public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken) authentication;
		String loginFornecido = userToken.getName();
		String senhaFornecida = (String) userToken.getCredentials();
		//Verificar preenchimento do login e senha		
		//Buscar usuario no banco login	
		
		Usuario usuario = usuarioService.obterPorLogin(loginFornecido);
		
		verificarLoginESenha(senhaFornecida, usuario);
		return new UsernamePasswordAuthenticationToken(usuario, senhaFornecida, usuario.getAuthorities());
	}
	
	private void verificarLoginESenha(String senhaFornecida, Usuario usuario) {
		
		if(usuario == null) {
			throw new BadCredentialsException("Usuário não existe");
		}
		
		if(!senhaFornecida.equals(usuario.getSenha())){
			throw new BadCredentialsException("Senha inválida");
		}
	}

	//Verificar para que serve isto
	@Override
	public boolean supports(@SuppressWarnings("rawtypes") Class authentication) {
		
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
	

}
