/**
 * 
 */
package com.app.abacus.panel;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.app.abacus.Abacus;
import com.app.abacus.Bead;
import com.app.abacus.BlinkBead;
import com.app.abacus.BlinkBeam;
import com.app.abacus.BlinkFrame;
import com.app.abacus.BlinkRod;
import com.app.abacus.Rod;
import com.app.abacus.panel.exception.AbacusException;

/**
 * Class is responsible to create and handle Abacus Operations
 * 
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 04-Aug-2017
 */
public class AbacusPanel extends JPanel {
	
	private static final long serialVersionUID = -2312019179090620771L;
	private Abacus abacus;
	private boolean doWeNeedToHighlightFrame = Boolean.FALSE;
	private boolean doWeNeedToHighlightRods = Boolean.FALSE;
	private boolean doWeNeedToHighlightSpecificRods = Boolean.FALSE;
	private boolean doWeNeedToHighlightBeads = Boolean.FALSE;
	private boolean doWeNeedToHighlightLowerBeads = Boolean.FALSE;
	private boolean doWeNeedToHighlightUpperBeads = Boolean.FALSE;
	private boolean doWeNeedToHighlightSpecificBeads = Boolean.FALSE;
	private boolean doWeNeedToHighlightDivider = Boolean.FALSE;
	private boolean doWeNeedToDisplayCount = Boolean.FALSE;
	
	private String abacusAttributesFileName = null;
	private int rodNumber;
	private int beadNumber;
	private String displayCount;
	private BlinkBead bBlink = null;
	private BlinkRod rBlink = null;
	private BlinkFrame fBlink = null;
	private BlinkBeam beamBlink = null;
	private int noOfrod;
	
	public int getNoOfrod() {
		return noOfrod;
	}

	public void setNoOfrod(int noOfrod) {
		this.noOfrod = noOfrod;
	}

	
	public AbacusPanel(String abacusAttributesFileName)  {
		this.abacusAttributesFileName = abacusAttributesFileName;
	}
	
	public AbacusPanel() {}
	
	/**
	 * Method is responsible to initialize Abacus
	 */
	public void initializeAbacus() throws AbacusException {
		if(abacusAttributesFileName != null) {
			abacus = new Abacus(abacusAttributesFileName, this);
		} else {
			abacus = new Abacus(this);
		}
		
		this.repaint();
	}
	
	/**
	 * Method is responsible to move Earth bead up
	 */
	public void moveEarthBeadUp(int rodNum, int beadNum, String finger) throws AbacusException {
		getAbacus().getLogger().logDebug("Inside moveEarthBeadUp....");
		
		//Display Finger
		displayFinger(rodNum, beadNum, finger);
		
		// Move Earth Bead Up
		getAbacus().getLogger().logDebug("Move the Earth Bead Up....");
		SwingUtilities.invokeLater(new Runnable() {
		   public void run() {
		      try {
		         getAbacus().moveEarthBeadToUp(rodNum, beadNum);
		      } catch ( Exception e ) {}
		      getCurrentInstance().repaint();
		   }
		});
		
		// Hide Finger
		hideFinger(rodNum, beadNum, finger);
	}
	
	/**
	 * Method is responsible to move bead up together
	 */
	public void moveBeadUpTogether(int rodNum, int[] beadNum, String bead1finger, String bead2Finger) throws AbacusException {
		getAbacus().getLogger().logDebug("Inside moveBeadUpTogether....");
		
		//Display Finger
		displayFingerTogether(rodNum, beadNum[0], beadNum[1], bead1finger, bead2Finger);
		
		// Move Earth Bead Up
		getAbacus().getLogger().logDebug("Move the Bead Up Together....");
		SwingUtilities.invokeLater(new Runnable() {
		   public void run() {
		      try {
		    	 if(beadNum[0] == 5)
		    		 getAbacus().moveHeavenBeadToUp(rodNum);
		    	 else
		    		 getAbacus().moveEarthBeadToUp(rodNum, beadNum[0]);
		    	 
		    	 if(beadNum[1] == 5)
		    		 getAbacus().moveHeavenBeadToUp(rodNum);
		    	 else 
		    		 getAbacus().moveEarthBeadToUp(rodNum, beadNum[1]);
		      } catch ( Exception e ) {}
		      getCurrentInstance().repaint();
		   }
		});
		
		// Hide Finger
		hideFingerTogether(rodNum, beadNum[0], beadNum[1], bead1finger, bead2Finger);
	}
	
