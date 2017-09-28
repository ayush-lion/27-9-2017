/**
 * 
 */
package com.app.abacus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;

import com.app.abacus.panel.AbacusPanel;
import com.app.abacus.panel.exception.AbacusException;
import com.app.abacus.utils.AbacusAttributesLoader;
import com.app.abacus.utils.AbacusLogger;

/**
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 05-AUG-2017
 */
public class Abacus extends AbstractAbacus {
	
	private static final Integer DEFAULT_NUMBER_OF_RODS = 4;
	private static Integer DEFAULT_FRAME_VERTICAL_WIDTH = 10;
	private static Integer DEFAULT_FRAME_VERTICAL_HEIGHT = 10;
	private static Integer DEFAULT_BEAD_WIDTH = 50;
	private static Integer DEFAULT_BEAD_HEIGHT = 50;
	private static Integer DEFAULT_ROD_WIDTH = 10;
	private AbacusPanel panel;
	private boolean isPropertiesProvided;
	
	/* Holds Abacus Commands */
	private String[] commands = { "AddRod", "MinusRod", "Wait", "HighlightFrame", "HighlightRods", "HighlightBeam", "HighlightLowerBeads", "HighlightUpperBeads", "HighlightDots", "Reset", "Display" };
	private ArrayList<String> placevalues = new ArrayList<String>();
	
	
	/* Holds boolean values */
	private boolean doWeNeedToDisplayFingers;
	private boolean doWeNeedToDisplayFrame;
	
	/**
	 * Default Constructor
	 */
	public Abacus(AbacusPanel panel) throws AbacusException {
		this.panel = panel;
		this.numOfRods = panel.getNoOfrod();
		//this.setNumOfRods(panel.getNoOfrod());
		this.isPropertiesProvided = Boolean.FALSE;
		settingUpAbacusAttributes(null);
	}
	
	public Abacus(String attributesFileName, AbacusPanel panel) throws AbacusException {
		this.panel = panel;
		this.isPropertiesProvided = Boolean.TRUE;
		settingUpAbacusAttributes(attributesFileName);
	}
	
	
	/**
	 * Method is responsible to move earth bead towards up
	 */
	public final void moveEarthBeadToUp(int rodNum, int beadNum) throws AbacusException {
		preMoveEarthBeadUp(rodNum, beadNum);
		moveEarthBeadUp(rodNum, beadNum);
		postMoveEarthBeadUp(rodNum, beadNum);
	}

	/**
	 * Method is responsible to move earth bead towards down
	 */
	public final void moveEarthBeadToDown(int rodNum, int beadNum) throws AbacusException {
		preMoveEarthBeadDown(rodNum, beadNum);
		moveEarthBeadDown(rodNum, beadNum);
		postMoveEarthBeadDown(rodNum, beadNum);
	}

	/**
	 * Method is responsible to move earth bead towards down
	 */
	public final void moveHeavenBeadToDown(int rodNum) throws AbacusException {
		preMoveHeavenBeadDown(rodNum);
		moveHeavenBeadDown(rodNum);
		postMoveHeavenBeadDown(rodNum);
	}
	
	/**
	 * Method is responsible to move earth bead towards down
	 */
	public final void moveHeavenBeadToUp(int rodNum) throws AbacusException {
		preMoveHeavenBeadUp(rodNum);
		moveHeavenBeadUp(rodNum);
		postMoveHeavenBeadUp(rodNum);
	}
	
	/**
	 * Method is responsible to highlight frame
	 */
	public void highLightFrame(Graphics g) {
		if(canWeDisplayFrame())
			frame.highlight(g);
	}
	
	/**
	 * Method is responsible to highlight Rod
	 */
	public void highLightRods(Graphics g) {
		for (Rod rod : rods) {
			rod.highlight(g);
		}
	}
	
	/**
	 * Method is responsible to highlight Rod
	 */
	public void highLightRod(int rodNum, Graphics g) throws AbacusException {
		if(rodNum > numOfRods || rodNum < 1) {
			throw (new AbacusException(" Rod Number should not be increased by ==> " + numOfRods));
		}
		rods[rodNum - 1 ].highlight(g);
	}
	
