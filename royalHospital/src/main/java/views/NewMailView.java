package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class NewMailView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField addresseeText;
	private JTextField subjectText;
	private final int width = 30;
	private final int height = 30;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewMailView frame = new NewMailView();
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
	public NewMailView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel addresseeLabel = new JLabel("Destinatario");
		addresseeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		addresseeLabel.setBounds(67, 29, 102, 27);
		contentPane.add(addresseeLabel);
		
		addresseeText = new JTextField();
		addresseeText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addresseeText.setBounds(230, 29, 515, 27);
		contentPane.add(addresseeText);
		addresseeText.setColumns(10);
		
		JLabel subjectLabel = new JLabel("Asunto");
		subjectLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		subjectLabel.setBounds(67, 87, 62, 27);
		contentPane.add(subjectLabel);
		
		subjectText = new JTextField();
		subjectText.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		subjectText.setColumns(10);
		subjectText.setBounds(230, 87, 515, 27);
		contentPane.add(subjectText);
		
		JTextArea bodyText = new JTextArea();
		bodyText.setBounds(67, 160, 678, 200);
		contentPane.add(bodyText);
		
		JScrollBar uploadedScroll = new JScrollBar();
		uploadedScroll.setBounds(278, 405, 467, 48);
		contentPane.add(uploadedScroll);
		
		JLabel uploadedLabel = new JLabel("Archivos Adjuntos");
		uploadedLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		uploadedLabel.setBounds(67, 416, 142, 27);
		contentPane.add(uploadedLabel);
		
		JButton newFileButton = new JButton(" Adjuntar");
		newFileButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newFileButton.setBounds(67, 503, 150, 40);
		ImageIcon fileIcon = new ImageIcon("src//main//java//views//descarga.png");
		Icon newFileIcon = new ImageIcon(fileIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		newFileButton.setIcon(newFileIcon);
		contentPane.add(newFileButton);
		
		JButton sendButton = new JButton(" Enviar");
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sendButton.setBounds(595, 503, 150, 40);
		ImageIcon sendIcon = new ImageIcon("src//main//java//views//enviar.png");
		Icon newSendIcon = new ImageIcon(sendIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		sendButton.setIcon(newSendIcon);
		contentPane.add(sendButton);
		
		JButton CancelButton = new JButton(" Cancelar");
		CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CancelButton.setBounds(384, 503, 150, 40);
		ImageIcon cancelIcon = new ImageIcon("src//main//java//views//cancelar.png");
		Icon newCancelIcon = new ImageIcon(cancelIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		CancelButton.setIcon(newCancelIcon);
		contentPane.add(CancelButton);
	}
}
