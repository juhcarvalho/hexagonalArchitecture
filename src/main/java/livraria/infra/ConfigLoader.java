package livraria.infra;

import livraria.Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    public static Properties loadProperties() {
        Properties props = new Properties();
        try {
            InputStream is = Main.class.getClassLoader().getResourceAsStream("application.properties");
            props.load(is);

        } catch (IOException e) {
            System.out.println("Erro ao carregar application.properties");
            System.out.println(e.getMessage());
        }
        return props;
    }
}
