package server;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.github.flbulgarelli.jpa.extras.perthread.PerThreadEntityManagerProperties;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;
import io.javalin.rendering.JavalinRenderer;
import server.middlewares.AuthMiddelware;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Server {
    private static Javalin app = null;

    public static EntityManagerFactory entityManagerFactory;

    public static Javalin app() {
        if(app == null)
            throw new RuntimeException("App no inicializada");
        return app;
    }

    public static void init() {
        if(app == null) {
            Integer port = Integer.parseInt(System.getProperty("port", "8080"));
            app = Javalin.create(config()).start(port);
            initTemplateEngine();
            Router.init();
        }

        Map<String, String> env = System.getenv();
        for (String string : env.keySet()) {
            System.out.println(string + ": " + env.get(string));
        }

        //entityManagerFactory =  createEntityManagerFactory();

       // Map<String, String> env = System.getenv();
        /*Map<String, Object> configOverrides = new HashMap<String, Object>();

        String[] keys = new String[] { "hibernate.archive.autodetection", "hibernate.connection.driver_class",
            "hibernate.connection.url", "hibernate.connection.username", "hibernate.connection.password", "hibernate.hbm2ddl.auto"};

        for (String key : keys) {
            if (env.containsKey(key)) {

                String value = env.get(key);
                configOverrides.put(key, value);

            }
        }
        PerThreadEntityManagerProperties properties = new PerThreadEntityManagerProperties();
        properties.putAll(configOverrides);
        WithSimplePersistenceUnit.
        WithSimplePersistenceUnit.configure(properties);*/
    }

    public static EntityManagerFactory createEntityManagerFactory() {
        // https://stackoverflow.com/questions/8836834/read-environment-variables-in-persistence-xml-file
        Map<String, String> env = System.getenv();
        Map<String, Object> configOverrides = new HashMap<String, Object>();

        String[] keys = new String[] { "hibernate.archive.autodetection", "hibernate.connection.driver_class",
                "hibernate.connection.url", "hibernate.connection.username", "hibernate.connection.password", "hibernate.hbm2ddl.auto"};

        for (String key : keys) {
            if (env.containsKey(key)) {

                String value = env.get(key);
                configOverrides.put(key, value);

            }
        }

        return Persistence.createEntityManagerFactory("simple-persistence-unit", configOverrides);

    }

    private static Consumer<JavalinConfig> config() {
        return config -> {
            config.staticFiles.add(staticFiles -> {
                staticFiles.hostedPath = "/public";
                staticFiles.directory = "public";
            });
            AuthMiddelware.apply(config); //Hey, check this you ...
        };
    }


    private static void initTemplateEngine() {
        JavalinRenderer.register(
                (path, model, context) -> { // Función que renderiza el template
                    Handlebars handlebars = new Handlebars();
                    Template template = null;
                    try {
                        template = handlebars.compile(
                                "templates/" + path.replace(".hbs",""));
                        return template.apply(model);
                    } catch (IOException e) {
                        e.printStackTrace();
                        context.status(HttpStatus.NOT_FOUND);
                        return "No se encuentra la página indicada...";
                    }
                }, ".hbs" // Extensión del archivo de template
        );
    }
}
