package dev.edisonmartins.managedbeans;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class InicioBean {
	
	public void inicializar() {
		System.out.println("Inicializar...");
	}

}
