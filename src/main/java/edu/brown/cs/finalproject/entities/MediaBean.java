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
    return eventID;
  }

  @Override
  public String getUserName() {
    return username;
  }

  @Override
  public String getPath() {
    return path;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public String getTimeStamp() {
    return timestamp;
  }

}
