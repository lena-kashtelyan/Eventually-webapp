package edu.brown.cs.finalproject.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.Map;

import com.cartodb.CartoDBClientIF;
import com.cartodb.CartoDBException;
import com.cartodb.impl.ApiKeyCartoDBClient;
import com.cartodb.model.CartoDBResponse;

public class EventProxy extends EntityProxy<Event> implements Event {

    public EventProxy(String id) throws ClassNotFoundException {
        super(id);
    }

    @Override
    protected void pullFromDB() {
        CartoDBResponse<Map<String, Object>> res;
        try {
            CartoDBClientIF cartoDBCLient= new ApiKeyCartoDBClient("cs32finalproject", "ad54038628d84dceb55a7adb81eddfcf9976e994");
            String query = String.format("select * from events where eventid='%s';", id);
//            System.out.println(query);
            res = cartoDBCLient.request(query);
        } catch (CartoDBException e) {
            e.printStackTrace();
            return;
        }

        String name = (String) res.getRows().get(0).get("name");
//        System.out.println(name);
        String venueName = (String) res.getRows().get(0).get("venuename");
//        System.out.println(venueName);
        String originType = (String) res.getRows().get(0).get("origintype");
        String creatorID = (String) res.getRows().get(0).get("creatorid");
//        System.out.println("before user creation");
        User creator = new UserProxy(creatorID);
//        System.out.println("hello");
        String startDate = (String) res.getRows().get(0).get("startdate");
//        System.out.println(startDate);
//        double lat = (double) res.getRows().get(0).get("latitude");
//        System.out.println("after lat");
//        System.out.println(lat);
//        double lng = (double) res.getRows().get(0).get("longitude");
//        boolean isPublic = (boolean) res.getRows().get(0).get("public");
        
        boolean isPublic = true;
        String category = (String) res.getRows().get(0).get("category");
        String description = (String) res.getRows().get(0).get("description");
//        System.out.println(description);
        int attendingCount = (int) res.getRows().get(0).get("attendingcount");
//        System.out.println(attendingCount);
        //    int invitedCount = (int) res.getRows().get(0).get("invitedcount");
        int maybeCount = (int) res.getRows().get(0).get("maybecount");
        int noReplyCount = (int) res.getRows().get(0).get("noreplycount");
        int declinedCount = (int) res.getRows().get(0).get("declinedcount");
        String eventphoto = (String) res.getRows().get(0).get("eventphoto");

        internal = new EventBean(id, name, venueName, originType, creator, startDate,
                attendingCount, declinedCount, noReplyCount, maybeCount, isPublic, category, description, eventphoto);
    }

    @Override
    public String getName() {
        getInternal();
        return internal.getName();
    }

    @Override
    public String setName(String newName) {
        getInternal();
        return internal.setName(newName);
    }

    @Override
    public String getVenueName() {
        getInternal();
        return internal.getVenueName();
    }

    @Override
    public String getOriginType() {
        getInternal();
        return internal.getOriginType();
    }

    @Override
    public String setOriginType(String newOriginType) {
        getInternal();
        return internal.setOriginType(newOriginType);
    }

    @Override
    public User getCreator() {
        getInternal();
        return internal.getCreator();
    }

    @Override
    public User setCreator(User user) {
        getInternal();
        return internal.setCreator(user);
    }

    @Override
    public String getStartDate() {
        getInternal();
        return internal.getStartDate();
    }

    @Override
    public String setStartDate(String newStartDate) {
        getInternal();
        return internal.setStartDate(newStartDate);
    }

    @Override
    public String getCategory() {
        getInternal();
        return internal.getCategory();
    }

    @Override
    public String setCategory(String newCategory) {
        getInternal();
        return internal.setCategory(newCategory);
    }

    @Override
    public boolean isPublic() {
        getInternal();
        return internal.isPublic();
    }

    @Override
    public boolean setPublicity(boolean isPublic) {
        getInternal();
        return internal.setPublicity(isPublic);
    }

    @Override
    public String getDescription() {
        getInternal();
        return internal.getDescription();
    }

    @Override
    public String setDescription(String newDescription) {
        getInternal();
        return internal.setDescription(newDescription);
    }

//    @Override
//    public double getLatitude() {
//        getInternal();
//        return internal.getLatitude();
//    }
//
//    @Override
//    public double getLongitude() {
//        getInternal();
//        return internal.getLongitude();
//    }

    //  @Override
    //  public int getInvitedCount() {
    //    // TODO Auto-generated method stub
    //    getInternal();
    //    return internal.getInvitedCount();
    //  }

    @Override
    public int getAttendingCount() {
        getInternal();
        return internal.getAttendingCount();
    }

    @Override
    public int getMaybeCount() {
        getInternal();
        return internal.getMaybeCount();
    }

    @Override
    public int getNoReplyCount() {
        getInternal();
        return internal.getNoReplyCount();
    }

    @Override
    public int getDeclinedCount() {
        getInternal();
        return internal.getDeclinedCount();
    }

    @Override
    public String getEventphoto() {
        getInternal();
        System.out.println("here");
        System.out.println(internal.getEventphoto());
        return internal.getEventphoto();
    }

    @Override
    public String setEventphoto(String newEventphoto) {
      getInternal();
      return internal.setEventphoto(newEventphoto);
    }

}
