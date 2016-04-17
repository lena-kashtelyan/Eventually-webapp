package edu.brown.cs.finalproject.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class EventProxy extends EntityProxy<Event> implements Event {

  public EventProxy(String id) throws SQLException, ClassNotFoundException {
    super(id);
  }

  @Override
  protected void pullFromDB(Connection conn) {
    String eventquery = "SELECT * FROM events WHERE eventID=?;";
    String name;
    String venueID;
    String originType;
    User creator;
    Date startDate;
    Time startTime;
    Time endTime;
    boolean isPublic;
    String category;
    String description;

    try (PreparedStatement prep = conn.prepareStatement(eventquery)) {
      prep.setString(1, id);
      try (ResultSet rs = prep.executeQuery()) {
        name = rs.getString(2);
        venueID = rs.getString(3);
        originType = rs.getString(4);
        String creatorID = rs.getString(5);
        startDate = rs.getDate(6);
        startTime = rs.getTime(7);
        endTime = rs.getTime(8);
        category = rs.getString(9);
        isPublic = rs.getBoolean(10);
        description = rs.getString(11);
        creator = new UserProxy(creatorID);
      }
    } catch (SQLException s) {
      System.out.println("ERROR: ID not in Events Table");
      internal = null;
      return;
    } catch (NullPointerException | ClassNotFoundException e) {
      System.out.println("ERROR: ID not in User Table");
      return;
    }

    internal = new EventBean(id, name, venueID, originType, creator, startDate,
        startTime, endTime, isPublic, category, description);
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return internal.getName();
  }

  @Override
  public String setName(String newName) {
    // TODO Auto-generated method stub
    return internal.setName(newName);
  }

  @Override
  public String getVenueID() {
    // TODO Auto-generated method stub
    return internal.getVenueID();
  }

  @Override
  public String setVenueID(String newVenueID) {
    // TODO Auto-generated method stub
    return internal.setVenueID(newVenueID);
  }

  @Override
  public String getOriginType() {
    // TODO Auto-generated method stub
    return internal.getOriginType();
  }

  @Override
  public String setOriginType(String newOriginType) {
    // TODO Auto-generated method stub
    return internal.setOriginType(newOriginType);
  }

  @Override
  public User getCreator() {
    // TODO Auto-generated method stub
    return internal.getCreator();
  }

  @Override
  public User setCreator(User user) {
    // TODO Auto-generated method stub
    return internal.setCreator(user);
  }

  @Override
  public Date getStartDate() {
    // TODO Auto-generated method stub
    return internal.getStartDate();
  }

  @Override
  public Date setStartDate(Date newStartDate) {
    // TODO Auto-generated method stub
    return internal.setStartDate(newStartDate);
  }

  @Override
  public Time getStartTime() {
    // TODO Auto-generated method stub
    return internal.getStartTime();
  }

  @Override
  public Time setStartTime(Time newStartTime) {
    // TODO Auto-generated method stub
    return internal.setStartTime(newStartTime);
  }

  @Override
  public Time getEndTime() {
    // TODO Auto-generated method stub
    return internal.getEndTime();
  }

  @Override
  public Time setEndTime(Time newEndTime) {
    // TODO Auto-generated method stub
    return internal.setEndTime(newEndTime);
  }

  @Override
  public String getCategory() {
    // TODO Auto-generated method stub
    return internal.getCategory();
  }

  @Override
  public String setCategory(String newCategory) {
    // TODO Auto-generated method stub
    return internal.setCategory(newCategory);
  }

  @Override
  public boolean isPublic() {
    // TODO Auto-generated method stub
    return internal.isPublic();
  }

  @Override
  public boolean setPublicity(boolean isPublic) {
    // TODO Auto-generated method stub
    return internal.setPublicity(isPublic);
  }

  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return internal.getDescription();
  }

  @Override
  public String setDescription(String newDescription) {
    // TODO Auto-generated method stub
    return internal.setDescription(newDescription);
  }

}
