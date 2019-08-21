package lt.kono.currencyRates;

public class Rate {

	  	private String date;

	    private String currency;

	    private Double rate;

		public Rate(String date, String currency, Double rate) {
			this.date = date;
			this.currency = currency;
			this.rate = rate;
		}

		public String getDate() {
			return date;
		}

		public String getCurrency() {
			return currency;
		}

		public Double getRate() {
			return rate;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public void setRate(Double rate) {
			this.rate = rate;
		}

		@Override
		public String toString() {
			return "Rate [date=" + date + ", currency=" + currency + ", rate=" + rate + "]";
		}
	    
}