	/**
	 * Method is responsible to highlight Beam
	 */
	public void highLightBeam(Graphics g) {
		beam.highlight(g);
	}
	
	/**
	 * Method is responsible to highlight Beam
	 */
	public void highLightBeads(Graphics g) {
		for (int i = 0; i < numOfRods; i++) {
			for(int j = 0; j < 5; j++) {
				beads[i][j].highlight(g);
			}
		}
	}
	
	/**
	 * Method is responsible to highlight Beam
	 */
	public void highLightLowerBeads(Graphics g) {
		for (int i = 0; i < numOfRods; i++) {
			for(int j = 0; j < 4; j++) {
				beads[i][j].highlight(g);
			}
		}
	}
	
	/**
	 * Method is responsible to highlight Beam
	 */
	public void highLightUpperBeads(Graphics g) {
		for (int i = 0; i < numOfRods; i++) {
			beads[i][4].highlight(g);
		}
	}
	
	/**
	 * Method is responsible to highlight Rod
	 */
	public void highLightRowBead(int rodNum, Graphics g) throws AbacusException {
		if(rodNum > numOfRods || rodNum < 1) {
			throw (new AbacusException(" Rod Number should not be increased by ==> " + numOfRods));
		}
		for(int i = 0; i < 5; i++) {
			beads[rodNum - 1 ][i].highlight(g);
		}
	}
	
	/**
	 * Method is responsible to highlight Rod
	 */
	public void highLightBead(int rodNum, int beadNum, Graphics g) throws AbacusException {
		if((rodNum > numOfRods || rodNum < 1)) {
			throw (new AbacusException(" Rod Number should in between 1 to " + numOfRods));
		}
		
		if((beadNum > 5 || beadNum < 1)) {
			throw (new AbacusException(" Bead Number should in between 1 to 5"));
		}
		
		for(int i = 0; i < 5; i++) {
			beads[rodNum - 1 ][i].highlight(g);
		}
	}
	
	/**
	 * Method is responsible to draw Abacus
	 */
	public void drawAbacus(Graphics g) {
		// Draw Frame
		if(canWeDisplayFrame())
			frame.drawFrame(g);
		
		// Draw Rod
		for (Rod rod : rods) {
			rod.drawRod(g);
		}
		
		// Draw Beam
		beam.drawBeam(g);
		
		// Drww Rods
		for (int i = 0; i < numOfRods; i++) {
			for(int j = 0; j < 5; j++) {
				beads[i][j].drawBead(g);
				//System.out.println(""+beads[i][j]);
				beads[i][j].drawFinger(g);
			}
		}
	}
	
	/**
	 * Method is responsible to setting up Abacus Attributes
	 */
	private void settingUpAbacusAttributes(String attributesFileName) throws AbacusException {
		Map<String, String> attributes = null;
		if(attributesFileName == null) {
			attributes = AbacusAttributesLoader.getAllPropertiesFromResource("abacus.properties");
		} else {
			attributes = AbacusAttributesLoader.getAllPropertiesFromFile(attributesFileName);
		}
		
		if(attributes == null || attributes.isEmpty()) {
			throw (new AbacusException("Not able to initialize Abacus. Please provide Abacus attributes...."));
		}
		
		/* Setting up logger */
		logger = new AbacusLogger(isTrueOrFalse(attributes, "loggingDebug")
									, isTrueOrFalse(attributes, "loogingWarning")
									, isTrueOrFalse(attributes, "doWeNeedToDisplayAbacusFrame"));
		
		/* Print abacus attributes file */
		logger.logInfo("attributesFileName => " + attributesFileName);
		
		/* Print Abacus Attributes */
		logger.logDebug(attributes);
		
		/* Setting up Abcus Attributes */
		//numOfRods = attributes.get("numOfRods") != null? Integer.valueOf(attributes.get("numOfRods")) : Abacus.DEFAULT_NUMBER_OF_RODS;
		numOfRods = getNumOfRods();
		doWeNeedToDisplayFrame = isTrueOrFalse(attributes, "doWeNeedToDisplayAbacusFrame");
		doWeNeedToDisplayFingers = isTrueOrFalse(attributes, "doWeNeedToDisplayFingers") ;
		
		/* Initialize Abacus */
		try {
			initializeFrame(attributes);
			initializeRods(attributes);
			initializeBeam(attributes);
			initializeBeads(attributes);
		} catch (IOException e) { e.printStackTrace(); throw (new AbacusException("Unable to initialize Abacus...")); }
		
	}
	
