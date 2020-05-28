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
   
    
    //C�digo do cliente usado no filtro do relat�rio.
   
   private Integer codigoClienteDe = 0;
  
   
    //C�digo do cliente usado no filtro do relat�rio.
    
   private Integer codigoClienteAte = 99999;
  
       //Gera o relat�rio pr�-compilado pelo iReport.
    
  
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
