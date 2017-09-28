/**
 * 
 */
package com.app.integrated.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.app.abacus.Finger;
import com.app.abacus.Frame;
import com.app.abacus.panel.AbacusPanel;
import com.app.abacus.panel.exception.AbacusException;
import com.app.callouts.panel.MainPanel;

import com.app.instructions.beans.Action;
import com.app.instructions.beans.Component;
import com.app.instructions.beans.Row;
import com.app.instructions.compiler.InstructionCompiler;
import com.app.instructions.compiler.exception.CompilerException;
import com.app.sound.DownloadSpeech;
import com.app.test.TextAreaRenderer;
import com.app.topic.panel.TopicPanel;

/**
 * @author prashant.joshi
 *
 */
public class TestAllAbacusComponent extends JFrame {

	private static final long serialVersionUID = 1L;

	// Top panels
	
	private JPanel rightPanel;
	private JPanel abacusTopPanel;
	private JPanel imagePanel;
	private JMenuBar menuBar;
	private JMenu menu;
	private JCheckBoxMenuItem robotics;
	private JMenu natural;
	private JCheckBoxMenuItem Sharon;
	private JCheckBoxMenuItem Rachel;
	private JCheckBoxMenuItem Deepa;
	private ButtonGroup vGroup = null;
	private JRadioButton voice1 = new JRadioButton("Sharon");
	private JRadioButton voice2 = new JRadioButton("Rachel");
	private JRadioButton voice3 = new JRadioButton("Deepa");
	private boolean isPlayRobotics = false;
	private boolean isPlayNatural = true;
	private DownloadSpeech downloadSpeech;
	private AbacusPanel panel;
	private Frame frame;
	private JPanel tablepanel;
	private JPanel playPanel;
	String filenameatt;
	
	// Abacus Top Panel
	
	private JCheckBox doWeNeedFrame;
	private JCheckBox doWeNeedFingers;
	private JTextField attrTxt;
	private JButton loadAbacus;
	private JButton showAbacus;
	private JButton stopAbacus;
	private JButton killButton;
	
	// Abacus Bead Up Panel

	private String abacuspath;
	private String instructionpath;
	private boolean isabacusprovided;
	private boolean isinstructionprovided;
	private String excelfile;
	private MainPanel instructionpanel;
	private JMenu menu1;
	private JCheckBoxMenuItem abacusprop;
	private JCheckBoxMenuItem instprop;
	private Performer performer;
	private InstructionCompiler complier;
	private JMenu menu2;
	private JCheckBoxMenuItem loadinstructionfile;
	private JCheckBoxMenuItem startcheck;
	private JTable table;
	private JCheckBoxMenuItem refresh;
	private JCheckBoxMenuItem stop;
	private int topic_height=5, abacus_height=60, instruction_height=30, image_height=5;
	private TopicPanel topicPanel;
	LinkedHashMap<String, HashMap<String, List<Action>>>  compilerdata;
	
	
	
	
	
	/**
	 * @return the compilerdata
	 */
	public LinkedHashMap<String, HashMap<String, List<Action>>> getCompilerdata() {
		return compilerdata;
	}

	/**
	 * @param compilerdata the compilerdata to set
	 */
	public void setCompilerdata(LinkedHashMap<String, HashMap<String, List<Action>>> compilerdata) {
		this.compilerdata = compilerdata;
	}

	/**
	 * @return the image_height
	 */
	public int getImage_height() {
		return image_height;
	}

	/**
	 * @param image_height the image_height to set
	 */
	public void setImage_height(int image_height) {
		this.image_height = image_height;
	}

	/**
	 * @return the topic_height
	 */
	public int getTopic_height() {
		return topic_height;
	}

	/**
	 * @param topic_height the topic_height to set
	 */
	public void setTopic_height(int topic_height) {
		this.topic_height = topic_height;
	}

	/**
	 * @return the abacus_height
	 */
	public int getAbacus_height() {
		return abacus_height;
	}

	/**
	 * @param abacus_height the abacus_height to set
	 */
	public void setAbacus_height(int abacus_height) {
		this.abacus_height = abacus_height;
	}

	/**
	 * @return the instruction_height
	 */
	public int getInstruction_height() {
		return instruction_height;
	}

