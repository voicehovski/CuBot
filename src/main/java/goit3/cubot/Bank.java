package goit3.cubot;

import java.io.IOException;
import java.util.List;

public abstract class Bank {

    /**
     *
     * @return Empty list if nothing to return
     *
     * @throws RuntimeException if problem occurs while request processing
     */
    public abstract List<CurrencyInfo> getCurrencyList();

    /**
     * @throws RuntimeException if problem occurs while request processing or no currency with such code
     */
    public abstract CurrencyInfo getCurrencyByCode(Currency currencyCode);
}
