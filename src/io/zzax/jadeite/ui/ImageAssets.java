package io.zzax.jadeite.ui;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class ImageAssets{
	/**
	 * Method imageNamed.
	 * @param name String
	 * @return Image
     */
	public static Image imageNamed(String name){
		return new ImageIcon("images/" + name).getImage();
	}
}
