package UsersAndProducts;

public enum productInfo {;
	
	public enum DeviceName{
		MOUSE{
			@Override
			public String toString() {
				return "mouse";
			}  	
		},
	KEYBOARD{
			@Override
			public String toString() {
				return "keyboard";
			}
		},
	
	NONE{
			public String toString() {
				return "None";
			}
		}
	

}
	
	public enum DeviceType{
		GAMING{
			@Override
			public String toString() {
				return "gaming";
			}  	
		},
		STANDARD{
			@Override
			public String toString() {
				return "standard";
			}  	
		},
		INTERNET{
			@Override
			public String toString() {
				return "internet";
			}  	
		},
		FLEXIBLE{
			@Override
			public String toString() {
				return "flexible";
			}  	
		}
		
		
		
		
	}
	
	public enum Connectivity{
		WIRED{
			@Override
			public String toString() {
				return "wired";
			}  	
		},
		WIRELESS{
			@Override
			public String toString() {
				return "wireless";
			}  	
		}
	}
	
	
	public enum Layout{
		UK{
			@Override
			public String toString() {
				return "UK";
			}  	
		},
		US{
			@Override
			public String toString() {
				return "US";
			}  	
		}
	}
	
}
