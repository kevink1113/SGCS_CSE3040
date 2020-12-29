package cse3040mp1;

class PalindromeChecker {
	public static void check(Object a) {
		int isPalindrome = 1;
		String str = a + "";
		int len = str.length();
		int idx1 = 0; int idx2 = len -1;
		
		while(idx1 <= idx2) {
			if(str.charAt(idx1) != str.charAt(idx2)) {
				isPalindrome = 0;
				break;
			}
			idx1++; idx2--;
		}
		
		if(isPalindrome == 1) System.out.println(str + " is a palindrome.");
		else System.out.println(str + " is not a palindrome.");
	}
}

public class Problem11 {
	public static void main(String[] args) {
		PalindromeChecker.check("abcde"); 
		PalindromeChecker.check("abcba"); 
		PalindromeChecker.check(1234); 
		PalindromeChecker.check(12321);
	}
}