	/**
	 * Method is responsible to move Earth bead down
	 */
	public void moveEarthBeadDown(int rodNum, int beadNum, String finger) throws AbacusException {
		getAbacus().getLogger().logDebug("Inside moveEarthBeadDown....");
		
		//Display Finger
		displayFinger(rodNum, beadNum, finger);
		
		// Move Earth Bead Up
		getAbacus().getLogger().logDebug("Move the Earth Bead Down....");
		SwingUtilities.invokeLater(new Runnable() {
		   public void run() {
		      try {
		    	  getAbacus().moveEarthBeadToDown(rodNum, beadNum);
		      } catch ( Exception e ) {}
		      getCurrentInstance().repaint();
		   }
		});
		
		// Hide Finger
		hideFinger(rodNum, beadNum, finger);
	}
	
	/**
	 * Method is responsible to move bead up together
	 */
	public void moveBeadDownTogether(int rodNum, int[] beadNum, String bead1finger, String bead2Finger) throws AbacusException {
		getAbacus().getLogger().logDebug("Inside moveBeadDownTogether....");
		
		//Display Finger
		displayFingerTogether(rodNum, beadNum[0], beadNum[1], bead1finger, bead2Finger);
		
		// Move Earth Bead Up
		getAbacus().getLogger().logDebug("Move the Bead Down Together....");
		SwingUtilities.invokeLater(new Runnable() {
		   public void run() {
		      try {
		    	 if(beadNum[0] == 5)
		    		 getAbacus().moveHeavenBeadToDown(rodNum);
		    	 else
		    		 getAbacus().moveEarthBeadToDown(rodNum, beadNum[0]);
		    	 
		    	 if(beadNum[1] == 5)
		    		 getAbacus().moveHeavenBeadToDown(rodNum);
		    	 else 
		    		 getAbacus().moveEarthBeadToDown(rodNum, beadNum[1]);
		      } catch ( Exception e ) {}
		      getCurrentInstance().repaint();
		   }
		});
		
		// Hide Finger
		hideFingerTogether(rodNum, beadNum[0], beadNum[1], bead1finger, bead2Finger);
	}
	
	/**
	 * Method is responsible to heaven bead up
	 */
	public void moveHeavenBeadUp(int rodNum, String finger) throws AbacusException {
		getAbacus().getLogger().logDebug("Inside moveHeavenBeadUp....");
		
		//Display Finger
		displayFinger(rodNum, 5, finger);
		
		// Move Earth Bead Up
		getAbacus().getLogger().logDebug("Move the Heaven Bead Up....");
		SwingUtilities.invokeLater(new Runnable() {
		   public void run() {
		      try {
		    	  getAbacus().moveHeavenBeadToUp(rodNum);
		      } catch ( Exception e ) {}
		      getCurrentInstance().repaint();
		   }
		});
		
		// Hide Finger
		hideFinger(rodNum, 5, finger);
	}
	
	/**
	 * Method is responsible to move heaven bead down
	 * @param rodNum
	 */
	public void moveHeavenBeadDown(int rodNum, String finger) throws AbacusException {
		getAbacus().getLogger().logDebug("Inside moveHeavenBeadDown....");
		
		//Display Finger
		displayFinger(rodNum, 5, finger);
		
		// Move Earth Bead Up
		getAbacus().getLogger().logDebug("Move the Heaven Bead Down....");
		SwingUtilities.invokeLater(new Runnable() {
		   public void run() {
		      try {
		    	  getAbacus().moveHeavenBeadToDown(rodNum);
		      } catch ( Exception e ) {}
		      getCurrentInstance().repaint();
		   }
		});
		
		// Hide Finger
		hideFinger(rodNum, 5, finger);
	}
	
	/**
	 * Method is responsible to hightlight Frame
	 */
	public void highlightFrame() {
		switchBoolean("doWeNeedToHighlightFrame");
		this.repaint();
	}
	
	/**
	 * Method is responsible to hightlight Frame
	 */
	private void highlightFrame(Graphics g) {
		if(getAbacus().canWeDisplayFrame()) {
			getAbacus().getFrame().highlight(g);
		}
	}
	
	/**
	 * Method is responsible to highlight rods
	 */
	public void highlightRods() {
		switchBoolean("doWeNeedToHighlightRods");
		this.repaint();
	}
	
