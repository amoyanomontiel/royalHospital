package Listeners;

// All imports
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * Class that controll the scroll of emails
 * 
 * @author Javier
 * @version 1.0
 *
 */
public class ScrollEmailListener {

	// All variables
	static JPanel emailPanelBoxCopy;
	static JPanel contenPaneCopy;

	/**
	 * Add ItemListener to Scroll
	 * 
	 * @param JComboBoxParam JComboBox, this JCombo contains all emails of user
	 * @param emailPanelBox  JPanel, this JPanel contain all elements of emails
	 * @param contenPane     JPanel, this JPanel contain all view elements
	 */
	public static void addScrollEmailListener(JComboBox JComboBoxParam, JPanel emailPanelBox, JPanel contenPane) {
		emailPanelBoxCopy = emailPanelBox;
		contenPaneCopy = contenPane;
		JComboBoxParam.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if ((e.getStateChange() == ItemEvent.SELECTED) && (JComboBoxParam.getSelectedIndex() - 1) >= 0) {
					contenPaneCopy.remove(emailPanelBoxCopy);
					emailPanelBoxCopy = com.royalhospital.royalHospital.MailMethods
							.generateJEditorPaneEmail(JComboBoxParam.getSelectedIndex() - 1);
					emailPanelBoxCopy.setLocation(100, 500);
					emailPanelBoxCopy.setBounds(12, 329, 1510, 353);
					contenPaneCopy.add(emailPanelBoxCopy);
					contenPaneCopy.setLayout(new BorderLayout(0, 0));
					contenPaneCopy.revalidate();
					contenPaneCopy.repaint();
					System.out.println("scroll");
				}
			}
		});

	}

	// All get and set
	public static JPanel getEmailPanelBoxCopy() {
		return emailPanelBoxCopy;
	}

	public static void setEmailPanelBoxCopy(JPanel emailPanelBoxCopy) {
		ScrollEmailListener.emailPanelBoxCopy = emailPanelBoxCopy;
	}

}
