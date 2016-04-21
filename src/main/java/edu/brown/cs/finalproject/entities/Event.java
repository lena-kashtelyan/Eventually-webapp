package edu.brown.cs.finalproject.entities;

import java.sql.Time;
import java.util.Date;

public interface Event extends Entity {
  public String getName();

  public String setName(String newName);

  public String getVenueID();

  public String setVenueID(String newVenueID);

  public String getOriginType();

  public String setOriginType(String newOriginType);

  public User getCreator();

  public User setCreator(User user);

  public Date getStartDate();

  public Date setStartDate(Date newStartDate);

  public Time getStartTime();

  public Time setStartTime(Time newStartTime);

  public Time getEndTime();

  public Time setEndTime(Time newEndTime);

  public String getCategory();

  public String setCategory(String newCategory);

  public boolean isPublic();

  public boolean setPublicity(boolean isPublic);

  public String getDescription();

  public String setDescription(String newDescription);

}