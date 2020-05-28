package servlet;

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

import br.com.belcanto.model.Aluno;
import br.com.belcanto.services.AlunoServices;
import service.RelatorioService;

@WebServlet("/paginas/ServletDownloadFile")
public class ServletDownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RelatorioService relatorioService = new RelatorioService();
	private Aluno Aluno = new Aluno();
	@Autowired
	private AlunoServices alunoServices;

	public ServletDownloadFile() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			ServletContext context = request.getServletContext();
			
			String tipoExportar = request.getParameter("tipoExportar");
			List<Aluno> alunos = new ArrayList<Aluno>();
			 alunos = buscar();
			
			
			String fileUrl = relatorioService.gerarRelatorio(alunos, new HashMap(), "rel_alunos", "rel_alunos",
					context);
			
			File downloadFile = new File(fileUrl);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			
			String mimeType = context.getMimeType(fileUrl);
			
			if(mimeType == null) {
				mimeType = "application/octec-stream";
			}
			
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			
			String headerKey = "Context-Disposition";
			String headervalue = String.format("attachment; filename=\"%s\"" , downloadFile.getName());
			
			response.setHeader(headerKey, headervalue);
			
			OutputStream outputStream = response.getOutputStream();
			
			byte[] buffer = new byte[4096];
			int bytesReader = -1;
			
			while((bytesReader = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesReader);
				
			}
			
			inputStream.close();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro: " + e);
		}

	}
	
	private List<Aluno> buscar(){
		List<Aluno> alunos = new ArrayList<Aluno>();
		alunos = alunoServices.listar();
		return alunos;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
