package server.middlewares;

import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class AuthMiddelware {

    public static void apply(JavalinConfig config){

        List<String> paths = new ArrayList<String>();
        paths.add("/home");
        paths.add("/registro");
        paths.add("/login");
        //////////////////////// PROVISORIO////////////////////////////
        paths.add("/comunidades");
        paths.add("/propuestas-de-fusion");

        config.accessManager(((handler, context, set) ->
        {
            String path = context.path();

            if (paths.contains(path) || path.startsWith("/propuestas-de-fusion/")) {
                handler.handle(context);
            } else {
                String sessionValueId = context.sessionAttribute("id");

                if(sessionValueId == null) {
                    context.status(HttpStatus.FORBIDDEN);
                    context.redirect("/home");
                }else{
                    handler.handle(context);
                }
            }
        }));

    }

}
