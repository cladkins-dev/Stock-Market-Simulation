package stocktradingsimulator.market.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stocktradingsimulator.market.entity.Market;
import stocktradingsimulator.market.enums.MarketTrajectory;
import stocktradingsimulator.market.repository.MarketRepository;

@Service
@AllArgsConstructor
public class MarketService {

    @Autowired
    private final MarketRepository marketRepository;

    public Market findMarketEntity(){
        Market market = marketRepository.findById(1).orElse(null);
        if(market == null || checkMarketForNullValues(market)){
            market = new Market("1/1/1" ,100.0, MarketTrajectory.NORMAL);
            saveMarketEntity(market);
            return market;
        }
        return market;
    }

    public void saveMarketEntity(Market market){
        if(market == null) return;
        marketRepository.save(market);
    }

    private static boolean checkMarketForNullValues(Market market){
        if(market.getDate() == null) return true;
        if(market.getMarketTrajectory() == null) return true;
        if(market.getLastMonthAveragePrice() == null) return true;
        if(market.getId() == null) return true;
        return false;
    }
}
