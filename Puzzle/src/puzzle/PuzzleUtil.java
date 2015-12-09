package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuzzleUtil {

	static int xPos;
	static int yPos;

	static int min_n=0;
	static int max_n=2;

	public static int getMin_n() {
		return min_n;
	}

	public static int getMax_n() {
		return max_n;
	}


	/**
	 * Calculate manhattan distance
	 * @param inputArray
	 * @param outputArray
	 * @return
	 */
	public static int calculateManhattan(int[][] inputArray, int[][] outputArray){

		int manhattanSum =0;
		for(int i=0;i<inputArray.length;i++){
			for(int j=0; j<inputArray[0].length ;j++){

				int value = inputArray[i][j];
				if(value!=0){
					findPosInGoalState(outputArray,value);
					manhattanSum += Math.abs(i-xPos) + Math.abs(j-yPos);
				}	
			}			
		}
		return manhattanSum;
	}


	/**
	 * Find the position of element in Goal Array
	 * @param outputArray
	 * @param value
	 */
	private static void findPosInGoalState(int[][] outputArray, int value){

		xPos =0;
		yPos =0;

		for ( int i = 0; i < outputArray.length; i++ ) {
			for ( int j = 0; j < outputArray[0].length; j++ ) {
				if ( outputArray[i][j] == value ) {
					xPos = i;
					yPos = j;
				} 
			}
		}
	}

	/**
	 * Swap elements
	 * @param inputArray
	 * @param x1 - position of empty tile
	 * @param x2 - position of move for swapping
	 * @param y1 - position of empty tile
	 * @param y2 - position of move for swapping
	 * @return 
	 */
	public static int[][] swap (int [][]inputArray, int x1 , int x2, int y1, int y2){


		int value = inputArray [x2][y2];

		int [][] copyArray = new int[inputArray.length][];
		for(int i = 0; i < inputArray.length; i++)
			copyArray[i] = inputArray[i].clone();


		copyArray[x1][y1] = value;
		copyArray[x2][y2] = 0;
		return copyArray;
	}


	/**
	 * Check repeated elements
	 * @param exploredNode
	 * @param node
	 * @return
	 */
	public static boolean checkDuplicates(List<int[][]> exploredNode , int[][] node){

		for(int[][] obj:exploredNode){
			boolean check1 = Arrays.deepEquals(obj, node);
			if(check1) return true;
		}
		return false;
	}

	/**
	 * Get X direction for swapping the value
	 * @param dir
	 * @param x1
	 * @return
	 */
	public static int getX2(Direction dir, int x1){

		int x2;

		if(dir == Direction.UP)
			//if(x2>0)	
			x2 = x1 -1;
		else
			x2= x1+1;

		return x2;
	}

	/**
	 * Get Y direction for swapping the value
	 * @param dir
	 * @param y1
	 * @return
	 */
	public static int getY2(Direction dir, int y1){

		int y2;

		if(dir == Direction.RIGHT)
			y2 = y1 + 1;
		else
			y2= y1 - 1;
		return y2;
	}


	public static int calculateCost(int hValue , int gValue){
		return hValue+gValue;
	}

	/**
	 * Get all possible moves to generate child node.
	 * @param min
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return
	 */
	static List<Moves> compareFValue(int min, int a,int b,int c, int d) {
		// TODO Auto-generated method stub

		List<Moves> list = new ArrayList<Moves>();
		if(min==a) {
			Moves m = new Moves();
			m.setDirection(Direction.LEFT);
			m.setfValue(a);
			list.add(m);
		}
		if(min==b) {
			Moves m = new Moves();
			m.setDirection(Direction.RIGHT);
			m.setfValue(b);
			list.add(m);
		}
		if(min==c) {
			Moves m = new Moves();
			m.setDirection(Direction.UP);
			m.setfValue(c);
			list.add(m);
		}
		if(min==d) {
			Moves m = new Moves();
			m.setDirection(Direction.BOTTOM);
			m.setfValue(d);
			list.add(m);
		}
		return list;
	}


	/**
	 * Compare heuristic value and get the minimum one 
	 * @param hValue1
	 * @param hValue2
	 * @return
	 */
	static int compareHeuristic(int hValue1, int hValue2) {
		// TODO Auto-generated method stub

		int min = 0;

		if(hValue1 >-1&& hValue2 >-1)			
			min = hValue1 < hValue2 ? hValue1 : hValue2;

//		if(hValue1 >0 && hValue2 >0)			
//			min = hValue1 < hValue2 ? hValue1 : hValue2;
		else if(hValue1 >= 0) min=hValue1;
		else min=hValue2;

		return min;
	}


	/**
	 * Retrieve input element from user
	 * @param text
	 * @return
	 */
	public static int[][] getInputArray(String text){

		String[] token=text.split(",");
		int[] elements = new int[token.length];

		for(int i=0;i<elements.length;i++){
			elements[i] = Integer.parseInt(token[i]);
		}

		int[][] array = new int[3][3];
		int k=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				array[i][j] = elements[k];
				k++;
			}
		}
		return array;
	}


	public static String[][] genearteResultedStateSpaceInString(int[][] array){

		String[][] outputArray = new String[3][3];

		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				outputArray[i][j] = String.valueOf(array[i][j]);

			}
		}
		return outputArray;
	}


	/**
	 * Get goal direction
	 * @param text
	 * @return
	 */
	public static String getDirection(String text){

		String[] token=text.trim().split(":");
		StringBuilder sb = new StringBuilder();

		for(int i=token.length-1;i>-1;i--){
			System.out.println(token[i]);
			sb.append(token[i].trim());
			if(i!=0)sb.append( "->");
		}
		return sb.toString();

	}


}
