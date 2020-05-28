package br.com.belcanto.relatorio;

import java.io.InputStream;
import java.util.List;

import br.com.belcanto.model.Aluno;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class RelAluno {
	
	public void gerarRelatorio(List<Aluno> lista) throws JRException{
		InputStream fonte = RelAluno.class.getResourceAsStream("/report/relatorio.jrxml");
		
		
		JasperReport report = JasperCompileManager.compileReport(fonte);
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
		
		JasperViewer.viewReport(print, false);
		
	}
	
	
	
}
