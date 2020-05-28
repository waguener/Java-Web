package br.com.sistemaepi.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sistemaepi.Bean.UsuarioBean;
import br.com.sistemaepi.service.UsuarioService;

/*public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken) authentication;
		String loginFornecido = userToken.getName();
		String senhaFornecida = (String) userToken.getCredentials();
		//Verificar preenchimento do login e senha		
		//Buscar usuario no banco login	
		
		UsuarioBean usuario = usuarioService.obterPorLogin(loginFornecido);
		verificarLoginESenha(senhaFornecida, usuario);
		return new UsernamePasswordAuthenticationToken(usuario, senhaFornecida, usuario.getAuthorities());
	}
	
	private void verificarLoginESenha(String senhaFornecida, UsuarioBean usuario) {
		
		if(usuario == null) {
			throw new BadCredentialsException("Usuário não existe");
		}
		
		if(!senhaFornecida.equals(usuario.getSenha())){
			throw new BadCredentialsException("Senha inválidos");
		}
	}

	//Verificar para que serve isto
	@Override
	public boolean supports(@SuppressWarnings("rawtypes") Class authentication) {
		
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
	*/


