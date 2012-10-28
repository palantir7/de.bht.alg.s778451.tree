import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.*;

class JavaPaintUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private int tool = 1;
	private JFileChooser fc;
	int currentX, currentY, oldX, oldY;

	private static int r = 20;
	private static int x = 0;
	private static int y = 0;
	private static String name = null;
	private static String file = null;

	public JavaPaintUI() {
		fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(JavaPaintUI.this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			JavaPaintUI.setFile(file.getAbsolutePath());
			// Testausgabe in der Console
			System.out.println(JavaPaintUI.file);
		}

		initComponents();
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
				new JavaPaintUI().setVisible(true);
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
			JavaPaintUI.x = 85;
			JavaPaintUI.y = 35;
			JavaPaintUI.name = "0";
			break;
		case 1:
			JavaPaintUI.x = 55;
			JavaPaintUI.y = 65;
			JavaPaintUI.name = "1";
			break;
		case 2:
			JavaPaintUI.x = 115;
			JavaPaintUI.y = 65;
			JavaPaintUI.name = "2";
			break;
		case 3:
			JavaPaintUI.x = 85;
			JavaPaintUI.y = 95;
			JavaPaintUI.name = "3";
			break;
		case 4:
			JavaPaintUI.x = 55;
			JavaPaintUI.y = 125;
			JavaPaintUI.name = "4";
			break;
		case 5:
			JavaPaintUI.x = 115;
			JavaPaintUI.y = 125;
			JavaPaintUI.name = "5";
			break;
		case 6:
			JavaPaintUI.x = 85;
			JavaPaintUI.y = 155;
			JavaPaintUI.name = "6";
			break;
		case 7:
			JavaPaintUI.x = 85;
			JavaPaintUI.y = 185;
			JavaPaintUI.name = "7";
			break;
		case 8:
			JavaPaintUI.x = 55;
			JavaPaintUI.y = 215;
			JavaPaintUI.name = "8";
			break;
		case 9:
			JavaPaintUI.x = 115;
			JavaPaintUI.y = 215;
			JavaPaintUI.name = "9";
			break;
		default:
			JavaPaintUI.x = 0;
			JavaPaintUI.y = 0;
			JavaPaintUI.name = "-";
		}
	}

	public static String getFile() {
		return file;
	}

	public static void setFile(String file) {
		JavaPaintUI.file = file;
	}
}