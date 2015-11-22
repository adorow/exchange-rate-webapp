package cx.domain.service.external.openexchangerates;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import cx.domain.ExchangeRateQuery;
import cx.domain.ExchangeRateResult;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class OpenExchangeRatesService {

    private static final String APP_ID = "430f44474f92484783dc22cd09498901";

    public ExchangeRateResult query(ExchangeRateQuery query) {
        try {
            final HttpResponse<JsonNode> response = Unirest.get(query.isLatest() ? latestRatesUrl() : historicalRatesUrl(query.getExchangeRateDate()))
                    .header("accept", "application/json")
                    .queryString("app_id", APP_ID)
                    .asJson();

            final JSONObject rates = response.getBody().getObject().getJSONObject("rates");
            final double fromRate = rates.getDouble(query.getFromCurrency());
            final double toRate = rates.getDouble(query.getToCurrency());

            final ExchangeRateResult result = new ExchangeRateResult();
            result.setValue(fromRate / toRate);// == a * 1/b
            return result;
        } catch (UnirestException ex) {
            // todo: treat exception properly
            System.out.println(ex);
            return null;
        }
    }

    private static String latestRatesUrl() {
        return "https://openexchangerates.org/api/latest.json";
    }

    private static String historicalRatesUrl(Date date) {
        ZonedDateTime dateTime = date.toInstant().atZone(ZoneId.systemDefault());

        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();
        return String.format("https://openexchangerates.org/api/historical/%02d-%02d-%02d.json", year, month, day);
    }

}
