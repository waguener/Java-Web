package br.com.cpo.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cpo.model.Carga;
import br.com.cpo.services.CargaServices;
import br.com.cpo.services.RelatorioServices;

/**
 * Servlet implementation class ServletDownloadFile
 */
@WebServlet("/ServletDownloadFile")
public class ServletDownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private RelatorioServices relatorioServices = new RelatorioServices();
	
	@Autowired
	private CargaServices cargaServices;
	
    public ServletDownloadFile() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			ServletContext context = request.getServletContext();
			
			String tipoExportar = request.getParameter("tipoExportar");
			
			List<Carga> cargas = cargaServices.listar();
			
			List dados = new ArrayList();
			dados.add(cargas);
			
			String FileURL = relatorioServices.gerarRelatorio(dados, new HashMap(), "rel_kanban", "rel_kanban", context);
			
			File downloadFile = new File(FileURL);
			
			FileInputStream inputStream = new FileInputStream(downloadFile);
			
			String mimeType = context.getMimeType(FileURL);
			
			if(mimeType == null) {
				
				mimeType = "aplication/octet-stream";
			}
			
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			
			String headerKey = "Content-Disposition";
			String headerValeu = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		
			response.setHeader(headerKey, headerValeu);
			
			OutputStream outputStream = response.getOutputStream();
			
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			
			while((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer,0,bytesRead);
				
			}
			inputStream.close();
			outputStream.close();
			
			
		} catch (Exception e) {
			
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
