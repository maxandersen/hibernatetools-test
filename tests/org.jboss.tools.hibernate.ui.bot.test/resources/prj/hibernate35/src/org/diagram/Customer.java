package org.diagram;

// Generated Jul 16, 2012 4:06:14 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Customer generated by hbm2java
 */
public class Customer implements java.io.Serializable {

	private short customerId;
	private Address address;
	private Store store;
	private String firstName;
	private String lastName;
	private String email;
	private boolean active;
	private Date createDate;
	private Date lastUpdate;
	private Set rentals = new HashSet(0);
	private Set payments = new HashSet(0);

	public Customer() {
	}

	public Customer(short customerId, Address address, Store store,
			String firstName, String lastName, boolean active, Date createDate,
			Date lastUpdate) {
		this.customerId = customerId;
		this.address = address;
		this.store = store;
		this.firstName = firstName;
		this.lastName = lastName;
		this.active = active;
		this.createDate = createDate;
		this.lastUpdate = lastUpdate;
	}

	public Customer(short customerId, Address address, Store store,
			String firstName, String lastName, String email, boolean active,
			Date createDate, Date lastUpdate, Set rentals, Set payments) {
		this.customerId = customerId;
		this.address = address;
		this.store = store;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.active = active;
		this.createDate = createDate;
		this.lastUpdate = lastUpdate;
		this.rentals = rentals;
		this.payments = payments;
	}

	public short getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(short customerId) {
		this.customerId = customerId;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Set getRentals() {
		return this.rentals;
	}

	public void setRentals(Set rentals) {
		this.rentals = rentals;
	}

	public Set getPayments() {
		return this.payments;
	}

	public void setPayments(Set payments) {
		this.payments = payments;
	}

}