	/**
	 * Method is responsible to initialize Frame
	 */
	private void initializeFrame(Map<String, String> attributes) throws IOException {
		Boolean doWeNeedToDisplayTotalCount = isTrueOrFalse(attributes, "doWeNeedToDisplayTotalCount") ;
		Integer frameVerticalWidth = attributes.get("frameVerticalWidth") != null? Integer.valueOf(attributes.get("frameVerticalWidth")) : Abacus.DEFAULT_FRAME_VERTICAL_WIDTH;
		Integer frameHorizontalWidth = attributes.get("frameHorizontalWidth") != null? Integer.valueOf(attributes.get("frameHorizontalWidth")) : Abacus.DEFAULT_FRAME_VERTICAL_HEIGHT;
		
		frame = new Frame();
		frame.setFrameHorizontalWidth(frameHorizontalWidth.intValue());
		frame.setFrameVerticalWidth(frameVerticalWidth.intValue());
		frame.setDoWeNeedToDisplayTotalCount(doWeNeedToDisplayTotalCount.booleanValue());
		frame.setImage(getImage(attributes.get("frameImagePath")));
		
		frame.setPosX(0);
		frame.setPosY(0);
		frame.setShowlabel(false); 
		frame.setWidth(panel.getWidth());
		frame.setHeight(panel.getHeight());
	}
	
	
	
	
	/**
	 * Method is responsible to initialize rods
	 */
	
	
		private void hidelabels()
	{
		for(int i=0; i< numOfRods; i++) {
			rods[i].setDoWeNeedToDisplayRodNumbers(false);
			//rods[i].setRodWidth(rodWidth.intValue());
		}
	}
	
	private void initializeRods(Map<String, String> attributes) throws IOException {
		
		String[] placevalues = {"1","1","10","100","1K","10K","100K","1M","10M","100M","1B","10B","100B","1T","10T","100T"};		
		Image rodImagePath = getImage(attributes.get("rodImagePath"));
		Boolean doWeNeedToDisplayRodNumbers = isTrueOrFalse(attributes, "doWeNeedToDisplayRodNumbers");
		Integer rodWidth = attributes.get("rodWidth") != null? Integer.valueOf(attributes.get("rodWidth")) : Abacus.DEFAULT_ROD_WIDTH;
		
		/* Draw Rods */
		int minusFrameWidth = frame.getFrameVerticalWidth();
		if(!canWeDisplayFrame()) {
			minusFrameWidth = 0;
		}
		int rodSpace = (panel.getWidth() - minusFrameWidth) / (getNumOfRods() + 1);
		getLogger().logDebug("initializeRods Total Width ==> " + panel.getWidth() + " :: Caclulated Rod Space ==> " + rodSpace);
		
		if(numOfRods==0)
		{
			numOfRods=6;
		}
		
		rods = new Rod[numOfRods];
		for(int i=0; i< numOfRods; i++) {
			rods[i] = new Rod();
			rods[i].setPlacevalues(placevalues[getNumOfRods() - i]);
			rods[i].setHideplacevales(true);
			rods[i].setImage(rodImagePath);
			rods[i].setPosX(frame.getFrameVerticalWidth() + ((i + 1) * rodSpace));
			rods[i].setPosY(frame.getFrameHorizontalWidth());
			rods[i].setNumber(getNumOfRods() - i);
			rods[i].setnPosX(frame.getFrameVerticalWidth() + ((i + 1) * rodSpace));
			rods[i].setnPosY(panel.getHeight());
			rods[i].setDisplayNumbers(Boolean.TRUE);
			rods[i].setWidth(10);
			rods[i].setHeight(panel.getHeight() - (frame.getFrameHorizontalWidth() * 2));
			rods[i].setDoWeNeedToDisplayRodNumbers(doWeNeedToDisplayRodNumbers.booleanValue());
			rods[i].setRodWidth(rodWidth.intValue());
		}
	}
	
