import javax.swing.JPanel;

import paint.PaintPanel;

/**
 * 
 */

/**
 * @author Marcel Buchmann (s778451)
 * @email marcel.buchmann(- at -)googlemail.com
 * @version 1.0.0
 * @date 30.10.2012
 * @project de.bht.alg.s778451.tree
 * 
 */
public class Start {

	/**
	 * global buffer of paint-elements lines 999, rows 10 
	 * (type, name, x, y, to x, to y, color)
	 */
	private static String[][] buffer = new String[999][10];
	private static JPanel paintPanel = new PaintPanel();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		((PaintPanel) paintPanel).setElementBuffer(buffer);
		// TODO Auto-generated method stub
		// Starte MainGUI (Algo-Auswahl)
		
		// Übergib Zeichenfläche an MainGUI
	}

	/**
	 * Paint-Buffer Getter
	 * 
	 * @return buffer
	 */
	public static String[][] getBuffer() {
		return buffer;
	}

	/**
	 * Paint-Buffer Setter
	 * 
	 * @param buffer
	 *            2 dimensional String (String[999][10])
	 */
	public static void setBuffer(String[][] buffer) {
		Start.buffer = buffer;
	}

}
