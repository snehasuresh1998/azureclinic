package com.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.demo.entity.Coovid;
import com.demo.entity.Medicine;
import com.demo.entity.Patient;
import com.demo.entity.User;
import com.demo.service.MedicineService;
import com.demo.service.PatientService;
import com.demo.service.UserService;

@Controller
public class MedicinePatientController {
	@Autowired
	private MedicineService mservice;
	
	@Autowired
	private PatientService pservice;
	
	@Autowired
	private UserService uservice;
	
	@GetMapping("/index")
	public String home() {
		return "index";
	}
	

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String UserSave(@ModelAttribute User u) {
		
		uservice.addUser(u);
		return "adminlogin";
	}
	
	@GetMapping("/adminlogin")
	public String admin() {
		return "adminlogin";
	}
	
	@GetMapping("/addMedicine")
	public String addMedForm() {
		return "addMedicine";
	}
	
	@GetMapping("/addPatient")
	public String addPatientForm() {
		return "addPatient";
	}
	
	@PostMapping("/addMed")
	public String medSave(@ModelAttribute Medicine m,HttpSession session) {
		System.out.println(m);
		mservice.addMed(m);
		session.setAttribute("msg", "Medicine added successfully..");
		return "redirect:/viewMedicine";
	}
	
	@PostMapping("/addPatients")
	public String patientSave(@ModelAttribute Patient p,HttpSession session) {
		System.out.println(p);
		pservice.addPatient(p);
		session.setAttribute("msg", "Patient added successfully..");
		return "redirect:/viewPatient";
	}
	
	@GetMapping("/viewMedicine")
	public String medView(Model m1) {
		List<Medicine> med = mservice.getAllMed();
		m1.addAttribute("med", med);
		return "viewMedicine";
	}
	
	@GetMapping("/viewPatient")
	public String patientView(Model m) {
		List<Patient> pat = pservice.getAllPatient();
		m.addAttribute("patient", pat);
		return "viewPatient";
	}
	
	@GetMapping("/updateMedicine/{mid}")
	public String medEdit(@PathVariable String mid,Model m1) {
		
		Medicine m=mservice.getMedById(mid);
		m1.addAttribute("med", m);
		return "updateMedicine";
	}
	
	
	
	@GetMapping("/updatePatient/{pid}")
	public String patientEdit(@PathVariable String pid,Model m2) {
		
		Patient p=pservice.getPatientById(pid);
		m2.addAttribute("patient", p);
		return "updatePatient";
	}
	
	@PostMapping("/updatemed")
	public String medUpdate(@ModelAttribute Medicine m,HttpSession session) {
		
		mservice.addMed(m);
		session.setAttribute("msg", "Medicine updated successfully..");
		return "redirect:/viewMedicine";
	}
	
	@PostMapping("/updatePatients")
	public String patientUpdate(@ModelAttribute Patient p,HttpSession session) {
		
		pservice.addPatient(p);
		session.setAttribute("msg", "Patient updated successfully..");
		return "redirect:/viewPatient";
	}
	
	@GetMapping("/deleteMed/{mid}")
	public String patientDelete(@PathVariable String mid,HttpSession session) {
		mservice.deleteMed(mid);
		session.setAttribute("msg", "Medicine deleted successfully..");
		return "redirect:/viewMedicine";
	}
	
	
	@GetMapping("/deletepat/{pid}")
	public String medDelete(@PathVariable String pid,HttpSession session) {
		pservice.deletePatient(pid);
		session.setAttribute("msg", "Patient deleted successfully..");
		return "redirect:/viewPatient";
	}
	
	

	@RequestMapping("/Covid")
	public ModelAndView home1() {
		ModelAndView model=new ModelAndView("Covid");
		
		//ModelAndView m=new ModelAndView("color");
		RestTemplate resttemplate=new RestTemplate();
		
		Coovid result=resttemplate.getForObject("http://localhost:8089/Covid/details",Coovid.class);
		System.out.println(result);
		//System.out.println(result.getColo());
		model.addObject("Covid",result.getcov());
	
		return model;
		
		
	}
}


