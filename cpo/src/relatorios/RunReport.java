package relatorios;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

 @ManagedBean
 @RequestScoped
 public class RunReport implements Serializable {
  
   private static final long serialVersionUID = 1L;
   
    
    //Código do cliente usado no filtro do relatório.
   
   private Integer codigoClienteDe = 0;
  
   
    //Código do cliente usado no filtro do relatório.
    
   private Integer codigoClienteAte = 99999;
  
       //Gera o relatório pré-compilado pelo iReport.
    
  
  public void printReport() throws JRException, IOException {
  
    // Define o caminho do template.
     String path = "/WEB-INF/reports/listagemDocumentos.jrxml";
     InputStream jasperTemplate = FacesContext.getCurrentInstance().
        getExternalContext().getResourceAsStream(path);
  
    // Compila o template.
     JasperReport report = JasperCompileManager.compileReport(jasperTemplate);
 
  }
 
  
   public Integer getCodigoClienteDe() {
     return codigoClienteDe;
   }
  
   public void setCodigoClienteDe(Integer codigoClienteDe) {
     this.codigoClienteDe = codigoClienteDe;
     }
  
   public Integer getCodigoClienteAte() {
     return codigoClienteAte;
   }
  
   public void setCodigoClienteAte(Integer codigoClienteAte) {
     this.codigoClienteAte = codigoClienteAte;
  }
  
  
 }
