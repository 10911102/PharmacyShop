package com.medical.Shop;

import java.util.List;

import com.medical.Pharmacy.Doctor;

public class DoctorOperations {
	private List<Doctor> doctors;

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
	
	/**
	 * Search name of doctor from list. may be first name or last name of the doctor.
	 * @param name The String to search in Doctor List
	 * @return doctor object if it is present in list, if not then return null
	 */
	public Doctor searchDoctor(String name){
		for(Doctor doctor:doctors) {
			if(doctor.getfName().equalsIgnoreCase(name)){
				return doctor;
			}
			else if(doctor.getlName().equalsIgnoreCase(name)) {
				return doctor;
			}
		}
		System.out.println("Try another Name!");
		return null;
	}

}
