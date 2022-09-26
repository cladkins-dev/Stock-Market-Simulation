package stocktradingsimulator.stocks.earnings;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stocktradingsimulator.stocks.stock.model.entity.Stock;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "earnings_reports")
@Getter
@Setter
@NoArgsConstructor
public class EarningsReport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    @JsonBackReference
    private Stock stock;

    @Column(name = "estimated_eps")
    private Double estimatedEPS;

    @Column(name = "actual_eps")
    private Double actualEPS;

    @Column(name = "report_message")
    private String reportMessage;

    @Column(name = "date_of_release")
    private String dateOfRelease;

    public EarningsReport(Stock stock,
                          double estimatedEPS,
                          double actualEPS,
                          String reportMessage,
                          String dateOfRelease){
        this.stock = stock;
        this.estimatedEPS = estimatedEPS;
        this.actualEPS = actualEPS;
        this.reportMessage = reportMessage;
        this.dateOfRelease = dateOfRelease;
    }
}