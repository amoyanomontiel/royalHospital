//package gestionHospital;
//
//import java.io.IOException;
//import java.util.Arrays;
//
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPFile;
//
//public class ClientFTP {
//	static String sFTP = "localhost";
//	static String sUser = "cris";
//	static String sPassword = "";
//
//	public static void main(String[] args) {
//		FTPClient client = new FTPClient();
//
//		try {
//			client.connect(sFTP,9000);
//			boolean login = client.login(sUser, sPassword);
//			String[] array = client.listNames();
//			System.out.println(Arrays.asList(array));
//			FTPFile[] direc = client.listDirectories();
//			System.out.println(Arrays.asList(direc));
//		} catch (IOException ioe) {
//			System.out.println(ioe.getMessage());
//			System.out.println("nada");
//		}
//	}
//}
package com.royalhospital.royalHospital;

import java.io.BufferedReader;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import org.apache.commons.net.ftp.FTPClient;

public class ClientFTP extends JFrame implements ActionListener {
	private JButton boton1, boton2, boton3, boton4, boton5;

	private JLabel usuario, servidor, pass;

	private JTextField users, servi;

	private JPasswordField contra;

	private JTextArea consola;

	JFileChooser fileChooser; /* Declaramos el objeto fileChooser */
	FTPClient ftpcliente;

	public ClientFTP() {
		fileChooser = new JFileChooser();
		ftpcliente = new FTPClient();
		setLayout(null);
		boton1 = new JButton("UPLOAD");
		boton1.setBounds(10, 100, 100, 30);
		add(boton1);
		boton1.addActionListener(this);
		boton2 = new JButton("BORRAR");
		boton2.setBounds(110, 100, 100, 30);
		add(boton2);
		boton2.addActionListener(this);
		boton3 = new JButton("LISTAR");
		boton3.setBounds(210, 100, 100, 30);
		add(boton3);
		boton3.addActionListener(this);
		boton4 = new JButton("FINALIZAR");
		boton4.setBounds(310, 100, 100, 30);
		add(boton4);
		boton4.addActionListener(this);
		boton5 = new JButton("CONECTAR");
		boton5.setBounds(410, 100, 100, 30);
		add(boton5);
		boton5.addActionListener(this);
		usuario = new JLabel("USUARIO");
		usuario.setBounds(10, 20, 90, 30);
		add(usuario);
		servidor = new JLabel("SERVIDOR");
		servidor.setBounds(110, 20, 90, 30);
		add(servidor);
		pass = new JLabel("CONTRASE�A");
		pass.setBounds(210, 20, 90, 30);
		add(pass);
		users = new JTextField(10);
		users.setBounds(10, 50, 90, 30);
		add(users);
		servi = new JTextField(10);
		servi.setBounds(110, 50, 90, 30);
		add(servi);
		contra = new JPasswordField(10);
		contra.setBounds(210, 50, 90, 30);
		add(contra);
		consola = new JTextArea();// �rea de texto
		consola.setBounds(10, 150, 500, 100);
		add(consola);
	}

