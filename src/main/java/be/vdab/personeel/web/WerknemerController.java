package be.vdab.personeel.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.personeel.entities.Werknemer;

@Controller
@RequestMapping("werknemers")
public class WerknemerController {

	private static final String WERKNEMER_VIEW="werknemer";
	private static final String REDIRECT_BIJ_WERKNEMER_NIET_GEVONDEN="redirect:/";
	private static final String OPSLAG_VIEW = "werknemers/opslag";
	private static final String REDIRECT_NA_OPSLAG="redirect:/werknemers/{id}"; 
	
//	@GetMapping
//	String redirect...
	
//	@GetMapping("{werknemer}")
//	ModelAndView read(@PathVariable Optional<Werknemer> werknemer) {
//		
//			return new ModelAndView(WERKNEMER_VIEW).addObject(werknemer.get());
//		
//		
//	}
	
	@GetMapping("{werknemer}")
	ModelAndView read(@PathVariable Optional<Werknemer> werknemer) {
		return new ModelAndView(WERKNEMER_VIEW, "werknemer", werknemer.get());
	}
		
	@GetMapping("{werknemer}/opslag")
	ModelAndView opslag(@PathVariable Optional<Werknemer> werknemer) {
		
		OpslagForm form = new OpslagForm(); 
		return new ModelAndView(OPSLAG_VIEW)
					.addObject(werknemer.get())
					.addObject(form);
	
		
	}	

	@PostMapping("{werknemer}/opslag")
	ModelAndView opslag(@PathVariable Optional<Werknemer> werknemer, @Valid OpslagForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (werknemer.isPresent()) {
			if (bindingResult.hasErrors()) {
				return new ModelAndView(OPSLAG_VIEW).addObject(werknemer.get());
			}
			werknemer.get().opslag(form.getBedrag());
			werknemerService.update(werknemer.get());
			redirectAttributes.addAttribute("id", werknemer.get().getId());
			return new ModelAndView(REDIRECT_NA_OPSLAG);
		}
		redirectAttributes.addAttribute("fout", "Werknemer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_WERKNEMER_NIET_GEVONDEN);
		
	}	


}
