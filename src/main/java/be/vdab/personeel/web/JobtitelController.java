package be.vdab.personeel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.personeel.services.JobtitelService;
import be.vdab.personeel.services.WerknemerService;

@Controller
@RequestMapping("jobtitels")
class JobtitelController {

	private static final String JOBTITELS_VIEW="jobtitels/jobtitels"; 
	
	private final JobtitelService jobtitelService;
	private final WerknemerService werknemerService; 
	
	JobtitelController(JobtitelService jobtitelService, WerknemerService werknemerService) {
		this.jobtitelService=jobtitelService;
		this.werknemerService=werknemerService;
	}
	
	@GetMapping
	ModelAndView read() {

			return new ModelAndView(JOBTITELS_VIEW, "jobtitels", jobtitelService.findAll());
	}
	
	@GetMapping(params="jobtitel")
	ModelAndView werknemers(String jobtitel) {
		return new ModelAndView(JOBTITELS_VIEW, "jobtitels", jobtitelService.findAll())
				.addObject("jobtitel", jobtitel)
				.addObject("werknemers", werknemerService.findByJobtitelNaam(jobtitel));
	}
}