	/**
	 * @param instruction_height the instruction_height to set
	 */
	public void setInstruction_height(int instruction_height) {
		this.instruction_height = instruction_height;
	}

	public TestAllAbacusComponent(int topicheight,int topicWidth ,int abacusWidth , int numofrod,
			int instructionWidth,int abacusheight, int instructionheight, String topicname, String fontname, int fontsize, String align,String stalign,String ttalign )  throws Throwable 
	{	
		
		try {
			ArrayList<String> dat = new ArrayList<String>();
			this.setLayout(null);
			this.setBounds(100, 50, 1030, 750);
			
			this.setResizable(false);
			this.setTitle(topicname);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			topicPanel = new TopicPanel();

			topicPanel.setBounds(10, 0, this.getWidth(), this.getHeight()*topicheight/100);
			topicPanel.setTopicName(topicname);
			topicPanel.setFontName(fontname);
			topicPanel.setFontSize(fontsize);
			topicPanel.setAlign(align);
			panel = new AbacusPanel();
			panel.setNoOfrod(numofrod);
			panel.setBounds(10, topicPanel.getHeight(), this.getWidth()-25, this.getHeight()*abacusheight/100);
			panel.initializeAbacus();
			panel.displayCount("");
			
			//panel.hidePlaceValues();
			
			panel.hideBeadLabels();
			panel.hideUpperBeadsLabels(); 
			panel.hideRodLabels(); 
			panel.showDots();
			panel.hideDots();
			
			setUpAbacusTopPanel();
			instructionpanel = new MainPanel();
			instructionpanel.setStdalign(stalign);
			instructionpanel.setTuteralign(ttalign);
			instructionpanel.ChangeInstructions(dat, instructionpanel);
			instructionpanel.setBounds(10, panel.getHeight()+topicPanel.getHeight(), this.getWidth()-10, this.getHeight()*instructionheight/100-this.getHeight()/20);
			setupPlayPanel();
			setupMenuBar();
			//Add constraints
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.fill = GridBagConstraints.BOTH;
			constraints.ipady = 0;
			this.setJMenuBar(menuBar);
			// Add Student Panel
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.weightx = 1;
			constraints.weighty = 10/100;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			
			//this.add(topicPanel, constraints);
			this.add(topicPanel);
			// Add Callout panel
			
			constraints.gridx = 0; 
			constraints.gridy = 1;
			constraints.weightx = 1;
			System.out.println(""+abacusheight+" "+instructionheight);
			constraints.weighty = 50/100;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			//this.add(panel, constraints);
			this.add(panel);
			
			
			// Add teacher Panel
			constraints.gridx = 0; 
			constraints.gridy = 2;
			constraints.weightx = 1;
			constraints.weighty = 40/100;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			//this.add(instructionpanel, constraints);
			
			this.add(instructionpanel);
			//this.revalidate();
			//this.validate();
			//this.repaint();
			//this.pack();
			//this.getContentPane().add(rightPanel);
			
		//	this.getContentPane().add(topicPanel);
		//	this.getContentPane().add(panel);
		//	this.getContentPane().add(instructionpanel);

			//this.setVisible(true);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		tablepanel = new JPanel();
		tablepanel.setBackground(Color.RED);
		tablepanel.setBounds(10, 100, this.getWidth() - 20, this.getHeight() - 150);
		// add the table to the frame

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Row");
		model.addColumn("Instructions");
		model.addColumn("Actions");

		// this.getContentPane().add(new WrappableTable(model));
		table = new JTable(model);
		table.setGridColor(Color.LIGHT_GRAY);

		table.getColumn("Instructions").setCellRenderer(new TextAreaRenderer());
		table.getColumn("Actions").setCellRenderer(new TextAreaRenderer());

		TableColumn column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(100);

		column = table.getColumnModel().getColumn(1);
		column.setPreferredWidth(300);

		column = table.getColumnModel().getColumn(2);
		column.setPreferredWidth(600);

		table.setPreferredScrollableViewportSize(
				new Dimension(tablepanel.getWidth() - 20, tablepanel.getHeight() - 40));
		tablepanel.add(new JScrollPane(table));

	}

	private void enableNaturalVoices(boolean enable) {
		Sharon.setEnabled(enable);
		Rachel.setEnabled(enable);
		Deepa.setEnabled(enable);

		if (enable) {
			Sharon.setSelected(true);
			Rachel.setSelected(false);
			Deepa.setSelected(false);
		} else {
			Sharon.setSelected(false);
			Rachel.setSelected(false);
			Deepa.setSelected(false);
		}
	}

	private void setupMenuBar() {
		menuBar = new JMenuBar();
		menu = new JMenu("Voices");
		menu.setMnemonic(KeyEvent.VK_V);
		menuBar.add(menu);

		natural = new JMenu("Natural");
		natural.setMnemonic(KeyEvent.VK_N);
		natural.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				robotics.setSelected(false);
				isPlayRobotics = false;
				isPlayNatural = true;

				/** Disabling the voice selection pane */
				enableNaturalVoices(true);

				killButton.setEnabled(true);
			}
		});
		menu.add(natural);

		robotics = new JCheckBoxMenuItem("Robotics");
		robotics.setMnemonic(KeyEvent.VK_R);
		robotics.setSelected(true);
		robotics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				natural.setSelected(false);
				isPlayRobotics = true;
				isPlayNatural = false;

				/** Disabling the voice selection pane */
				enableNaturalVoices(false);

				killButton.setEnabled(false);
			}
		});
		menu.add(robotics);

		Sharon = new JCheckBoxMenuItem("Sharon");
		Sharon.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				robotics.setSelected(false);
				isPlayRobotics = false;
				isPlayNatural = true;

				/** Disabling the voice selection pane */
				Rachel.setSelected(false);
				Deepa.setSelected(false);

				killButton.setEnabled(true);
			}
		});
		natural.add(Sharon);

		Rachel = new JCheckBoxMenuItem("Rachel");
		Rachel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				robotics.setSelected(false);
				isPlayRobotics = false;
				isPlayNatural = true;

				/** Disabling the voice selection pane */
				Sharon.setSelected(false);
				Deepa.setSelected(false);

				killButton.setEnabled(true);
			}
		});
		natural.add(Rachel);

		Deepa = new JCheckBoxMenuItem("Deepa");
		Deepa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				robotics.setSelected(false);
				isPlayRobotics = false;
				isPlayNatural = true;

				/** Disabling the voice selection pane */
				Sharon.setSelected(false);
				Rachel.setSelected(false);

				killButton.setEnabled(true);
			}
		});
		natural.add(Deepa);

		menu1 = new JMenu("Properties");
		menu1.setMnemonic(KeyEvent.VK_V);
		abacusprop = new JCheckBoxMenuItem("Load Abacus Properties");
		abacusprop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				abacusprop.setSelected(false);
				try {
					JFileChooser jFileChooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Properties FILES", "properties");
					jFileChooser.setFileFilter(filter);
					int result = jFileChooser.showOpenDialog(new JFrame());
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jFileChooser.getSelectedFile();
						attrTxt.setText(selectedFile.getAbsolutePath());
						panel.setAbacusAttributesFileName(attrTxt.getText());
						showAbacus.setEnabled(true);

					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		instprop = new JCheckBoxMenuItem("Load Instruction Properties");
		instprop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				instprop.setSelected(false);
				// TODO Auto-generated method stub
				try {
					JFileChooser jFileChooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Properties FILES", "properties");
					jFileChooser.setFileFilter(filter);
					int result = jFileChooser.showOpenDialog(new JFrame());
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jFileChooser.getSelectedFile();
						attrTxt.setText(selectedFile.getAbsolutePath());
						setInstructionpath(attrTxt.getText());
						System.out.println("instruction prop" + getInstructionpath());

						//instructionpanel.setAttributespath(selectedFile.getAbsolutePath());
						// instructionpanel.Initialize_Instruction_Panel(instructionpanel);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		menu1.add(abacusprop);
		menu1.add(instprop);
		//menuBar.add(menu1);

		menu2 = new JMenu("Start");
		menu2.setMnemonic(KeyEvent.VK_V);
		
		JCheckBoxMenuItem initcompile = new JCheckBoxMenuItem("Compile");
		initcompile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					compile(attrTxt.getText().toString());
				} catch (AbacusException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		loadinstructionfile = new JCheckBoxMenuItem("Load File");
		loadinstructionfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				loadinstructionfile.setSelected(false);
				// TODO Auto-generated method stub
				try {
					JFileChooser jFileChooser = new JFileChooser();
					int result = jFileChooser.showOpenDialog(new JFrame());
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jFileChooser.getSelectedFile();
						attrTxt.setText(selectedFile.getAbsolutePath());
						// panel.setAbacusAttributesFileName(attrTxt.getText());
						// showAbacus.setEnabled(true);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		startcheck = new JCheckBoxMenuItem("Start");
		startcheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					start_instructions(getCompilerdata());
					//compile(attrTxt.getText().toString());
				} catch (AbacusException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				startcheck.setSelected(false);
			}
		});

		refresh = new JCheckBoxMenuItem("Refresh");
		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refresh.setSelected(false);
				panel.showAbacus();
				panel.repaint();

				
				// TODO Auto-generated method stub
				// instructionpanel

			}
		});

		stop = new JCheckBoxMenuItem("Stop");
		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// panel.showAbacus();
				performer.stopPlayback();
			}
		});

		menu2.add(loadinstructionfile);
		menu2.add(startcheck);
		menu2.add(refresh);
		menu2.add(stop);
		menu2.add(initcompile);
		menuBar.add(menu2);

	}

	public void showPanel() throws IOException {
		//this.setVisible(true);
		
	}

	private void setupPlayPanel() {
		playPanel = new JPanel(new GridLayout(3, 6));

		playPanel.setBounds(0, this.getHeight() - 130, this.getWidth(), 90);
	}

	private void setUpAbacusTopPanel() {
		abacusTopPanel = new JPanel(new GridLayout(1, 5));
		abacusTopPanel.setBounds(0, 0, 1050, 0);

		// Buttons
		
		loadAbacus = new JButton("Load Abacus Instructions");
		showAbacus = new JButton("Start");
		stopAbacus = new JButton("Stop");

		// Frame

		doWeNeedFrame = new JCheckBox("Show Frame");

		doWeNeedFrame.setSelected(true);
		doWeNeedFrame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (doWeNeedFrame.isSelected()) {
					panel.hideFrame(Boolean.TRUE);
				} else {
					panel.hideFrame(Boolean.FALSE);
				}
			}
		});

		// Fingers
		
		doWeNeedFingers = new JCheckBox("Show Fingers");
		doWeNeedFingers.setSelected(true);
		doWeNeedFingers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (doWeNeedFingers.isSelected()) {
					panel.hideFingers(Boolean.TRUE);
				} else {
					panel.hideFingers(Boolean.FALSE);
				}
			}
		});

		attrTxt = new JTextField();

		// Show Abacus Button
		// showAbacus.setEnabled(Boolean.FALSE);
		
		showAbacus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					startcompilation(attrTxt.getText());
				} catch (AbacusException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// Load Abacus
		
		loadAbacus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser jFileChooser = new JFileChooser();
					int result = jFileChooser.showOpenDialog(new JFrame());
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jFileChooser.getSelectedFile();
						attrTxt.setText(selectedFile.getAbsolutePath());
						// panel.setAbacusAttributesFileName(attrTxt.getText());
						// showAbacus.setEnabled(true);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		// Add components in Panel
		// abacusTopPanel.add(doWeNeedFrame);
		// abacusTopPanel.add(doWeNeedFingers);

		killButton = new JButton("Kill Demo");
		killButton.setBounds(945, 10, 90, 40);
		killButton.setEnabled(false);
		killButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panel.initializeAbacus();
				} catch (AbacusException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// performer.stopPlayback();
				// fileButton.setEnabled(true);
				// startButton.setEnabled(true);

				if (isPlayNatural) {
					voice1.setEnabled(true);
					voice2.setEnabled(true);
					voice3.setEnabled(true);
				}

				// startButton.setEnabled(false);
			}
		});
		// abacusTopPanel.add(attrTxt);
		// abacusTopPanel.add(loadAbacus);
		// abacusTopPanel.add(showAbacus);
	}

	public void startcompilation(String filename) throws AbacusException {
		compile(filename);

	}

	private void compile(String filename) throws AbacusException {
		try {
			complier = new InstructionCompiler(filename);
			boolean isAllSet = complier.compileInstructions();
			if (!isAllSet) {
				JOptionPane.showMessageDialog(null, "Found Errors!!!. Please resolve!!!", "InfoBox: Abacus Compiler",
						JOptionPane.INFORMATION_MESSAGE);
				displayErrors(complier.getMapOfErrors());
			} else {
				JOptionPane.showMessageDialog(null, "No Errors!!!.", "InfoBox: Abacus Compiler",
						JOptionPane.INFORMATION_MESSAGE);
				setCompilerdata(complier.getInstructionData());
				//start_instructions();
			}
		} catch (CompilerException e1) {
			e1.printStackTrace();
		}
	}

	private void displayErrors(LinkedHashMap<String, HashMap<String, List<String>>> errors) {
		// TODO Auto-generated method stub

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.getDataVector().removeAllElements();

		Set<Entry<String, HashMap<String, List<String>>>> entrySet = errors.entrySet();
		for (Entry<String, HashMap<String, List<String>>> entry : entrySet) {
			Object[] tableRow = new Object[3];
			String row = entry.getKey();
			tableRow[0] = row;

			HashMap<String, List<String>> errorMsgs = entry.getValue();
			List<String> errorList = errorMsgs.get("instructionError");
			StringBuffer insBuf = new StringBuffer();
			for (String errorMsg : errorList) {
				System.out.println(errorMsg);
				insBuf.append(errorMsg).append("\n");
			}

			tableRow[1] = insBuf.toString();

			StringBuffer actBuf = new StringBuffer();
			List<String> errorAList = errorMsgs.get("actionError");
			for (String errorMsg : errorAList) {
				actBuf.append(errorMsg).append("\n");
			}
			tableRow[2] = actBuf.toString();

			model.addRow(tableRow);
		}

		JOptionPane.showConfirmDialog(null, tablepanel, "Error Details", JOptionPane.CANCEL_OPTION);
	}

	public void start_instructions(LinkedHashMap<String, HashMap<String, List<Action>>> linkedHashMap) throws AbacusException {

	
		//start_beforInstructing(linkedHashMap);
		performer = new Performer();
		performer.setAbacusPanel(panel);
		performer.setInstructionPanel(instructionpanel);
		performer.setData(linkedHashMap);
		performer.setPlayRobotics(isPlayRobotics);
		performer.setPlayNatural(false);
		performer.startReading();
		// odel.addRow(tableRow);
	}

	/**
	 * @param args
	 * @throws Throwable
	 */
	

	/**
	 * @return the instructionpath
	 */
	public String getInstructionpath() {
		return instructionpath;
	}

	/**
	 * @param instructionpath
	 *            the instructionpath to set
	 */
	public void setInstructionpath(String instructionpath) {
		this.instructionpath = instructionpath;
	}

	/**
	 * @return the is abacus provided
	 */
	public boolean isIsabacusprovided() {
		return isabacusprovided;
	}

	/**
	 * @param isabacusprovided
	 *            the is abacus provided to set
	 */
	public void setIsabacusprovided(boolean isabacusprovided) {
		this.isabacusprovided = isabacusprovided;
	}

	/**
	 * @return the is instruction provided
	 */
	public boolean isIsinstructionprovided() {
		return isinstructionprovided;
	}

	/**
	 * @param isinstructionprovided
	 *            the is instruction provided to set
	 */
	public void setIsinstructionprovided(boolean isinstructionprovided) {
		this.isinstructionprovided = isinstructionprovided;
	}

	/**
	 * @return the excel file
	 */
	public String getExcelfile() {
		return excelfile;
	}

	/**
	 * @param excelfile
	 *            the excel file to set
	 */
	public void setExcelfile(String excelfile) {
		this.excelfile = excelfile;
	}

	/**
	 * @return the abacusprop
	 */
	public JCheckBoxMenuItem getAbacusprop() {
		return abacusprop;
	}

	/**
	 * @param abacusprop
	 *            the abacusprop to set
	 */
	public void setAbacusprop(JCheckBoxMenuItem abacusprop) {
		this.abacusprop = abacusprop;
	}
	
	
	public static void main(String[] args) throws Throwable {
		TestAllAbacusComponent ob = null;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					ob.showPanel();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
