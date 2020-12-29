package cse3040mp1;

import java.util.Vector;

class SubsequenceChecker {
	public static void check(String a, String b) {
		int i=0,j=0;
		Vector<Integer> arr = new Vector<>();
		
        while (i<a.length() && j<b.length()){
            if(a.charAt(i) == b.charAt(j)) {
            	j++;
            	// System.out.print(i + " ");
            	arr.add(i);
            }
            i++;
            if (!(j<b.length())){
                System.out.println(b + " is a subsequence of " + a);
                for(Integer t: arr)
                	System.out.print(t + " ");
                System.out.println();
                arr.clear();
                return;
            }
        }
        
        System.out.println(b + " is not a subsequence of " + a);
        arr.clear();
        return;
	}
}

public class Problem12 {
	public static void main(String[] args) {
		SubsequenceChecker.check("supercalifragilisticexpialidocious", "pads");
		SubsequenceChecker.check("supercalifragilisticexpialidocious", "padsx");
	}
}
