package edu.brown.cs.finalproject.frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import edu.brown.cs.finalproject.credentials.AuthToken;
import edu.brown.cs.finalproject.entities.Event;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

/**
 * Private visible class to handle the serving of the login
 * ftl template.
 */
public class CreateView extends BackendInteraction implements TemplateViewRoute {
    private String htmlUrl;

    CreateView(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    /**
     * The handle method.
     * @param req
     *          The request object.
     * @param res
     *          The response object.
     */
    @Override
    public ModelAndView handle(Request req, Response res) {
        QueryParamsMap qm = req.queryMap();
        String authString = qm.value("auth");
        if (authString != null) {
            System.out.println("in create view w/auth");
            System.out.println(authString);
            AuthToken authToken = AuthToken.generateAuthToken(authString);
            if (auth.verifyAuthToken(authToken)) {      
                Map<Object, Object> data = ImmutableMap.builder()
                        .put("title", "Browse")
                        .put("auth", authToken.toString()).build();
                System.out.println("returning from create view w/auth");
                return new ModelAndView(data, htmlUrl);
            } else {
                //malicious user, redirect to login
                Map<Object, Object> data = ImmutableMap.builder()
                        .put("title", "Login").build();
                return new ModelAndView(data, "login.ftl");
            }
        } else {
            // no authentification token, redirect to login
            System.out.println("in createview, no auth");
            Map<Object, Object> data = ImmutableMap.builder()
                    .put("title", "Login").build();
            return new ModelAndView(data, "login.ftl");
        }
    }
}
