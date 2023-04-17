package com.ait.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ait.entity.CitizenPlan;
import com.ait.repository.CitizenPlanRepository;
import com.ait.requst.SearchRequest;
import com.ait.utils.EmailUtils;
import com.ait.utils.ExcelGenerator;
import com.ait.utils.PdfGenerator;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class ReportServiceImpl implements ReportService {
    
	@Autowired
	private CitizenPlanRepository planRepo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtils emailUtils;
	
	public List<String> getPlanNames() {
		
		return planRepo.getPlanNames();
	}

	
	public List<String> getPlanStatuses() {


		return planRepo.getPlanStatus();
	}

	
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();
		//BeanUtils.copyProperties(request,entity);
		if(null!=request.getPlanName() && !"".equals(request.getPlanName())) {
		 entity.setPlanName(request.getPlanName());	
		}
		if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			 entity.setPlanStatus(request.getPlanStatus());	
			}
		if(null!=request.getGender() && !"".equals(request.getGender())) {
			 entity.setGender(request.getGender());	
			}
		if(null!=request.getStartDate()&& !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(startDate,formatter);
		    entity.setPlanStartDate(localDate);
		}
		if(null!=request.getEndDate()&& !"".equals(request.getEndDate())) {
			String endDate = request.getEndDate();
			DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(endDate,formatter);
			entity.setPlanEndDate(localDate);
		}
		return planRepo.findAll(Example.of(entity));
	}

	
	public boolean exportExcel(HttpServletResponse response)throws Exception {
		
		 File f = new File("plans.xls");
		List<CitizenPlan> plans = planRepo.findAll();
		excelGenerator.Generate(response,plans,f);
		
		String subject = "Test Mail subject";
		String body = "<h1>Test mail body</h1>";
		String to = "kodapajeeja@gmail.com";
		
		emailUtils.sendEmail(subject, body, to,f);
		
		f.delete();
		
		return true;
	}

	
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		
		 File f = new File("plans.pdf");
		 List<CitizenPlan> plans = planRepo.findAll();
		 
		pdfGenerator.Generate(response,plans,f);
		
		String subject = "Test Mail subject";
		String body = "<h1>Test mail body</h1>";
		String to = "kodapajeeja@gmail.com";
		
		emailUtils.sendEmail(subject, body, to,f);
		
		f.delete();
		
		return true;
	}

}
