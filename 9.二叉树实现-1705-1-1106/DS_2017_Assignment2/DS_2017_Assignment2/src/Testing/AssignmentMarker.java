package Testing;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AssignmentMarker {
	public static HashMap<String, Float> marks = new HashMap<String, Float>();

	public static ArrayList<String> passed = new ArrayList<String>();
	public static ArrayList<String> failed = new ArrayList<String>();

	private static java.util.ArrayList<Failure> failures = new java.util.ArrayList<Failure>();

	private static void testrunner(String name, Class c) {

		Result test = JUnitCore.runClasses(c);
		failures.addAll(test.getFailures());

		for ( int i = 0 ; i < test.getFailures().size() ; ++i ) {
			String testID = test.getFailures().get(i).getDescription().getClassName() + ":" +
					test.getFailures().get(i).getDescription().getMethodName();
			testID = testID.replaceAll("Test", "").replaceAll("test", "");
		}
	}

	// Simple test information
	private static void runATest(String name, Class c) {
		System.out.println("\n" + name);
		for ( int i = 0 ; i < name.length() ; ++i )
			System.out.print("-");
		System.out.println();

		testrunner(name, c);
	}

	public static void main(String[] args) {

		failures = new java.util.ArrayList<Failure>();
		passed = new ArrayList<String>();
		failed = new ArrayList<String>();
		marks = new HashMap<String, Float>();

		float gP, gL, tP, tL, uP, uL, glL, glP, bL, bP, bpP, bpL;

		System.out.println("Data Structures Assignment #2:\n\tMy PlayStation Friends\n");

		System.out.println("-----------------------------");

		runATest("Game", GameTest.class);

		System.out.println("Summary: ");
		{
			float gained = 0.0f;
			for ( int i = 0 ; i < passed.size(); ++i ) {
				if ( passed.get(i).contains("Game:") )
					gained += marks.get(passed.get(i));
			}

			float lost = 0.0f;
			for ( int i = 0 ; i < failed.size(); ++i ) {
				if ( failed.get(i).contains("Game:") )
					lost += marks.get(failed.get(i));
			}
			gP = gained;
			gL = lost;
			System.out.println(gained + " marks gained, " + lost + " marks lost.");
		}

		runATest("Trophy", TrophyTest.class);

		System.out.println("Summary: ");
		{
			float gained = 0.0f;
			for ( int i = 0 ; i < passed.size(); ++i ) {
				if ( passed.get(i).contains("Trophy:") )
					gained += marks.get(passed.get(i));
			}

			float lost = 0.0f;
			for ( int i = 0 ; i < failed.size(); ++i ) {
				if ( failed.get(i).contains("Trophy:") )
					lost += marks.get(failed.get(i));
			}
			tP = gained;
			tL = lost;
			System.out.println(gained + " marks gained, " + lost + " marks lost.");
		}

		runATest("User", UserTest.class);

		System.out.println("Summary: ");
		{
			float gained = 0.0f;
			for ( int i = 0 ; i < passed.size(); ++i ) {
				if ( passed.get(i).contains("User:") )
					gained += marks.get(passed.get(i));
			}

			float lost = 0.0f;
			for ( int i = 0 ; i < failed.size(); ++i ) {
				if ( failed.get(i).contains("User:") )
					lost += marks.get(failed.get(i));
			}
			uP = gained;
			uL = lost;
			System.out.println(gained + " marks gained, " + lost + " marks lost.");
		}

		runATest("GameList", GameListTest.class);

		System.out.println("Summary: ");
		{
			float gained = 0.0f;
			for ( int i = 0 ; i < passed.size(); ++i ) {
				if ( passed.get(i).contains("GameList:") )
					gained += marks.get(passed.get(i));
			}

			float lost = 0.0f;
			for ( int i = 0 ; i < failed.size(); ++i ) {
				if ( failed.get(i).contains("GameList:") )
					lost += marks.get(failed.get(i));
			}
			glP = gained;
			glL = lost;
			System.out.println(gained + " marks gained, " + lost + " marks lost.");
		}

		runATest("BinaryTree", BinaryTreeTest.class);

		System.out.println("Summary: ");
		{
			float gained = 0.0f;
			for ( int i = 0 ; i < passed.size(); ++i ) {
				if ( passed.get(i).contains("BinaryTree:") )
					gained += marks.get(passed.get(i));
			}

			float lost = 0.0f;
			for ( int i = 0 ; i < failed.size(); ++i ) {
				if ( failed.get(i).contains("BinaryTree:") )
					lost += marks.get(failed.get(i));
			}
			bP = gained;
			bL = lost;
			System.out.println(gained + " marks gained, " + lost + " marks lost.");
		}

		runATest("AVL", AVLTest.class);

		System.out.println("Summary: ");
		{
			float gained = 0.0f;
			for ( int i = 0 ; i < passed.size(); ++i ) {
				if ( passed.get(i).contains("AVL:") )
					gained += marks.get(passed.get(i));
			}

			float lost = 0.0f;
			for ( int i = 0 ; i < failed.size(); ++i ) {
				if ( failed.get(i).contains("AVL:") )
					lost += marks.get(failed.get(i));
			}
			bpP = gained;
			bpL = lost;
			System.out.println(gained + " marks gained, " + lost + " marks lost.");
		}

		System.out.println("-----------------------------");
		System.out.println("\nFailed test details");
		System.out.println("( Test class: test name -> Error details )\n");
		for (Failure failure : failures) {
			String name = failure.getDescription().getClassName().replaceAll("Testing.", "").replaceAll("Test",  "")
					+ ": " +  failure.getDescription().getMethodName();
			System.out.print(name + " -> ");
			if ( failure.getMessage() != null )
				System.out.print(failure.getMessage());
			else
				System.out.print("No failure message present " +
						"(indicates systemic issues somewhere in the codebase)." +
						"\nTrace: " + failure.getTrace());
			System.out.print("\n");
		}
		System.out.println("-----------------------------");

		System.out.println("Mark summary:");
		System.out.println("\tGame:  [gained " + gP + ", lost " + gL + "]");
		System.out.println("\tTrophy: [gained " + tP + ", lost " + tL + "]");
		System.out.println("\tUser: [gained " + uP + ", lost " + uL + "]");
		System.out.println("\tGameList: [gained " + glP + ", lost " + glL + "]");
		System.out.println("\tBinaryTree: [gained " + bP + ", lost " + bL + "]");
		System.out.println("\tBTree: [gained " + bpP + ", lost " + bpL + "]\n");
		System.out.println("Total: [" + (gP + tP + uP + glP + bP + bpP) + ", lost " + (gL + tL + uL + glL + bL + bpL) +"] (out of: " + (gP + tP + uP + glP + bP + bpP + gL + tL + uL + glL + bL + bpL) + ")");
	}
}
