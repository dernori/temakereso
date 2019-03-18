package temakereso.helper;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class ReportFilters {

    private static ReportFilters EMPTY = new ReportFilters();

    @DateTimeFormat(pattern = "yy.MM.dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yy.MM.dd")
    private Date endDate;

    public boolean isEmpty() {
        return this.equals(EMPTY);
    }

}
