package edu.brown.cs.finalproject.entities;

import java.util.Date;
import java.sql.Time;

public interface Event {
	
	public String getName();
	public String setName(String newName);
	public String getVenueID();
	public String setVenueID(String newVenueID);
	public String getOriginType();
	public String setOriginType(String newOriginType);
	public String getCreatorID();
	public String setCreatorID(String newCreatorID);
	public Date getStartDate();
	public Date setStartDate(Date newStartDate);
	public Time getStartTime();
	public Time setStartTime(Time newStartDate);
	public Date getEndTime();
	public Date setEndTime(Date newEndTime);
	public String getCategory();
	public String setCategory(String newCategory);
	public boolean isPublic();
	public boolean setPublicity(boolean isPublic);
	public String getDescription();
	public String setDescription(String newDescription);
	

}
