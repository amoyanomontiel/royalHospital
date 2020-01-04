package views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FilenameUtils;

import com.royalhospital.royalHospital.Extensions;
import com.royalhospital.royalHospital.SendNewMail;
import com.royalhospital.royalHospital.UploadedFile;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import javax.swing.JScrollPane;
import java.awt.Color;

public class NewMailView extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int width = 30;
	private final int height = 30;
	private ArrayList<UploadedFile> fileList;
	private JPanel uploadedFilesPane;
	private Extensions extensions;
	private String gmailUsername = "thenapo212@gmail.com";
	private String gmailPassword = "N@pitoG@tito2";

	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					NewMailView frame = new NewMailView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					System.out.println(e.getMessage());
//				}
//			}
//		});
//	}

	public void createFileChooser(JPanel contentPane) {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setMultiSelectionEnabled(true);
		int seleccion = fc.showOpenDialog(contentPane);

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File[] selectedFiles = fc.getSelectedFiles();

			for (int i = 0; i < selectedFiles.length; i++) {
				fileList.add(new UploadedFile(selectedFiles[i].getName(), selectedFiles[i].getAbsolutePath()));
			}
		}
	}

	public void refreshUploadedFiles() {
		int cellsNumber = 3;
		if (uploadedFilesPane.getComponentCount() != 0) {
			uploadedFilesPane.removeAll();
		}
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(fileList.size(), cellsNumber));

		Object[][] listUploadedFiles = refillArrays(cellsNumber);

		for (int i = 0; i < fileList.size(); i++) {
			for (int j = 0; j < cellsNumber; j++) {
				panel.add((Component) listUploadedFiles[i][j]);
			}
		}

		createScrollPane(panel);

		uploadedFilesPane.revalidate();
	}

	public Object[][] refillArrays(int cellsNumber) {
		Object[][] listUploadedFiles = new Object[fileList.size()][cellsNumber];
		int i = 0;
		for (UploadedFile attachedFile : fileList) {
			String extension = FilenameUtils.getExtension(attachedFile.getFileName());
			String basename = FilenameUtils.getBaseName(attachedFile.getFileName());
			for (int j = 0; j < cellsNumber; j++) {
				if (j == 0) {
					JLabel newIcon = new JLabel();
					String route = searchImage(extension);

					ImageIcon extensionIcon = new ImageIcon(route);
					Icon newExtensionIcon = new ImageIcon(
							extensionIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
					newIcon.setIcon(newExtensionIcon);
					newIcon.putClientProperty("id", "ic" + i);
					listUploadedFiles[i][j] = newIcon;

				} else if (j == 1) {
					JLabel newFile;
					if (basename.length() > 15) {
						String fileName = basename.substring(0, 16) + "..." + extension;
						newFile = new JLabel(fileName);
					} else {
						newFile = new JLabel(basename + "." + extension);
					}
					newFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
					newFile.putClientProperty("id", "jl" + i);
					if (extensions.getTextExtensions().contains(extension)) {
						File fichero = new File(attachedFile.getFilePath());
						if (fichero.length() == 0) {
							newFile.setForeground(Color.RED);
							newFile.setToolTipText("Este archivo contiene 0 bytes; por tanto, no se adjuntará");
						}
					}
					
					if (extensions.getForbiddenGmailExtensions().contains(extension)) {
						newFile.setForeground(Color.RED);
						newFile.setToolTipText("Este archivo no se puede mandar por gmail por motivos de seguridad");
					}
					
					listUploadedFiles[i][j] = newFile;

				} else if (j == 2) {
					JCheckBox newDelete = new JCheckBox("Cancelar");
					newDelete.putClientProperty("id", "cb" + i);
					newDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
					newDelete.addItemListener(new ItemListener() {

						@Override
						public void itemStateChanged(ItemEvent e) {
							if (e.getStateChange() == ItemEvent.SELECTED) {
								JCheckBox aux = (JCheckBox) e.getItem();
								String id = (String) aux.getClientProperty("id");
								int position = Integer.valueOf(id.substring(2));
								fileList.remove(position);
								refreshUploadedFiles();
							}
						}
					});

					listUploadedFiles[i][j] = newDelete;
				}
			}
			i++;
		}
		return listUploadedFiles;
	}

	private String searchImage(String extension) {
		switch (extension) {
		case "mp4":
			return "src//main//java//views//mp4_mp3_webm_gif.png";

		case "mp3":
			return "src//main//java//views//mp4_mp3_webm_gif.png";

		case "webm":
			return "src//main//java//views//mp4_mp3_webm_gif.png";

		case "gif":
			return "src//main//java//views//mp4_mp3_webm_gif.png";

		case "docx":
			return "src//main//java//views//docx.png";

		case "png":
			return "src//main//java//views//png_jpg_jpeg.png";

		case "jpg":
			return "src//main//java//views//png_jpg_jpeg.png";

		case "txt":
			return "src//main//java//views//txt.png";

		case "pdf":
			return "src//main//java//views//pdf.png";

		case "jpeg":
			return "src//main//java//views//png_jpg_jpeg.png";

		case "bmp":
			return "src//main//java//views//png_jpg_jpeg.png";

		case "zip":
			return "src//main//java//views//compressed.png";

		case "7z":
			return "src//main//java//views//compressed.png";

		case "rar":
			return "src//main//java//views//compressed.png";

		case "sql":
			return "src//main//java//views//sql.png";

		case "java":
			return "src//main//java//views//jar.png";

		default:
			return "src//main//java//views//general icon.png";
		}
	}

	public void createScrollPane(JPanel panel) {
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 515, 61);
		uploadedFilesPane.setPreferredSize(new Dimension(515, 61));
		uploadedFilesPane.add(scrollPane);
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
			SendNewMail newGmail = new SendNewMail(gmailUsername, gmailPassword);
			
			if (emptyaddressee) {
				JOptionPane.showMessageDialog(null, "Debes introducir un destinatario", "Introduzca Destinatario",
						JOptionPane.WARNING_MESSAGE);
			} else if (emptysubject || emptybody) {
				int answer = JOptionPane.showConfirmDialog(null,
						"¿Estás seguro de que quieres enviar un mensaje sin asunto o sin cuerpo?", "Enviar Mensaje",
						JOptionPane.YES_NO_OPTION);
				if (answer == 0) {
					newGmail.sendNewGmail(addressee, subject, body, fileList);
				}
			} else {
				newGmail.sendNewGmail(addressee, subject, body, fileList);
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
		fileList = new ArrayList<UploadedFile>();
		extensions = new Extensions();

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 700);
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
		textAreaBody.setWrapStyleWord(true);
		textAreaBody.setLineWrap(true);
		contentPane.add(textAreaBody);

		JLabel lblUploaded = new JLabel("Archivos Adjuntos");
		lblUploaded.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUploaded.setBounds(340, 392, 142, 27);
		contentPane.add(lblUploaded);

		uploadedFilesPane = new JPanel(null);
		uploadedFilesPane.setBorder(null);
		uploadedFilesPane.setBounds(67, 441, 678, 105);
		contentPane.add(uploadedFilesPane);
		uploadedFilesPane.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewFile = new JButton(" Adjuntar");
		btnNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFileChooser(contentPane);
				refreshUploadedFiles();
			}
		});
		btnNewFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewFile.setBounds(67, 593, 150, 40);
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
		btnSend.setBounds(595, 593, 150, 40);
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
		btnCancel.setBounds(381, 593, 150, 40);
		ImageIcon cancelIcon = new ImageIcon("src//main//java//views//cancel.png");
		Icon newCancelIcon = new ImageIcon(cancelIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		btnCancel.setIcon(newCancelIcon);
		contentPane.add(btnCancel);
		setVisible(true);
	}
}