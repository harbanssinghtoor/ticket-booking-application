package ticket.booking.entities;

import lombok.*;

import java.util.Date;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private String ticketId;

    private String userId;

    private String source;

    private String destination;

    private Date dateOfTravel;

    private Train train;

    public String getTicketInfo(){
        return String.format("Ticker ID: %s belongs to User %s from %s to %s on %s", ticketId, userId , source ,destination);
    }
}
