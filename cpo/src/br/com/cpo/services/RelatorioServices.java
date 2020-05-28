package br.com.cpo.services;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioServices {

	
	
	private static final String FOLDERS_RELATORIOS = "/relatorios";
	private static final String SUBREPORT_DIR = "SUBREPORT";
	private String SEPARATOR = File.separator;
	private String caminhoArquivoRelatorio = null;
	
	
	private JRExporter exporter = null;
	private String caminhoSubReport_Dir = "";
	private File arquivoGerado = null;
	
	
	//Metodos
	
	
	@SuppressWarnings("rawtypes")
	public String gerarRelatorio(List<?> listaDataBeanCollection, HashMap parametrosRelatorio,
			String nomeRelatoriojasper, String nomeRelatorioSaida, ServletContext servletContext) throws Exception {
		
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanCollection);
		
		String caminhoRelatorio = servletContext.getRealPath(FOLDERS_RELATORIOS);
		
		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatoriojasper + ".jasper");
		
		if(caminhoRelatorio == null || caminhoRelatorio != null && caminhoRelatorio.isEmpty() || !file.exists()) {
			
			caminhoRelatorio = this.getClass().getResource(FOLDERS_RELATORIOS).getPath();
			SEPARATOR = "";
			
			parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
			
			String caminhoArquivosJasper = caminhoRelatorio + SEPARATOR + nomeRelatoriojasper + ".jasper";
			
			JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivosJasper);
			
			caminhoSubReport_Dir = caminhoRelatorio + SEPARATOR;
			parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_Dir);
			
			JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);
			
			exporter = new JRPdfExporter();
			
			caminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + ".pdf";
			
			arquivoGerado = new File(caminhoArquivoRelatorio);
			
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
			
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
			
			exporter.exportReport();
			
			arquivoGerado.deleteOnExit();
			
		}
		
		return caminhoArquivoRelatorio;
	}
	
}
