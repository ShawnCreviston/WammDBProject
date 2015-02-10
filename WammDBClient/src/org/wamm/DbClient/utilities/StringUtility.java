package org.wamm.DbClient.utilities;

/**
 * @author Shawn Creviston
 * WAMM
 */
public class StringUtility {

	/**
	 * Reformats the capitalization of the text, first character capital in each word.
	 * @param text
	 * @return
	 */
	public static String formatStringCaps(String text) {
		String ft = "";
		text = text.trim().toLowerCase();
		String words[] = text.split(" ");
		for (int w = 0; w < words.length; w++) {
			if (words[w].length() == 1) {
				ft += (w > 0?" ":"")+words[w].toUpperCase();
			} else if (words[w].length() > 1) {
				ft += (w > 0?" ":"")+words[w].substring(0,1).toUpperCase()+words[w].substring(1);
			}
		}
		return ft;
	}
	
	/**
	 * Compares to strings and determines between 0 and 1 how similar they are based on
	 * character sequences matched. To ignore case, either upper or lower strings sent in.
	 * @param str1 ( String ) One of two strings to compare for similarity
	 * @param str2 ( String ) The second of two strings to compare for similarity
	 * @return
	 */
	public static double getSimilarity(String str1, String str2) {
		// Filter out nulls or empty strings
		if (str1 == null && str2 == null) {
			// Both null, perfect match
			return 1.0;
		} else if (str1 == null || str2 == null) {
			// One null, one not, can't match
			return 0.0;
		}

		double similarity = 0.0;
		
		// First try a simple check
		if (str1.equals(str2)) {
			return 1.0;
		}
		
		if (str2.length() < str1.length()) {
			// Swap, make the smaller length string
			// str1, simpler for loop below
			String temp = str1;
			str1 = str2;
			str2 = temp;
			temp = null;
		}
		
		if (str1.length() == 0) {
			// Can not match an empty string
			return 0.0;
		}
		
		// Step through each character and compare to other string
		int matchIndex2 = -1;
		double matchedStr1 = 0;
		double matchedStr2 = 0;
		for (int s1si = 0; s1si < str1.length(); s1si++) {
			for (int s1ei = str1.length(); s1ei > s1si; s1ei--) {
				String c1 = str1.substring(s1si, s1ei);
				int matchTestIndex = str2.indexOf(c1, matchIndex2);
				if (matchTestIndex > -1) {
					// String 2 contains the current sequence
					matchIndex2 = matchTestIndex;
					matchedStr1 += s1ei-s1si;
					matchedStr2 += s1ei-s1si;
					s1si = s1ei;
				}
			}
		}
		similarity = (matchedStr1/str1.length()+matchedStr2/str2.length())/2.0;
		
		return similarity;
	}
}
