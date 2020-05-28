package br.com.sistemaepi.autenticacao;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationContext {
	
	/*public UsuarioBean getUsuarioLogado() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		return (UsuarioBean) authentication.getPrincipal();
	}

	public void logout() throws IOException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession) context.getSession(false);
		session.invalidate();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		context.redirect(context.encodeResourceURL(request.getContextPath()+"/logout"));
	} */
}
