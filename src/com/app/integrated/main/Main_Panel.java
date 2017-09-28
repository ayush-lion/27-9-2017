package com.app.integrated.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.app.abacus.panel.exception.AbacusException;

import com.app.instructions.beans.Action;
import com.app.instructions.compiler.InstructionCompiler;
import com.app.instructions.compiler.exception.CompilerException;
import com.app.test.TextAreaRenderer;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Main_Panel extends JFrame {

	private JPanel contentPane;

	LinkedHashMap<String, HashMap<String, List<Action>>> data;
	private String teacheralign;
	private String studentalign;
	/**
	 * @return the data
	 */
	public LinkedHashMap<String, HashMap<String, List<Action>>> getData() {
		return data;
	}

	/**
	 * @return the teacheralign
	 */
	public String getTeacheralign() {
		return teacheralign;
	}

	/**
	 * @param teacheralign the teacheralign to set
	 */
	public void setTeacheralign(String teacheralign) {
		this.teacheralign = teacheralign;
	}

	/**
	 * @return the studentalign
	 */
	public String getStudentalign() {
		return studentalign;
	}

	/**
	 * @param studentalign the studentalign to set
	 */
	public void setStudentalign(String studentalign) {
		this.studentalign = studentalign;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(LinkedHashMap<String, HashMap<String, List<Action>>> data) {
		this.data = data;
	}

	private int topicheight, topicwidth, abacusheight, abacuswidth, instructionheight, instructionwidth, fontsize, numofrows, noofcoloumns, noofrods;
	String  topicalignment;
	InstructionCompiler compiler;
	/**
	 * @return the topicalignment
	 */
	public String getTopicalignment() {
		return topicalignment;
	}

	/**
	 * @param topicalignment the topicalignment to set
	 */
	public void setTopicalignment(String topicalignment) {
		this.topicalignment = topicalignment;
	}

	/**
	 * @return the noofrods
	 */
	public int getNoofrods() {
		return noofrods;
	}

	/**
	 * @param noofrods the noofrods to set
	 */
	public void setNoofrods(int noofrods) {
		this.noofrods = noofrods;
	}

	/**
	 * @return the numofrows
	 */
	public int getNumofrows() {
		return numofrows;
	}

	/**
	 * @param numofrows the numofrows to set
	 */
	public void setNumofrows(int numofrows) {
		this.numofrows = numofrows;
	}

	/**
	 * @return the noofcoloumns
	 */
	public int getNoofcoloumns() {
		return noofcoloumns;
	}

	/**
	 * @param noofcoloumns the noofcoloumns to set
	 */
	public void setNoofcoloumns(int noofcoloumns) {
		this.noofcoloumns = noofcoloumns;
	}

	/**
	 * @return the fontsize
	 */
	public int getFontsize() {
		return fontsize;
	}

	/**
	 * @param fontsize the fontsize to set
	 */
	public void setFontsize(int fontsize) {
		this.fontsize = fontsize;
	}

	/**
	 * @return the fontname
	 */
	public String getFontname() {
		return fontname;
	}

	/**
	 * @param fontname the fontname to set
	 */
	public void setFontname(String fontname) {
		this.fontname = fontname;
	}

	private String topicname, fontname;
	private JTextField textField;

	private JPanel tablepanel;

	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Panel frame = new Main_Panel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main_Panel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//compiler = new InstructionCompiler();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Load Instruction Sheet");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					JFileChooser jFileChooser = new JFileChooser();
					int result = jFileChooser.showOpenDialog(new JFrame());
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jFileChooser.getSelectedFile();
						textField.setText(selectedFile.getAbsolutePath());
						// panel.setAbacusAttributesFileName(attrTxt.getText());
						// showAbacus.setEnabled(true);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(17, 58, 172, 48);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Show Integrated Panel\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(getInstructionheight()>0&&getAbacusheight()>0)
				{
				try {
					TestAllAbacusComponent testAllAbacusComponent = new TestAllAbacusComponent(getTopicheight(),
							getTopicwidth(), getAbacuswidth(), getNoofrods(), getInstructionwidth(),
							getAbacusheight(), getInstructionheight(), getTopicname(), getFontname(), getFontsize(),getTopicalignment(), getStudentalign(), getTeacheralign());
					testAllAbacusComponent.setCompilerdata(getData());
					testAllAbacusComponent.showPanel();
					
					testAllAbacusComponent.setVisible(true);
					testAllAbacusComponent.invalidate();
					testAllAbacusComponent.validate();
					testAllAbacusComponent.repaint();
				
					
					//testAllAbacusComponent.pack();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}else
				{
					JOptionPane.showMessageDialog(null, "Please select a file first", "InfoBox: Integrated Panel",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnNewButton_1.setBounds(17, 177, 172, 48);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(201, 68, 221, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Compiler");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()>0)
				{
				try {
					
					compiler = new InstructionCompiler(textField.getText());
					boolean isAllSet = compiler.compileInstructions();
					if (!isAllSet) {
						JOptionPane.showMessageDialog(null, "Found Errors!!!. Please resolve!!!", "InfoBox: Abacus Compiler",
								JOptionPane.INFORMATION_MESSAGE);
						displayErrors(compiler.getMapOfErrors());
					} else {
						JOptionPane.showMessageDialog(null, "No Errors!!!.", "InfoBox: Abacus Compiler",
								JOptionPane.INFORMATION_MESSAGE);
						setData(compiler.getInstructionData());
						start_beforInstructing();
						//start_instructions();
					}
				} catch (CompilerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (AbacusException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please select a file first", "InfoBox: Integrated Panel",
							JOptionPane.INFORMATION_MESSAGE);
						
				}
			}
		});
		btnNewButton_2.setBounds(17, 123, 172, 42);
		contentPane.add(btnNewButton_2);
		
		
		
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
	
	
	
	public void start_beforInstructing() throws AbacusException {
		Set<Entry<String, HashMap<String, List<Action>>>> entrySet = data.entrySet();
		int i = 0;
		HashMap<String, String> actionMap = new HashMap<String, String>();
		for (Entry<String, HashMap<String, List<Action>>> entry : entrySet) {
			String key = entry.getKey();

			HashMap<String, List<Action>> map = entry.getValue();
			Set<Entry<String, List<Action>>> sEntry = map.entrySet();
			for (Entry<String, List<Action>> entry2 : sEntry) {
				i++;
				String instruction = entry2.getKey();
				//setInstruction(instruction);
				//System.out.println(getInstruction());
				ArrayList<String> strings = new ArrayList<>(Arrays.asList(instruction.split("")));
				if (instruction.contains("Learning")) {
					String s = instruction.replace("<Topic>", "");
					String s2 = s.replace("</Topic>", "");
					String s3 = s2.replace("\"", " ");
					setTopicname(s3);
				}
				// instructionPanel.performinstruction(instruction, instructionPanel);
				List<Action> listOfActions = entry2.getValue();
				for (Action actionlist : listOfActions) 
				{
					if (actionlist.getActionName().contains("Font")) 
					{
						setFontname(String.valueOf(actionlist.getFont().getName()));
						//setName(String.valueOf(actionlist.getFont().getName()));
						setFontsize(Integer.parseInt(actionlist.getFont().getSize()));
					}
					else
						
					if (actionlist.getActionName().contains("Layout")) 
					{
					setNumofrows((actionlist.getLayout().getNumOfRow()));
					setNoofcoloumns(((actionlist.getLayout().getNumOfCols())));

					System.out.println(actionlist.getLayout().getRows());

					for (int j = 0; j < actionlist.getLayout().getRows().size(); j++) {
						if (j == 0) {

							if (actionlist.getLayout().getRows().get(j).getCols().get(0).getComponentName()
									.contains("topic")) {
								setTopicheight(Integer.parseInt(actionlist.getLayout().getRows().get(j).getHeight()
										.replaceAll("null", "")));
								setTopicwidth(Integer.parseInt(
										actionlist.getLayout().getRows().get(j).getWidth().replaceAll("null", "")));
								System.out.println("topic hight : " + getTopicheight());
							} else if (actionlist.getLayout().getRows().get(j).getCols().get(0).getComponentName()
									.contains("abacus")) {
								setAbacusheight(Integer.parseInt(actionlist.getLayout().getRows().get(j).getHeight()
										.replaceAll("null", "")));
								setAbacuswidth((Integer.parseInt(actionlist.getLayout().getRows().get(j).getWidth()
										.replaceAll("null", ""))));
								System.out.println("abacus hight : " + getAbacusheight());
							} else if (actionlist.getLayout().getRows().get(j).getCols().get(0).getComponentName()
									.contains("actor")) {
								setInstructionheight(Integer.parseInt((actionlist.getLayout().getRows().get(j)
										.getHeight().replaceAll("null", ""))));
								setInstructionwidth(Integer.parseInt(
										actionlist.getLayout().getRows().get(j).getWidth().replaceAll("null", "")));
								System.out.println("instruction hight :" + getInstructionheight());
							}
						}
						if (j == 1) {
							if (actionlist.getLayout().getRows().get(j).getCols().get(0).getComponentName()
									.contains("topic")) {
								setTopicheight(Integer.parseInt((actionlist.getLayout().getRows().get(j).getHeight()
										.replaceAll("null", ""))));
								setTopicwidth(Integer.parseInt(
										actionlist.getLayout().getRows().get(j).getWidth().replaceAll("null", "")));
								System.out.println("instruction hight :" + getTopicheight());
							} else if (actionlist.getLayout().getRows().get(j).getCols().get(0).getComponentName()
									.contains("abacus")) {
								setAbacusheight(Integer.parseInt(actionlist.getLayout().getRows().get(j).getHeight()
										.replaceAll("null", "")));
								setAbacuswidth((Integer.parseInt(actionlist.getLayout().getRows().get(j).getWidth()
										.replaceAll("null", ""))));
								System.out.println("abacus hight : " + getAbacusheight());
							} else if (actionlist.getLayout().getRows().get(j).getCols().get(0).getComponentName()
									.contains("actor")) {
								setInstructionheight(Integer.parseInt((actionlist.getLayout().getRows().get(j)
										.getHeight().replaceAll("null", ""))));
								setInstructionwidth(Integer.parseInt(
										actionlist.getLayout().getRows().get(j).getWidth().replaceAll("null", "")));
								System.out.println("instruction hight :" + getInstructionheight());
							}
						}
						if (j == 2) {
							if (actionlist.getLayout().getRows().get(j).getCols().get(0).getComponentName()
									.contains("topic")) {
								setTopicheight(Integer.parseInt(actionlist.getLayout().getRows().get(j).getHeight()
										.replaceAll("null", "")));
								setTopicwidth(Integer.parseInt(
										actionlist.getLayout().getRows().get(j).getWidth().replaceAll("null", "")));
								System.out.println("topic hight : " + getTopicheight());
							} else if (actionlist.getLayout().getRows().get(j).getCols().get(0).getComponentName()
									.contains("abacus")) {
								setAbacusheight(Integer.parseInt((actionlist.getLayout().getRows().get(j)
										.getHeight().replaceAll("null", ""))));
								setAbacuswidth(Integer.parseInt(
										actionlist.getLayout().getRows().get(j).getWidth().replaceAll("null", "")));
								System.out.println("instruction hight :" + getAbacusheight());
							} else if (actionlist.getLayout().getRows().get(j).getCols().get(0).getComponentName()
									.contains("actor")) {
								setInstructionheight(Integer.parseInt((actionlist.getLayout().getRows().get(j)
										.getHeight().replaceAll("null", ""))));
								setInstructionwidth(Integer.parseInt(
										actionlist.getLayout().getRows().get(j).getWidth().replaceAll("null", "")));
								System.out.println("instruction hight :" + getInstructionheight());
							}					
							}
					}
					}
					else if (actionlist.getActionName().replaceAll("null", "").contains("Component")) 
					{
						System.out.println(""+actionlist);
							if(actionlist.getComponent().getName().replaceAll("null", "").equals("abacus"))
							{
							if(actionlist.getRodNumber()!=null)
							{
							setNoofrods(actionlist.getRodNumber());	
							System.out.println("get rods "+getNoofrods());
						
							}
							}
						else if (actionlist.getComponent().getName().equals("topic"))
						{
							setTopicalignment(actionlist.getComponent().getAlign());

							System.out.println("get topic "+getTopicalignment());
							
						}
						else if (actionlist.getComponent().getName().contains("actor")) {
							setTeacheralign((actionlist.getComponent().getTeacherAlign()));
							setStudentalign((actionlist.getComponent().getStudentAlign()));

							System.out.println("teacheralign : " + getTeacheralign());
							System.out.println("studentalign : " + getStudentalign());

						}
						//setName((actionlist.getComponent().getName()));

					//	setTeacherAlign((actionlist.getComponent().getTeacherAlign()));
					//	setStudentAlign((actionlist.getComponent().getStudentAlign()));

						//setName((actionlist.getComponent().getName()));
//						setAlign(actionlist.getComponent().getAlign());

//						setImage((actionlist.getComponent().getImage()));
					}
				}
			}
		}
	}

	
	/**
	 * @return the topicwidth
	 */
	public int getTopicwidth() {
		return topicwidth;
	}

	/**
	 * @param topicwidth the topicwidth to set
	 */
	public void setTopicwidth(int topicwidth) {
		this.topicwidth = topicwidth;
	}

	/**
	 * @return the abacuswidth
	 */
	public int getAbacuswidth() {
		return abacuswidth;
	}

	/**
	 * @param abacuswidth the abacuswidth to set
	 */
	public void setAbacuswidth(int abacuswidth) {
		this.abacuswidth = abacuswidth;
	}

	/**
	 * @return the instructionwidth
	 */
	public int getInstructionwidth() {
		return instructionwidth;
	}

	/**
	 * @param instructionwidth the instructionwidth to set
	 */
	public void setInstructionwidth(int instructionwidth) {
		this.instructionwidth = instructionwidth;
	}

	private char[] getInstruction() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the topicheight
	 */
	public int getTopicheight() {
		return topicheight;
	}

	/**
	 * @param topicheight the topicheight to set
	 */
	public void setTopicheight(int topicheight) {
		this.topicheight = topicheight;
	}

	/**
	 * @return the abacusheight
	 */
	public int getAbacusheight() {
		return abacusheight;
	}

	/**
	 * @param abacusheight the abacusheight to set
	 */
	public void setAbacusheight(int abacusheight) {
		this.abacusheight = abacusheight;
	}

	/**
	 * @return the instructionheight
	 */
	public int getInstructionheight() {
		return instructionheight;
	}

	/**
	 * @param instructionheight the instructionheight to set
	 */
	public void setInstructionheight(int instructionheight) {
		this.instructionheight = instructionheight;
	}

	/**
	 * @return the topicname
	 */
	public String getTopicname() {
		return topicname;
	}

	/**
	 * @param topicname the topicname to set
	 */
	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}
}
