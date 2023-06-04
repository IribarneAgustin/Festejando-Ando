package com.microservice.festejandoando.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table (name = "Booking")
public class Booking extends PersistentObject{

	private LocalDate date;
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	@OneToMany
	@JoinColumn(name = "booking_id") 
	private List<Topic> topic = new ArrayList<>();
	private Long deposit;
	private Boolean isPaid;
	private Boolean confirm;
	private Double cost;
	
	public Booking() {	
	}
	
	public Booking(Long id, Boolean active, LocalDate date, Client client, ArrayList<Topic> topic, Long deposit, Boolean isPaid, Boolean confirm,
			Double cost) {
		super(id, active);
		this.date = date;
		this.client = client;
		this.topic = topic;
		this.deposit = deposit;
		this.isPaid = isPaid;
		this.confirm = confirm;
		this.cost = cost;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Topic> getTopic() {
		return topic;
	}

	public void setTopic(ArrayList<Topic> topic) {
		this.topic = topic;
	}

	public Long getDeposit() {
		return deposit;
	}

	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Boolean getConfirm() {
		return confirm;
	}

	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Booking [date=" + date + ", client=" + client + ", topic=" + topic + ", deposit=" + deposit
				+ ", isPaid=" + isPaid + ", confirm=" + confirm + ", cost=" + cost + "]";
	}
	
}
