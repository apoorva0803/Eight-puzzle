package puzzle;

/**
 * Maintains all the property of the respective move
 * @author apoorva
 *
 */
public class Board {

	
	Board ancestor;
	int[][] currentNode;
	int hCost;
	int gCost;
	int xPos;
	int yPos;
	String direction;
	boolean isExplored = false;

	public int[][] getCurrentNode() {
		return currentNode;
	}
	public void setCurrentNode(int[][] currentNode) {
		this.currentNode = currentNode;
	}
	public int gethCost() {
		return hCost;
	}
	public void sethCost(int hCost) {
		this.hCost = hCost;
	}
	public int getgCost() {
		return gCost;
	}
	public void setgCost(int gCost) {
		this.gCost = gCost;
	}
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public boolean isExplored() {
		return isExplored;
	}
	public void setExplored(boolean isExplored) {
		this.isExplored = isExplored;
	}
	public Board getAncestor() {
		return ancestor;
	}
	public void setAncestor(Board ancestor) {
		this.ancestor = ancestor;
	}





}
