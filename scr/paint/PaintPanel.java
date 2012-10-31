/**
 * 
 */
package paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author Marcel Buchmann (s778451)
 * @email marcel.buchmann(- at -)googlemail.com
 * @version 1.0.0
 * @date 30.10.2012
 * @project de.bht.alg.s778451.tree
 * 
 */
public class PaintPanel extends JPanel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static String[] element = new String[10];
	private static Graphics graphic;
	private int x;
	private int y;
	@SuppressWarnings("rawtypes")
	private List elementBuffer = new ArrayList();

	/**
	 * Constructor PaintPanel.java
	 */
	public PaintPanel() {
		initPaintArea();
	}

	/**
	 * Initialization of PaintArea
	 */
	private void initPaintArea() {
		setPreferredSize(new Dimension(550, 350));
	}

	public void updateArea() {
		super.repaint();
		super.updateUI();
		//(getGraphic());
		//this.repaint();
		//this.updateUI();
		breaking();
	}
	
	/**
	 * Pause-Mode (0.1 sec)
	 */
	private static void breaking() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Paint now ...
	 */
	@SuppressWarnings({ "static-access", "rawtypes" })
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setGraphic(g);

		Iterator itr = elementBuffer.iterator();

		// mini-parser
		while (itr.hasNext()) {
			element = (String[]) itr.next();

			if (element[0].equals("Node")) {
				this.drawNode(Integer.parseInt(element[1]),
						Integer.parseInt(element[2]), element[5]);
			} else if (element[0].equals("Edge")) {
				this.drawEdge(Integer.parseInt(element[1]),
						Integer.parseInt(element[2]),
						Integer.parseInt(element[3]),
						Integer.parseInt(element[4]), element[5]);
			} else if (element[0].equals("Text")) {
				this.drawText(Integer.parseInt(element[1]),
						Integer.parseInt(element[2]), element[6], element[5]);
			} else {
				System.out
						.println("Error on Paint ... Element not definied ...");
			}
		}
		this.updateUI();
	}

	// ---------- Draw-Methodes ---------->>>

	/**
	 * Draws a Node
	 * 
	 * @param x
	 * @param y
	 * @param color
	 */
	private void drawNode(int x, int y, String color) {
		if (color.equals("BLACK")) {
			getGraphic().setColor(Color.BLACK);
			getGraphic().fillOval(x, y, 10, 10);
		} else if (color.equals("GRAY")) {
			getGraphic().setColor(Color.GRAY);
			getGraphic().fillOval(x, y, 10, 10);
			getGraphic().setColor(Color.BLACK);
			getGraphic().drawOval(x, y, 10, 10);
		} else {
			getGraphic().setColor(Color.WHITE);
			getGraphic().fillOval(x, y, 10, 10);
			getGraphic().setColor(Color.BLACK);
			getGraphic().drawOval(x, y, 10, 10);
		}
	}

	/**
	 * Draws a Edge
	 * 
	 * @param x
	 * @param y
	 * @param toX
	 * @param toY
	 * @param color
	 */
	private void drawEdge(int x, int y, int toX, int toY, String color) {
		if (color.equals("BLACK")) {
			getGraphic().setColor(Color.BLACK);
			getGraphic().drawLine(x + 5, y + 5, toX + 5, toY + 5);
		} else if (color.equals("GRAY")) {
			getGraphic().setColor(Color.GRAY);
			getGraphic().drawLine(x + 5, y + 5, toX + 5, toY + 5);
		} else {
			getGraphic().setColor(Color.WHITE);
			getGraphic().drawLine(x + 5, y + 5, toX + 5, toY + 5);
		}
	}

	/**
	 * Draws a Text
	 * 
	 * @param x
	 * @param y
	 * @param text
	 * @param color
	 */
	private void drawText(int x, int y, String text, String color) {
		// Font settings
		String family = "Lucida Sans Typewriter";
		int style = Font.PLAIN;
		int size = 10;
		Font font = new Font(family, style, size);

		getGraphic().setFont(font);

		if (color.equals("BLACK")) {
			getGraphic().setColor(Color.BLACK);
			getGraphic().drawString(text, x, y);
		} else if (color.equals("GRAY")) {
			getGraphic().setColor(Color.GRAY);
			getGraphic().drawString(text, x, y);
		} else {
			getGraphic().setColor(Color.WHITE);
			getGraphic().drawString(text, x, y);
		}
	}

	// ---------- Getter/Setter-Methodes ---------->>>

	/**
	 * Gets the Element-Buffer
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getElementBuffer() {
		return elementBuffer;
	}

	/**
	 * Sets the Element-Buffer
	 * 
	 * @param buffer
	 */
	@SuppressWarnings("rawtypes")
	public void setElementBuffer(List buffer) {
		this.elementBuffer = buffer;
	}

	/**
	 * Getter of Graphics
	 * 
	 * @return @Graphics
	 */
	public static Graphics getGraphic() {
		return graphic;
	}

	/**
	 * Setter of Graphics
	 * 
	 * @param graphic
	 */

	public static void setGraphic(Graphics graphic) {
		PaintPanel.graphic = graphic;
	}

	// ---------- Adder-Methodes ---------->>>

	/**
	 * Add a Node
	 * 
	 * @param x
	 * @param y
	 * @param color
	 */
	@SuppressWarnings("unchecked")
	public void addNode(String color, int id) {
		getPos(id);
		element = new String[] { "Node", "" + this.x, "" + this.y, null, null,
				color.toString(), null, "" + id };
		elementBuffer.add(element);
		updateArea();
	}

	/**
	 * Update a Node
	 * 
	 * @param x
	 * @param y
	 * @param color
	 * @param id
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateNode(int x, int y, String color, int id) {
		int i = 0;
		List tempList = new ArrayList(elementBuffer);
		Iterator itr = tempList.iterator();

		// mini-parser
		while (itr.hasNext()) {
			element = (String[]) itr.next();
			if (element[0].equals("Node") && element[7].equals("" + id)) {
				elementBuffer.remove(i);
			}
			i++;
		}
		element = new String[] { "Node", "" + x, "" + y, null, null,
				color.toString(), null, "" + id };
		elementBuffer.add(element);
		updateArea();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateNode(String color, int id) {
		getPos(id);
		int i = 0;
		List tempList = new ArrayList(elementBuffer);
		Iterator itr = tempList.iterator();

		// mini-parser
		while (itr.hasNext()) {
			element = (String[]) itr.next();
			if (element[0].equals("Node") && element[7].equals("" + id)) {
				elementBuffer.remove(i);
			}
			i++;
		}
		element = new String[] { "Node", "" + this.x, "" + this.y, null, null,
				color.toString(), null, "" + id };
		elementBuffer.add(element);
		updateArea();
	}

	/**
	 * Add a Edge
	 * 
	 * @param x
	 * @param y
	 * @param toX
	 * @param toY
	 * @param color
	 */
	@SuppressWarnings("unchecked")
	public void addEdge(String color, int idFrom, int idTo) {
		getPos(idFrom);
		int tempX = this.x;
		int tempY = this.y;
		getPos(idTo);
		element = new String[] { "Edge", "" + tempX, "" + tempY, "" + this.x,
				"" + this.y, color.toString(), null, idFrom + "-" + idTo };
		elementBuffer.add(element);
		updateArea();
	}

	/**
	 * Update a Edge
	 * 
	 * @param x
	 * @param y
	 * @param toX
	 * @param toY
	 * @param color
	 * @param id
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateEdge(int x, int y, int toX, int toY, String color,
			int idFrom, int idTo) {
		int i = 0;
		List tempList = new ArrayList(elementBuffer);
		Iterator itr = tempList.iterator();

		// mini-parser
		while (itr.hasNext()) {
			element = (String[]) itr.next();
			if (element[0].equals("Edge")
					&& element[7].equals(idFrom + "-" + idTo)) {
				elementBuffer.remove(i);
			}
			i++;
		}
		element = new String[] { "Edge", "" + x, "" + y, "" + toX, "" + toY,
				color.toString(), null, "" + idFrom + "-" + idTo };
		elementBuffer.add(element);
		updateArea();
	}

	/**
	 * Add a Text
	 * 
	 * @param x
	 * @param y
	 * @param text
	 * @param color
	 */
	@SuppressWarnings("unchecked")
	public void addText(String text, String color, int id) {
		getPos(id);
		element = new String[] { "Text", "" + x, "" + y, null, null,
				color.toString(), text, "" + id };
		elementBuffer.add(element);
		updateArea();
	}

	/**
	 * Update a Text
	 * 
	 * @param x
	 * @param y
	 * @param text
	 * @param color
	 * @param id
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateText(int x, int y, String text, String color, int id) {
		int i = 0;
		List tempList = new ArrayList(elementBuffer);
		Iterator itr = tempList.iterator();

		// mini-parser
		while (itr.hasNext()) {
			element = (String[]) itr.next();
			if (element[0].equals("Text") && element[7].equals("" + id)) {
				elementBuffer.remove(i);
			}
			i++;
		}
		element = new String[] { "Text", "" + x, "" + y, null, null,
				color.toString(), text, "" + id };
		elementBuffer.add(element);
		updateArea();
	}

	/**
	 * Position Getter
	 * 
	 * @param id
	 *            Node-ID
	 */
	private void getPos(int id) {
		switch (id) {
			case (0):
				this.x = 80;
				this.y = 10;
				break;
			case (1):
				this.x = 60;
				this.y = 20;
				break;
			case (2):
				this.x = 100;
				this.y = 20;
				break;
			case (3):
				this.x = 40;
				this.y = 40;
				break;
			case (4):
				this.x = 120;
				this.y = 40;
				break;
			case (5):
				this.x = 30;
				this.y = 60;
				break;
			case (6):
				this.x = 130;
				this.y = 60;
				break;
			case (7):
				this.x = 40;
				this.y = 80;
				break;
			case (8):
				this.x = 120;
				this.y = 80;
				break;
			case (9):
				this.x = 60;
				this.y = 100;
				break;
			case (10):
				this.x = 100;
				this.y = 100;
				break;
			case (11):
				this.x = 80;
				this.y = 110;
				break;
			case (12):
				this.x = 80;
				this.y = 10;
				break;
			case (99):
				this.x = 10;
				this.y = 15;
				break;
			default:
				this.x = 10;
				this.y = 15;
				break;
		}
	}
}
