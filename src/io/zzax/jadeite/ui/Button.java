package io.zzax.jadeite.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;

/**
 * A Parallel Class, which represents a button.
 *
 * @author Zhe Zhao
 *
 * @version $Revision: 1.0 $
 */
public class Button extends JButton {
	private static final long serialVersionUID = -3246005973006189406L;

	public Button() {
		super();
		this.initFont();
	}

	/**
	 * Constructor for Button.
	 * @param text String
	 */
	public Button(String text) {
		super(text);
		this.initFont();
	}

	private void initFont() {
		this.setFont(new Font("Verdana", 0, 14));
	}

	/**
	 * Method setFontSize.
	 *
	 * @param fontSize int
	 */
	public void setFontSize(int fontSize){
		this.setFont(new Font("Verdana", 0, fontSize));
	}
	
	/*
	 * Mark - Basic - Properties
	 */
	
	private String title;
	private String subtitle;
	
	/*
	 * Mark - Basic - Methods
	 */
	
	private void updateText(){
		super.setText(getText()); 
	}
	
	/*
	 * Mark - Basic - Getters & Setters
	 */

	
	/**
	 * Getter for property <code>title</code>
	 * 
	 * @return String
     */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Setter for property <code>title</code>
	 * @param title String
	 */
	public void setTitle(String title) {
		this.title = title;
		updateText();
	}
	
	/**
	 * Getter for property <code>subtitle</code>	
	 * @return String
     */
	public String getSubtitle() {
		return subtitle;
	}
	
	/**
	 * Setter for property <code>subtitle</code>
	 * 
	 * @param subtitle String
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
		updateText();
	}
	
	/*
	 * Mark - Overlap - Methods
	 */
	
	/**
	 * This method should be no longer called. Use <code>setTitle</code> instead.
	 *  
	 * 
	 * @param text String	
	 * @see #setTitle(String)
     */
	@Override
	public void setText(String text) {
		this.setTitle(text);
	}
	
	/**
	 * This method should be no longer called. Use <code>getTitle</code> instead.
	 *  
	 * 
	 * @deprecated since <unknown>	
	 * @return String 
     * @see #getTitle()
     */
	@Override
	public String getText() {
		if (subtitle == null){
			return title;
		} else {
			return title + " " + subtitle;
		}
	}

	/**
	 * Method paint.
	 * @param g Graphics
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D graphics2d = (Graphics2D)g;
		graphics2d.setFont(this.getFont());
		boolean isPressed = this.getModel().isPressed();
		boolean enabled = this.isEnabled();

		Color colorTint = enabled ? Theme.tintColorEnabled : Theme.tintColorDisabled;
		Color colorBack = Theme.backgroundColor1;

		if (enabled && isPressed) {
			graphics2d.setColor(colorTint);
			graphics2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		} else {
			graphics2d.setColor(colorBack);
			graphics2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			graphics2d.setColor(colorTint);
			graphics2d.drawRect(0, 0, this.getWidth(), this.getHeight());
		}

		FontMetrics metrics = graphics2d.getFontMetrics(this.getFont());

		int height = metrics.getHeight();
		int titleWidth = metrics.stringWidth(title);
		float y = (float)((this.getHeight() - height ) / 2.0f) + metrics.getAscent();

		graphics2d.setColor(isPressed ? colorBack : colorTint);
		graphics2d.drawString(title, (float)((this.getWidth() - titleWidth ) / 2.0f), y);
	}
}
