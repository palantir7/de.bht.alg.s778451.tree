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
		setPreferredSize(new Dimension(500, 300));

		// Test
		this.addNode(15, 30, "GRAY");
		this.addEdge(20, 35, 20, 55, "WHITE");
		this.addText(30, 40, "Test...", "GRAY");
	}

	/**
	 * Paint now ...
	 */
	@SuppressWarnings("static-access")
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setGraphic(g);
		
		@SuppressWarnings("rawtypes")
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
		System.out.println("Node ->" + color);
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
		System.out.println("Edge ->" + color);
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
		System.out.println("Text ->" + color);
	}

	// ---------- Getter/Setter-Methodes ---------->>>

	/**
	 * Gets the Element-Buffer
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getElementBuffer() {
		return elementBuffer;
	}

	/**
	 * Sets the Element-Buffer
	 * @param buffer
	 */
	@SuppressWarnings("rawtypes")
	public void setElementBuffer(List buffer) {
		this.elementBuffer = buffer;

	}

	
	/**
	 * Getter of Graphics
	 * @return @Graphics
	 */
	public static Graphics getGraphic() {
		return graphic;
	}
	
	/**
	 * Setter of Graphics
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
	public void addNode(int x, int y, String color) {
		element = new String[] { "Node", "" + x, "" + y, null, null,
				color.toString(), null };
		elementBuffer.add(element);
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
	public void addEdge(int x, int y, int toX, int toY, String color) {
		element = new String[] { "Edge", "" + x, "" + y, "" + toX, "" + toY,
				color.toString(), null };
		elementBuffer.add(element);
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
	public void addText(int x, int y, String text, String color) {
		element = new String[] { "Text", "" + x, "" + y, null, null,
				color.toString(), text };
		elementBuffer.add(element);
	}
}
