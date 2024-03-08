package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Train {
    private String trainId;

    private String trainNo;

    private List<List<Integer>> seats;

    private Map<String , String> stationTimes;

    private List<String> stations;

    public String getTrainInfo(){
        return String.format("Train ID: %s , Train No: %s", trainId , trainNo);
    }
}
