package puzzle;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * Processes the puzzle.
 * Consider the h function ad g function. Choose the smallest one
 * @author apoorva
 *
 */
public class ProcessPuzzle {

	static Puzzle puzzleInstance=Puzzle.getInstance();
	static int[][] inputArray= puzzleInstance.getInputArray();
	static int[][] outputArray= puzzleInstance.getOutputArray();

	static List<int[][]> exploredNode = new ArrayList<int[][]>();

	/**
	 * Process the puzzle and produces the output 
	 * as per the heuristic value and move accordingly
	 */
	public static void getOutput(){

		//Queue<Board> queue = new LinkedList<Board>();
		Queue<Board> queue = new LinkedList<Board>();

		// Take the initial state and add in queue 
		Board initialState = new Board();
		initialState.setCurrentNode(inputArray);
		initialState.setAncestor(null);
		initialState.setgCost(0);
		initialState.sethCost(-1);
		queue.add(initialState);

		for ( int i = 0; i < inputArray.length; i++ ){
			for ( int j = 0; j < inputArray[0].length; j++ ) {
				if ( inputArray[i][j] == 0 ) {
					initialState.setxPos(i);
					initialState.setyPos(j);
				} 
			}
		}

		int hCost=-1;
		JFrame outputFrame = new JFrame();
		outputFrame.setSize(new Dimension(500, 300));
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		outputFrame.setVisible(true);
		panel.setEnabled(true);

		//Iterate till we get the solution
		while(hCost!=0){
			Board parent = queue.peek();
			List<Moves> mvList=findPossibleDirection(parent,parent.getgCost()+1);

			int size=mvList.size();

			//No of elements of parent to be parsed
			for(int i=0;i<size;i++){

				Moves mv =mvList.get(i);

				if(parent.getgCost()==999){
					System.out.println();
					JLabel lb = new JLabel("Cannot run further");
					panel.add(lb);
					break;

				}

				Board b = new Board();
				parent.setExplored(true);
				b.setAncestor(parent);
				b.setgCost(parent.getgCost()+1);
				System.out.println("G"+b.getgCost());

				int x2;
				int y2;
				String dir = " ";

				if(mv.getDirection()==Direction.RIGHT || mv.getDirection()==Direction.LEFT ){
					dir = mv.getDirection().toString();
					x2= parent.getxPos();
					y2= PuzzleUtil.getY2(mv.getDirection(),parent.getyPos());
					if(y2>2||y2<0) continue;
				}
				else{
					dir = mv.getDirection().toString();
					x2 = PuzzleUtil.getX2(mv.getDirection(), parent.getxPos());
					y2 = parent.getyPos();
					if(x2>2||x2<0) continue;
				}
				int[][] current =PuzzleUtil.swap(queue.peek().currentNode, parent.getxPos(), x2, parent.getyPos(), y2);

				b.setDirection(dir);
				b.setCurrentNode(current);
				b.setxPos(x2);
				b.setyPos(y2);
				hCost=PuzzleUtil.calculateManhattan(current, outputArray);
				b.sethCost(hCost);

				exploredNode.add(parent.getCurrentNode());
				queue.add(b);
				System.out.println("Move " + mv.getDirection().toString() );


				for ( int k = 0; k < current.length; k++ ) {
					for ( int j = 0; j < current[0].length; j++ ) {
						System.out.print(current[k][j] + "  ");
					}
					System.out.println("");
				}

				System.out.println("hCost" + hCost);


				if(hCost==0){

					Board b1=b;
					String str="";

					List<String[][]> resultList = new ArrayList<String[][]>();
					while(b1.getAncestor()!=null){
						str = str.trim() + b1.getDirection().toString() +":";
						resultList.add(PuzzleUtil.genearteResultedStateSpaceInString(b1.getCurrentNode()));
						b1=(b1.getAncestor());
					}
					resultList.add(PuzzleUtil.genearteResultedStateSpaceInString(inputArray));

					String outputDirection=	PuzzleUtil.getDirection(str);
					JLabel lblGeneratedNodes = new JLabel("New Generated Nodes "+puzzleInstance.getNoOfNodesGenerated()+1);
					JLabel lblExpandedNodes = new JLabel(" \n Expanded Nodes "+exploredNode.size());
					JLabel lblGCost = new JLabel("No of steps "+b.getgCost());

					panel.add(lblGeneratedNodes);
					panel.add(lblExpandedNodes);
					panel.add(lblGCost);

					Collections.reverse(resultList);

					for(String[][] obj : resultList){

						JPanel subPanel = new JPanel();
						subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));

						for(int row=0;row<obj.length;row++){
							StringBuilder strOutput = new StringBuilder();
							JLabel lb = new JLabel();
							for(int column=0;column<obj.length;column++){
								strOutput.append(obj[row][column] +" ");								
							}
							strOutput.append("\n");
							lb.setText(strOutput.toString());
							subPanel.add(lb);
							panel.add(subPanel);
						}
						JLabel lb1 = new JLabel(" ---- ");
						panel.add(lb1);
					}


					JTextArea textArea = new JTextArea();
					textArea.setColumns(20);
					textArea.setText(outputDirection);
					panel.add(textArea);

					JScrollPane scroll_pane = new JScrollPane(panel);
					//scroll_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); //SETTING SCHEME FOR HORIZONTAL BAR
					scroll_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
					scroll_pane.getVerticalScrollBar().setValue(0);
					scroll_pane.getViewport().setViewPosition(new Point(0,0));
					outputFrame.add(scroll_pane);
					break;
				}
			}
			queue.poll();
			int nodeExpanded = puzzleInstance.getNoOfNodesExpanded()+1;
			puzzleInstance.setNoOfNodesExpanded(nodeExpanded);
			if(queue.size()>1){

				List<Integer> cost = new ArrayList<Integer>();
				for (Board e : queue) {
					cost.add(e.getgCost()+e.gethCost());
				}


				int minCost =Collections.min(cost);
				List<Board> nodeToRemove = new ArrayList<Board>();
				for (Board e : queue) {
					if((e.getgCost()+e.gethCost())>minCost)
						if(e.isExplored)
							nodeToRemove.add(e);
				}

				for(Board bObj:nodeToRemove){
					queue.remove(bObj);
				}

				//				int maxCost =Collections.max(cost);
				//				int noOfElementsToRemove =0;;
				//				for (Board e : queue) {
				//					if(e.gCost<maxCost)
				//						if(e.isExplored)noOfElementsToRemove++;
				//				}
				//				for(int a=0;a<noOfElementsToRemove;a++){
				//
				//					queue.poll();
				//				}
			}
			System.out.println(queue.size());
		}	

	}

	/**
	 * Find possible direction to move 
	 * @param pState
	 * @param b
	 * @param gValue
	 * @return
	 */
	private static List<Moves> findPossibleDirection(Board b, int gValue) {
		// TODO Auto-generated method stub


		int xPos = b.getxPos();
		int yPos = b.getyPos();

		int newYPos =0;
		int newXPos =0;

		int hValueRight = -1;
		int hValueLeft = -1;
		int hValueBottom = -1;
		int hValueUp = -1;

		int[][] current = b.getCurrentNode();
		int nodes=puzzleInstance.getNoOfNodesGenerated();

		//find valid h values as per direction
		newYPos = yPos + 1;
		if(newYPos <= PuzzleUtil.getMax_n()){
			int[][] moveRight=PuzzleUtil.swap(current, b.getxPos(), xPos, b.getyPos(), newYPos);
			boolean value=PuzzleUtil.checkDuplicates(exploredNode, moveRight);
			if(!value){
				hValueRight= PuzzleUtil.calculateManhattan(moveRight, outputArray);
				nodes++;
			}
		}


		newYPos = yPos - 1;
		if(newYPos >= PuzzleUtil.getMin_n()){
			int[][] moveLeft=PuzzleUtil.swap(current, b.getxPos(), xPos, b.getyPos(), newYPos);
			boolean value=PuzzleUtil.checkDuplicates(exploredNode, moveLeft);
			if(!value){
				hValueLeft= PuzzleUtil.calculateManhattan(moveLeft, outputArray) ;
				nodes++;
			}
		}

		newXPos = xPos + 1;				
		if(newXPos <= PuzzleUtil.getMax_n()){
			int[][] moveBottom=PuzzleUtil.swap(current, b.getxPos(), newXPos, b.getyPos(), yPos);
			boolean value=PuzzleUtil.checkDuplicates(exploredNode, moveBottom);
			if(!value){
				hValueBottom= PuzzleUtil.calculateManhattan(moveBottom, outputArray);
				nodes++;
			}
		}

		newXPos = xPos - 1;			
		if(newXPos >= PuzzleUtil.getMin_n()){
			int[][] moveUp=PuzzleUtil.swap(current, b.getxPos(), newXPos, b.getyPos(), yPos);
			boolean value=PuzzleUtil.checkDuplicates(exploredNode, moveUp);
			if(!value){
				hValueUp= PuzzleUtil.calculateManhattan(moveUp, outputArray);
				nodes++;
			}
		}

		int min1 = 0 ;
		int min2 = 0 ;

		//find min h value and consider that only fir further expansion
		min1 = PuzzleUtil.compareHeuristic(hValueLeft,hValueRight);
		min2 = PuzzleUtil.compareHeuristic(hValueUp,hValueBottom);
		int min = PuzzleUtil.compareHeuristic(min1,min2);
		puzzleInstance.setNoOfNodesGenerated(nodes);

		List<Moves> mvList=PuzzleUtil.compareFValue(min,hValueLeft,hValueRight,hValueUp,hValueBottom);
		return mvList;
	}
}
