import java.util.Scanner;

public class nim {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); 
		boolean over = gameStart();
		
		// To judge whether the game is over
		while(over==true) {
			System.out.println("Want to play again? (Y/N): ");
			if(input.next().equals("Y")) {
				over = gameStart();
			} else {
				break;
			}
		}
	}

	private static boolean gameStart() {
		Scanner keyboard = new Scanner(System.in); 
		System.out.println("Welcome to the Nim Game!\n");
		System.out.println("Please enter the number of stones to be used in this \n" + "game:");
		
		int Numstone = keyboard.nextInt(); // read int from keyboard
		System.out.println("Enter your name: ");
		String player = keyboard.next();
		System.out.println("Which player will start the game? Select a number from \n the following:\n\n"
				+"1. Computer"+"\n"
				+"2. "+player+"\n"
				+"Choice:");
		int flag = -1;
		flag = keyboard.nextInt();
		if(flag!=1||flag!=2) {
			System.out.println("Invalid choice. Please select from (1, or 2) Try again...");
		}
		int Numstar = 0;
		// System.out.print(Numstone + " stone left:");
		System.out.println();// change to the next line
		
		//Take the stone
		while (Numstone > 0) {
			// player
			if(flag==1) {
				int random = 0;
				if(Numstone<=3) {
					random = Numstone;
					Numstone = 0;
				} else {
					random = (int)(Math.random()*3+1);
					Numstone-=random;
				}
				System.out.println("Computer takes "+random+" stones, there are "+Numstone+" stones remaining."+"\n");
				flag=-1;
				if(Numstone==0) {
					System.out.println("Computer had to take last stone and You win the game.");
					System.out.println("Thanks for playing.");
					return true;
				}
				flag = 2;
			}
			// Computer
			else if(flag==2) {
				System.out.println("Please enter how many (1, 2, or 3) stones you want to\n"
						+"take from the pile of "+Numstone+" stones: ");
				int random = 0;
				while((random= keyboard.nextInt())>Numstone||random<1||random>3) {
					System.out.println("Invalid choice. Try again...");
				}
				Numstone-=random;
				System.out.println("Sam takes "+random+" stones, there are "+Numstone+" stones remaining.\n");
				
				if(Numstone==0) {
					System.out.println("Sam had to take last stone and lost in the game.");
					System.out.println("Thanks for playing.");
					return true;
				}
				flag = 1;
			}
		}
		return true;
	}

}
