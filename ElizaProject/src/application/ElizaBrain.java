package application;

public class ElizaBrain{
	private String[][] words;
	
	public ElizaBrain() {
		 words = new String[][]{
				// greetings  group0
				{ "hello", "hi", "yo", "what's up", "hi there", "hey" },
				{ "Hi", "Hello", "hey" },

				// asking about well being     group1
				{ "how are you", "how r you", "how r u", "how you doing", "how u doing", "are you ok" },
				{ "Good", "Great", "I'm doing ok thanks for asking" },
				
				// default response   group2
				{ "I'm not sure if I got that right", "I did not understand that", "can you rephrase that ?", "LoL","Eliza is offline" } };

	
	}
	
	
	public String[][] getWords(){
		return words;
	}
}
