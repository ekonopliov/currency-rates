package lt.kono.currencyrates;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/* Rate class. Getters & setters, toString, 
equals and contructor are managed by Lombok */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Rate {

	   	@NonNull private String date;

	   	@NonNull private String currency;

	    private Double rate;
}
