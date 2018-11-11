package io.zzax.jadeite.ui;

import java.awt.Graphics;

import javax.swing.JTextField;

/**
 * A Parallel Class, which represents a text field.
 *
 * @author Zhe Zhao
 *
 * @version $Revision: 1.0 $
 */
public class TextField extends JTextField{
	
	public TextField() {
		super();
		this.setBackground(Theme.backgroundColor1);
		this.setForeground(Theme.tintColorEnabled);
		this.setSelectionColor(Theme.tintColorEnabled);
	}
	
	/**
	 * Method paint.
	 * @param g Graphics
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Theme.tintColorEnabled);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
	}
}
