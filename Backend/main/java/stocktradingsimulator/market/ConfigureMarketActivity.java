package stocktradingsimulator.market;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
@SuppressWarnings("unused")
public class ConfigureMarketActivity {

    @Autowired
    private final HandleMarketActivity handleMarketActivity;
    private static int marketHour = 0;

    @Scheduled(fixedDelay = 10000L)
    public void marketActivity(){
        marketHour++;
        if(marketHour >= 24){
            handleMarketActivity.updateNewStockPrices(true);
            marketHour = 0;
            return;
        }
        handleMarketActivity.updateNewStockPrices(false);
    }
}