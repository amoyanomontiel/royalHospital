/**
 * New mail view which allows you to send emails
 * @author Fernando Cañadas Ortega
 * @version 1.0
 * Realizado el 9 november 2020
 */

package views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FilenameUtils;

import com.royalhospital.royalHospital.DataModel;
import com.royalhospital.royalHospital.Extensions;
import com.royalhospital.royalHospital.ListEmailViews;
import com.royalhospital.royalHospital.SendNewMail;
import com.royalhospital.royalHospital.UploadedFile;

import listeners.CheckEmailAddressListener;
import listeners.OpenNewEmailListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewMailView extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int width = 30;
	private final int height = 30;
	private ArrayList<UploadedFile> fileList;
	private JPanel uploadedFilesPane;
	private Extensions extensions;
	private String gmailUsername = MainMailView.getTxtUserName().getText();
	private String gmailPassword = new String(MainMailView.getTxtPassword().getPassword());
	private JTextField textFieldAddressee;
	private JTextField textFieldSubjectText;
	private JTextArea textAreaBody;
	private static JFrame instance = null;
	private DataModel model;

	/**
	 * Method that creates a file selector to select any file on the computer and
	 * saves the file name and its path in an array
	 * 
	 * @param contentPane container frame where all the elements of the window are
	 *                    put
	 */
	public void createFileChooser(JPanel contentPane) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.out.println(e.getMessage());
		}
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

	/**
	 * Method that when he has the scroll container, refreshes the panel where the
	 * attached files are displayed
	 */
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

	/**
	 * Method that fills a two-dimensional array with the name of the attached
	 * files, the icons of to the extensions and the check box to delete those files
	 * 
	 * @param Number of cells the array will have, depending on the number of
	 *               attachments
	 * @return two-dimensional array that contains the name of the attached files,
	 *         the icons of to the extensions and the check box to delete those
	 *         files
	 */
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

	/**
	 * Method that receives the extension of a file and depending on its extension
	 * an icon or another is assigned
	 * 
	 * @param extension String that contains the extension of the file
	 * @return String that contains the route where the icon is saved
	 */
	private String searchImage(String extension) {
		switch (extension) {
		case "mp4":
			return model.getMp4_mp3_webm_gifRoute();

		case "mp3":
			return model.getMp4_mp3_webm_gifRoute();

		case "webm":
			return model.getMp4_mp3_webm_gifRoute();

		case "gif":
			return model.getMp4_mp3_webm_gifRoute();

		case "docx":
			return model.getDocxRoute();

		case "png":
			return model.getPng_jpg_jpegRoute();

		case "jpg":
			return model.getPng_jpg_jpegRoute();

		case "txt":
			return model.getTxtRoute();

		case "pdf":
			return model.getPdfRoute();

		case "jpeg":
			return model.getPng_jpg_jpegRoute();

		case "bmp":
			return model.getPng_jpg_jpegRoute();

		case "zip":
			return model.getCompressedRoute();

		case "7z":
			return model.getCompressedRoute();

		case "rar":
			return model.getCompressedRoute();

		case "gz":
			return model.getCompressedRoute();

		case "sitx":
			return model.getCompressedRoute();

		case "sql":
			return model.getSqlRoute();

		case "java":
			return model.getJarRoute();

		case "jar":
			return model.getJarRoute();

		default:
			return model.getGeneralIconRoute();
		}
	}

	/**
	 * Method that introduces the panel with all attachments in a scroll container
	 * so they can view
	 * 
	 * @param panel JPanel that contains all the attachments with its icons
	 */
	public void createScrollPane(JPanel panel) {
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 515, 61);
		uploadedFilesPane.setPreferredSize(new Dimension(515, 61));
		uploadedFilesPane.add(scrollPane);
	}

	/**
	 * Method that is responsible for receiving information from all text boxes and
	 * the array of attachments and make different checks with that information
	 * 
	 * @param addresseeText TextField that contains the addressee to whom the
	 *                      message is aimed to
	 * @param subjectText   TextField that contains the subject of the message
	 * @param bodyText      TextField that contains the message body
	 * @param action        String that indicate if you want to send the message or
	 *                      dispose the message
	 */
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
			boolean messageState = false;

			if (emptyaddressee) {
				JOptionPane.showMessageDialog(null, model.getAddresseeError(), model.getEmailHeader(),
						JOptionPane.WARNING_MESSAGE);
			} else if (emptysubject || emptybody) {
				int answer = JOptionPane.showConfirmDialog(null,
						model.getNoBodyNoSubjectWarning(), model.getEmailHeader(),
						JOptionPane.YES_NO_OPTION);
				if (answer == 0) {
					messageState = newGmail.sendNewGmail(addressee, subject, body, fileList);
				}
			} else {
				messageState = newGmail.sendNewGmail(addressee, subject, body, fileList);
			}
			if (messageState) {
				JOptionPane.showMessageDialog(null, model.getEmailSent(), model.getEmailHeader(),
						JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, model.getEmailNotSent(),
						model.getEmailHeader(), JOptionPane.WARNING_MESSAGE);
			}
		} else {
			if (!emptyaddressee || !emptysubject || !emptybody || fileList.size() != 0) {
				int answer = JOptionPane.showConfirmDialog(null, model.getDisposeMessage(),
						model.getEmailHeader(), JOptionPane.YES_NO_OPTION);
				if (answer == 0) {
					setVisible(false);
				}
			} else {
				setVisible(false);
			}
		}
	}

	/**
	 * Create the frame
	 */
	public NewMailView() {

		instance = this;
		ListEmailViews.getAllEmailView().add(instance);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				checkTextBoxes(textFieldAddressee, textFieldSubjectText, textAreaBody, "cancel");
			}
		});
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fileList = new ArrayList<UploadedFile>();
					extensions = new Extensions();
					model = new DataModel();

					setResizable(false);
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					setBounds(100, 100, 850, 700);
					JPanel contentPane = new JPanel();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					setContentPane(contentPane);
					contentPane.setLayout(null);

					JLabel lblAddressee = new JLabel("Destinatario");
					lblAddressee.setFont(new Font("Tahoma", Font.BOLD, 15));
					lblAddressee.setBounds(67, 29, 102, 27);
					contentPane.add(lblAddressee);

					textFieldAddressee = new JTextField();
					textFieldAddressee.setFont(new Font("Tahoma", Font.PLAIN, 15));
					textFieldAddressee.setBounds(230, 29, 515, 27);
					contentPane.add(textFieldAddressee);
					textFieldAddressee.setColumns(10);

					JLabel lblSubject = new JLabel("Asunto");
					lblSubject.setFont(new Font("Tahoma", Font.BOLD, 15));
					lblSubject.setBounds(67, 87, 62, 27);
					contentPane.add(lblSubject);

					textFieldSubjectText = new JTextField();
					textFieldSubjectText.setFont(new Font("Times New Roman", Font.PLAIN, 18));
					textFieldSubjectText.setColumns(10);
					textFieldSubjectText.setBounds(230, 87, 515, 27);
					contentPane.add(textFieldSubjectText);

					textAreaBody = new JTextArea();
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
					ImageIcon fileIcon = new ImageIcon(model.getDownloadIconRoute());
					Icon newFileIcon = new ImageIcon(
							fileIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
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
					ImageIcon sendIcon = new ImageIcon(model.getSendIconRoute());
					Icon newSendIcon = new ImageIcon(
							sendIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
					btnSend.setIcon(newSendIcon);
					contentPane.add(btnSend);

					JLabel lblWarning = new JLabel("");
					lblWarning.setEnabled(false);
					lblWarning.setBounds(760, 21, 55, 40);
					lblWarning.setVisible(false);
					ImageIcon warningIcon = new ImageIcon(model.getWarningRoute());
					Icon newWarningIcon = new ImageIcon(
							warningIcon.getImage().getScaledInstance(width + 15, height + 10, Image.SCALE_DEFAULT));
					lblWarning.setIcon(newWarningIcon);
					contentPane.add(lblWarning);

					CheckEmailAddressListener.addNewLostFocusListener(textFieldAddressee, lblWarning);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
	}

	public static JFrame getInstance() {
		return instance;
	}

	public static void setInstance(JFrame instance) {
		NewMailView.instance = instance;
	}

}