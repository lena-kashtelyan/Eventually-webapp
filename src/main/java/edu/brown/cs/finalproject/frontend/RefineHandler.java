package edu.brown.cs.finalproject.frontend;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import com.google.common.collect.ImmutableMap;

import edu.brown.cs.finalproject.database.DatabaseManager;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class RefineHandler extends BackendInteraction implements Route {

    @Override
    public Object handle(Request req, Response res) {
        System.out.println("in savehandler");

        //DO SOMETHING WITH THESE
        return null;
    }
}

