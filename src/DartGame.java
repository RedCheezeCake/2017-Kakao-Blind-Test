import java.util.Stack;


// 41`08``
// Stack, Reverse Approach, String to Integer, Temporary Storage

public class DartGame {

	public static boolean isOption(char c) {
		if (c == '*' || c == '#') 
			return true;
		else return false; 
	}
	
	public static boolean isNum(char b) {
		if (b =='S' || b =='D' || b =='T' || isOption(b))
			return false;
		else
			return true;
	}	
	
	// a is number, b is stage
	public static int calcScore(int a, char b) {
		if(b=='S')
			return a;
		else if(b=='D')
			return a*a;
		else
			return a*a*a;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String input = "1D2S3T*";
		
		Stack<Object> stack = new Stack<>();
		
		/* The number a can be two or more digits.
		 * 'temp' is temporary storage to store 1 digit number
		 */
		String temp = "";
		for (int i=0; i<input.length(); i++) {
			char a = input.charAt(i);
			System.out.println(a);
			
			if(isNum(a))
				temp += a;
			else {
				if (!temp.equals(""))
					stack.push(Integer.parseInt(temp));
				stack.push(a);
				temp = "";
			}				
		}
		
		int optionValue = 1;
		int result = 0;
		
		while(stack.size()>0) {
			int midNum = 0;
			char c = (char) stack.pop();	// 'c' is Stage or Option Character
//			System.out.println(c);
			
			optionValue = optionValue / 2;	// 'optionValue' effect to before number(next stack's number) step
			if(optionValue < 1)
				optionValue = 1;
			
			if(isOption(c)) {
//				System.out.println("** c is option");
//				System.out.println("optionValue : " + optionValue);

				char b = (char) stack.pop();
				int a = (int) stack.pop();
				
				midNum = calcScore(a,b);

//				System.out.println("midNum : " + midNum);

				if(c=='*') {
					result += midNum * 2 * optionValue;
					optionValue = optionValue*4;	// 'optionValue' is must divided in the first step of 'while' cycle 
				}
				if(c=='#') {
					result += midNum * (-1) * optionValue;
				}	
			}else {
//				System.out.println("** c is NOT option");
//				System.out.println("optionValue : " + optionValue);

				int a = (int) stack.pop();
				
				midNum = calcScore(a, c);
				
//				System.out.println("midNum : " + midNum);

				result += midNum * optionValue;
			}
			
//			System.out.println("== result : " + result);

		}
		
		System.out.println(result);
	}

}
