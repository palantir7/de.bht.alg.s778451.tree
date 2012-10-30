/**
 * 
 */
package paint;

import java.awt.Color;

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
	private String[][] elementBuffer;

	/**
	 * Constructor PaintPanel.java
	 */
	public PaintPanel() {
		//TODO: Fill me ...
		initPaintArea();
	}
	
	private void initPaintArea() {
		//TODO: Fill me ...
		// Iteriere über Buffer und wähle die richtigen
		// Zeichenmethoden ...
	}
	
	//---------- Draw-Methodes ---------->>>
	
	/**
	 * Draws a Node
	 * @param x
	 * @param y
	 * @param color
	 */
	private void drawNode(int x, int y, Color color) {
		//TODO: Fill me ...
	}

	/**
	 * Draws a Edge
	 * @param x
	 * @param y
	 * @param toX
	 * @param toY
	 * @param color
	 */
	private void drawEdge(int x, int y, int toX, int toY, Color color) {
		//TODO: Fill me ...
	}

	/**
	 * Draws a Text
	 * @param x
	 * @param y
	 * @param text
	 * @param color
	 */
	private void drawText(int x, int y, String text, Color color) {
		//TODO: Fill me ...
	}
	
	//---------- Getter/Setter-Methodes ---------->>>

	/**
	 * Gets the Element-Buffer
	 * @return
	 */
	public String[][] getElementBuffer() {
		return elementBuffer;
	}

	/**
	 * Sets the Element-Buffer
	 * @param buffer
	 */
	public void setElementBuffer(String[][] buffer) {
		this.elementBuffer = buffer;
		
	}
	
	//---------- Adder-Methodes ---------->>>
	
	/**
	 * Add a Node
	 * @param x
	 * @param y
	 * @param color
	 */
	public void addNode(int x, int y, Color color) {
		
	}
	
	/**
	 * Add a Edge
	 * @param x
	 * @param y
	 * @param toX
	 * @param toY
	 * @param color
	 */
	public void addEdge(int x, int y, int toX, int toY, Color color) {
		
	}
	
	/**
	 * Add a Text
	 * @param x
	 * @param y
	 * @param text
	 * @param color
	 */
	public void addText(int x, int y, String text, Color color) {
		
	}
}
