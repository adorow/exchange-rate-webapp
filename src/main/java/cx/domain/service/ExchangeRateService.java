package cx.domain.service;

import cx.domain.*;
import cx.domain.entity.ExchangeRateHistoryEntity;
import cx.domain.service.external.openexchangerates.OpenExchangeRatesService;
import cx.domain.respository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private OpenExchangeRatesService openExchangeRatesService;

    public ExchangeRateHistoryEntity create(ExchangeRateQuery query, ExchangeRateResult result) {
        ExchangeRateHistoryEntity exchangeRate = new ExchangeRateHistoryEntity();

        exchangeRate.setFromCurrency(query.getFromCurrency());
        exchangeRate.setToCurrency(query.getToCurrency());
        exchangeRate.setExchangeRateDate(query.isLatest() ? new Date() : query.getExchangeRateDate());
        exchangeRate.setValue(result.getValue());

        return exchangeRateRepository.save(exchangeRate);
    }

    public List<ExchangeRateData> latestQueries() {
        List<ExchangeRateHistoryEntity> latestRates = exchangeRateRepository.findTop10ByOrderByIdDesc();

        return latestRates.stream().map((ExchangeRateHistoryEntity entity) -> {
            ExchangeRateData erd = new ExchangeRateData();
            erd.setFromCurrency(Currency.valueOf(entity.getFromCurrency()));
            erd.setToCurrency(Currency.valueOf(entity.getToCurrency()));
            erd.setExchangeRateDate(entity.getExchangeRateDate());
            erd.setValue(String.format("%f", entity.getValue()));
            return erd;
        }).collect(Collectors.<ExchangeRateData>toList());
    }

    public ExchangeRateHistoryEntity queryExchangeRates(ExchangeRateQuery query) {
        ExchangeRateResult result = openExchangeRatesService.query(query);
        return create(query, result);
    }
}
