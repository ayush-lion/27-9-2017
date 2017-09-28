package com.app.integrated.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import com.app.abacus.Finger;
import com.app.abacus.panel.AbacusPanel;
import com.app.abacus.panel.exception.AbacusException;
import com.app.callouts.panel.MainPanel;

import com.app.instructions.beans.Action;
import com.app.sound.SpeechController;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Performer implements Runnable {

	LinkedHashMap<String, HashMap<String, List<Action>>> data;

	private Thread readerThread;
	MainPanel instructionPanel;
	AbacusPanel abacusPanel;
	private SpeechController playSound;
	private static final String VOICE = "kevin16";
	private VoiceManager vm = null;
	private Voice voice = null;
	private boolean isPlayRobotics;

	/**
	 * @return the isPlayRobotics
	 */
	public boolean isPlayRobotics() {
		return isPlayRobotics;
	}

	/**
	 * @param isPlayRobotics
	 *            the isPlayRobotics to set
	 */
	public void setPlayRobotics(boolean isPlayRobotics) {
		this.isPlayRobotics = isPlayRobotics;
	}

	/**
	 * @return the isPlayNatural
	 */
	public boolean isPlayNatural() {
		return isPlayNatural;
	}

	/**
	 * @param isPlayNatural
	 *            the isPlayNatural to set
	 */
	public void setPlayNatural(boolean isPlayNatural) {
		this.isPlayNatural = isPlayNatural;
	}

	private boolean isPlayNatural;

	public Performer() {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		isPlayRobotics = false;
		isPlayNatural = false;

	}

	/**
	 * @return the instructionPanel
	 */
	public MainPanel getInstructionPanel() {
		return instructionPanel;
	}

	/**
	 * @param instructionPanel
	 *            the instructionPanel to set
	 */
	public void setInstructionPanel(MainPanel instructionPanel) {
		this.instructionPanel = instructionPanel;
	}

	/**
	 * @return the abacusPanel
	 */
	public AbacusPanel getAbacusPanel() {
		return abacusPanel;
	}

	/**
	 * @param abacusPanel
	 *            the abacusPanel to set
	 */
	public void setAbacusPanel(AbacusPanel abacusPanel) {
		this.abacusPanel = abacusPanel;
	}

	/**
	 * @return the data
	 */
	public LinkedHashMap<String, HashMap<String, List<Action>>> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(LinkedHashMap<String, HashMap<String, List<Action>>> data) {
		this.data = data;
	}

	public void startReading() {
		readerThread = new Thread(this);
		readerThread.start();
	}

	private void playRoboticsVoice(String insTxt) {
		String test = insTxt.replaceAll("[^a-zA-Z0-9]", " ");
		System.out.println("display before saying " + test);
		String[] txt = test.split("\n");
		String txtInput = "";
		for (String data : txt) {
			if (!data.trim().equalsIgnoreCase("")) {
				txtInput = txtInput + data + " ";
			}
		}
		try {
			/** Setting up voice manager */
			vm = VoiceManager.getInstance();

			voice = vm.getVoice("kevin16");
			voice.allocate();
			voice.speak(txtInput);
			System.out.println("display before saying " + txtInput);

			voice.deallocate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// setDataReadyForRead(false);
		}
	}

	private void playText(String insTxt, int counter) {
		String[] txt = insTxt.split("\n");
		String txtInput = "";
		for (String data : txt) {
			if (!data.trim().equalsIgnoreCase("")) {
				txtInput = txtInput + data + " \\pau=1000\\ ";
			}
		}

		playSound = new SpeechController();
		try {
			// playSound.Speak("sharon22k", txtInput);
			playSound.playSound("/audio/" + counter + ".wav");
			// setDataReadyForRead(false);
		} catch (IllegalArgumentException e1) { /** Eating exceptions */
		} catch (IllegalStateException e1) {
			/** Eating exceptions */
		}
	}

	/**
	 * @return the playSound
	 */
	public SpeechController getPlaySound() {
		return playSound;
	}

	/**
	 * @param playSound
	 *            the playSound to set
	 */
	public void setPlaySound(SpeechController playSound) {
		this.playSound = playSound;
	}

	public void stopPlayback() {
		if (readerThread.isAlive()) {
			readerThread.stop();
			if (getPlaySound() != null && getPlaySound().getClip() != null) {
				getPlaySound().stopClip();
			}

		}
	}

	public void start_instructing() throws AbacusException, InterruptedException {
		Set<Entry<String, HashMap<String, List<Action>>>> entrySet = data.entrySet();
		int i = 0;
		ArrayList<String> temp = new ArrayList<String>();
		for (Entry<String, HashMap<String, List<Action>>> entry : entrySet) {
			Object[] tableRow = new Object[3];
			String key = entry.getKey();
			tableRow[0] = key;

			HashMap<String, List<Action>> map = entry.getValue();
			Set<Entry<String, List<Action>>> sEntry = map.entrySet();

			for (Entry<String, List<Action>> entry2 : sEntry) {

				String instruction = entry2.getKey();

				System.out.println("this is instruction" + i);
				if (instruction.isEmpty() || instruction == null) {
					System.out.println("empty");

				} else {

					i++;
					temp.add(instruction);

					if (temp.size() < 3) {
						ArrayList<String> tosend = temp;
						try {
							Thread.sleep(500);

							instructionPanel.ChangeInstructions(tosend, instructionPanel);
							instructionPanel.validate();

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						List<String> tosend = temp.subList(temp.size() - 3, temp.size());
						try {
							Thread.sleep(500);
							instructionPanel.ChangeInstructions(tosend, instructionPanel);
							instructionPanel.validate();

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					if (i > 0) {
						playRoboticsVoice(instruction.substring(2).replaceAll("\\<.*?\\>", "").replace("opic>", ""));

					}
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				List<Action> listOfActions = entry2.getValue();
				StringBuffer actBuf = new StringBuffer();
				for (Action action : listOfActions) {

					if (action.getActionName().equalsIgnoreCase("HighlightFrame")) {
						abacusPanel.highlightFrame();
						abacusPanel.showFrameLabel();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} else if (action.getActionName().equalsIgnoreCase("HighlightRod")) {
						System.out.println("" + action);

						if (action.getRodNumber() != null && action.getBeadNumber() != null) {

							abacusPanel.highlightSpecificBead(action.getRodNumber(), action.getBeadNumber());
							System.out.println("inside hightlight specific bead " + action);

							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

						} else if (action.getRodNumber() != null) {
							abacusPanel.highlightSpecificRod(action.getRodNumber());
							System.out.println("inside hightlight specific rod " + action);
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

					} else if (action.getActionName().equalsIgnoreCase("HighlightRods")) {
						abacusPanel.highlightRods();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} else if (action.getActionName().equalsIgnoreCase("HidePlaceValue")) {
						abacusPanel.hidePlaceValues();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} else if (action.getActionName().equalsIgnoreCase("addrod")) {

						System.out.println("inside add rod");
						int rodNum = action.getRodNumber();
						int beadNum = action.getBeadNumber();
						if (beadNum > 9) {
							String Beads = "" + action.getBeadNumber();
							int[] beads = new int[2];
							beads[0] = Integer.valueOf("" + Beads.split("")[0]);
							System.out.println("" + Beads.split("")[0]);
							beads[1] = Integer.valueOf("" + Beads.split("")[1]);
							if (beads[0] == 5) {
								if (action.getFinger() != null) {
									abacusPanel.hideFingers(true);
									abacusPanel.moveBeadUpTogether(rodNum, beads, Finger.LEFT_INDEX, Finger.LEFT_THUMB);
								} else {
									abacusPanel.hideFingers(false);
									abacusPanel.moveBeadUpTogether(rodNum, beads, Finger.RIGHT_INDEX, Finger.RIGHT_THUMB);
								}
							} else if (beads[1] == 5) { 
								if (action.getFinger() != null) {
									abacusPanel.hideFingers(true); 
									abacusPanel.moveBeadUpTogether(rodNum, beads, Finger.LEFT_THUMB, Finger.LEFT_INDEX);
								} else {
									abacusPanel.hideFingers(false);
									abacusPanel.moveBeadUpTogether(rodNum, beads, Finger.LEFT_THUMB, Finger.RIGHT_THUMB);
								}
							}
						} else {
							if (beadNum == 5) {
								if (action.getFinger() != null) {
									abacusPanel.hideFingers(true);
									abacusPanel.moveHeavenBeadUp(rodNum, Finger.LEFT_INDEX);
								} else {
									abacusPanel.hideFingers(false);
									abacusPanel.moveHeavenBeadUp(rodNum, Finger.RIGHT_INDEX);
								}
							} else {
								abacusPanel.hideFingers(true);
								abacusPanel.moveEarthBeadUp(rodNum, beadNum, Finger.RIGHT_THUMB);
							}
						}
					} else if (action.getActionName().contains("MinusRod")) {
						System.out.println("inside minus rod");
						int rodNum = action.getRodNumber();
						int beadNum = action.getBeadNumber();
						if (beadNum > 9) {
							String Beads = "" + action.getBeadNumber();
							int[] beads = new int[2];
							beads[0] = Integer.valueOf("" + Beads.split("")[0]);
							System.out.println("" + Beads.split("")[0]);
							beads[1] = Integer.valueOf("" + Beads.split("")[1]);
							if (beads[0] == 5) {
								if (action.getFinger() != null) {
									abacusPanel.hideFingers(true);
									abacusPanel.moveBeadDownTogether(rodNum, beads, Finger.LEFT_INDEX,
											Finger.LEFT_THUMB);
									System.out.println("moving bead up together");
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								} else {
									abacusPanel.hideFingers(false);
									abacusPanel.moveBeadDownTogether(rodNum, beads, Finger.LEFT_INDEX,
											Finger.LEFT_THUMB);
									System.out.println("moving bead up together");
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							} else if (beads[1] == 5) {
								if (action.getFinger() != null) {
									abacusPanel.hideFingers(true);
									abacusPanel.moveBeadDownTogether(rodNum, beads, Finger.LEFT_THUMB,
											Finger.LEFT_INDEX);
									System.out.println("moving bead up not together");
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								} else {
									abacusPanel.hideFingers(false);
									abacusPanel.moveBeadDownTogether(rodNum, beads, Finger.LEFT_THUMB,
											Finger.LEFT_INDEX);
									System.out.println("moving bead up not together");
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						} else {
							if (beadNum == 5) {
								if (action.getFinger() != null) {
									abacusPanel.hideFingers(true);
									abacusPanel.moveHeavenBeadDown(rodNum, Finger.LEFT_INDEX);
									System.out.println("moving bead up not together");
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										e.printStackTrace(); 
									}
								} else {
									abacusPanel.hideFingers(false);
									abacusPanel.moveHeavenBeadDown(rodNum, Finger.LEFT_INDEX);
									System.out.println("moving bead up not together");
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							} else {
								abacusPanel.hideFingers(true);
								abacusPanel.moveEarthBeadDown(rodNum, beadNum, Finger.LEFT_THUMB);
								System.out.println("moving bead up not together");
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					} else if (action.getActionName().contains("BlinkRod")) {
						if (action.getBeadNumber() != null) {
							abacusPanel.startBlinkBead(action.getRodNumber(), action.getBeadNumber());

							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

						} else {
							abacusPanel.startBlinkRod(action.getRodNumber());

							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

						}
					} else if (action.getActionName().equalsIgnoreCase("stopBlink")) {
						abacusPanel.stopBlinkBead();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} else if (action.getActionName().contains("wait")) {
						abacusPanel.wait(action.getNumber() * 1000);

					} else if (action.getActionName().equalsIgnoreCase("HighlightUpperBeads")) {
						abacusPanel.highlightUpperBeads();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} else if (action.getActionName().equalsIgnoreCase("HighlightLowerBeads")) {
						abacusPanel.highlightLowerBeads();

						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} else if (action.getActionName().equalsIgnoreCase("answer")) {
						abacusPanel.displayCount("" + action.getNumber());
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} else if (action.getActionName().equalsIgnoreCase("Reset")) {
						abacusPanel.showAbacus();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} else if (action.getActionName().equalsIgnoreCase("Rod")) {
						System.out.println("Rod " + action);
						if (action.getBeadNumber() != null && action.getBeadNumber() != null
								&& action.getFinger() != null) {
							abacusPanel.hideFingers(true);
							abacusPanel.moveEarthBeadUp(action.getRodNumber(), action.getBeadNumber(),
									action.getFinger());
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

						} else if (action.getBeadNumber() != null) {
							abacusPanel.moveEarthBeadUp(action.getRodNumber(), action.getBeadNumber(), "");
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

						} else {
							abacusPanel.highlightSpecificRod(action.getRodNumber());
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

					} else if (action.getActionName().equalsIgnoreCase("showalllabels")) {
						abacusPanel.hidePlaceValues();
						abacusPanel.showBeadLabels();
						abacusPanel.showRodLabels();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} else if (action.getActionName().equalsIgnoreCase("showlbl_rod")) {
						abacusPanel.hidePlaceValues();
						abacusPanel.showRodLabels();

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else if (action.getActionName().equalsIgnoreCase("showlbl_ubeads")) {
						abacusPanel.showUpperBeadsLabels();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else if (action.getActionName().equalsIgnoreCase("showlbl_lbeads")) {
						abacusPanel.showLowerBeadsLabels();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else if (action.getActionName().equalsIgnoreCase("hidealllabels")) {
						abacusPanel.hideBeadLabels();
						abacusPanel.hideUpperBeadsLabels();
						abacusPanel.hideFrameLabel();
						abacusPanel.hideBeamLabel();
						abacusPanel.hideFrameLabel();
						abacusPanel.hideRodLabels();
						abacusPanel.hidePlaceValues();
						abacusPanel.hideRodLabels();

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else if (action.getActionName().equalsIgnoreCase("highlightdots")) {
						abacusPanel.showDots();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else if (action.getActionName().equalsIgnoreCase("showlbl_frame")) {
						abacusPanel.showFrameLabel();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else if (action.getActionName().equalsIgnoreCase("wait")) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} else if (action.getActionName().equalsIgnoreCase("hideplacevalue")) {
						abacusPanel.hidePlaceValues();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	@Override
	public void run() {
		try {
			start_instructing();
		} catch (AbacusException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	}
}
