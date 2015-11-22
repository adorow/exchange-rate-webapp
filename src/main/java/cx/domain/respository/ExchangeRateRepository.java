package cx.domain.respository;


import cx.domain.entity.ExchangeRateHistoryEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ExchangeRateRepository extends Repository<ExchangeRateHistoryEntity, Long> {

    ExchangeRateHistoryEntity save(ExchangeRateHistoryEntity user);

    List<ExchangeRateHistoryEntity> findTop10ByOrderByIdDesc();
}
