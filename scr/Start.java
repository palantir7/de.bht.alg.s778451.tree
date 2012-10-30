import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;

import paint.PaintPanel;

/**
 * @author Marcel Buchmann (s778451)
 * @email marcel.buchmann(- at -)googlemail.com
 * @version 1.0.0
 * @date 30.10.2012
 * @project de.bht.alg.s778451.tree
 * 
 */
class Start extends JFrame {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * global buffer of paint-elements lines 999, rows 10 (type, x, y, to x, to
	 * y, color, text)
	 */
	@SuppressWarnings("unused")
	private static String[] element = new String[10];
	@SuppressWarnings("rawtypes")
	private static List buffer;
	@SuppressWarnings("unused")
	private static JPanel mainPanel;
	private static JPanel paintPanel;
	private static JPanel iPanel;
	private static JButton button1;
	private JFileChooser fc;
	@SuppressWarnings("unused")
	private String file;
	private JPanel panel;

	/**
	 * 
	 */
	public Start() {
		initComponents();
		loadFile();		
	}

	/**
	 * main-Methode
	 * @param args not in use!!!
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new Start();
		        frame.setLayout(new BorderLayout());
				frame.setTitle("ALGO - TreeViewer");
		        frame.setSize(600, 350);
		        mainPanel = new JPanel();
			}
		});
	}

	/**
	 * initComponents()
	 */
	private void initComponents() {
		
		panel = new PaintPanel();
		paintPanel = panel;
		iPanel = new JPanel();

		panel.setBackground(new java.awt.Color(255, 255, 255));
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		button1 = new JButton("Submit");

		iPanel.setSize(50, 600);
		iPanel.setBackground(new java.awt.Color(255, 255, 255));
		iPanel.add(button1);

		this.getContentPane().add(panel, BorderLayout.CENTER);
		this.getContentPane().add(iPanel , BorderLayout.WEST);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		pack();
		
		((PaintPanel) paintPanel).updateArea();
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
	public void loadFile() {
		fc = new JFileChooser(new File(
				"D:\\SVN\\LAN-HDDs\\ALG\\de.bht.alg.s778451.tree\\scr\\dat"));
		int returnVal = fc.showOpenDialog(Start.this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();
			this.file = f.getAbsolutePath();
		}
	}
}
