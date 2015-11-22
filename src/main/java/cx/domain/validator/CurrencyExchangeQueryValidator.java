package cx.domain.validator;

import cx.domain.ExchangeRateQuery;
import cx.util.DateUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

@Component
public class CurrencyExchangeQueryValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ExchangeRateQuery.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ExchangeRateQuery query = (ExchangeRateQuery) target;
        checkDateIfNecessary(query, errors);
    }

    private void checkDateIfNecessary(ExchangeRateQuery query, Errors errors) {
        if (query.isLatest()) {
            return;
        }
        if (query.getExchangeRateDate() == null) {
            errors.rejectValue("exchangeRateDate", "exchangeRateDate.notnull", "The date can only be ommited if 'latest' is selected.");
            return;
        }

        // valid from 1.1.1999
        LocalDate exchangeRateDate = DateUtil.oldJavaToLocalDate(query.getExchangeRateDate());
        LocalDate initialDate = firstOfJanuary1999();
        if (exchangeRateDate.isBefore(initialDate) || exchangeRateDate.isAfter(yesterday())) {
            errors.rejectValue("exchangeRateDate", "exchangeRateDate.invalid", "Must be from 01.01.1999 to yesterday. Unless 'latest' checkbox is selected.");
        }
    }

    private LocalDate firstOfJanuary1999() {
        return LocalDate.of(1999, Month.JANUARY, 1);
    }

    private static LocalDate yesterday() {
        return LocalDate.now().minus(1, ChronoUnit.DAYS);
    }

}