	/**
	 * Method is responsible to initialize Beam
	 */
	private void initializeBeam(Map<String, String> attributes) throws IOException {
		beam = new Beam();
		beam.setImage(getImage(attributes.get("rodDividerImagePath")));
		beam.setPosX(0);
		beam.setCounterx(getRods()[0].getPosX());
		int minusFrameWidth = frame.getFrameVerticalWidth();
		int rodSpace = (panel.getWidth() - minusFrameWidth) / (getNumOfRods() + 1);
		beam.setRodwidth(rodSpace);
		beam.setShowdots(true);
		beam.setNumofrods(numOfRods);
		beam.setBeamlabel(false);
		beam.setPosY(panel.getHeight() / 5);
		beam.setWidth(panel.getWidth());
		beam.setHeight(rods[0].getRodWidth());
	}
	
	/**
	 * Method is responsible to initial beads
	 */
	private void initializeBeads(Map<String, String> attributes) throws IOException {
		Image beadEarthImagePath = getImage(attributes.get("beadEarthImagePath"));
		Image beadHeavenImagePath = getImage(attributes.get("beadHeavenImagePath"));
		
		Image rightThumbFingerImagePath = getImage(attributes.get("rightThumbFingerImagePath"));
		Image rightIndexFingerImagePath = getImage(attributes.get("rightIndexFingerImagePath"));
		Image leftThumbFingerImagePath = getImage(attributes.get("leftThumbFingerImagePath"));
		Image leftIndexFingerImagePath = getImage(attributes.get("leftIndexFingerImagePath"));
	
		/* other images */
		
		Image leftThumbIndexImagePath = getImage(attributes.get("leftThumbIndexImagePath"));
		Image rightThumbIndexImagePath = getImage(attributes.get("leftIndexFingerImagePath"));
		Image leftPointerImagePath = getImage(attributes.get("leftPointerImagePath"));
		Image rightPointerImagePath = getImage(attributes.get("rightPointerImagePath"));
		
		Integer beadWidth = attributes.get("beadWidth") != null 
								? Integer.valueOf(attributes.get("beadWidth")) : Abacus.DEFAULT_BEAD_WIDTH;
		Integer beadHeight = attributes.get("beadHeight") != null
								? Integer.valueOf(attributes.get("beadHeight")) : Abacus.DEFAULT_BEAD_HEIGHT;
		int minusFrameWidth = frame.getFrameVerticalWidth();
		if(!canWeDisplayFrame()) {
			minusFrameWidth = 0;
		}
		int rodSpace = (panel.getWidth() - minusFrameWidth) / (getNumOfRods() + 1);
								
								
		/* Setting up beads */
		beads = new Bead[numOfRods][5];
		for (int i = 0; i < numOfRods; i++) {
			for (int j = 0; j < 5; j++) {
				beads[i][j] = new Bead();
				if(j != 4) {
					//beads[i][j].setDisplaybead(false);
					beads[i][j].setDisplayNumber(j+1);
					beads[i][j].setDisplaylabels(false);
					beads[i][j].setPosX(frame.getFrameVerticalWidth() + ((i + 1) * rodSpace) - (beadWidth / 2) + (getRods()[i].getRodWidth() / 2));
					beads[i][j].setEPosY(panel.getHeight() - (frame.getFrameHorizontalWidth()-15) - ( (j + 1) * beadHeight)-panel.getHeight()/25);
					int height = ((panel.getHeight() / 5) + getRods()[i].getRodWidth()) + ((3 - j) * beadHeight);
					beads[i][j].setHPosY(height);
				} else {
					beads[i][j].setDisplayNumber(j+1);
					//beads[i][j].setDisplaybead(true);
					beads[i][j].setDisplaylabels(false);
					beads[i][j].setPosX(frame.getFrameVerticalWidth() + ((i + 1) * rodSpace) - (beadWidth / 2) + (getRods()[i].getRodWidth() / 2));
					beads[i][j].setEPosY(frame.getFrameHorizontalWidth());
					int height = (panel.getHeight() / 5) - beadHeight;
					beads[i][j].setHPosY(height);
				}
				beads[i][j].setEarthImage(beadEarthImagePath);
				beads[i][j].setHeavenImage(beadHeavenImagePath);
				beads[i][j].setRightThumbFingerImagePath(rightThumbFingerImagePath);
				beads[i][j].setRightIndexFingerImagePath(rightIndexFingerImagePath);
				beads[i][j].setLeftIndexFingerImagePath(leftIndexFingerImagePath);
				beads[i][j].setLeftThumbFingerImagePath(leftThumbFingerImagePath);
				
				beads[i][j].setLeftThumbIndexImagePath(leftThumbIndexImagePath);
				beads[i][j].setRightThumbIndexImagePath(rightThumbIndexImagePath);
				beads[i][j].setLeftPointerImagePath(leftPointerImagePath);
				beads[i][j].setRightPointerImagePath(rightPointerImagePath);
				
				beads[i][j].setWidth(beadWidth.intValue());
				beads[i][j].setHeight(beadHeight.intValue());
				beads[i][j].setLeftIndex(Boolean.FALSE);
				beads[i][j].setLeftThumb(Boolean.FALSE);
				beads[i][j].setRightIndex(Boolean.FALSE);
				beads[i][j].setRightThumb(Boolean.FALSE);
				beads[i][j].setLeftThumbIndex(Boolean.FALSE);
				beads[i][j].setRightThumbIndex(Boolean.FALSE);			
			}
		}
	}
	
