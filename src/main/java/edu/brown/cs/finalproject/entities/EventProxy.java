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
      System.out.println(query);
      res = cartoDBCLient.request(query);
    } catch (CartoDBException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return;
    }
    
    String name = (String) res.getRows().get(0).get("name");
    String venueName = (String) res.getRows().get(0).get("venuename");
    String originType = (String) res.getRows().get(0).get("origintype");
    String creatorID = (String) res.getRows().get(0).get("creatorid");
    User creator = new UserProxy(creatorID);
    String startDate = (String) res.getRows().get(0).get("startdate");
    double lat = (double) res.getRows().get(0).get("latitude");
    double lng = (double) res.getRows().get(0).get("longitude");
    boolean isPublic = (boolean) res.getRows().get(0).get("public");
    String category = (String) res.getRows().get(0).get("category");
    String description = (String) res.getRows().get(0).get("description");
    int attendingCount = (int) res.getRows().get(0).get("attendingcount");
//    int invitedCount = (int) res.getRows().get(0).get("invitedcount");
    int maybeCount = (int) res.getRows().get(0).get("maybecount");
    int noReplyCount = (int) res.getRows().get(0).get("noreplycount");
    int declinedCount = (int) res.getRows().get(0).get("declinedcount");

    internal = new EventBean(id, name, venueName, originType, creator, startDate,
        attendingCount, declinedCount, noReplyCount, maybeCount, lat, lng, isPublic, category, description);
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getName();
  }

  @Override
  public String setName(String newName) {
    // TODO Auto-generated method stub
    getInternal();
    return internal.setName(newName);
  }

  @Override
  public String getVenueName() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getVenueName();
  }

  @Override
  public String getOriginType() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getOriginType();
  }

  @Override
  public String setOriginType(String newOriginType) {
    // TODO Auto-generated method stub
    getInternal();
    return internal.setOriginType(newOriginType);
  }

  @Override
  public User getCreator() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getCreator();
  }

  @Override
  public User setCreator(User user) {
    // TODO Auto-generated method stub
    getInternal();
    return internal.setCreator(user);
  }

  @Override
  public String getStartDate() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getStartDate();
  }

  @Override
  public String setStartDate(String newStartDate) {
    // TODO Auto-generated method stub
    getInternal();
    return internal.setStartDate(newStartDate);
  }

  @Override
  public String getCategory() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getCategory();
  }

  @Override
  public String setCategory(String newCategory) {
    // TODO Auto-generated method stub
    getInternal();
    return internal.setCategory(newCategory);
  }

  @Override
  public boolean isPublic() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.isPublic();
  }

  @Override
  public boolean setPublicity(boolean isPublic) {
    // TODO Auto-generated method stub
    getInternal();
    return internal.setPublicity(isPublic);
  }

  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getDescription();
  }

  @Override
  public String setDescription(String newDescription) {
    // TODO Auto-generated method stub
    getInternal();
    return internal.setDescription(newDescription);
  }

  @Override
  public double getLatitude() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getLatitude();
  }

  @Override
  public double getLongitude() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getLongitude();
  }

//  @Override
//  public int getInvitedCount() {
//    // TODO Auto-generated method stub
//    getInternal();
//    return internal.getInvitedCount();
//  }

  @Override
  public int getAttendingCount() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getAttendingCount();
  }

  @Override
  public int getMaybeCount() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getMaybeCount();
  }

  @Override
  public int getNoReplyCount() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getNoReplyCount();
  }

  @Override
  public int getDeclinedCount() {
    // TODO Auto-generated method stub
    getInternal();
    return internal.getDeclinedCount();
  }

}
