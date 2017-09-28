/**
 * 
 */
package com.app.callouts.panel;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 13-Sept-2017
 */
public class CalloutsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private CalloutPanel[] callouts ;
	private String stalign;
	private String ttalign;
	
	 
	/**
	 * @return the stalign
	 */
	public String getStalign() {
		return stalign;
	}

	/**
	 * @param stalign the stalign to set
	 */
	public void setStalign(String stalign) {
		this.stalign = stalign;
	}

	/**
	 * @return the ttalign
	 */
	public String getTtalign() {
		return ttalign;
	}

	/**
	 * @param ttalign the ttalign to set
	 */
	public void setTtalign(String ttalign) {
		this.ttalign = ttalign;
	}

	public CalloutsPanel(List<String> data,String stalign,String ttalign) {
		
		callouts = new CalloutPanel[data.size()*2];
		boolean isStudent = Boolean.TRUE;
		
	if(data.size()==2 || data.size()==47 || data.size()==57)
	{
		this.setLayout(new GridLayout(1,3));
		for (int i = 0; i < data.size(); i++) 
		{ 
			if(data.get(i).startsWith("ST")) 
			{ 
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TT",true);	
			callouts[i*2] = new CalloutPanel(stalign,"ST",true);	
			callouts[i*2].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[(i*2)+1].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[i*2]);
			this.add(callouts[(i*2)+1]);
		    	
			}
			else if(data.get(i).startsWith("TT"))
			{
			callouts[i*2] = new CalloutPanel(stalign,"ST",true);		
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TT",true);	
			callouts[(i*2)+1].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			this.add(callouts[i*2]);
			this.add(callouts[(i*2)+1]);
			callouts[i*2].getCallout().setVisible(Boolean.FALSE);
			
			}
			else
			if(data.get(i).startsWith("SS")) 
			{
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TS",false);	
			callouts[i*2] = new CalloutPanel(stalign,"SS",false);	
			callouts[i*2].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			this.add(callouts[i*2]);
			this.add(callouts[(i*2)+1]);
			callouts[(i*2)+1].getCallout().setVisible(Boolean.FALSE);
			
			}
			else if(data.get(i).startsWith("TS"))
			{
			callouts[i*2] = new CalloutPanel(stalign,"SS",false);		
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TS",false);	
			callouts[(i*2)+1].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			this.add(callouts[i*2]);
			this.add(callouts[(i*2)+1]);
			callouts[i*2].getCallout().setVisible(Boolean.FALSE);
			
			}
		 }
	}else
		{
		this.setLayout(new GridLayout(3,3));
		for (int i = 0; i < data.size(); i++) 
		{ 
			if(data.get(i).startsWith("ST")) 
			{ 
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TT",true);	
			callouts[i*2] = new CalloutPanel(stalign,"ST",true);	
			callouts[i*2].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[(i*2)+1].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[i*2]);
			this.add(callouts[(i*2)+1]);
		    	
			}
			else if(data.get(i).startsWith("TT"))
			{
			callouts[i*2] = new CalloutPanel(stalign,"ST",true);		
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TT",true);	
			callouts[(i*2)+1].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			this.add(callouts[i*2]);
			this.add(callouts[(i*2)+1]);
			callouts[i*2].getCallout().setVisible(Boolean.FALSE);
			
			}
			else
			if(data.get(i).startsWith("SS")) 
			{
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TS",false);	
			callouts[i*2] = new CalloutPanel(stalign,"SS",false);	
			callouts[i*2].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			this.add(callouts[i*2]);
			this.add(callouts[(i*2)+1]);
			callouts[(i*2)+1].getCallout().setVisible(Boolean.FALSE);
			
			}
			else if(data.get(i).startsWith("TS"))
			{
			callouts[i*2] = new CalloutPanel(stalign,"SS",false);		
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TS",false);	
			callouts[(i*2)+1].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			this.add(callouts[i*2]);
			this.add(callouts[(i*2)+1]);
			callouts[i*2].getCallout().setVisible(Boolean.FALSE);
			
			}
		 }
		
		}
}
	
	public CalloutsPanel(List<String> data,String stalign,String ttalign,String string) {
		callouts = new CalloutPanel[data.size()*2];
		boolean isStudent = Boolean.TRUE;
		
		if(data.size()<=2) 
		{
		this.setLayout(new GridLayout(1,3));
		for (int i = 0; i < data.size(); i++)
		{ 	
			if(data.get(i).startsWith("ST")) 
			{
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TT",true);	
			callouts[i*2] = new CalloutPanel(stalign,"ST",true);	
			callouts[i*2].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[(i*2)+1].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[(i*2)+1]);
			this.add(callouts[i*2]);
			
			}
			else if(data.get(i).startsWith("TT"))
			{
			callouts[i*2] = new CalloutPanel(stalign,"ST",true);		
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TT",true);	
			callouts[(i*2)+1].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[i*2].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[(i*2)+1]);
			this.add(callouts[i*2]);
		
			}
			else
			if(data.get(i).startsWith("SS")) 
			{
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TS",false);	
			callouts[i*2] = new CalloutPanel(stalign,"SS",false);	
			callouts[i*2].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[(i*2)+1].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[(i*2)+1]);
			this.add(callouts[i*2]);
			
			}
			else if(data.get(i).startsWith("TS"))
			{
			callouts[i*2] = new CalloutPanel(stalign,"SS",false);		
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TS",false);	
			callouts[(i*2)+1].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[i*2].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[(i*2)+1]);
			this.add(callouts[i*2]);
			
			}	
		 }		
 	  }else 
		{
		this.setLayout(new GridLayout(3,3));
		for (int i = 0; i < data.size(); i++)
		{ 	
			if(data.get(i).startsWith("ST")) 
			{
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TT",true);	
			callouts[i*2] = new CalloutPanel(stalign,"ST",true);	
			callouts[i*2].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[(i*2)+1].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[(i*2)+1]);
			this.add(callouts[i*2]);
			
			}
			else if(data.get(i).startsWith("TT"))
			{
			callouts[i*2] = new CalloutPanel(stalign,"ST",true);		
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TT",true);	
			callouts[(i*2)+1].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[i*2].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[(i*2)+1]);
			this.add(callouts[i*2]);
		
			}
			else
			if(data.get(i).startsWith("SS")) 
			{
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TS",false);	
			callouts[i*2] = new CalloutPanel(stalign,"SS",false);	
			callouts[i*2].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[(i*2)+1].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[(i*2)+1]);
			this.add(callouts[i*2]);
			
			}
			else if(data.get(i).startsWith("TS"))
			{
			callouts[i*2] = new CalloutPanel(stalign,"SS",false);		
			callouts[(i*2)+1] = new CalloutPanel(ttalign,"TS",false);	
			callouts[(i*2)+1].getCallout().setText(data.get(i).replaceAll("\\<.*?>", "").substring(2).replaceAll(":", ""));
			callouts[i*2].getCallout().setVisible(Boolean.FALSE);
			this.add(callouts[(i*2)+1]);
			this.add(callouts[i*2]);
			
			}	
		 }		
	  }
   }
}