/**
 * 
 */
package com.app.callout;

import java.awt.Color;

import javax.swing.JLabel;

/**
 * @author prashant.joshi (198joshi@gmail.com)
 * @version 05-Spet-2017 
 */
public abstract class Callout extends JLabel {
	
	public abstract void displayIcon();
	
	protected String wordWrap(String text, int wordWrapLength, String position) {
		wordWrapLength = wordWrapLength - 4;
		String arrangedMessage = "";
		StringBuffer buffer = new StringBuffer();
		int length = text.length();
        
        String[][] insColors = { 
			{"coloryellow", "yellow"}, 
			{"colorgold", "gold"},
			{"colorred", "red"}, 
			{"colorblue", "blue"},
			{"colorgreen", "green"}, 
			{"colorcyan", "cyan"},
			{"colorbrown", "brown"},
			{"colorblack", " black"}
		};

        if(length <= wordWrapLength) {
        	if(position.equalsIgnoreCase("Left")) {
        		arrangedMessage = "&nbsp;&nbsp;&nbsp;&nbsp;"+ text + "&nbsp;&nbsp;";
        		arrangedMessage = arrangedMessage.replaceAll("<br>","<br>&nbsp;&nbsp;&nbsp;");
        	} else {
        		arrangedMessage = text + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        	}
        	
        	/*for(int i = length; i < wordWrapLength; i++) {
        		arrangedMessage = arrangedMessage + "&nbsp;";
        	}*/
        	
        } else {
           String split[] = text.split(" ");
           for (int j = 0; j < split.length; j++) {
               buffer.append(split[j] + " "); 

               if (buffer.length() >= wordWrapLength) { 
                   int lastindex = buffer.lastIndexOf(" ");
                   if (lastindex < buffer.length()) {
                       buffer.subSequence(lastindex, buffer.length() - 1);
                       String tempTxt = buffer.toString();
                       if(position.equalsIgnoreCase("Left")) {
                    	   tempTxt = tempTxt.replaceAll("<br>","<br>&nbsp;&nbsp;&nbsp;");
                    	   tempTxt = tempTxt + "&nbsp;&nbsp;<br>";
                    	   arrangedMessage = arrangedMessage + "&nbsp;&nbsp;&nbsp;&nbsp;" + tempTxt;
                       } else {
                    	   tempTxt = tempTxt.replaceAll("<br>","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>");
                    	   tempTxt = tempTxt + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>";
                    	   arrangedMessage = arrangedMessage + tempTxt;
                       }
                       buffer = null;
                       buffer = new StringBuffer();
                   }
               } else {
            	 if(j == split.length - 1) {
            		 String tempTxt = buffer.toString();
            		 if(position.equalsIgnoreCase("Left")) {
	                     tempTxt = tempTxt.replaceAll("<br>","<br>&nbsp;&nbsp;&nbsp;");
	            		 arrangedMessage = arrangedMessage + "&nbsp;&nbsp;&nbsp;&nbsp;" + tempTxt + "&nbsp;&nbsp;";
            		 } else {
            			 arrangedMessage = arrangedMessage + tempTxt + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            		 }
            	 }
               }
           }
        }
        // Handle Colors
        for (int i = 0; i < insColors.length; i++) {
        	arrangedMessage = arrangedMessage.replaceAll("<"+ insColors[i][0]  +">", "<font color=\"" + insColors[i][1] + "\">");
            arrangedMessage = arrangedMessage.replaceAll("</" + insColors[i][0] + ">", "</font>");
		}
        return arrangedMessage;
	}
}
