import graph.Edge;
import graph.Graph;
import graph.GraphLesen;
import graph.Vertex;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import paint.PaintPanel;
import pathfinder.Dijkstra;
import search.BreadthFirstSearch;
import search.DepthFirstSearch;

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
	private static JButton button6;
	private static JButton button7;
	private static JButton button8;
	@SuppressWarnings("rawtypes")
	public static JComboBox txtFrom;
	@SuppressWarnings("rawtypes")
	public static JComboBox txtTo;
	public static JTextField txtStatus;

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
	@SuppressWarnings("rawtypes")
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
		txtFrom = new JComboBox();
		iPanel.add(txtFrom);
		txtTo = new JComboBox();
		iPanel.add(txtTo);
		button4 = new JButton("Dijkstra - Algo");
		button4.addActionListener(this);
		iPanel.add(button4);
		button5 = new JButton("Kruskal - Algo");
		button5.addActionListener(this);
		iPanel.add(button5);
		button6 = new JButton("Floyd - Algo");
		button6.addActionListener(this);
		iPanel.add(button6);
		button7 = new JButton("Bellman Ford - Algo");
		button7.addActionListener(this);
		iPanel.add(button7);
		button8 = new JButton("- Clear PaintPanel -");
		button8.addActionListener(this);
		iPanel.add(button8);
		txtStatus = new JTextField("Bereit ...");
		iPanel.add(txtStatus);

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
	@SuppressWarnings({ "unchecked" })
	public void loadFile() {
		fc = new JFileChooser(new File(
				"D:\\SVN\\LAN-HDDs\\ALG\\de.bht.alg.s778451.tree\\scr\\dat"));
		int returnVal = fc.showOpenDialog(Start.this);

		txtFrom.removeAllItems();
		txtTo.removeAllItems();

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();
			this.file = f.getAbsolutePath();

			Graph<Vertex, Edge<Vertex>> G = GraphLesen.FileToWeightedGraph(
					this.file, false);
			Collection<Vertex> vertex = G.getVertices();
			String[] nodes = new String[vertex.size()];
			for (int i = 0; i < vertex.size(); i++) {
				nodes[i] = G.getVertex(i).toString();
				txtFrom.addItem(nodes[i]);
				txtTo.addItem(nodes[i]);
			}
			this.repaint();
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
		txtStatus.setText("Beginne Berechung ...");

		if (e.getSource() == button1) {
			txtStatus.setText("Lade Datei ...");
			this.loadFile();
			txtStatus.setText("Bereit ...");
		} else if (e.getSource() == button2) {
			if (this.file != null) {
				((PaintPanel) paintPanel).clearArea();
				// DFS
				((PaintPanel) paintPanel).resetElementBuffer();
				DepthFirstSearch.run(this.file, (PaintPanel) paintPanel);
				PaintPanel.setStatus(true);
				((PaintPanel) paintPanel).addNode("BLACK", 0);
			}
		} else if (e.getSource() == button3) {
			if (this.file != null) {
				((PaintPanel) paintPanel).clearArea();
				// BFS
				((PaintPanel) paintPanel).resetElementBuffer();
				BreadthFirstSearch.run(this.file, (PaintPanel) paintPanel);
				PaintPanel.setStatus(true);
				((PaintPanel) paintPanel).addNode("BLACK", 0);
			}
		} else if (e.getSource() == button4) {
			if (this.file != null) {
				((PaintPanel) paintPanel).clearArea();
				((PaintPanel) paintPanel).resetElementBuffer();
				// Dijkstra
				try {
					Dijkstra.run(this.file, (PaintPanel) paintPanel, txtFrom
							.getSelectedItem().toString(), txtTo
							.getSelectedItem().toString());
					txtStatus.setText("Bereit ...");
				} catch (Exception ex) {
					txtStatus.setText("Fehler aufgetreten ...");
					((PaintPanel) paintPanel).addText(
							"FEHLER: Knoten k�nnen nicht"
									+ " ausgewertet werden !!!"
									+ " Negative Kanten entdeckt !!!", "RED",
							99);
				}
				PaintPanel.setStatus(true);
				((PaintPanel) paintPanel).addNode("BLACK", 0);
			}
		} else if (e.getSource() == button5) {
			if (this.file != null) {
				((PaintPanel) paintPanel).clearArea();
				((PaintPanel) paintPanel).resetElementBuffer();
				// Kruskal
				// TODO: Fill me ...
				PaintPanel.setStatus(true);
				((PaintPanel) paintPanel).addText("Zur Zeit nicht m�glich !!!",
						"RED", 99);
			}
		} else if (e.getSource() == button6) {
			if (this.file != null) {
				((PaintPanel) paintPanel).clearArea();
				((PaintPanel) paintPanel).resetElementBuffer();
				// Floyd
				// TODO: Fill me ...
				PaintPanel.setStatus(true);
				((PaintPanel) paintPanel).addText("Zur Zeit nicht m�glich !!!",
						"RED", 99);
			}
		} else if (e.getSource() == button7) {
			if (this.file != null) {
				((PaintPanel) paintPanel).clearArea();
				((PaintPanel) paintPanel).resetElementBuffer();
				// Bellman Ford
				// TODO: Fill me ...
				PaintPanel.setStatus(true);
				((PaintPanel) paintPanel).addText("Zur Zeit nicht m�glich !!!",
						"RED", 99);
			}
		} else if (e.getSource() == button8) {
			if (this.file != null) {
				((PaintPanel) paintPanel).clearArea();
				((PaintPanel) paintPanel).resetElementBuffer();
				// Clear PaintPanel
				PaintPanel.setStatus(true);
			}
		}
	}
}