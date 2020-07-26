package UsersAndProducts;

public enum LogfileInfo{;

	public enum CustomerDecision{
		PURCHASED{
			@Override
			public String toString() {
				return "purchased";
			}
			
		},

		SAVED{
			@Override
			public String toString() {
				return "saved";
			}
			
		},

		CANCELLED{
			@Override
			public String toString() {
				return "Cancelled";
			}
			
		}
	

	}
	
	public enum PaymentType{
		PAYPAL{
			@Override
			public String toString() {
				return "PayPal";
			}
			
		}, 
	
		CREDITCARD{
			@Override
			public String toString() {
				return "Credit Card";
			}
			
		},
		
		NOPAYMENT{
			@Override
			public String toString() {
				return " ";
			}
			
		},
		
	}
	
	
	
}
