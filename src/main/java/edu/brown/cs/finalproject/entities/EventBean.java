package edu.brown.cs.finalproject.entities;

import java.sql.Time;
import java.util.Date;

public class EventBean extends EntityBean implements Event {
  private String name;
  private String venueName;
  private String originType;
  private User creator;
  private String startDate;
  private boolean isPublic;
  private String category;
  private String description;
  private double latitude;
  private double longitude;
  private int attendingCount;
//  private int invitedCount;
  private int declinedCount;
  private int maybeCount;
  private int noReplyCount;
  private String eventphoto;

  public EventBean(String id, String Name, String venueName, String originType,
      User creator, String startDate, int attendingCount, int declinedCount, int noReplyCount, int maybeCount,
      double latitude, double longitude, boolean ispublic, String category, String description, String eventphoto) {
    super(id);
    this.name = Name;
    this.venueName = venueName;
    this.originType = originType;
    this.creator = creator;
    this.startDate = startDate;
    this.isPublic = ispublic;
    this.category = category;
    this.description = description;
    this.latitude = latitude;
    this.longitude = longitude;
    this.attendingCount = attendingCount;
//    this.invitedCount = invitedCount;
    this.maybeCount = maybeCount;
    this.noReplyCount = noReplyCount;
    this.declinedCount = declinedCount;
    this.eventphoto = eventphoto;
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return name;
  }

  @Override
  public String setName(String newName) {
    // TODO Auto-generated method stub
    name = newName;
    return name;
  }

  @Override
  public String getVenueName() {
    // TODO Auto-generated method stub
    return venueName;
  }

  @Override
  public String getOriginType() {
    // TODO Auto-generated method stub
    return originType;
  }

  @Override
  public String setOriginType(String newOriginType) {
    // TODO Auto-generated method stub
    originType = newOriginType;
    return originType;
  }

  @Override
  public User getCreator() {
    // TODO Auto-generated method stub
    return creator;
  }

  @Override
  public User setCreator(User newCreator) {
    // TODO Auto-generated method stub
    creator = newCreator;
    return creator;
  }

  @Override
  public String getStartDate() {
    // TODO Auto-generated method stub
    return startDate;
  }

  @Override
  public String setStartDate(String newStartDate) {
    // TODO Auto-generated method stub
    startDate = newStartDate;
    return startDate;
  }

  @Override
  public String getCategory() {
    // TODO Auto-generated method stub
    return category;
  }

  @Override
  public String setCategory(String newCategory) {
    // TODO Auto-generated method stub
    category = newCategory;
    return category;
  }

  @Override
  public boolean isPublic() {
    // TODO Auto-generated method stub
    return isPublic;
  }

  @Override
  public boolean setPublicity(boolean isPublic) {
    // TODO Auto-generated method stub
    this.isPublic = isPublic;
    return isPublic;
  }

  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return description;
  }

  @Override
  public String setDescription(String newDescription) {
    // TODO Auto-generated method stub
    description = newDescription;
    return description;
  }

  @Override
  public double getLatitude() {
    // TODO Auto-generated method stub
    return latitude;
  }

  @Override
  public double getLongitude() {
    // TODO Auto-generated method stub
    return longitude;
  }

//  @Override
//  public int getInvitedCount() {
//    // TODO Auto-generated method stub
//    return invitedCount;
//  }

  @Override
  public int getAttendingCount() {
    // TODO Auto-generated method stub
    return attendingCount;
  }

  @Override
  public int getMaybeCount() {
    // TODO Auto-generated method stub
    return maybeCount;
  }

  @Override
  public int getNoReplyCount() {
    // TODO Auto-generated method stub
    return noReplyCount;
  }

  @Override
  public int getDeclinedCount() {
    // TODO Auto-generated method stub
    return declinedCount;
  }

    public String getEventphoto() {
        System.out.println(eventphoto);
        return eventphoto;
    }
}
