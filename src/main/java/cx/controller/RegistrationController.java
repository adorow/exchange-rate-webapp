package cx.controller;

import cx.domain.Country;
import cx.domain.RegistrationData;
import cx.domain.service.RegistrationService;
import cx.domain.validator.RegistrationDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private RegistrationDataValidator validator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(Model model) {
        feedModel(model, new RegistrationData());

        return "cx/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationSubmit(
            @Valid RegistrationData registration,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        validator.validate(registration, bindingResult);

        if (bindingResult.hasErrors()) {
            feedModel(model, registration);
            return "cx/registration";
        }

        registrationService.register(registration);
        redirectAttributes.addFlashAttribute("registered", "success");
        return "redirect:/login";
    }

    private void feedModel(Model model, RegistrationData registrationData) {
        model.addAttribute("registrationData", registrationData);
        model.addAttribute("countriesList", Country.values());
    }

}
