package edu.almabridge.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Event extends ErrorModel{
	@Id
	private int eventId ;
	private String eventLocation ;
	private Date eventDate ;
	private String description ;
	
	
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
