package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;

public class TradingAgentRetail extends TradingAgent {

    public TradingAgentRetail(Trader t, StockExchange e, NewsBoard n) {
        super(t, e, n);
    }

    @Override
    public void actOnEvent(Event e, int pos, int price) {
        strategy.actOnEvent(e, pos, price);
    }
}
