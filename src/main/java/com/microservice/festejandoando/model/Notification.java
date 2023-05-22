package com.microservice.festejandoando.model;

public class Notification {

	private Boolean isSent;
	private Booking booking;
	private EStatus status;

	public Boolean getIsSent() {
		return isSent;
	}

	public void setIsSent(Boolean isSent) {
		this.isSent = isSent;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Notification [isSent=" + isSent + ", booking=" + booking + ", status=" + status + "]";
	}

}
