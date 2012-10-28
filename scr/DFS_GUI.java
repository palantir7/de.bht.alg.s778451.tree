import graph.Edge;
import graph.Graph;
import graph.GraphLesen;
import graph.Vertex;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.*;

class DFS_GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private int tool = 1;
	private JFileChooser fc;
	int currentX, currentY, oldX, oldY;

	private static int r = 20;
	private static int x = 0;
	private static int y = 0;
	private static String name = null;
	private static String file = null;

	static int vertexCount;
	static Graph graph;

	public DFS_GUI() {
		fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(DFS_GUI.this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			DFS_GUI.setFile(file.getAbsolutePath());
			// Testausgabe in der Console
			System.out.println(DFS_GUI.file);
		}

		initComponents();

		run(DFS_GUI.file);
	}

	private void initComponents() {

		jPanel2 = new Panel2();

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

	private void jPanel2MousePressed(MouseEvent evt) {
		oldX = evt.getX();
		oldY = evt.getY();
		System.out.println(oldX + " " + oldY);
	}

	// mouse released//
	private void jPanel2MouseReleased(MouseEvent evt) {
		if (tool == 2) {
			currentX = evt.getX();
			currentY = evt.getY();
			System.out.println("line!!!! from" + oldX + "to" + currentX);
		}
	}

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new DFS_GUI().setVisible(true);
			}
		});
	}

	private JPanel jPanel2;

	class Panel2 extends JPanel {
		private static final long serialVersionUID = 1L;

		Panel2() {
			// set a preferred size for the custom panel.
			setPreferredSize(new Dimension(420, 420));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			for (int i = 0; i < 10; i++) {

				selectNode(i);

				// die Linien zum verbinden der Nodes fehlt noch
				// hier die Farbe des Kreises wechseln
				g.setColor(Color.GRAY);
				g.fillOval(x, y, r, r);
				// hier die Farbe des Textes wechseln
				g.setColor(Color.WHITE);
				g.drawString(name, x + 7, y + 15);
			}
		}
	}

	private void selectNode(int number) {
		switch (number) {
		case 0:
			DFS_GUI.x = 85;
			DFS_GUI.y = 35;
			DFS_GUI.name = "0";
			break;
		case 1:
			DFS_GUI.x = 55;
			DFS_GUI.y = 65;
			DFS_GUI.name = "1";
			break;
		case 2:
			DFS_GUI.x = 115;
			DFS_GUI.y = 65;
			DFS_GUI.name = "2";
			break;
		case 3:
			DFS_GUI.x = 85;
			DFS_GUI.y = 95;
			DFS_GUI.name = "3";
			break;
		case 4:
			DFS_GUI.x = 55;
			DFS_GUI.y = 125;
			DFS_GUI.name = "4";
			break;
		case 5:
			DFS_GUI.x = 115;
			DFS_GUI.y = 125;
			DFS_GUI.name = "5";
			break;
		case 6:
			DFS_GUI.x = 85;
			DFS_GUI.y = 155;
			DFS_GUI.name = "6";
			break;
		case 7:
			DFS_GUI.x = 85;
			DFS_GUI.y = 185;
			DFS_GUI.name = "7";
			break;
		case 8:
			DFS_GUI.x = 55;
			DFS_GUI.y = 215;
			DFS_GUI.name = "8";
			break;
		case 9:
			DFS_GUI.x = 115;
			DFS_GUI.y = 215;
			DFS_GUI.name = "9";
			break;
		default:
			DFS_GUI.x = 0;
			DFS_GUI.y = 0;
			DFS_GUI.name = "-";
		}
	}

	public static String getFile() {
		return file;
	}

	public static void setFile(String file) {
		DFS_GUI.file = file;
	}

	// DFS-Part

	/**
	 * @param args
	 */
	public static void run(String file) {
		// URL zu Zieldatei
		String url = file;
		// Lade Graph der Zieldatei
		Graph<Vertex, Edge<Vertex>> G = GraphLesen.FileToGraph(url, false);

		readGraph(G);

	}

	private static enum VertexState {
		White, Gray, Black
	}

	// liest den Graphen
	private static void readGraph(Graph G) {
		// Anzahl der Knoten
		vertexCount = G.getNumberVertices();
		graph = G;

		// Setzte alle Vertizes auf Weiﬂ
		VertexState state[] = new VertexState[vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			state[i] = VertexState.White;
		}
		loopDFS(0, state);
	}

	// Iteriert ¸ber den Baum
	private static void loopDFS(int u, VertexState[] state) {
		System.out.println(u + " -> ");

		state[u] = VertexState.Gray;
		for (int v = 0; v < vertexCount; v++) {
			if (isEdge(u, v) && state[v] == VertexState.White) {
				loopDFS(v, state);
			}
		}
		state[u] = VertexState.Black;
	}

	// Schaut ob Punkt u und Punkt v eine Kante haben
	private static boolean isEdge(int u, int v) {
		Collection<Vertex> neighbor = graph.getNeighbours(u);

		for (Iterator i = neighbor.iterator(); i.hasNext();) {
			Vertex x = (Vertex) i.next();
			if (x.equals(graph.getVertex(v))) {
				// hier kann die GUI angeschlossen werden
				// hier wird bei true der Status von Weiﬂ zu Grau zu Schwarz
				System.out.println(u + " -> " + neighbor + " -> " + v);
				return true;
			}
		}
		return false;
	}
}