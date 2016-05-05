package edu.brown.cs.finalproject.entities;

public interface Event extends Entity {
  public String getName();

  public String setName(String newName);

  public String getVenueName();

  public String getOriginType();

  public String setOriginType(String newOriginType);

  public User getCreator();

  public User setCreator(User user);

  public String getStartDate();

  public String setStartDate(String newStartDate);

//  public double getLatitude();
//  
//  public double getLongitude();
  
//  public int getInvitedCount();
  
  public int getAttendingCount();
  
  public int getMaybeCount();
  
  public int getNoReplyCount();
  
  public int getDeclinedCount();

  public String getCategory();

  public String setCategory(String newCategory);

  public boolean isPublic();

  public boolean setPublicity(boolean isPublic);

  public String getDescription();

  public String setDescription(String newDescription);
  
  public String getEventphoto();
  
  public String setEventphoto(String newEventphoto);


}