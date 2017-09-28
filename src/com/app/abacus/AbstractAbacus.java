/**
 * 
 */
package com.app.abacus;

import com.app.abacus.panel.exception.AbacusException;
import com.app.abacus.utils.AbacusLogger;

/**
 * @author prashant.joshi
 *
 */
public abstract class AbstractAbacus {

	/* Holds Abacus Customized Integer Parameters */
	protected Integer numOfRods;
	
	/* Holds Custom Classes */
	protected AbacusLogger logger;
	protected Bead[][] beads;
	protected Rod[] rods;
	protected Frame frame;
	protected Beam beam;

	/* Move Earth Bead Up */
	public void preMoveEarthBeadUp(int rodNum, int beadNum) throws AbacusException {
		if(rodNum > numOfRods || rodNum < 1 || beadNum < 1 && beadNum > 5) {
			throw (new AbacusException("Input is not valid"));
		}
	}
	public void postMoveEarthBeadUp(int rodNum, int beadNum){}
	public void moveEarthBeadUp(int rodNum, int beadNum) {
		for(int i = beadNum - 1; i < 4; i++)
			beads[numOfRods - rodNum][i].setSwitchable(true);
	}
	
	/* Move Earth Bead Down */
	public void preMoveEarthBeadDown(int rodNum, int beadNum) throws AbacusException {
		if(rodNum > numOfRods || rodNum < 1 || beadNum < 1 && beadNum > 5) {
			throw (new AbacusException("Input is not valid"));
		}
	}
	public void postMoveEarthBeadDown(int rodNum, int beadNum){}
	public void moveEarthBeadDown(int rodNum, int beadNum){
		for(int i = beadNum - 1; i >= 0; i--)
			beads[numOfRods - rodNum][i].setSwitchable(false);
	}
	
	
	/* Move Heaven Bead Up */
	public void preMoveHeavenBeadUp(int rodNum) throws AbacusException {
		if(rodNum > numOfRods || rodNum < 1) {
			throw (new AbacusException("Input is not valid"));
		}
	}
	public void postMoveHeavenBeadUp(int rodNum){}
	public void moveHeavenBeadUp(int rodNum){
		beads[numOfRods - rodNum][4].setSwitchable(true);
	}
	
	
	/* Move Heaven Bead Down */
	public void preMoveHeavenBeadDown(int rodNum) throws AbacusException {
		if(rodNum > numOfRods || rodNum < 1) {
			throw (new AbacusException("Input is not valid"));
		}
	}
	public void postMoveHeavenBeadDown(int rodNum){}
	public void moveHeavenBeadDown(int rodNum){
		beads[numOfRods - rodNum][4].setSwitchable(false);
	}
	
	/* Display Finger */
	public void displayFinger(int rodNum, int beadNum, boolean isOnOrOff, String finger) throws AbacusException {
		if(rodNum > numOfRods || rodNum < 1 || beadNum < 1 && beadNum > 5) {
			throw (new AbacusException("Input is not valid"));
		}
		beads[numOfRods - rodNum][beadNum - 1].setDisplayFingerImage(isOnOrOff);
		 
		switch (finger) {
			case Finger.RIGHT_INDEX:
				beads[numOfRods - rodNum][beadNum - 1].setRightIndex(isOnOrOff);
				break;
			case Finger.RIGHT_THUMB:
				beads[numOfRods - rodNum][beadNum - 1].setRightThumb(isOnOrOff);
				break;
			case Finger.LEFT_INDEX:
				beads[numOfRods - rodNum][beadNum - 1].setLeftIndex(isOnOrOff);
				break;
			case Finger.LEFT_THUMB:
				beads[numOfRods - rodNum][beadNum - 1].setLeftThumb(isOnOrOff);
				break;
			case Finger.LEFT_THUMB_INDEX:
				beads[numOfRods - rodNum][beadNum - 1].setLeftThumbIndex(isOnOrOff);
				break;
			case Finger.RIGHT_THUMB_INDEX:
				beads[numOfRods - rodNum][beadNum - 1].setRightThumbIndex(isOnOrOff);
				break;
			case Finger.LEFT_POINTER:
				beads[numOfRods - rodNum][beadNum - 1].setLeftThumbIndex(isOnOrOff);
				break;
			case Finger.RIGHT_POINTER:
				beads[numOfRods - rodNum][beadNum - 1].setRightThumbIndex(isOnOrOff);
				break;	
		}
	}
	
	/**
	 * @return the numOfRods
	 */
	public Integer getNumOfRods() {
		return numOfRods;
	}
	/**
	 * @param numOfRods the numOfRods to set
	 */
	public void setNumOfRods(Integer numOfRods) {
		this.numOfRods = numOfRods;
	}
	/**
	 * @return the logger
	 */
	public AbacusLogger getLogger() {
		return logger;
	}
	/**
	 * @param logger the logger to set
	 */
	public void setLogger(AbacusLogger logger) {
		this.logger = logger;
	}
	/**
	 * @return the beads
	 */
	public Bead[][] getBeads() {
		return beads;
	}
	/**
	 * @param beads the beads to set
	 */
	public void setBeads(Bead[][] beads) {
		this.beads = beads;
	}
	/**
	 * @return the rods
	 */
	public Rod[] getRods() {
		return rods;
	}
	/**
	 * @param rods the rods to set
	 */
	public void setRods(Rod[] rods) {
		this.rods = rods;
	}
	/**
	 * @return the frame
	 */
	public Frame getFrame() {
		return frame;
	}
	/**
	 * @param frame the frame to set
	 */
	public void setFrame(Frame frame) {
		this.frame = frame;
	}
	/**
	 * @return the beam
	 */
	public Beam getBeam() {
		return beam;
	}
	/**
	 * @param beam the beam to set
	 */
	public void setBeam(Beam beam) {
		this.beam = beam;
	}
}
