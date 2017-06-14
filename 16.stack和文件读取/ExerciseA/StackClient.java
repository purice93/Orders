public class StackClient {
	public static void main(String[] args) {
		Stack<String> stack1 = new Stack<>();

		try {
			stack1.push("a");
			System.out.println(stack1.peek());
			System.out.println(stack1.pop());
			
			stack1.push("b");
			System.out.println(stack1.pop());
			
			System.out.println(stack1.isFull());
			System.out.println(stack1.isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println("------------");
		Stack<String> stack2 = new Stack<>(2);
		try {
			stack2.push("a");
			stack2.push("b");
			System.out.println(stack2.isFull());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
