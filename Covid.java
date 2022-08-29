package com.mindtree.entity;

import java.io.Serializable;
import java.sql.Date;

public class Covid implements Serializable {

		private static final long serialVersionUID = 1L;

		private Integer id;
		private String state;
		private String district;
		private Date date;
		private String confirmed;
		private String firstState;
		private String firstConfirmed;
		private String secState;
		private String secConfirmed;
		
		
		public Covid() {
		}

		public Covid(Integer id, String state) {
			this.id = id;
			this.state = state;
			this.district = district;
			this.date = date;
			this.confirmed = confirmed;
			this.firstState = firstState;
			this.firstConfirmed = firstConfirmed;
			this.secState = secState;
			this.secConfirmed = secConfirmed;
			
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}
		
		public String getDistrict() {
			return district;
		}
		
		public void setDistrict(String district) {
			this.district = district;
		}
		
		public String getConfirmed() {
			return confirmed;
		}
		
		public void setConfirmed(String confirmed) {
			this.confirmed = confirmed;
		}

		public Date getDate() {
			return date;
		}
		
		public void setDate(Date date) {
			this.date = date;
		}
		
		public String getfirstState() {
			return firstState;
		}
		
		public void setfirstState(String firstState) {
			this.firstState = firstState;
		}
		
		
		public String getfirstConfirmed() {
			return firstConfirmed;
		}
		
		public void setfirstConfirmed(String firstConfirmed) {
			this.firstConfirmed = firstConfirmed;
		}
		
		public String getsecState() {
			return secState;
		}
		
		public void setsecState(String secState) {
			this.secState = secState;
		}
		
		
		public String getsecConfirmed() {
			return secConfirmed;
		}
		
		public void setsecConfirmed(String secConfirmed) {
			this.secConfirmed = secConfirmed;
		}
		
		
		@Override
		public String toString() {
			return "Covid [id=" + id + ", state=" + state + ", district=" + district + ", date="+ date +", confirmed="+ confirmed + ", firstState=" + firstState + ", firstConfirmed=" + firstConfirmed + ", secState=" + secState + ", secConfirmed=" + secConfirmed +"]";
		}
	}
	
	
