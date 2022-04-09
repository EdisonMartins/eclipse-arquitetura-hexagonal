package dev.edisonmartins.scene;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.edisonmartins.build.prd.Build4;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * FAT JAR 
 *   BuildPath > Library > Classpath > Add External Jar (C:\Program Files\Java\javafx-sdk-18\lib)
 *   Run Configurations > VM Arguments
 *      --module-path "C:\Program Files\Java\javafx-sdk-18\lib" --add-modules=javafx.controls
 * 
 * Module-info.java
 * module dev.edisonmartins {
	opens dev.edisonmartins.build.prd;
	
    // Usar spring
    requires spring.tx;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires java.sql;
    requires cdi.api;
    
    // Usar javafx
    requires javafx.controls;
    requires javafx.fxml;
	requires javax.inject;


    opens dev.edisonmartins.scene;  
    requires hexagon;
}
 * 

 * @author Edison Martins
 *
 */

// Responsável por fazer o ponto de inicio de execução
public class AdaptadorJavaFx extends Application {

    private ApplicationContext spring;

    @Override
    public void init() {
        System.out.println("iniciando spring..");
        //spring = new AnnotationConfigApplicationContext(Build2.class);
        //spring = new AnnotationConfigApplicationContext(Build3.class);
        spring = new AnnotationConfigApplicationContext(Build4.class);
    }

    @Override
    public void start(Stage stage) {
        var form = spring.getBean(TransferenciaFrm.class);
        form.mostrar(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
