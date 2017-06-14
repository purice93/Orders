package Testing;



import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;


public class DSUnitTesting {

	/*
	 * Display the methods that fail.
	 */
	@Rule
	public TestRule classWatchman = new TestWatcher() {

		@Override
		protected void failed(Throwable e, Description desc) {
			String className = desc.getClassName().replaceAll("Testing.", "").replaceAll("Test", "");
			String methodName = desc.getMethodName().replaceAll("test","").replaceAll("Test","");

			String testID = className + ":" + methodName;
			AssignmentMarker.failed.add(testID);
			System.err.println("FAIL: \t" + testID + ". Marks lost: " + AssignmentMarker.marks.get(testID));

		}

		@Override
		protected void succeeded(Description desc) {
			String className = desc.getClassName().replaceAll("Testing.", "").replaceAll("Test", "");
			String methodName = desc.getMethodName().replaceAll("test","").replaceAll("Test","");

			String testID = className + ":" + methodName;

			AssignmentMarker.passed.add(testID);
			System.out.println(testID);
			float mark = AssignmentMarker.marks.get(testID);
			System.out.print(String.format("   %-4s %-8s %-33s %-4s", "PASS",
					className, methodName, mark));
			System.out.print("\n");


		}
	};


}
