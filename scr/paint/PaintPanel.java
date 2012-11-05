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
	private int count = 0;
	private int frame = 0;
	@SuppressWarnings("rawtypes")
	private List elementBuffer = new ArrayList();
	private static boolean status;

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
		// (getGraphic());
		// this.repaint();
		// this.updateUI();
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
	@SuppressWarnings({ "static-access" })
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setGraphic(g);

		if (isStatus() == true) {
			//TODO: Timer
			int mod = count % 1000;
			if (mod == 0) {
				if (frame < elementBuffer.size()) {
					// System.out.println(getElement(frame));
					frame++;
				} else {
					// frame = 0;
				}
			}
		}

		for (int i = 0; i < frame; i++) {
			element = getElement(i);

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
		count++;
	}

	@SuppressWarnings("rawtypes")
	private String[] getElement(int index) {
		try {
			Iterator itr = elementBuffer.iterator();
			while (index > 0) {
				element = (String[]) itr.next();
				index--;
			}
		} catch (Exception e) {
			// do nothing
		}
		return element;
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
			getArrow(x, y, toX, toY);
		} else if (color.equals("GRAY")) {
			getGraphic().setColor(Color.GRAY);
			getGraphic().drawLine(x + 5, y + 5, toX + 5, toY + 5);
			getArrow(x, y, toX, toY);
		} else if (color.equals("RED")) {
			getGraphic().setColor(Color.RED);
			getGraphic().drawLine(x + 5, y + 5, toX + 5, toY + 5);
			getArrow(x, y, toX, toY);
		} else {
			getGraphic().setColor(Color.WHITE);
			getGraphic().drawLine(x + 5, y + 5, toX + 5, toY + 5);
			getArrow(x, y, toX, toY);
		}
	}
	
	private void getArrow(int x, int y, int toX, int toY) {
		// orientation of arrow
		int o1 = x - toX;
		int o2 = y - toY;
		
		if (o1 > 0 && o2 < 0)  { 
			// x+ y+
			int[] xPoints = {toX + 15, toX + 10, toX + 17};
			int[] yPoints = {toY - 2, toY + 3, toY + 3};
		    getGraphic().drawPolygon(xPoints, yPoints, 3);
		} else if (o1 < 0 && o2 < 0) { 
			// x+ y+
			int[] xPoints = {toX - 5, toX, toX - 7};
			int[] yPoints = {toY - 3, toY + 2, toY + 2};
		    getGraphic().drawPolygon(xPoints, yPoints, 3);
		} else if (o1 < 0 && o2 > 0) { 
			// x+ y+
			int[] xPoints = {toX - 5, toX, toX - 7};
			int[] yPoints = {toY + 13, toY + 8, toY + 8};
		    getGraphic().drawPolygon(xPoints, yPoints, 3);
		} else if (o1 > 0 && o2 > 0) { 
			// x+ y+
			int[] xPoints = {toX + 15, toX + 10, toX + 17};
			int[] yPoints = {toY + 13, toY + 8, toY + 8};
		    getGraphic().drawPolygon(xPoints, yPoints, 3);
		} else if (o1 > 0 && o2 == 0) { 
			// x+ y+
			int[] xPoints = {toX + 10, toX + 15, toX + 15};
			int[] yPoints = {toY + 5, toY + 2, toY + 7};
		    getGraphic().drawPolygon(xPoints, yPoints, 3);
		} else if (o1 < 0 && o2 == 0) { 
			// x+ y+
			int[] xPoints = {toX, toX - 5, toX - 5};
			int[] yPoints = {toY + 5, toY + 2, toY + 7};
		    getGraphic().drawPolygon(xPoints, yPoints, 3);
		} else if (o2 < 0 && o1 == 0) { 
			// x+ y+
			int[] xPoints = {toX + 2, toX + 5, toX + 7};
			int[] yPoints = {toY - 5, toY, toY - 5};
		    getGraphic().drawPolygon(xPoints, yPoints, 3);
		} else if (o2 > 0 && o1 == 0) { 
			// x+ y+
			int[] xPoints = {toX + 2, toX + 5, toX + 7};
			int[] yPoints = {toY + 15, toY + 10, toY + 15};
		    getGraphic().drawPolygon(xPoints, yPoints, 3);
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
		int modX = 7;
		int modY = -3;
		Font font = new Font(family, style, size);

		getGraphic().setFont(font);

		if (color.equals("BLACK")) {
			getGraphic().setColor(Color.BLACK);
			getGraphic().drawString(text, x + modX, y + modY);
		} else if (color.equals("GRAY")) {
			getGraphic().setColor(Color.GRAY);
			getGraphic().drawString(text, x + modX, y + modY);
		} else if (color.equals("RED")) {
			getGraphic().setColor(Color.RED);
			getGraphic().drawString(text, x + modX, y + modY);
		} else {
			getGraphic().setColor(Color.WHITE);
			getGraphic().drawString(text, x + modX, y + modY);
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
	 * Sets the Element-Buffer
	 * 
	 * @param buffer
	 */
	@SuppressWarnings("rawtypes")
	public void resetElementBuffer() {
		this.elementBuffer = new ArrayList();
		frame = 0;
		count = 0;
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

	/**
	 * Getter of Status
	 * 
	 * @return status as @boolean
	 */
	public static boolean isStatus() {
		return status;
	}

	/**
	 * Setter of Status
	 * 
	 * @param status
	 *            as @boolean
	 */
	public static void setStatus(boolean status) {
		PaintPanel.status = status;
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
	 * Position Getter
	 * 
	 * @param id
	 *            Node-ID
	 */
	private void getPos(int id) {
		switch (id) {
		case (0):
			this.x = 200;
			this.y = 50;
			break;
		case (1):
			this.x = 100;
			this.y = 100;
			break;
		case (2):
			this.x = 200;
			this.y = 100;
			break;
		case (3):
			this.x = 300;
			this.y = 100;
			break;
		case (4):
			this.x = 100;
			this.y = 150;
			break;
		case (5):
			this.x = 200;
			this.y = 150;
			break;
		case (6):
			this.x = 300;
			this.y = 150;
			break;
		case (7):
			this.x = 100;
			this.y = 200;
			break;
		case (8):
			this.x = 200;
			this.y = 200;
			break;
		case (9):
			this.x = 300;
			this.y = 200;
			break;
		case (10):
			this.x = 100;
			this.y = 250;
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