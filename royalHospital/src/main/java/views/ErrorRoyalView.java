package views;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.royalhospital.royalHospital.DataModel;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;

/**
 * View class for errors and warnings
 * 
 * @author Daniel Cuenca
 *
 */
public class ErrorRoyalView extends JDialog {

	private JPanel contentPane;
	DataModel data = new DataModel();

	/**
	 * Creates the frame depending type
	 * 
	 * @param String error Error message
	 * @param int    i Indicates a warning or an error type
	 */
	public ErrorRoyalView(String error, int i) {
		setResizable(false);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 220);
		ImageIcon royal = new ImageIcon(data.getIconRoyalLogo());
		setIconImage(royal.getImage());
		setTitle(data.getErrorTag());
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMessage = new JLabel(error);
		lblMessage.setBounds(95, 48, 350, 46);
		contentPane.add(lblMessage);

		JLabel img_error = new JLabel();
		img_error.setBounds(34, 48, 49, 46);

		if (i == 0) {
			ImageIcon img = new ImageIcon(ErrorRoyalView.class.getResource(data.getErrorIconRoute()));
			Icon icon = new ImageIcon(
					img.getImage().getScaledInstance(img_error.getWidth(), img_error.getHeight(), Image.SCALE_DEFAULT));
			img_error.setIcon(icon);
		} else {
			ImageIcon img_warning = new ImageIcon(ErrorRoyalView.class.getResource(data.getWarningIconRoute()));
			Icon icon_warning = new ImageIcon(img_warning.getImage().getScaledInstance(img_error.getWidth(),
					img_error.getHeight(), Image.SCALE_DEFAULT));
			img_error.setIcon(icon_warning);
		}

		contentPane.add(img_error);

		JButton btnAccept = new JButton(data.getAcceptTag());
		btnAccept.setBounds(190, 123, 100, 30);
		contentPane.add(btnAccept);

		btnAccept.addActionListener(new ActionListener() {
			/**
			 * Function which close the frame when the accept button is clicked
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
}
