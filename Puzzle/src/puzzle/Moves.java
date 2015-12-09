package puzzle;

/**
 * Maintains the possible moves
 * @author apoorva
 *
 */
public class Moves {

	Direction direction;
	int newPosX ;
	int newPosY ;
	int fValue;
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction right) {
		this.direction = right;
	}
	public int getfValue() {
		return fValue;
	}
	public void setfValue(int fValue) {
		this.fValue = fValue;
	}
	public int getNewPosX() {
		return newPosX;
	}
	public void setNewPosX(int newPosX) {
		this.newPosX = newPosX;
	}
	public int getNewPosY() {
		return newPosY;
	}
	public void setNewPosY(int newPosY) {
		this.newPosY = newPosY;
	}
}
