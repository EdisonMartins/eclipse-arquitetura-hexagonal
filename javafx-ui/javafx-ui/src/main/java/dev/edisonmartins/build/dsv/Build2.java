package dev.edisonmartins.build.dsv;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * Configuration: Responsavel por configurar os serviços do spring
 * @ComponentScan: Configura as diretivas de varredura.
 *
 * conta.sistema: Pacote do Hexágono
 * conta.adaptador: Pacote dos Adaptadores Mock
 *
 * Build 2: Adaptador Interface Gráfica → Sistema ← Adaptadores Mocks
 *          O Objetivo é testar o hexágono.
 *
 * **/
@Configuration
@ComponentScan({
        "conta.dsv",
        "conta.tela",
        "conta.sistema",
        "conta.adaptador",
    })
public class Build2 {
}
