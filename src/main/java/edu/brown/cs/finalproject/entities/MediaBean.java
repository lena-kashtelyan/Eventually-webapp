package edu.brown.cs.finalproject.entities;

public class MediaBean extends EntityBean implements Media {
  private String eventID;
  private String username;
  private String path;
  private String type;
  private String timestamp;

  public MediaBean(String id, String eventID, String username, String path,
      String timestamp, String type) {
    super(id);
    this.eventID = eventID;
    this.username = username;
    this.path = path;
    this.type = type;
    this.timestamp = timestamp;
  }

  @Override
  public String getEventID() {
    // TODO Auto-generated method stub
    return eventID;
  }

  @Override
  public String getUserName() {
    // TODO Auto-generated method stub
    return username;
  }

  @Override
  public String getPath() {
    // TODO Auto-generated method stub
    return path;
  }

  @Override
  public String getType() {
    // TODO Auto-generated method stub
    return type;
  }

  @Override
  public String getTimeStamp() {
    // TODO Auto-generated method stub
    return timestamp;
  }

}
