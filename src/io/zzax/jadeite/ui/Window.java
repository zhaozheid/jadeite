package io.zzax.jadeite.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 *@author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class Window extends JFrame {
	private static final long serialVersionUID = -1894544342715903093L;
	
	private View contentView;
	
	/**
	 * Constructor for Window.
	 * @param contentView View
	 */
	public Window(View contentView) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.contentView = contentView;

		this.setResizable(false);
		this.setSize(800, 600);
		this.setLayout(null);
		
		this.add(contentView);
		this.setVisible(true);
	}
	
	/**
	 * Method setSize.
	 * @param d Dimension
	 */
	@Override
	public void setSize(Dimension d) {
		super.setSize(d);
		contentView.setSize(d);
	}
	
	/**
	 * Method setSize.
	 * @param width int
	 * @param height int
	 */
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		contentView.setSize(width, height);
	}
}

