import java.util.StringTokenizer;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 *  A helper class containing functions that you should use in SimilarSounds.java
 *  Note that in your implementations you could use StringTokenizer and String.split()
 *
 */
 
class Extractor
{
	// **************//
	// DO NO CHANGE
	
	/**
	 *  Reads a text file one line at a time and returns the lines in a List.
	 *  This function is provided to you, no need to change it
	 *  @param fileName file name
	 *  @return return
	 */
	public static List<String> readFile(String fileName) {
		
		File file = new File(fileName);
        ArrayList<String> list = new ArrayList<String>();
			
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				list.add(st);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		
		return list;
	}
	// DO NO CHANGE
	// **************//
	
	
	
	/**
	 * Given a line of text, this method must return the first token
	 * In the context of this project, it will return the Word. For Example: 
	 * Input:	"moderated M AA1 D ER0 EY2 T IH0 D"
	 * Return: 	"moderated"
	 * Input:	""
	 * Return: 	""
	 * @param line line
	 * @return string
	 */
	public static String extractWordFromLine(String line) {
		StringTokenizer tokenizer = new StringTokenizer(line);

		if (tokenizer.countTokens() == 0) {
			return "";
		}
		return tokenizer.nextToken(); //should return first "token" in given string
	}
	
	
	/**
	 * Given a line of text, this method must return all tokens except the first one. 
	 * In the context of this project, it will return the Sound (sequence of unisounds). For Example: 
	 * Input:	"moderated M AA1 D ER0 EY2 T IH0 D"
	 * Return: 	"M AA1 D ER0 EY2 T IH0 D"
	 * Input:	""
	 * Return: 	""
	 * @param line line
	 * @return string
	 */
	public static String extractSoundFromLine(String line) {
		String returnString = "";
		StringTokenizer tokenizer = new StringTokenizer(line);

		if (tokenizer.countTokens() == 0) { //check for empty string
			return returnString;
		}

		tokenizer.nextToken(); //skip first token
		while (tokenizer.hasMoreTokens()) {
			returnString = returnString + tokenizer.nextToken(); //append returnString with token
			if (tokenizer.hasMoreTokens()) {
				returnString = returnString + " "; //add a space if we haven't just added the last token
			}
		}
		return returnString;
	}
	
	
	/**
	 * Given a string representing the sound of a word (sequence of unisounds), 
	 * this method must return the sound-group, i.e., "the trailing sequence of unisounds starting 
	 * from the last occurring most emphasized unisound". For Example: 
	 * Input:	""M AA1 D ER0 EY2 T IH0 D""
	 * Return: 	"EY2 T IH0 D"
	 * Input:	"S EY1 N T M AA1 R T IH0 N"
	 * Return: 	"AA1 R T IH0 N"
	 * Input:	""
	 * Return: 	""
	 * @param sound sound
	 * @return string
	 */
	public static String extractSoundGroupFromSound(String sound) {
		int uniNumber = -1; //tracks largest number for most emphasized unisound
		String uni = ""; //tracks most emphasized unisound
		StringTokenizer tokenizer = new StringTokenizer(sound);
		String returnString = "";

		while (tokenizer.hasMoreTokens()) { //iterate through each token
			String currentToken = tokenizer.nextToken();
			char[] tokenArray = currentToken.toCharArray(); //turn token into a character array
			if (Character.isDigit(tokenArray[tokenArray.length-1])) { //check if the last character in currentToken is a number
				int lastDigit = Character.getNumericValue(tokenArray[tokenArray.length-1]); //if it's a number, it's assigned to lastDigit
				if (lastDigit >= uniNumber) { //if lastDigit is greater than or equal to uniNumber...
					uniNumber = lastDigit; //lastDigit becomes the new uniNumber
					uni = currentToken; //currentToken becomes the current most emphasized sound
				}
			}
		}

		tokenizer = new StringTokenizer(sound); //remake tokenizer because we have to iterate again
		boolean foundUni = false;

		while (tokenizer.hasMoreTokens()) {
			String currentToken = tokenizer.nextToken();

			if (foundUni) {
				returnString = returnString + currentToken;
				if (tokenizer.hasMoreTokens()) {
					returnString = returnString + " ";
				}
			}
			else {
				if (currentToken == uni) {
					returnString = returnString + currentToken;
					if (tokenizer.hasMoreTokens()) {
						returnString = returnString + " ";
					}
					foundUni = true;
				}
			}
		}

		return returnString;
		/* PSEUDOCODE:
		greatest token number = -1 (initial number)
		most emphasized unisound = string token
		tokenizer, etc.

		iterate through the sound string {
			check each token for a number at the end of it; so, basically, need to iterate through each token {
				if there's a number at the end of the token {
					if number is greater than current greatest token number {
						this token becomes the new main sound
					}
				}
			}
		}

		search for most emphasized unisound again {
			if the sound is found {
				change boolean to true
			}
			if soundfoundboolean true {
				add sound to returnString
			}
		}
		 */
	}


	/**
	 *  Main Method For Your Testing -- Edit all you want.
	 *  
	 *  @param args not used
	 */
	public static void main(String args[]) {
		int location;
		String line, word, sound, soundGroup;
		List lines = readFile("word_to_sound.txt");

		location = 61905;
		line = lines.get(location).toString();
		if (line.equals("ENTHUSIASTICALLY  IH0 N TH UW2 Z IY0 AE1 S T IH0 K L IY0")) {
			System.out.println("Yay1");
		}
		word = extractWordFromLine(line);
		if (word.equals("ENTHUSIASTICALLY")) {
			System.out.println("Yay2");
		}
		sound = extractSoundFromLine(line);
		if (sound.equals("IH0 N TH UW2 Z IY0 AE1 S T IH0 K L IY0")) {
			System.out.println("Yay3");
		}
		soundGroup = extractSoundGroupFromSound(sound);
		if (soundGroup.equals("UW2 Z IY0 AE1 S T IH0 K L IY0")) {
			System.out.println("Yay4");
		}

		location = 63;
		line = lines.get(location).toString();
		if (line.equals("ST_MARTIN  S EY1 N T M AA1 R T IH0 N")) {
			System.out.println("Yay5");
		}
		word = extractWordFromLine(line);
		if (word.equals("ST_MARTIN")) {
			System.out.println("Yay6");
		}
		sound = extractSoundFromLine(line);
		if (sound.equals("S EY1 N T M AA1 R T IH0 N")) {
			System.out.println("Yay7");
		}
		soundGroup = extractSoundGroupFromSound(sound);
		if (soundGroup.equals("AA1 R T IH0 N")) {
			System.out.println("Yay8");
		}
		
		line = "";
		word = extractWordFromLine(line);
		if (word.equals("")) {
			System.out.println("Yay9");
		}
		sound = extractSoundFromLine(line);
		if (sound.equals("")) {
			System.out.println("Yay10");
		}
		soundGroup = extractSoundGroupFromSound(sound);
		if (soundGroup.equals("")) {
			System.out.println("Yay11");
		}
		
		soundGroup = extractSoundGroupFromSound("S EY N T M AA R T IH N");
		if (soundGroup.equals("S EY N T M AA R T IH N")) {
			System.out.println("Yay12");
		}
	}
}
