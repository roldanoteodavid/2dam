package org.example.app.config;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.example.app.common.Constantes;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.util.Properties;


@Getter
@Log4j2
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Configuration
public class Configuracion {

    private String pathDatos;
    private String password;
    private String userkeystore;

    public Configuracion() {
        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader().getResourceAsStream(Constantes.CONFIG_PROPERTIES));
            this.pathDatos = p.getProperty(Constantes.PATH_DATOS);
            this.password = p.getProperty(Constantes.PASS);
            this.userkeystore = p.getProperty(Constantes.USERKEYSTORE);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }


}
