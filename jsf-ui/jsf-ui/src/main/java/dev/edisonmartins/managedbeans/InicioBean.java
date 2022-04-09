package dev.edisonmartins.managedbeans;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class InicioBean {
	
	private Double contaDebito, contaCredito;
	
	
	public void inicializar() {
		System.out.println("Inicializar...");
	}


	public Double getContaDebito() {
		return contaDebito;
	}


	public void setContaDebito(Double contaDebito) {
		this.contaDebito = contaDebito;
	}


	public Double getContaCredito() {
		return contaCredito;
	}


	public void setContaCredito(Double contaCredito) {
		this.contaCredito = contaCredito;
	}
	
	
	

}
