/**
 * 
 */
package com.app.callout;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 * @author prashant.joshi
 *
 */
public class FilledLeftCallout extends Callout {
	
	private static final long serialVersionUID = 1L;
	private Icon icon = null; 
	boolean iconApplied; 

	public FilledLeftCallout(Color filledColor, Color foreColor) {
		this.setBackground(filledColor); 
		this.setForeground(Color.GREEN);
		this.setOpaque(true);
		this.setBorder(new FilledLeftCalloutBorder(filledColor));
		this.iconApplied = Boolean.FALSE;
	}
	
	@Override
	public void setText(String text) {
		StringBuffer txtToPrint = new StringBuffer("<html>");
		String txtArr[] = text.split("<br>");
		for (String sText : txtArr) {
			String txt = wordWrap(sText, 30, "Left");
			txtToPrint.append(txt).append("<br>");
		}
		txtToPrint.append("</html>");
		System.out.println("html check kr l bhai : " + txtToPrint);
		super.setText(txtToPrint.toString());
	}

	@Override
	public void displayIcon() {
		iconApplied = Boolean.TRUE;
		URL url = this.getClass().getClassLoader().getResource("student.gif");
		icon = new ImageIcon(url);
		this.setIcon(icon);
		this.setVerticalAlignment(SwingConstants.CENTER);
		this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		this.setIconTextGap(10);
	}
}