	/**
	 * Method is responsible to hightlight Rods
	 */
	private void highlightRods(Graphics g) {
		for(int i = 0; i < getAbacus().getNumOfRods(); i++) {
			getAbacus().getRods()[i].highlight(g);
		}
	}
	
	/**
	 * Method is responsible to highlight Specific rod
	 */
	public void highlightSpecificRod(int rodNum) {
		switchBoolean("doWeNeedToHighlightSpecificRods");
		this.rodNumber = rodNum;
		this.repaint();
	} 
	
	/**
	 * Method is responsible to hightlight Specific Rod
	 */
	private void highlightSpecificRod(Graphics g) {
		if(rodNumber < 1 || rodNumber > getAbacus().getNumOfRods()) {
			return;
		}
		getAbacus().getRods()[getAbacus().getNumOfRods() - rodNumber].highlight(g);
	}
	
	/**
	 * Method is responsible to highlight Lower Beads
	 */
	public void highlightLowerBeads() {
		switchBoolean("doWeNeedToHighlightLowerBeads");
		this.repaint();
	}
	
	/**
	 * Method is responsible to hightlight Lower Beads
	 */
	private void highlightLowerBeads(Graphics g) {
		for(int i = 0; i < getAbacus().getNumOfRods(); i++) {
			for(int j = 0; j < 4; j++) {
				getAbacus().getBeads()[i][j].highlight(g);
			}
		}
	}
	
	public void showPlaceValues()
	{
		for(int i=0;i<getAbacus().getNumOfRods();i++)
		{
			getAbacus().getRods()[i].setHideplacevales(true);
		}
		this.repaint();
	}
	
	public void hideRodLabels() 
	{
		for(int i=0; i< getAbacus().getNumOfRods();i++)
		{
			getAbacus().getRods()[i].setDisplayNumbers(false);
		}
		this.repaint();
	}
	
	public void showRodLabels()
	{
		for(int i=0;i<getAbacus().getRods().length;i++)
		{
			getAbacus().getRods()[i].setDisplayNumbers(true);
		}
		this.repaint();
	}
	
	public void showBeadLabels()
	{
		Bead[][] beads = getAbacus().getBeads();
		for(int row = 0; row < getAbacus().getNumOfRods(); row ++) {
			for(int beadNum = 0; beadNum < 5; beadNum++) {
				beads[row][beadNum].setDisplaylabels(true);
			}
		}
		this.repaint();
	}
	
	public void hideBeadLabels()
	{
		Bead[][] beads = getAbacus().getBeads();
		for(int row = 0; row < getAbacus().getNumOfRods(); row ++) {
			for(int beadNum = 0; beadNum < 5; beadNum++) {
				beads[row][beadNum].setDisplaylabels(false);
			}
		}
		this.repaint();
	}
	
	public void hideUpperBeadsLabels()
	{
		for(int i = 0; i < getAbacus().getNumOfRods(); i++) {
			getAbacus().getBeads()[i][4].setDisplaylabels(false);
		}
		this.repaint();
	}
	public void hideLoweBeadsLabels()
	{
		for(int i = 0; i < getAbacus().getNumOfRods(); i++) {
			for(int j = 0; j < 4; j++) {
				getAbacus().getBeads()[i][j].setDisplaylabels(false);
			}
		}
		this.repaint();
	}
	
	public void showUpperBeadsLabels()
	{
		for(int i = 0; i < getAbacus().getNumOfRods(); i++) {
			getAbacus().getBeads()[i][4].setDisplaylabels(true);
		}
		this.repaint();
	}
	public void showLowerBeadsLabels()
	{
		for(int i = 0; i < getAbacus().getNumOfRods(); i++) {
			for(int j = 0; j < 4; j++) {
				getAbacus().getBeads()[i][j].setDisplaylabels(true);
			}
		}
		this.repaint();
	}
	
	/**
	 * Method is responsible to highlight Upper Beads
	 */
	public void highlightUpperBeads() {
		switchBoolean("doWeNeedToHighlightUpperBeads");
		this.repaint();
	}
	
	/**
	 * Method is responsible to hightlight Upper Beads
	 */
	private void highlightUpperBeads(Graphics g) {
		for(int i = 0; i < getAbacus().getNumOfRods(); i++) {
			getAbacus().getBeads()[i][4].highlight(g);
		}
	}
	
	
	/**
	 * Method is responsible to highlight rods
	 */
	public void highlightBeads() {
		switchBoolean("doWeNeedToHighlightBeads");
		this.repaint();
	}
	
