package puzzle;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PuzzleUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected static JTextField inputField;
	protected static JTextField outputField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PuzzleUI frame = new PuzzleUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PuzzleUI() {
		setBounds(100, 100, 600, 120);
		this.contentPane = new JPanel();
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new FlowLayout());
		setTitle("8 Puzzle");

		JLabel lblInput = new JLabel("Input array elements ");
		lblInput.setHorizontalAlignment(4);
		lblInput.setBounds(10, 11, 143, 23);
		this.contentPane.add(lblInput);

		inputField = new JTextField();
		inputField.setBounds(156, 14, 318, 20);
		this.contentPane.add(inputField);
		inputField.setColumns(10);

		JLabel lblGoalState = new JLabel("Goal State: ");
		lblGoalState.setHorizontalAlignment(4);
		lblGoalState.setBounds(10, 42, 143, 23);
		this.contentPane.add(lblGoalState);

		outputField = new JTextField();
		outputField.setColumns(10);
		outputField.setBounds(156, 45, 318, 20);
		this.contentPane.add(outputField);

		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Puzzle pz=Puzzle.getInstance();
				String inputElement =inputField.getText();
				pz.setInputArray(PuzzleUtil.getInputArray(inputElement));
				String outputElement =outputField.getText();
				pz.setOutputArray(PuzzleUtil.getInputArray(outputElement));
				ProcessPuzzle.getOutput();
			}
		});

		btnRun.setBounds(385, 77, 89, 23);
		this.contentPane.add(btnRun);


		JLabel lblNote = new JLabel("Enter comma seperated elements & blank tile as 0");
		this.contentPane.add(lblNote);
	}
}

