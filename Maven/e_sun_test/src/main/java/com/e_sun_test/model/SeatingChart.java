package com.e_sun_test.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;

import jakarta.persistence.Table;

@Entity
@Table(name="seatingChart")
@NamedStoredProcedureQuery(name = "showAllChart",procedureName = "showAllChart")

public class SeatingChart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int floorSeatSeq;
	
	private int floorNo;
	
	private int seatNo;
	

	public int getFloorSeatSeq() {
		return floorSeatSeq;
	}

	public void setFloorSeatSeq(int floorSeatSeq) {
		this.floorSeatSeq = floorSeatSeq;
	}

	public int getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	
	

}
