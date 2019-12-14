package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import com.royalhospital.royalHospital.MailMethods;

public class ScrollEmailListener {
	
	public static void addScrollEmailListener(JComboBox JComboBoxParam) {
		JComboBoxParam.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if((JComboBoxParam.getSelectedIndex()-1) >= 0) {
					com.royalhospital.royalHospital.MailMethods.generateJEditorPaneEmail(JComboBoxParam.getSelectedIndex()-1);	
				}
				
			}
		});
	}
	
}
