package edu.brown.cs.finalproject.entities;

import java.sql.Time;
import java.util.Date;

public class EventBean extends EntityBean implements Event {
  private String name;
  private String venueID;
  private String originType;
  private User creator;
  private Date startDate;
  private Time startTime;
  private Time endTime;
  private boolean isPublic;
  private String category;
  private String description;

  public EventBean(String id, String Name, String venueID, String originType,
      User creator, Date startdate, Time starttime, Time endTime,
      boolean ispublic, String category, String description) {
    super(id);
    this.name = Name;
    this.venueID = venueID;
    this.originType = originType;
    this.creator = creator;
    this.startDate = startdate;
    this.startTime = starttime;
    this.endTime = endTime;
    this.isPublic = ispublic;
    this.category = category;
    this.description = description;
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
  public String getVenueID() {
    // TODO Auto-generated method stub
    return venueID;
  }

  @Override
  public String setVenueID(String newVenueID) {
    // TODO Auto-generated method stub
    venueID = newVenueID;
    return venueID;
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
  public Date getStartDate() {
    // TODO Auto-generated method stub
    return startDate;
  }

  @Override
  public Date setStartDate(Date newStartDate) {
    // TODO Auto-generated method stub
    startDate = newStartDate;
    return startDate;
  }

  @Override
  public Time getStartTime() {
    // TODO Auto-generated method stub
    return startTime;
  }

  @Override
  public Time setStartTime(Time newStartTime) {
    // TODO Auto-generated method stub
    startTime = newStartTime;
    return startTime;
  }

  @Override
  public Time getEndTime() {
    // TODO Auto-generated method stub
    return endTime;
  }

  @Override
  public Time setEndTime(Time newEndTime) {
    // TODO Auto-generated method stub
    endTime = newEndTime;
    return endTime;
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

}