	/**
	 * Method is responsible to hightlight beads
	 */
	private void highlightBeads(Graphics g) {
		Bead[][] beads = getAbacus().getBeads();
		for(int row = 0; row < getAbacus().getNumOfRods(); row ++) {
			for(int beadNum = 0; beadNum < 5; beadNum++) {
				beads[row][beadNum].highlight(g);
			}
		}
	}
	
	/**
	 * Method is responsible to highlight Specific Bead
	 */
	public void highlightSpecificBead(int rodNum, int beadNum) {
		switchBoolean("doWeNeedToHighlightSpecificBeads");
		this.rodNumber = rodNum;
		this.beadNumber = beadNum;
		this.repaint();
	}
	
	/**
	 * Method is responsible to hightlight Specific Rod
	 */
	private void highlightSpecificBead(Graphics g) {
		if(  (rodNumber < 1 || rodNumber > getAbacus().getNumOfRods()) && (beadNumber < 1 || beadNumber > 5) ) {
			return;
		}
		getAbacus().getBeads()[getAbacus().getNumOfRods() - rodNumber][beadNumber - 1].highlight(g);
	}
	
	public void hidePlaceValues()
	{
		Rod[] rods = getAbacus().getRods();
		for(int i=0;i<rods.length;i++)
		{
			rods[i].setHideplacevales(false);
		}
		this.repaint();
	}
	
	/**
	 * Method is responsible to highlight rods
	 */
	public void highlightDivider() {
		switchBoolean("doWeNeedToHighlightDivider");
		this.repaint();
	}
	
	/**
	 * Method is responsible to highlight rods
	 */
	private void highlightDivider(Graphics g) {
		getAbacus().getBeam().highlight(g);
	}
	
	/**
	 * Method is responsible to highlight rods
	 */
	public void hideFrame(boolean isOnorOff) {
		resetAllBooleans();
		getAbacus().setDoWeNeedToDisplayFrame(isOnorOff);
		this.repaint();
	}
	
	public void hideFrameLabel()
	{
		getAbacus().getFrame().setShowlabel(false);
		this.repaint();
	}
	
	public void showFrameLabel()
	{
		getAbacus().getFrame().setShowlabel(true);
		this.repaint();
	}
	
	public void hideBeamLabel()
	{
		getAbacus().getBeam().setBeamlabel(false);
		this.repaint();
	}
	
	public void showBeamLabel()
	{
		getAbacus().getBeam().setBeamlabel(true);
		this.repaint();
	}
	
	public void showDots()
	{
		getAbacus().getBeam().setShowdots(true);
		this.repaint();
	}
	public void hideDots()
	{
		getAbacus().getBeam().setShowdots(false);
		this.repaint();
	}
	
	/**
	 * Method is responsible to highlight rods
	 */
	public void hideFingers(boolean isOnorOff) {
		resetAllBooleans();
		getAbacus().setDoWeNeedToDisplayFingers(isOnorOff);
		this.repaint();
	}
	
	/**
	 * Method is responsible to highlight rods
	 */
	public void showAbacus() {
		resetAllBooleans();
		resetAbacus();
		this.repaint();
	}
	
	/**
	 * Method is responsible to blink the bead
	 */
	public void startBlinkBead(int rodNum, int beadNum) {
		showAbacus();
		if(bBlink != null) {
			bBlink.stopBlink();
			bBlink = null;
		}
		
		if((rodNum < 1 || rodNum > getAbacus().getNumOfRods())
				&& (beadNum < 1 || beadNum > 5) ) {
			return;
		}
		
		Bead bead = getAbacus().getBeads()[getAbacus().getNumOfRods() - rodNum][beadNum - 1];
		bBlink = new BlinkBead(this, bead);
		bBlink.startBlink();
	}
	
	/**
	 * Method is responsible to stop the blinking bead
	 */
	public void stopBlinkBead() {
		bBlink.stopBlink();
		bBlink = null;
	}
	
	/**
	 * Method is responsible to blink the rod
	 */
	public void startBlinkRod(int rodNum) {
		showAbacus();
		rBlink = new BlinkRod(this, getAbacus().getRods()[getAbacus().getNumOfRods() - rodNum]);
		rBlink.startBlink();
	}
	
