package puzzle;

/**
 * POJO of needed output
 * 
 * @author apoorva
 *
 */
public class Puzzle {

	private static Puzzle instance = null;	

	public static Puzzle getInstance() {
		if(instance == null) {
			instance = new Puzzle();
		}
		return instance;
	}

	int[][] inputArray = new int[3][3];
	int[][] outputArray = new int[3][3];
	int noOfNodesGenerated =0;
	int noOfNodesExpanded=0;

	public int[][] getInputArray() {
		return inputArray;
	}
	public void setInputArray(int[][] inputArray) {
		this.inputArray = inputArray;
	}
	public int[][] getOutputArray() {
		return outputArray;
	}
	public void setOutputArray(int[][] outputArray) {
		this.outputArray = outputArray;
	}
	public int getNoOfNodesGenerated() {
		return noOfNodesGenerated;
	}
	public void setNoOfNodesGenerated(int noOfNodesGenerated) {
		this.noOfNodesGenerated = noOfNodesGenerated;
	}
	public int getNoOfNodesExpanded() {
		return noOfNodesExpanded;
	}
	public void setNoOfNodesExpanded(int noOfNodesExpanded) {
		this.noOfNodesExpanded = noOfNodesExpanded;
	}
}
