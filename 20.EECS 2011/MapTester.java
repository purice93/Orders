import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;


/**
 * @author 
 *
 */
public class MapTester {
	public static void main(String[] args) {
		for(int N=10;N<=100000;N*=10){
			/*
			 * Define two arrays, storing Strings and Numbers, respectively
			 */
            Integer a [] = new Integer[2*N];
            String b [] = new String[2*N];
            
            /*
             * Define  two (for Strings and for Integers) HashMap and two TreeMap objects.
             */
            TreeMap<Integer,Integer> intTreeMap = new TreeMap<>();
            TreeMap<String,String> strTreeMap = new TreeMap<>();	
            HashMap<Integer,Integer> intHashMap = new HashMap<>();
            HashMap<String,String> strHashMap = new HashMap<>();
            for (int i = 0; i < a.length; i++) {
                a[i] = i;
                b[i] = getRandomStr();
            }

            /*
             * Upset order
             */
            Collections.shuffle(Arrays.asList(a));
            Collections.shuffle(Arrays.asList(b));
            
            System.out.println("N = "+N+" (times are per operation):");
            System.out.println("                           "+"Strings"+"    "+"Numbers");
            
            long startTime = System.nanoTime();
            for(int i = 0; i < a.length/2; i++) {
            	strTreeMap.put(b[i],b[i]);
            }
            long endTime = System.nanoTime();
            long strTime = (endTime-startTime)/N;
            
            startTime = System.nanoTime();
            for(int i = 0; i < a.length/2; i++) {
            	intTreeMap.put(a[i],a[i]);
            }
            endTime = System.nanoTime();
            long intTime = (endTime-startTime)/N;
            
            System.out.println("TreeMap, put               "+strTime+"ns"+"  "+intTime+"ns");
            
            System.out.println("(when key/value present)");
            startTime = System.nanoTime();
            for(int i = 0; i < N/10; i++) {
            	strTreeMap.containsKey(b[i]);
            }
            endTime = System.nanoTime();
            strTime = (endTime-startTime)/N/10;
            
            startTime = System.nanoTime();
            for(int i = 0; i < N/10; i++) {
            	intTreeMap.containsKey(a[i]);
            }
            endTime = System.nanoTime();
            intTime = (endTime-startTime)/N/10;
            
            System.out.println("TreeMap, containsKey       "+strTime+"ns"+"  "+intTime+"ns");
            
            startTime = System.nanoTime();
            for(int i = 0; i < N/10; i++) {
            	strTreeMap.containsValue(b[i]);
            }
            endTime = System.nanoTime();
            strTime = (endTime-startTime)/N/10;
            
            startTime = System.nanoTime();
            for(int i = 0; i < N/10; i++) {
            	intTreeMap.containsValue(a[i]);
            }
            endTime = System.nanoTime();
            intTime = (endTime-startTime)/N/10;
            System.out.println("TreeMap, containsValue     "+strTime+"ns"+"  "+intTime+"ns");
            
            

            System.out.println("(when key/value absent)");
            startTime = System.nanoTime();
            for(int i = N; i < N+N/10; i++) {
            	strTreeMap.containsKey(b[i]);
            }
            endTime = System.nanoTime();
            strTime = (endTime-startTime)/N/10;
            
            startTime = System.nanoTime();
            for(int i = N; i < N+N/10; i++) {
            	intTreeMap.containsKey(a[i]);
            }
            endTime = System.nanoTime();
            intTime = (endTime-startTime)/N/10;
            System.out.println("TreeMap, containsKey       "+strTime+"ns"+"  "+intTime+"ns");
            
            startTime = System.nanoTime();
            for(int i = N; i < N+N/10; i++) {
            	strTreeMap.containsValue(b[i]);
            }
            endTime = System.nanoTime();
            strTime = (endTime-startTime)/N/10;
            
            startTime = System.nanoTime();
            for(int i = N; i < N+N/10; i++) {
            	intTreeMap.containsValue(a[i]);
            }
            endTime = System.nanoTime();
            intTime = (endTime-startTime)/N/10;
            System.out.println("TreeMap, containsValue     "+strTime+"ns"+"  "+intTime+"ns");
            
            System.out.println();
            startTime = System.nanoTime();
            for(int i = 0; i < a.length/2; i++) {
            	strHashMap.put(b[i],b[i]);
            }
            endTime = System.nanoTime();
            strTime = (endTime-startTime)/N;
            
            startTime = System.nanoTime();
            for(int i = 0; i < a.length/2; i++) {
            	intHashMap.put(a[i],a[i]);
            }
            endTime = System.nanoTime();
            intTime = (endTime-startTime)/N;
            System.out.println("HashMap, put               "+strTime+"ns"+"  "+intTime+"ns");
            
            System.out.println("(when key/value present)");
            startTime = System.nanoTime();
            for(int i = 0; i < N/10; i++) {
            	strHashMap.containsKey(b[i]);
            }
            endTime = System.nanoTime();
            strTime = (endTime-startTime)/N/10;
            
            startTime = System.nanoTime();
            for(int i = 0; i < N/10; i++) {
            	intHashMap.containsKey(a[i]);
            }
            endTime = System.nanoTime();
            intTime = (endTime-startTime)/N/10;
            System.out.println("HashMap, containsKey       "+strTime+"ns"+"  "+intTime+"ns");
            
            startTime = System.nanoTime();
            for(int i = 0; i < N/10; i++) {
            	strHashMap.containsValue(b[i]);
            }
            endTime = System.nanoTime();
            strTime = (endTime-startTime)/N/10;
            
            startTime = System.nanoTime();
            for(int i = 0; i < N/10; i++) {
            	intHashMap.containsValue(a[i]);
            }
            endTime = System.nanoTime();
            intTime = (endTime-startTime)/N/10;
            System.out.println("HashMap, containsValue     "+strTime+"ns"+"  "+intTime+"ns");
            
            System.out.println("(when key/value absent)");
            startTime = System.nanoTime();
            for(int i = N; i < N+N/10; i++) {
            	strHashMap.containsKey(b[i]);
            }
            endTime = System.nanoTime();
            strTime = (endTime-startTime)/N/10;
            
            startTime = System.nanoTime();
            for(int i = N; i < N+N/10; i++) {
            	intHashMap.containsKey(a[i]);
            }
            endTime = System.nanoTime();
            intTime = (endTime-startTime)/N/10;
            System.out.println("HashMap, containsKey       "+strTime+"ns"+"  "+intTime+"ns");
            
            startTime = System.nanoTime();
            for(int i = N; i < N+N/10; i++) {
            	strHashMap.containsValue(b[i]);
            }
            endTime = System.nanoTime();
            strTime = (endTime-startTime)/N/10;
            
            startTime = System.nanoTime();
            for(int i = N; i < N+N/10; i++) {
            	intHashMap.containsValue(a[i]);
            }
            endTime = System.nanoTime();
            intTime = (endTime-startTime)/N/10;
            System.out.println("HashMap, containsValue     "+strTime+"ns"+"  "+intTime+"ns");

            System.out.println();
            System.out.println();
        }
	}

	/**
	 * Randomly generates a string
	 * @return Random string
	 */
	private static String getRandomStr() {
        String str = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        int stringLength = (int) (Math.random()*16);
        for (int j = 0; j < stringLength; j++) {
            int index = random.nextInt(str.length());
            char c = str.charAt(index);
            stringBuffer.append(c);
        }
        return stringBuffer.toString();
	}
}
