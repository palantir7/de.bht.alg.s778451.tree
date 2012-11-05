import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import paint.PaintPanel;
import algo.BreadthFirstSearch;
import algo.DepthFirstSearch;

/**
 * @author Marcel Buchmann (s778451)
 * @email marcel.buchmann(- at -)googlemail.com
 * @version 1.0.0
 * @date 30.10.2012
 * @project de.bht.alg.s778451.tree
 * 
 */
class Start extends JFrame implements ActionListener {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * global buffer of paint-elements lines 999, rows 10 (type, x, y, to x, to
	 * y, color, text)
	 */
	@SuppressWarnings("rawtypes")
	private static List buffer;
	@SuppressWarnings("unused")
	private static String[] element = new String[10];
	private static JButton button1;
	private static JButton button2;
	private static JButton button3;
	private static JButton button4;
	private static JButton button5;
	@SuppressWarnings("unused")
	private static JPanel mainPanel;
	private static JPanel paintPanel;
	private static JPanel iPanel;
	private JFileChooser fc;
	private String file = null;

	/**
	 * 
	 */
	public Start() {
		initComponents();
	}

	/**
	 * main-Methode
	 * 
	 * @param args
	 *            not in use!!!
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new Start();
				frame.setLayout(new BorderLayout());
				frame.setTitle("ALGO - TreeViewer");
				frame.setSize(718, 390);

				mainPanel = new JPanel();
			}
		});
	}

	public void centerToScreen() {
		// Move the window
		this.setLocationRelativeTo(getRootPane());
	}

	/**
	 * initComponents()
	 */
	private void initComponents() {

		// center to screen
		centerToScreen();

		// paint panel
		paintPanel = new PaintPanel();
		paintPanel.setBackground(new java.awt.Color(255, 255, 255));
		paintPanel.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.RAISED));

		// interaction panel
		iPanel = new JPanel();
		iPanel.setSize(50, 350);
		iPanel.setLayout(new GridLayout(0, 1));
		iPanel.setBackground(new java.awt.Color(255, 255, 255));

		// buttons of iPanel
		button1 = new JButton("Load a File");
		button1.addActionListener(this);
		iPanel.add(button1);
		button2 = new JButton("Depth First Search");
		button2.addActionListener(this);
		iPanel.add(button2);
		button3 = new JButton("Breadth First Search");
		button3.addActionListener(this);
		iPanel.add(button3);
		button4 = new JButton("Dijkstra - Algo");
		button4.addActionListener(this);
		iPanel.add(button4);
		button5 = new JButton("Kruskal - Algo");
		button5.addActionListener(this);
		iPanel.add(button5);

		// add panels to mainframe
		this.getContentPane().add(paintPanel, BorderLayout.CENTER);
		this.getContentPane().add(iPanel, BorderLayout.WEST);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		pack();
	}

	/**
	 * Paint-Buffer Getter
	 * 
	 * @return buffer
	 */
	@SuppressWarnings("rawtypes")
	public static List getBuffer() {
		return buffer;
	}

	/**
	 * Paint-Buffer Setter
	 * 
	 * @param buffer
	 *            2 dimensional String (String[999][10])
	 */
	@SuppressWarnings("rawtypes")
	public static void setBuffer(List buffer) {
		Start.buffer = buffer;
	}

	/**
	 * load a file
	 */
	// "D:\\SVN\\LAN-HDDs\\ALG\\de.bht.alg.s778451.tree\\scr\\dat"
	// "C:\\Users\\Marcel\\Documents\\GitHub\\de.bht.alg.s778451.tree\\scr\\dat"
	public void loadFile() {
		fc = new JFileChooser(new File(
				"D:\\SVN\\LAN-HDDs\\ALG\\de.bht.alg.s778451.tree\\scr\\dat"));
		int returnVal = fc.showOpenDialog(Start.this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();
			this.file = f.getAbsolutePath();
		} else {
			this.file = null;
		}
	}

	/**
	 * Override actionPerformed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		PaintPanel.setStatus(false);

		if (e.getSource() == button1) {
			this.loadFile();
		} else if (e.getSource() == button2) {
			if (this.file != null) {
				// DFS
				((PaintPanel) paintPanel).resetElementBuffer();
				DepthFirstSearch.run(this.file, (PaintPanel) paintPanel);
				PaintPanel.setStatus(true);
				((PaintPanel) paintPanel).addNode("BLACK", 0);
			}
		} else if (e.getSource() == button3) {
			if (this.file != null) {
				// BFS
				((PaintPanel) paintPanel).resetElementBuffer();
				BreadthFirstSearch.run(this.file, (PaintPanel) paintPanel);
				PaintPanel.setStatus(true);
				((PaintPanel) paintPanel).addNode("BLACK", 0);
			}
		} else if (e.getSource() == button4) {
			if (this.file != null) {
				// Dijkstra
				((PaintPanel) paintPanel).resetElementBuffer();
				// TODO: Fill me ...
				PaintPanel.setStatus(true);
				((PaintPanel) paintPanel).addText("Zur Zeit nicht möglich !!!",
						"RED", 99);
				((PaintPanel) paintPanel).addNode("BLACK", 0);
			}
		} else if (e.getSource() == button5) {
			if (this.file != null) {
				// Kruskal
				((PaintPanel) paintPanel).resetElementBuffer();
				// TODO: Fill me ...
				PaintPanel.setStatus(true);
				((PaintPanel) paintPanel).addText("Zur Zeit nicht möglich !!!",
						"RED", 99);
				((PaintPanel) paintPanel).addNode("BLACK", 0);
			}
		}
	}
}