	/**
	 * Method is responsible to stop the blinking rod
	 */
	public void stopBlinkRod() {
		rBlink.stopBlink();
		rBlink = null;
	}
	
	/**
	 * Method is responsible to blink the rod
	 */
	public void startBlinkFrame() {
		showAbacus();
		fBlink = new BlinkFrame(this, getAbacus().getFrame());
		fBlink.startBlink();
	}
	
	/**
	 * Method is responsible to stop the blinking rod
	 */
	public void stopBlinkFrame() {
		fBlink.stopBlink();
		fBlink = null;
	}
	
	/**
	 * Method is responsible to blink the Beam
	 */
	public void startBlinkBeam() {
		showAbacus();
		beamBlink = new BlinkBeam(this, getAbacus().getBeam());
		beamBlink.startBlink();
	}
	
	/**
	 * Method is responsible to stop the blinking Beam
	 */
	public void stopBlinkBeam() {
		beamBlink.stopBlink();
		beamBlink = null;
	}
	
	/**
	 * Switch boolean to TRUE
	 */
	private void switchBoolean(String booleanName) {
		resetAllBooleans();
		switch (booleanName) {
			case "doWeNeedToHighlightFrame" :
				doWeNeedToHighlightFrame = Boolean.TRUE;
				break;
			case "doWeNeedToHighlightRods" :
				doWeNeedToHighlightRods = Boolean.TRUE;
				break;
			case "doWeNeedToHighlightSpecificRods" :
				doWeNeedToHighlightSpecificRods = Boolean.TRUE;
				break;
			case "doWeNeedToHighlightBeads" :
				doWeNeedToHighlightBeads = Boolean.TRUE;
				break;
			case "doWeNeedToHighlightLowerBeads" :
				doWeNeedToHighlightLowerBeads = Boolean.TRUE;
				break;
			case "doWeNeedToHighlightUpperBeads" :
				doWeNeedToHighlightUpperBeads = Boolean.TRUE;
				break;
			case "doWeNeedToHighlightSpecificBeads" :
				doWeNeedToHighlightSpecificBeads = Boolean.TRUE;
				break;
			case "doWeNeedToHighlightDivider" :
				doWeNeedToHighlightDivider = Boolean.TRUE;
				break;
			case "doWeNeedToDisplayCount" :
				doWeNeedToDisplayCount = Boolean.TRUE;
				break;
		}
	}
	
	/**
	 * Method is responsible to reset all booleans
	 */
	public void resetAllBooleans() {
		doWeNeedToHighlightFrame = Boolean.FALSE;
		doWeNeedToHighlightRods = Boolean.FALSE;
		doWeNeedToHighlightBeads = Boolean.FALSE;
		doWeNeedToHighlightLowerBeads = Boolean.FALSE;
		doWeNeedToHighlightUpperBeads = Boolean.FALSE;
		doWeNeedToHighlightSpecificBeads = Boolean.FALSE;
		doWeNeedToHighlightSpecificRods = Boolean.FALSE;
		doWeNeedToHighlightDivider = Boolean.FALSE;
		doWeNeedToDisplayCount = Boolean.FALSE;
		if(bBlink != null) {
			bBlink.stopBlink();
			bBlink = null;
		}
		if(rBlink != null) {
			rBlink.stopBlink();
			rBlink = null;
		}
		if(fBlink != null) {
			fBlink.stopBlink();
			fBlink = null;
		}
		if(beamBlink != null) {
			beamBlink.stopBlink();
			beamBlink = null;
		}
	}
	
	/**
	 * Reset Beads
	 */
	private void resetAbacus() {
		Bead[][] beads = getAbacus().getBeads();
		for(int i=0;i<getAbacus().getNumOfRods();i++) {
			for(int j=0;j<5;j++) {
				beads[i][j].setSwitchable(false);
			}
		}
	}
	
	/**
	 * Method is responsible to display Finger On Abacus
	 */
	private void displayFinger(int rodNum, int beadNum, String finger) throws AbacusException {
		// Display Finger
		if(getAbacus().canWeNeedToDisplayFingers()) {
			getAbacus().getLogger().logDebug("Display Finger....");
			getAbacus().displayFinger(rodNum, beadNum, true, finger);
			this.repaint();
			
			// Adding little delay
			SwingUtilities.invokeLater(new Runnable() {
			   public void run() {
			      try {
				     Thread.sleep(500);
				  } catch (Exception e) {}
			   }
			});
		}
	}
	
