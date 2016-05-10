package edu.brown.cs.finalproject.entities;

public class EventBean extends EntityBean implements Event {
  private String name;
  private String venueName;
  private String originType;
  private User creator;
  private String startDate;
  private String endDate;
  private boolean isPublic;
  private String category;
  private String description;

  private int attendingCount;
  // private int invitedCount;
  private int declinedCount;
  private int maybeCount;
  private int noReplyCount;
  private String eventphoto;

  public EventBean(String id, String Name, String venueName, String originType,
      User creator, String startDate, String endDate, int attendingCount,
      int declinedCount, int noReplyCount, int maybeCount, boolean ispublic,
      String category, String description, String eventphoto) {
    super(id);
    this.name = Name;
    this.venueName = venueName;
    this.originType = originType;
    this.creator = creator;
    this.startDate = startDate;
    this.endDate = endDate;
    this.isPublic = ispublic;
    this.category = category;
    this.description = description;

    this.attendingCount = attendingCount;
    // this.invitedCount = invitedCount;
    this.maybeCount = maybeCount;
    this.noReplyCount = noReplyCount;
    this.declinedCount = declinedCount;
    this.eventphoto = eventphoto;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String setName(String newName) {
    name = newName;
    return name;
  }

  @Override
  public String getVenueName() {
    return venueName;
  }

  @Override
  public String getOriginType() {
    return originType;
  }

  @Override
  public String setOriginType(String newOriginType) {
    originType = newOriginType;
    return originType;
  }

  @Override
  public User getCreator() {
    return creator;
  }

  @Override
  public User setCreator(User newCreator) {
    creator = newCreator;
    return creator;
  }

  @Override
  public String getStartDate() {
    return startDate;
  }

  @Override
  public String setStartDate(String newStartDate) {
    startDate = newStartDate;
    return startDate;
  }

  @Override
  public String getEndDate() {
    return endDate;
  }

  @Override
  public String getCategory() {
    return category;
  }

  @Override
  public String setCategory(String newCategory) {
    category = newCategory;
    return category;
  }

  @Override
  public boolean isPublic() {
    return isPublic;
  }

  @Override
  public boolean setPublicity(boolean isPublic) {
    this.isPublic = isPublic;
    return isPublic;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public String setDescription(String newDescription) {
    description = newDescription;
    return description;
  }

  // @Override
  // public int getInvitedCount() {
  // // TODO Auto-generated method stub
  // return invitedCount;
  // }

  @Override
  public int getAttendingCount() {
    return attendingCount;
  }
  
  @Override
  public int setAttendingCount(int newAttendingCount) {
	  attendingCount = newAttendingCount;
	  return attendingCount;
  }

  @Override
  public int getMaybeCount() {
    return maybeCount;
  }

  @Override
  public int getNoReplyCount() {
    return noReplyCount;
  }

  @Override
  public int getDeclinedCount() {
    return declinedCount;
  }

  @Override
  public String getEventphoto() {
    System.out.println(eventphoto);
    return eventphoto;
  }

  @Override
  public String setEventphoto(String newEventphoto) {
    this.eventphoto = newEventphoto;
    return this.eventphoto;
  }
}
