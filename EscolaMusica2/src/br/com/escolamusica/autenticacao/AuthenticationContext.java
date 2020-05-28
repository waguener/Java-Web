package br.com.escolamusica.autenticacao;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.escolamusica.model.Usuario;

@Component
public class AuthenticationContext {
	
	public Usuario getUsuarioLogado() {
		SecurityContext context = SecurityContextHolder.getContext();
 		Authentication authentication = context.getAuthentication();
		return (Usuario) authentication.getPrincipal();
	}

	public void logout() throws IOException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession) context.getSession(false);
		session.invalidate();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		context.redirect(context.encodeResourceURL(request.getContextPath()+"/logout"));
	} 
}
