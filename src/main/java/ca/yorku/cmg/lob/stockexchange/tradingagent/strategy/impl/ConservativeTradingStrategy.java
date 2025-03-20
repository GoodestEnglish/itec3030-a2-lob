package ca.yorku.cmg.lob.stockexchange.tradingagent.strategy.impl;

import ca.yorku.cmg.lob.orderbook.Ask;
import ca.yorku.cmg.lob.orderbook.Bid;
import ca.yorku.cmg.lob.stockexchange.events.BadNews;
import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.stockexchange.events.GoodNews;
import ca.yorku.cmg.lob.stockexchange.tradingagent.TradingAgent;
import ca.yorku.cmg.lob.stockexchange.tradingagent.strategy.ITradingStrategy;
import ca.yorku.cmg.lob.tradestandards.IOrder;

public class ConservativeTradingStrategy implements ITradingStrategy {

    public final TradingAgent tradingAgent;

    public ConservativeTradingStrategy(TradingAgent tradingAgent) {
        this.tradingAgent = tradingAgent;
    }

    @Override
    public void actOnEvent(Event e, int pos, int price) {
        IOrder newOrder = null;

        if (e instanceof GoodNews) {
            newOrder = new Bid(tradingAgent.getTrader(),e.getSecrity(),(int) Math.round(price*1.05), (int) Math.round(pos*0.2),e.getTime());
        } else if (e instanceof BadNews) {
            newOrder = new Ask(tradingAgent.getTrader(),e.getSecrity(),(int) Math.round(price*0.95), (int) Math.round(pos*0.2),e.getTime());
        } else {
            System.out.println("Unknown event type");
        }

        if (newOrder!=null) {
            tradingAgent.getExchange().submitOrder(newOrder,e.getTime());
        }
    }
}