	private String abrirArchivo() {
		String texto = "";
		/* llamamos el metodo que permite cargar la ventana */
		fileChooser.showOpenDialog(this);
		/* abrimos el archivo seleccionado */
		File abre = fileChooser.getSelectedFile();

		/*
		 * recorremos el archivo, lo leemos para plasmarlo en el area de texto
		 */
		if (abre != null) {
			texto = abre.getAbsolutePath();
		}
		return texto;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == boton1) {

			setTitle("UPLOAD");// Acci�n en el JFrame

			int replay = ftpcliente.getReplyCode();
			if (FTPReply.isPositiveCompletion(replay)) {
				FileInputStream input = null;
				try {
					String archivo = abrirArchivo();
					System.out.println(archivo);
					File file = new File(archivo);
					input = new FileInputStream(file);
					try {
						ftpcliente.setFileType(FTP.BINARY_FILE_TYPE);
						ftpcliente.storeFile(file.getName(), input);
					} catch (IOException ex) {
						Logger.getLogger(ClientFTP.class.getName()).log(Level.SEVERE, null, ex);
					}

					ftpcliente.enterLocalPassiveMode();
					consola.append("Subi� Satisfactoriamente el archivo\n");
					System.out.println("Subi� satisfactoriamente el archivo\n");

				} catch (FileNotFoundException ex) {
					Logger.getLogger(ClientFTP.class.getName()).log(Level.SEVERE, null, ex);
				} finally {
					try {
						input.close();
					} catch (IOException ex) {
						Logger.getLogger(ClientFTP.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			} else {
				consola.append("No se pudo subir el archivo verifica que est�s conectado\n");
				System.out.println("No se pudo subir el archivo verifica que est�s conectado\n");
			}
		}
		if (e.getSource() == boton2) {

			setTitle("BORRAR");

			int replay = ftpcliente.getReplyCode();
			if (FTPReply.isPositiveCompletion(replay)) {
				try {
					boolean deleted = ftpcliente.deleteFile("mismascotas.db");
					if (deleted) {
						consola.append("Se borr� el archivo\n");
						System.out.println("Se borr� el archivo\n");
					} else {
						consola.append("No se pudo borrar el archivo\n");
						System.out.println("No se pudo borrar el archivo\n");
					}

				} catch (IOException ex) {
					Logger.getLogger(ClientFTP.class.getName()).log(Level.SEVERE, null, ex);
				}
			} else {
				consola.append("No se pudo borrar el archivo verifica que est�s conectado al servidor\n");
				System.out.println("No se pudo borrar el archivo verifica que est�s conectado al servidor\n");
			}
		}
		if (e.getSource() == boton3) {

			setTitle("LISTAR");// Acci�n en el JFrame

			int replay = ftpcliente.getReplyCode();
			if (FTPReply.isPositiveCompletion(replay)) {
				try {
					for (FTPFile file : ftpcliente.listFiles()) {
						System.out.printf("%s %s [%d bytes]\n", (file.isDirectory() ? "[D]" : "   "), file.getName(),
								file.getSize());
					}
					consola.append("Listado de archivos y carpetas del servidor\n");
				} catch (IOException ex) {
					Logger.getLogger(ClientFTP.class.getName()).log(Level.SEVERE, null, ex);
				}

			} else {
				consola.append("No puedes listar archivos, inicia sesion primero\n");
				System.out.println("No puedes listar archivos, inicia sesion primero\n");
			}
		}
		if (e.getSource() == boton4) {
			try {
				ftpcliente.disconnect();
			} catch (IOException ex) {
				Logger.getLogger(ClientFTP.class.getName()).log(Level.SEVERE, null, ex);
			}
			consola.append("Se finalizo satisfactoriamente la aplicacion\n");
			consola.append("Te haz desconectado satisfactoriamente del servidor\n");
			System.out.println("Se finalizo satisfactoriamente la aplicacion\n");
			System.out.println("Te haz desconectado satisfactoriamente del servidor\n");
			System.exit(0);
		}
		if (e.getSource() == boton5) {

			setTitle("CONECTAR"); // Accion en el JFrame

			ftpcliente = new FTPClient();
			try {
				ftpcliente.connect(servi.getText(), 9000);
				if (ftpcliente.login(users.getText(), new String(contra.getPassword()))) {
					consola.append("Conectado con el servidor\n");
					System.out.println("Conectado con el servidor\n");
				} else {
					consola.append("No te has podido conectar, inicia sesi�n primero\n");
					System.out.println("No te has podido conectar, inicia sesion primero\n");
				}
			} catch (IOException ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		ClientFTP formulario1 = new ClientFTP();
		formulario1.setBounds(0, 0, 550, 300);
		formulario1.setVisible(true);// Habilitar formulario
		formulario1.setLocationRelativeTo(null);
		formulario1.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}