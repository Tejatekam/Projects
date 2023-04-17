package com.ait.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ait.entity.CitizenPlan;
import com.ait.requst.SearchRequest;
import com.ait.service.ReportService;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response)throws Exception {
		
		response.setContentType("application/pdf");
		response.addHeader("content-Disposition", "attachment;filename=plans.pdf");
		service.exportPdf(response);
	}

	
	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response)throws Exception {
		
		response.setContentType("application/octet-stream");
		response.addHeader("content-Disposition", "attachment;filename=plans.xls");
		service.exportExcel(response);
	}
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest request,Model model) {
		
		System.out.println(request);
		
		List<CitizenPlan> plans = service.search(request);
		model.addAttribute("plans",plans);
		 init(model);
		
		return "index";
	}
	
	@GetMapping("/")
	public String loadIndexPage(Model model) {
		model.addAttribute("search",new SearchRequest());
		 
		 init(model);
		return "index";
	}

	private void init(Model model) {
		 //model.addAttribute("search",new SearchRequest());
		 model.addAttribute("names",service.getPlanNames());
		 model.addAttribute("status",service.getPlanStatuses());
	}

}