	/**
	 * Method is responsible to create the image
	 */
	private Image getImage(String fileName) throws IOException {
		Image image = null;
		if(!isPropertiesProvided) {
			image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(fileName));
		} else {
			image = ImageIO.read(new File(fileName));
		}
		return image;
	}
	
	/**
	 * Method is responsible to get the boolean property value
	 * from properties file and convert it to Boolean
	 */
	private Boolean isTrueOrFalse(Map<String, String> attributes, String properyName) {
		String val = attributes.get(properyName) != null? attributes.get(properyName) : "false";
		return val.equalsIgnoreCase("true") ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * @return the commands
	 */
	public String[] getCommands() {
		return commands;
	}
	
	/**
	 * @return the frameVerticalWidth
	 */
	public Integer getFrameVerticalWidth() {
		if(!canWeDisplayFrame())
			return 0;
		return frame.getFrameVerticalWidth();
	}
	/**
	 * @param frameVerticalWidth the frameVerticalWidth to set
	 */
	public void setFrameVerticalWidth(Integer frameVerticalWidth) {
		frame.setFrameVerticalWidth(frameVerticalWidth);
	}
	/**
	 * @return the frameHorizontalWidth
	 */
	public Integer getFrameHorizontalWidth() {
		if(!canWeDisplayFrame())
			return 0;
		return frame.getFrameHorizontalWidth();
	}
	/**
	 * @param frameHorizontalWidth the frameHorizontalWidth to set
	 */
	public void setFrameHorizontalWidth(Integer frameHorizontalWidth) {
		frame.setFrameHorizontalWidth(frameHorizontalWidth);
	}
	
	/**
	 * @return the doWeNeedToDisplayFingers
	 */
	public boolean canWeNeedToDisplayFingers() {
		return doWeNeedToDisplayFingers;
	}

	/**
	 * @param doWeNeedToDisplayFingers the doWeNeedToDisplayFingers to set
	 */
	public void setDoWeNeedToDisplayFingers(boolean doWeNeedToDisplayFingers) {
		this.doWeNeedToDisplayFingers = doWeNeedToDisplayFingers;
	}
	
	/**
	 * @return the doWeNeedToDisplayFrame
	 */
	public boolean canWeDisplayFrame() {
		return doWeNeedToDisplayFrame;
	}

	/**
	 * @param doWeNeedToDisplayFrame the doWeNeedToDisplayFrame to set
	 */
	public void setDoWeNeedToDisplayFrame(boolean doWeNeedToDisplayFrame) {
		this.doWeNeedToDisplayFrame = doWeNeedToDisplayFrame;
	}
	
}
