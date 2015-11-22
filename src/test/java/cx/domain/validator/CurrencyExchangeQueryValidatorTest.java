package cx.domain.validator;

import cx.domain.Country;
import cx.domain.Currency;
import cx.domain.ExchangeRateQuery;
import cx.domain.RegistrationData;
import org.junit.Test;
import org.springframework.validation.MapBindingResult;

import java.util.Date;
import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class CurrencyExchangeQueryValidatorTest {

    private CurrencyExchangeQueryValidator validator = new CurrencyExchangeQueryValidator();

    @Test
    public void ok() {
        ExchangeRateQuery query = getGoodQuery();
        MapBindingResult errors = new MapBindingResult(new HashMap(), "query");
        validator.validate(query, errors);
        assertThat(errors.hasErrors(), is(false));
    }

    @Test
    public void tooOldDate() {
        ExchangeRateQuery query = getGoodQuery();
        query.setExchangeRateDate(new Date(/*1998=*/98, /*december=*/11, 31));
        MapBindingResult errors = new MapBindingResult(new HashMap(), "query");
        validator.validate(query, errors);
        assertThat(errors.hasErrors(), is(true));
        assertThat(errors.getFieldError("exchangeRateDate"), is(notNullValue()));
    }
    @Test
    public void futureDate() {
        ExchangeRateQuery query = getGoodQuery();
        query.setExchangeRateDate(new Date(/*3012=*/1112, /*december=*/11, 2));
        MapBindingResult errors = new MapBindingResult(new HashMap(), "query");
        validator.validate(query, errors);
        assertThat(errors.hasErrors(), is(true));
        assertThat(errors.getFieldError("exchangeRateDate"), is(notNullValue()));
    }

    private ExchangeRateQuery getGoodQuery() {
        ExchangeRateQuery query = new ExchangeRateQuery();
        query.setFromCurrency(Currency.AUD.getCode());
        query.setToCurrency(Currency.BRL.getCode());
        query.setLatest(false);
        query.setExchangeRateDate(new Date(/*2000=*/100, /*march=*/2, 1));
        return query;
    }

}
