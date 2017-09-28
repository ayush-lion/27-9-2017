/**
 * 
 */
package com.app.abacus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 10-Aug-2017
 */
public class Rod {

	private int posX;
	private int posY;
	private int nPosX;
	private int nPosY;
	private int number;
	private int width;
	private int height;
	private int rodWidth;
	private Image image;
	private boolean switchable;
	private boolean displayNumbers;
	private boolean doWeNeedToDisplayRodNumbers;
	private String placevalues;
	private boolean hideplacevales;
	/**
	 * @return the hideplacevales
	 */ 
	public boolean isHideplacevales() {
		return hideplacevales;
	}

	/**
	 * @param hideplacevales the hideplacevales to set
	 */
	public void setHideplacevales(boolean hideplacevales) {
		this.hideplacevales = hideplacevales;
	}

	public Rod() {}
	
	public Rod(int posX, int posY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		setSwitchable(Boolean.FALSE);
	}
	
	public void drawRod(Graphics g) {
		if(!isSwitchable())
			g.drawImage(image, this.posX, this.posY, this.getWidth(), this.getHeight(), null);
		
		if(isDisplayNumbers()) {
			g.setColor(Color.GREEN);
			g.setFont(new Font("TimesRoman", Font.BOLD,15)); 
			g.drawString(String.valueOf(number), nPosX, nPosY);
		}
		if(isHideplacevales())
		{
			g.setColor(Color.black);
			g.setFont(new Font("TimesRoman", Font.BOLD,15)); 
			g.drawString(getPlacevalues(), nPosX, nPosY);
		}
	}
	
	/**
	 * @return the placevalues
	 */
	public String getPlacevalues() {
		return placevalues;
	}

	/**
	 * @param placevalues the placevalues to set
	 */
	public void setPlacevalues(String placevalues) {
		this.placevalues = placevalues;
	}

	public void highlight(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(posX, posY, width, height);
	}
	
	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}
	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}
	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}
	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return the doWeNeedToDisplayRodNumbers
	 */
	public boolean canWeDisplayRodNumbers() {
		return doWeNeedToDisplayRodNumbers;
	}

	/**
	 * @param doWeNeedToDisplayRodNumbers the doWeNeedToDisplayRodNumbers to set
	 */
	public void setDoWeNeedToDisplayRodNumbers(boolean doWeNeedToDisplayRodNumbers) {
		this.doWeNeedToDisplayRodNumbers = doWeNeedToDisplayRodNumbers;
	}

	/**
	 * @return the rodWidth
	 */
	public int getRodWidth() {
		return rodWidth;
	}

	/**
	 * @param rodWidth the rodWidth to set
	 */
	public void setRodWidth(int rodWidth) {
		this.rodWidth = rodWidth;
	}

	/**
	 * @return the switchable
	 */
	public boolean isSwitchable() {
		return switchable;
	}

	/**
	 * @param switchable the switchable to set
	 */
	public void setSwitchable(boolean switchable) {
		this.switchable = switchable;
	}

	/**
	 * @return the nPosX
	 */
	public int getnPosX() {
		return nPosX;
	}

	/**
	 * @param nPosX the nPosX to set
	 */
	public void setnPosX(int nPosX) {
		this.nPosX = nPosX;
	}

	/**
	 * @return the nPosY
	 */
	public int getnPosY() {
		return nPosY;
	}

	/**
	 * @param nPosY the nPosY to set
	 */
	public void setnPosY(int nPosY) {
		this.nPosY = nPosY;
	}

	/**
	 * @return the displayNumbers
	 */
	public boolean isDisplayNumbers() {
		return displayNumbers;
	}

	/**
	 * @param displayNumbers the displayNumbers to set 
	 */
	public void setDisplayNumbers(boolean displayNumbers) {
		this.displayNumbers = displayNumbers;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
}
