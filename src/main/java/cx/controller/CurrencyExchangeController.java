package cx.controller;

import cx.domain.Currency;
import cx.domain.ExchangeRateQuery;
import cx.domain.service.ExchangeRateService;
import cx.domain.validator.CurrencyExchangeQueryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CurrencyExchangeController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private CurrencyExchangeQueryValidator validator;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexRedirectsToView() {
        return "redirect:/view";
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public String viewExchangeRates(Model model) {
        feedModel(model, new ExchangeRateQuery());

        return "cx/view";
    }

    @RequestMapping(value = "view", method = RequestMethod.POST)
    public String queryExchangeRates(@Valid ExchangeRateQuery query,
                                    BindingResult bindingResult,
                                    Model model,
                                    RedirectAttributes redirectAttributes) {
        validator.validate(query, bindingResult);
        if (!bindingResult.hasErrors()) {
            exchangeRateService.queryExchangeRates(query);
        }

        feedModel(model, query);
        return "cx/view";
    }

    private void feedModel(Model model, ExchangeRateQuery query) {
        model.addAttribute("currenciesList", Currency.values());
        model.addAttribute("latestQueries", exchangeRateService.latestQueries());
        model.addAttribute("exchangeRateQuery", query);
    }

}