	/**
	 * Method is responsible to display Finger together On Abacus
	 */
	private void displayFingerTogether(int rodNum, int bead1, int bead2, String bead1Finger, String bead2Finger) throws AbacusException {
		// Display Finger
		if(getAbacus().canWeNeedToDisplayFingers()) {
			getAbacus().getLogger().logDebug("Display Finger....");
			getAbacus().displayFinger(rodNum, bead1, true, bead1Finger);
			getAbacus().displayFinger(rodNum, bead2, true, bead2Finger);
			this.repaint();
			
			// Adding little delay
			SwingUtilities.invokeLater(new Runnable() {
			   public void run() {
			      try {
				     Thread.sleep(500);
				  } catch (Exception e) {}
			   }
			});
		}
	}
	
	/**
	 * Method is responsible to hide the finger on Abacus
	 */
	private void hideFinger(int rodNum, int beadNum, String finger) throws AbacusException {
		ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	try {
					 getAbacus().displayFinger(rodNum, beadNum, false, finger);
			      } catch ( Exception e ) {}
			      getCurrentInstance().repaint();
            }
        };
        if(getAbacus().canWeNeedToDisplayFingers()) {
	        Timer time = new Timer(1000, taskPerformer);
	        time.start();
	        time.setRepeats(false);
        }
	}
	
	/**
	 * Method is responsible to hide the finger on Abacus
	 */
	private void hideFingerTogether(int rodNum, int bead1, int bead2, String bead1Finger, String bead2Finger) throws AbacusException {
		ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	try {
					 getAbacus().displayFinger(rodNum, bead1, false, bead1Finger);
					 getAbacus().displayFinger(rodNum, bead2, false, bead2Finger);
			      } catch ( Exception e ) {}
			      getCurrentInstance().repaint();
            }
        };
        if(getAbacus().canWeNeedToDisplayFingers()) {
	        Timer time = new Timer(1000, taskPerformer);
	        time.start();
	        time.setRepeats(false);
        }
	}
	
	/**
	 * Method is responsible to display count on Abacus
	 */
	public void displayCount(String count) {
		switchBoolean("doWeNeedToDisplayCount");
		displayCount = count;
		this.repaint();
	}
	
	/**
	 * Method is responsible to display count on Abacus
	 */
	private void displayCount(Graphics g) {
		getAbacus().getFrame().setNumber(displayCount);
		getAbacus().getFrame().drawCount(g);
	}
	
	@Override
	public void paint(Graphics g) {
		// Draw complete Abacus
		if(getAbacus() != null)
			getAbacus().drawAbacus(g);
		
		// High light Frame
		if(doWeNeedToHighlightFrame) {
			highlightFrame(g);
		} 
		
		// High light Rods
		if(doWeNeedToHighlightRods) {
			highlightRods(g);
		}
		
		// High light Specific Rod
		if(doWeNeedToHighlightSpecificRods) {
			highlightSpecificRod(g);
		}
		
		// High Light Beads
		if(doWeNeedToHighlightBeads) {
			highlightBeads(g);
		}
		
		// High Light Lower Beads
		if(doWeNeedToHighlightLowerBeads) {
			highlightLowerBeads(g);
		}
		
		// High Light Upper Beads
		if(doWeNeedToHighlightUpperBeads) {
			highlightUpperBeads(g);
		}
		
		// High light Specific Bead
		if(doWeNeedToHighlightSpecificBeads) {
			highlightSpecificBead(g);
		}
		
		// Highlight divider
		if(doWeNeedToHighlightDivider) {
			highlightDivider(g);
		}
		
		//Draw Count
		if(doWeNeedToDisplayCount) {
			displayCount(g);
		}
		
	}

	/**
	 * @return the abacus
	 */
	public Abacus getAbacus() {
		return abacus;
	}
	
	public AbacusPanel getCurrentInstance() {
		return this;
	}
	
	/**
	 * @return the commands
	 */
	public String[] getCommands() {
		return getAbacus().getCommands();
	}

	/**
	 * @param abacusAttributesFileName the abacusAttributesFileName to set
	 */
	public void setAbacusAttributesFileName(String abacusAttributesFileName) {
		this.abacusAttributesFileName = abacusAttributesFileName;
	}
}
