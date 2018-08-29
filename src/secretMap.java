
// 26' 27''
// Character Comparison, OR operation

public class secretMap {
	
	public static String bitAdd(int n, int a, int b) {
		String bitA = nomalize(intTobit(a, ""), n);
		String bitB = nomalize(intTobit(b, ""), n);
		String bitC = "";
		
//		System.out.println(bitA);
//		System.out.println(bitB);
		
		for(int i=n-1; i>=0; i--) {
			if(bitA.charAt(i) == '1' || bitB.charAt(i) == '1') {
				bitC = "#" + bitC;
			} else {
				bitC = " " + bitC; 
			}
		}
				
		return bitC;
	}
	
	public static String nomalize(String bit, int n) {
		String tbit = bit;
		for(int s = bit.length(); s<n; s++) {
			tbit = "0" + tbit;
		}
		
		return tbit;
	}
	
	public static String intTobit(int a, String result) {
		if(a<2)
			return result + a;
		int b = a/2;
		return intTobit(b, result) + a%2;
	}
	
	public static void main(String[] args) {
//		int n = 5;
//		int[] arr1 = {9,20,28,18,11};
//		int[] arr2 = {30,1,21,17,28};

		int n = 6;
		int[] arr1 = {46,33,33,22,31,50};
		int[] arr2 = {27,56,19,14,14,10};
		
		for(int i=0; i<n; i++) {
			System.out.println(bitAdd(n, arr1[i], arr2[i]));
		}
	}
}
