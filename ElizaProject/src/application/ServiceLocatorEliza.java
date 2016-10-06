package application;

public class ServiceLocatorEliza{
	private static String chatterName;
	
	public  ServiceLocatorEliza() {
		
	}
	
	
	public static String getChatterName(){
		return chatterName;
	}
	
	public static void setChatterName(String name){
		chatterName=name;
	}
}
