package edu.brown.cs.finalproject.frontend;

import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class RefineHandler extends BackendInteraction implements Route {

    @Override
    public Object handle(Request req, Response res) {
    	System.out.println("in refinehandler");
        QueryParamsMap qm = req.queryMap();
        String authString = qm.value("auth");
        String username = qm.value("username");

        String location = qm.value("location");
        String sliderValue = qm.value("radius");
        String floorTime = qm.value("floorTime");
        String ceilingTime = qm.value("ceilingTime");
        
        System.out.println("authString: " + authString);
        System.out.println("username: " + username);
        System.out.println("location: " + location);
        System.out.println("sliderValue: " + sliderValue);
        System.out.println("floorTime: " + floorTime);
        System.out.println("ceilingTime: " + ceilingTime);

        //DO SOMETHING WITH THESE
        return 5;
    }
}

