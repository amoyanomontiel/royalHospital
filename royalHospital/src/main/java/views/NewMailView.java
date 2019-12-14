package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class NewMailView extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int width = 30;
	private final int height = 30;
	private HashMap<String, String> fileList;

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

	public void createFileChooser(JPanel contentPane) {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setMultiSelectionEnabled(true);
		int seleccion = fc.showOpenDialog(contentPane);

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File[] selectedFiles = fc.getSelectedFiles();

			for (int i = 0; i < selectedFiles.length; i++) {
				if (!fileList.containsKey(selectedFiles[i].getName()))
					;
				fileList.put(selectedFiles[i].getName(), selectedFiles[i].getAbsolutePath());
			}
		}
	}

	public void refreshUploadedFiles(JPanel uploadedFilesPanel, GridBagConstraints gblc_uploadedFilesPanel) {
		int contador = 0;
		for (HashMap.Entry<String, String> entry : fileList.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			// String extension = FilenameUtils.getExtension(value);
			String extension = "pdf";
			obtainExtension(contador, extension);

			JLabel newFile = new JLabel(key);
			newFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
			gblc_uploadedFilesPanel.gridx = 1;
			gblc_uploadedFilesPanel.gridy = contador;
			uploadedFilesPanel.add(newFile, gblc_uploadedFilesPanel);
			contador++;
		}

		uploadedFilesPanel.revalidate();
	}

	public void obtainExtension(int contador, String extension) {
		if (extension.equals("pdf")) {
			// poner icono del archivo
		} else if (extension.equals("png") || extension.equals("jpg")) {
			// poner icono del archivo
		} else if (extension.equals("docx")) {
			// poner icono del archivo
		} else if (extension.equals("mp4") || extension.equals("mp3") || extension.equals("webm")
				|| extension.equals("gif")) {
			// poner icono del archivo
		} else if (extension.equals("txt")) {
			// poner icono del archivo
		} else {
			// poner icono del archivo
		}
	}

	public void checkTextBoxes(JTextField addresseeText, JTextField subjectText, JTextArea bodyText, String action) {
		String addressee = addresseeText.getText();
		String subject = subjectText.getText();
		String body = bodyText.getText();

		boolean emptyaddressee = false;
		boolean emptysubject = false;
		boolean emptybody = false;
		if (addressee.isBlank() || addressee.isEmpty())
			emptyaddressee = true;
		if (subject.isBlank() || subject.isEmpty())
			emptysubject = true;
		if (body.isBlank() || body.isEmpty())
			emptybody = true;

		if (action.equals("send")) {
			if (emptyaddressee) {
				JOptionPane.showMessageDialog(null, "Debes introducir un destinatario", "Introduzca Destinatario",
						JOptionPane.WARNING_MESSAGE);
			} else if (emptysubject || emptybody) {
				int answer = JOptionPane.showConfirmDialog(null,
						"¿Estás seguro de que quieres enviar un mensaje sin asunto o sin cuerpo?", "Enviar Mensaje",
						JOptionPane.YES_NO_OPTION);
				if (answer == 0) {
					// enviar mensaje
				}
			}
			else {
				// enviar mensaje
			}
		} else {
			if (!emptyaddressee || !emptysubject || !emptybody) {
				int answer = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres desechar el mensaje?",
						"Desechar Mensaje", JOptionPane.YES_NO_OPTION);
				if (answer == 0) {
					System.exit(0);
				}
			} else {
				System.exit(0);
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public NewMailView() {
		fileList = new HashMap<String, String>();

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 617);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel addresseeLabel = new JLabel("Destinatario");
		addresseeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		addresseeLabel.setBounds(67, 29, 102, 27);
		contentPane.add(addresseeLabel);

		JTextField addresseeText = new JTextField();
		addresseeText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addresseeText.setBounds(230, 29, 515, 27);
		contentPane.add(addresseeText);
		addresseeText.setColumns(10);

		JLabel subjectLabel = new JLabel("Asunto");
		subjectLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		subjectLabel.setBounds(67, 87, 62, 27);
		contentPane.add(subjectLabel);

		JTextField subjectText = new JTextField();
		subjectText.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		subjectText.setColumns(10);
		subjectText.setBounds(230, 87, 515, 27);
		contentPane.add(subjectText);

		JTextArea bodyText = new JTextArea();
		bodyText.setBounds(67, 160, 678, 200);
		contentPane.add(bodyText);

		JLabel uploadedLabel = new JLabel("Archivos Adjuntos");
		uploadedLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		uploadedLabel.setBounds(67, 416, 142, 27);
		contentPane.add(uploadedLabel);

		JPanel uploadedFilesPanel = new JPanel();
		uploadedFilesPanel.setBounds(230, 406, 515, 61);
		contentPane.add(uploadedFilesPanel);

		GridBagLayout gbl_uploadedFilesPanel = new GridBagLayout();
		uploadedFilesPanel.setLayout(gbl_uploadedFilesPanel);
		GridBagConstraints gblc_uploadedFilesPanel = new GridBagConstraints();
		gblc_uploadedFilesPanel.weightx = 1;
		gblc_uploadedFilesPanel.weighty = 1;
		gblc_uploadedFilesPanel.fill = GridBagConstraints.BOTH;

		JButton newFileButton = new JButton(" Adjuntar");
		newFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFileChooser(contentPane);
				refreshUploadedFiles(uploadedFilesPanel, gblc_uploadedFilesPanel);
			}
		});
		newFileButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newFileButton.setBounds(67, 503, 150, 40);
		ImageIcon fileIcon = new ImageIcon("src//main//java//views//download.png");
		Icon newFileIcon = new ImageIcon(fileIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));

		newFileButton.setIcon(newFileIcon);
		contentPane.add(newFileButton);

		JButton sendButton = new JButton(" Enviar");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkTextBoxes(addresseeText, subjectText, bodyText, "send");
			}
		});
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sendButton.setBounds(595, 503, 150, 40);
		ImageIcon sendIcon = new ImageIcon("src//main//java//views//send.png");
		Icon newSendIcon = new ImageIcon(sendIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		sendButton.setIcon(newSendIcon);
		contentPane.add(sendButton);

		JButton CancelButton = new JButton(" Cancelar");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkTextBoxes(addresseeText, subjectText, bodyText, "cancel");
			}
		});
		CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CancelButton.setBounds(384, 503, 150, 40);
		ImageIcon cancelIcon = new ImageIcon("src//main//java//views//cancel.png");
		Icon newCancelIcon = new ImageIcon(cancelIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		CancelButton.setIcon(newCancelIcon);
		contentPane.add(CancelButton);
	}
}
