package stocktradingsimulator.stock;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }

    public List<Stock> getAllStocksByMarketCap(String marketCap){
        return stockRepository.findAll().stream()
                .filter(stock -> stock.getMarketCap()
                        .equals(marketCap)).collect(Collectors.toList());
    }

    public List<Stock> getAllStocksBySector(String sector){
        return stockRepository.findAll().stream()
                .filter(stock -> stock.getSector()
                        .equals(sector)).collect(Collectors.toList());
    }

    public Stock getStockByTickerSymbol(String ticker) throws StockNotFoundException {
        return stockRepository.findById(ticker)
                .orElseThrow(() -> new StockNotFoundException(
                        "No stock with ticker symbol " + ticker + " exists"));
    }

    public double getStockPriceWithTickerSymbol(String ticker) throws StockNotFoundException {
        if(!DoesStockExist.doesStockExistWithTicker(this, ticker)){
            throw new StockNotFoundException("No stock with ticker symbol " + ticker + " exists");
        }
        return getStockByTickerSymbol(ticker).getPrice();
    }

    //Ignore any stocks that do not currently exist
    public void updateStockInDatabase(Stock stock) {
        if(!DoesStockExist.doesStockExistWithTicker(this, stock.getTicker())){
            return;
        }
        stockRepository.save(stock);
    }

    public void saveDefaultStockToDatabase(List<Stock> defaultStocks){
        defaultStocks.forEach(defaultStock -> stockRepository.save(defaultStock));
    }
}
