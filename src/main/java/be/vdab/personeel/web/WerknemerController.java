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
import be.vdab.personeel.services.WerknemerService;

@Controller
@RequestMapping("werknemers")
class WerknemerController {

	private static final String WERKNEMER_VIEW="werknemers/werknemer";
	private static final String WERKNEMER_MET_ONDERGESCHIKTE_VIEW="werknemers/werknemerMetOndergeschikte";

	private static final String REDIRECT_BIJ_WERKNEMER_NIET_GEVONDEN="redirect:/";
	private static final String OPSLAG_VIEW = "werknemers/opslag";
	private static final String REDIRECT_NA_OPSLAG="redirect:/werknemers/{id}"; 
	private static final String RIJKSREGISTERNUMMER_VIEW="werknemers/rijksregisternummer"; 
	private static final String REDIRECT_NA_RIJKSREGISTERNUMMER="redirect:/werknemers/{id}"; 

	private final WerknemerService werknemerService; 
	
	WerknemerController(WerknemerService werknemerService) {
		this.werknemerService=werknemerService;
	}
	
	@GetMapping
	String initieelGetoondeWerknemer() {
		long idHoogste=werknemerService.findMetHoogsteHierarchie().getId(); 
		return "redirect:/werknemers/"+idHoogste;
	}
		
	@GetMapping("{werknemer}")
	ModelAndView read(@PathVariable Optional<Werknemer> werknemer, RedirectAttributes redirectAttributes) {
		if (werknemer.isPresent()) {
			return new ModelAndView(WERKNEMER_VIEW).addObject(werknemer.get())
					.addObject("ondergeschikten", werknemerService.findOndergeschikten(werknemer.get().getId()));
		}
		redirectAttributes.addAttribute("fout", "Werknemer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_WERKNEMER_NIET_GEVONDEN); 
	}

	@GetMapping(path="{werknemer}/ondergeschikten/{ondergeschikte}")
	ModelAndView ondergeschikte(@PathVariable Optional<Werknemer> werknemer, @PathVariable long ondergeschikte, RedirectAttributes redirectAttributes) {
		//return new ModelAndView(WERKNEMER_VIEW, "ondergeschikte", werknemerService.findById(ondergeschikte));
		
		if (werknemer.isPresent()) {
			return new ModelAndView(WERKNEMER_MET_ONDERGESCHIKTE_VIEW).addObject(werknemer.get())
					.addObject("ondergeschikten", werknemerService.findOndergeschikten(werknemer.get().getId()))
					.addObject("ondergeschikte", werknemerService.findById(ondergeschikte));
			//return new ModelAndView(WERKNEMER_VIEW, "werknemer", werknemer.get());
		}
		redirectAttributes.addAttribute("fout", "Werknemer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_WERKNEMER_NIET_GEVONDEN); 
		
		
		
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
			//werknemer.get().opslag(form.getBedrag());
			//werknemerService.update(werknemer.get());
			werknemer.get().setSalaris(werknemer.get().getSalaris().add(form.getBedrag()));
			werknemerService.update(werknemer.get());
			redirectAttributes.addAttribute("id", werknemer.get().getId());
			return new ModelAndView(REDIRECT_NA_OPSLAG);
		}
		redirectAttributes.addAttribute("fout", "Werknemer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_WERKNEMER_NIET_GEVONDEN);
	}	

	@GetMapping("{werknemer}/rijksregisternummer")
	ModelAndView rijksregisternummer(@PathVariable Optional<Werknemer> werknemer) {
		RijksregisternummerForm form = new RijksregisternummerForm();
		form.setGeboorte(werknemer.get().getGeboorte());
		form.setRijksregisternr(werknemer.get().getRijksregisternr());
		return new ModelAndView(RIJKSREGISTERNUMMER_VIEW)
					.addObject(werknemer.get())
					.addObject(form);
	}		

	@PostMapping("{werknemer}/rijksregisternummer")
	ModelAndView rijksregisternummer(@PathVariable Optional<Werknemer> werknemer, @Valid RijksregisternummerForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (werknemer.isPresent()) {
			if (bindingResult.hasErrors()) {
				return new ModelAndView(RIJKSREGISTERNUMMER_VIEW).addObject(werknemer.get());
			}
			werknemer.get().setRijksregisternr(form.getRijksregisternr());
			werknemerService.update(werknemer.get());
			redirectAttributes.addAttribute("id", werknemer.get().getId());
			return new ModelAndView(REDIRECT_NA_RIJKSREGISTERNUMMER);
		}
		redirectAttributes.addAttribute("fout", "Werknemer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_WERKNEMER_NIET_GEVONDEN);
	}	
	
	
}
