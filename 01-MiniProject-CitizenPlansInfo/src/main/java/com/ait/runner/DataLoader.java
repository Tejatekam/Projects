package com.ait.runner;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ait.entity.CitizenPlan;
import com.ait.repository.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner{
    
	
	@Autowired
	private CitizenPlanRepository repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		repo.deleteAll();
		
		CitizenPlan c1 = new CitizenPlan();
		c1.setCitizenName("Teja");
		c1.setGender("Fe-Male");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmount(6000.00);
		
		CitizenPlan c2 = new CitizenPlan();
		c2.setCitizenName("John");
		c2.setGender("Male");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denied");
		c2.setDenialReason("Tax Issue");
				
		CitizenPlan c3 = new CitizenPlan();
		c3.setCitizenName("Akhila");
		c3.setGender("Fe-Male");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
		c3.setTerminationReason("ABC");
		c3.setPlanStartDate(LocalDate.now());
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setBenefitAmount(2000.00);
		
		CitizenPlan c4 = new CitizenPlan();
		c4.setCitizenName("Smith");
		c4.setGender("Male");
		c4.setPlanName("Food");
		c4.setPlanStatus("Approved");
		c4.setPlanStartDate(LocalDate.now());
		c4.setPlanEndDate(LocalDate.now().plusMonths(6));
		c4.setBenefitAmount(9000.00);
		
		CitizenPlan c5 = new CitizenPlan();
		c5.setCitizenName("Cathy");
		c5.setGender("Fe-Male");
		c5.setPlanName("Food");
		c5.setPlanStatus("Denied");
		c5.setDenialReason("Loan Issue");
		
		
		CitizenPlan c6 = new CitizenPlan();
		c6.setCitizenName("Arjun");
		c6.setGender("Male");
		c6.setPlanName("Food");
		c6.setPlanStatus("Terminated");
		c6.setTerminationReason("Tax Issue");
		c6.setPlanStartDate(LocalDate.now());
		c6.setPlanEndDate(LocalDate.now().plusMonths(6));
		c6.setBenefitAmount(1000.00);
		
		CitizenPlan c7 = new CitizenPlan();
		c7.setCitizenName("Rosy");
		c7.setGender("Fe-Male");
		c7.setPlanName("Medical");
		c7.setPlanStatus("Approved");
		c7.setPlanStartDate(LocalDate.now());
		c7.setPlanEndDate(LocalDate.now().plusMonths(6));
		c7.setBenefitAmount(9000.00);
		
		CitizenPlan c8 = new CitizenPlan();
		c8.setCitizenName("Rahul");
		c8.setGender("Male");
		c8.setPlanName("Medical");
		c8.setPlanStatus("Denied");
		c8.setDenialReason("Pending loan Issue");
		
		CitizenPlan c9 = new CitizenPlan();
		c9.setCitizenName("Anitha");
		c9.setGender("Fe-Male");
		c9.setPlanName("Medical");
		c9.setPlanStatus("Terminated");
		c9.setTerminationReason("Multiple requests");
		c9.setPlanStartDate(LocalDate.now());
		c9.setPlanEndDate(LocalDate.now().plusMonths(6));
		c9.setBenefitAmount(1000.00);
		
		CitizenPlan c10 = new CitizenPlan();
		c10.setCitizenName("Jasvir");
		c10.setGender("Male");
		c10.setPlanName("Employment");
		c10.setPlanStatus("Approved");
		c10.setPlanStartDate(LocalDate.now());
		c10.setPlanEndDate(LocalDate.now().plusMonths(6));
		c10.setBenefitAmount(12000.00);
		
		CitizenPlan c11 = new CitizenPlan();
		c11.setCitizenName("Jenni");
		c11.setGender("Fe-Male");
		c11.setPlanName("Employment");
		c11.setPlanStatus("Denied");
		c11.setDenialReason("Tax Issue");
				
		CitizenPlan c12 = new CitizenPlan();
		c12.setCitizenName("Akhil");
		c12.setGender("Male");
		c12.setPlanName("Employment");
		c12.setPlanStatus("Terminated");
		c12.setTerminationReason("Branch transfer");
		c12.setPlanStartDate(LocalDate.now());
		c12.setPlanEndDate(LocalDate.now().plusMonths(6));
		c12.setBenefitAmount(2000.00);
		
		repo.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12));
	}

}
