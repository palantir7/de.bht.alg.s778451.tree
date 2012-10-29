import graph.Graph;
import graph.Vertex;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

class DFS_GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private int tool = 1;
	private JFileChooser fc;
	int currentX, currentY, oldX, oldY;

	private static String file = null;

	static int vertexCount;
	@SuppressWarnings("rawtypes")
	static Graph graph;

	/**
	 * Constructor DFS_GUI.java
	 */
	public DFS_GUI() {
		fc = new JFileChooser(new File("D:\\SVN\\LAN-HDDs\\ALG\\de.bht.alg.s778451.tree\\scr\\dat"));
		int returnVal = fc.showOpenDialog(DFS_GUI.this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			DFS_GUI.setFile(file.getAbsolutePath());
		}

		initComponents();

		DFS.run(file);
	}

	/**
	 * initComponents()
	 */
	private void initComponents() {

		jPanel2 = new PaintPanel();

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));
		jPanel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		jPanel2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				jPanel2MousePressed(evt);
			}

			public void mouseReleased(MouseEvent evt) {
				jPanel2MouseReleased(evt);
			}
		});
		jPanel2.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
				jPanel2MouseDragged(evt);
			}
		});

		this.setContentPane(jPanel2);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}

	/**
	 * MouseDragger
	 * @param evt Mouse-Event
	 */
	private void jPanel2MouseDragged(MouseEvent evt) {
		if (tool == 1) {
			currentX = evt.getX();
			currentY = evt.getY();
			oldX = currentX;
			oldY = currentY;
			System.out.println(currentX + " " + currentY);
			System.out.println("PEN!!!!");
		}
	}

	/**
	 * Pressed Mouse
	 * @param evt Mouse-Event
	 */
	private void jPanel2MousePressed(MouseEvent evt) {
		oldX = evt.getX();
		oldY = evt.getY();
		System.out.println(oldX + " " + oldY);
	}

	/**
	 * mouse released
	 * @param evt Mouse-Event
	 */
	private void jPanel2MouseReleased(MouseEvent evt) {
		if (tool == 2) {
			currentX = evt.getX();
			currentY = evt.getY();
			System.out.println("line!!!! from" + oldX + "to" + currentX);
		}
	}

	/**
	 * main-Methode
	 * @param args nothing
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new DFS_GUI().setVisible(true);
			}
		});
	}

	private JPanel jPanel2;

	/**
	 * @author Marcel Buchmann (s778451)
	 * @email marcel.buchmann(- at -)googlemail.com
	 * @version 1.0.0
	 * @date 29.10.2012
	 * @project de.bht.alg.s778451.tree
	 */
	class PaintPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		PaintPanel() {
			// set a preferred size for the custom panel.
			setPreferredSize(new Dimension(420, 420));
		}

		/**
		 * paintComponent
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graph G = DFS.getGraph();
			Collection<Vertex> nodes = G.getVertices();
			int[][] pos = initPos();

			// Iteriere über die nodes
			for (Iterator i = nodes.iterator(); i.hasNext();) {
				Vertex node = (Vertex) i.next();

				// zeiche nodes
				drawNode(node.getId(), g, pos);
				// zeiche edges
				Collection neighbors = G.getNeighbours(node);
				for (Iterator n = neighbors.iterator(); n.hasNext();) {
					Vertex neighbor = (Vertex) n.next();
					g.drawLine(pos[node.getId()][0] + 5,
							pos[node.getId()][1] + 5,
							pos[neighbor.getId()][0] + 5,
							pos[neighbor.getId()][1] + 5);
				}
				// stelle farben aktive da
				DFS.setPos(pos);
				DFS.setG(g);
				DFS.readGraph(G, 0);
			}
		}
	}

	/**
	 * Position Array of Nodes
	 * @return Position-Array
	 */
	private int[][] initPos() {
		int[][] pos = new int[10][2];
		pos[0][0] = 50;
		pos[0][1] = 25;
		pos[1][0] = 25;
		pos[1][1] = 50;
		pos[2][0] = 50;
		pos[2][1] = 50;
		pos[3][0] = 75;
		pos[3][1] = 50;
		pos[4][0] = 25;
		pos[4][1] = 75;
		pos[5][0] = 50;
		pos[5][1] = 75;
		pos[6][0] = 75;
		pos[6][1] = 75;
		pos[7][0] = 25;
		pos[7][1] = 100;
		pos[8][0] = 50;
		pos[8][1] = 100;
		pos[9][0] = 75;
		pos[9][1] = 100;

		return pos;
	}

	/**
	 * drawing Nodes
	 * @param node the Node-ID
	 * @param g Graphics-Area
	 * @param pos Position-Array of Nodes
	 */
	private void drawNode(int node, Graphics g, int[][] pos) {
		switch (node) {
			case (0):
				g.drawOval(pos[0][0], pos[0][1], 10, 10);
				break;
			case (1):
				g.drawOval(pos[1][0], pos[1][1], 10, 10);
				break;
			case (2):
				g.drawOval(pos[2][0], pos[2][1], 10, 10);
				break;
			case (3):
				g.drawOval(pos[3][0], pos[3][1], 10, 10);
				break;
			case (4):
				g.drawOval(pos[4][0], pos[4][1], 10, 10);
				break;
			case (5):
				g.drawOval(pos[5][0], pos[5][1], 10, 10);
				break;
			case (6):
				g.drawOval(pos[6][0], pos[6][1], 10, 10);
				break;
			case (7):
				g.drawOval(pos[7][0], pos[7][1], 10, 10);
				break;
			case (8):
				g.drawOval(pos[8][0], pos[8][1], 10, 10);
				break;
			case (9):
				g.drawOval(pos[9][0], pos[9][1], 10, 10);
				break;
		}
	}

	/**
	 * File Getter
	 * @return file
	 */
	public static String getFile() {
		return file;
	}

	/**
	 * File Setter
	 * @param file
	 */
	public static void setFile(String file) {
		DFS_GUI.file = file;
	}
}