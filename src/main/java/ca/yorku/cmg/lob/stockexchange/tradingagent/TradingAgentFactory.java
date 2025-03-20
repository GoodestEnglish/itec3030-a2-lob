package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.stockexchange.tradingagent.strategy.ITradingStrategy;
import ca.yorku.cmg.lob.stockexchange.tradingagent.strategy.impl.AggressiveTradingStrategy;
import ca.yorku.cmg.lob.stockexchange.tradingagent.strategy.impl.ConservativeTradingStrategy;
import ca.yorku.cmg.lob.trader.Trader;

public class TradingAgentFactory extends AbstractTradingAgentFactory{
    @Override
    public TradingAgent createAgent(String type, String style, Trader t, StockExchange e, NewsBoard n) {
        TradingAgent agent = type.equals("Institutional") ? new TradingAgentInstitutional(t, e, n) : type.equals("Retail") ? new TradingAgentRetail(t, e, n) : null;
        ITradingStrategy strategy = style.equals("Aggressive") ? new AggressiveTradingStrategy(agent) : style.equals("Conservative") ? new ConservativeTradingStrategy(agent) : null;

        if (agent == null) {
            throw new NullPointerException("Agent is null");
        }

        agent.setStrategy(strategy);

        return agent;
    }
}
