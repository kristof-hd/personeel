package be.vdab.personeel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.personeel.services.JobtitelService;

@Controller
@RequestMapping("jobtitels")
class JobtitelController {

	private static final String JOBTITELS_VIEW="jobtitels/jobtitels"; 
	
	private final JobtitelService jobtitelService;
	
	JobtitelController(JobtitelService jobtitelService) {
		this.jobtitelService=jobtitelService;
	}
	
	@GetMapping
	ModelAndView read() {
		return new ModelAndView(JOBTITELS_VIEW, "jobtitels", jobtitelService.findAll());
	}
	
	@GetMapping("{id}")
	ModelAndView werknemers(@PathVariable long id) {
		ModelAndView modelAndView=new ModelAndView(JOBTITELS_VIEW, "jobtitels", jobtitelService.findAll());
		jobtitelService.read(id).ifPresent(jobtitel->{
			modelAndView.addObject(jobtitel);
		});
		return modelAndView;
	}
}
