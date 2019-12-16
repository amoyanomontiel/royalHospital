package Listeners;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import views.InboxView;


public class ScrollEmailListener {
	
	static JPanel emailPanelBoxCopy;
	
	static JPanel contenPaneCopy;
	
	public static void addScrollEmailListener(JComboBox JComboBoxParam, JPanel emailPanelBox, JPanel contenPane) {
		emailPanelBoxCopy = emailPanelBox;
		contenPaneCopy = contenPane;
		JComboBoxParam.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if((e.getStateChange() == ItemEvent.SELECTED) && (JComboBoxParam.getSelectedIndex()-1) >= 0){
					contenPaneCopy.remove(emailPanelBoxCopy);
					System.out.println("pasa");
					emailPanelBoxCopy = com.royalhospital.royalHospital.MailMethods.generateJEditorPaneEmail(JComboBoxParam.getSelectedIndex()-1);
					emailPanelBoxCopy.setBounds(12, 329, 810, 353);
					contenPaneCopy.add(emailPanelBoxCopy);
					emailPanelBox.revalidate();
					contenPaneCopy.revalidate();
				}
			}
		});
		
	}
	
	
}
