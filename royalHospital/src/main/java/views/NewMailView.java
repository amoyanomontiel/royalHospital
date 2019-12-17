package views;

import java.awt.Dimension;
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
import java.awt.GridLayout;

import javax.swing.JScrollPane;

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
				fileList.put(selectedFiles[i].getName(), selectedFiles[i].getAbsolutePath());
			}
		}
	}

	public void refreshUploadedFiles(JPanel uploadedFilesPane) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(fileList.size(), 1));

		Object [][] listUploadedFiles = refillArrays();
		
		for (int i = 0; i < listUploadedFiles.length; i++) {
			
		}
		
		createScrollPane(uploadedFilesPane, panel);

		uploadedFilesPane.revalidate();
//		uploadedFilesPane.repaint();
//		String extension = FilenameUtils.getExtension(value);
//		String extension = "pdf";
//		obtainExtension(contador, extension);
	}
	
	public Object [][] refillArrays() {
		Object [][] listUploadedFiles = new Object [fileList.size()][2];
		int i = 0;
		for (HashMap.Entry<String, String> entry : fileList.entrySet()) {
			String key = entry.getKey();
			//String value = entry.getValue();
			JLabel newFile = new JLabel(key);
			newFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JButton newDelete = new JButton("Borrar");
			newDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					listUploadedFiles[i][j] = newFile;
				}
				else {
					listUploadedFiles[i][j] = newDelete;
				}
			}
			i++;
		}
		return listUploadedFiles;
	}

	public void createScrollPane(JPanel uploadedFilesPane, JPanel panel) {
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 515, 61);
		uploadedFilesPane.setPreferredSize(new Dimension(515, 61));
		uploadedFilesPane.add(scrollPane);
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
		if (addressee.isEmpty())
			emptyaddressee = true;
		if (subject.isEmpty())
			emptysubject = true;
		if (body.isEmpty())
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
			} else {
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

		JLabel lblAddressee = new JLabel("Destinatario");
		lblAddressee.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAddressee.setBounds(67, 29, 102, 27);
		contentPane.add(lblAddressee);

		JTextField textFieldAddressee = new JTextField();
		textFieldAddressee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldAddressee.setBounds(230, 29, 515, 27);
		contentPane.add(textFieldAddressee);
		textFieldAddressee.setColumns(10);

		JLabel lblSubject = new JLabel("Asunto");
		lblSubject.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSubject.setBounds(67, 87, 62, 27);
		contentPane.add(lblSubject);

		JTextField textFieldSubjectText = new JTextField();
		textFieldSubjectText.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textFieldSubjectText.setColumns(10);
		textFieldSubjectText.setBounds(230, 87, 515, 27);
		contentPane.add(textFieldSubjectText);

		JTextArea textAreaBody = new JTextArea();
		textAreaBody.setBounds(67, 160, 678, 200);
		contentPane.add(textAreaBody);

		JLabel lblUploaded = new JLabel("Archivos Adjuntos");
		lblUploaded.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUploaded.setBounds(67, 416, 142, 27);
		contentPane.add(lblUploaded);

		JPanel uploadedFilesPane = new JPanel(null);
		uploadedFilesPane.setBounds(230, 405, 515, 61);
		contentPane.add(uploadedFilesPane);
		uploadedFilesPane.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewFile = new JButton(" Adjuntar");
		btnNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFileChooser(contentPane);
				refreshUploadedFiles(uploadedFilesPane);
			}
		});
		btnNewFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewFile.setBounds(67, 503, 150, 40);
		ImageIcon fileIcon = new ImageIcon("src//main//java//views//download.png");
		Icon newFileIcon = new ImageIcon(fileIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		btnNewFile.setIcon(newFileIcon);
		contentPane.add(btnNewFile);

		JButton btnSend = new JButton(" Enviar");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkTextBoxes(textFieldAddressee, textFieldSubjectText, textAreaBody, "send");
			}
		});
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSend.setBounds(595, 503, 150, 40);
		ImageIcon sendIcon = new ImageIcon("src//main//java//views//send.png");
		Icon newSendIcon = new ImageIcon(sendIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		btnSend.setIcon(newSendIcon);
		contentPane.add(btnSend);

		JButton btnCancel = new JButton(" Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkTextBoxes(textFieldAddressee, textFieldSubjectText, textAreaBody, "cancel");
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(384, 503, 150, 40);
		ImageIcon cancelIcon = new ImageIcon("src//main//java//views//cancel.png");
		Icon newCancelIcon = new ImageIcon(cancelIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		btnCancel.setIcon(newCancelIcon);
		contentPane.add(btnCancel);
	}
}
