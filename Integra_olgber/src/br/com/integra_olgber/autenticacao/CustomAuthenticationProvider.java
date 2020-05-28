package br.com.integra_olgber.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import br.com.integra_olgber.model.Usuario;
import br.com.integra_olgber.services.UsuarioServices;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UsuarioServices usuarioServices;

	private String msm;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken) authentication;
		String loginFornecido = userToken.getName();
		System.out.println(loginFornecido);
		String senhaFornecida = (String) userToken.getCredentials();
		// Verificar preenchimento do login e senha
		// Buscar usuario no banco login

		Usuario usuario = usuarioServices.obterPorLogin(loginFornecido);

		verificarLoginESenha(senhaFornecida, usuario);	
		
		return new UsernamePasswordAuthenticationToken(usuario, senhaFornecida, usuario.getAuthorities());
		
	}

	
	
	private void verificarLoginESenha(String senhaFornecida, Usuario usuario) {

		if (usuario == null) {
			msm = "Usuario não Existe!";
		
			throw new BadCredentialsException(msm);
		}

		if (!senhaFornecida.equals(usuario.getSenha())) {

			throw new BadCredentialsException("Senha inválida");

		}

	}

	// Verificar para que serve isto
	@Override
	public boolean supports(@SuppressWarnings("rawtypes") Class authentication) {

		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}



	public String getMsm() {
		return msm;
	}



	public void setMsm(String msm) {
		this.msm = msm;
	}

	
}
