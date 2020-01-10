package com.royalhospital.royalHospital;

/**
 * Data Model class for all messages, routes, button tags and general info
 * 
 * @author Cristina Montilla
 *
 */
public class DataModel {

	public static String selectedFile = "";
	public static String actualUserPath = "";
	public static String directionPath = "";
	public static int codActualUser = 0;

	private String bigRoyalLogoRoute = "/views/royalhospital.png";
	private String iconRoyalLogo = "src\\main\\java\\views\\ic_launcher.png";// Porque tan larga
	private String errorIconRoute = "/views/errorIcono.png";
	private String warningIconRoute = "views/warning.png";
	private String loginTitle = "Royal Hospital - Login";
	private String mainTitle = "Royal Hospital - Main";
	private String fontType = "Tahoma";
	// Warning Message
	private String passwordUserIncorrectMsg = "El usuario o la contraseña no son correctos";
	private String noEmptyLoginFieldsMsg = "El campo usuario y/o contraseña no pueden estar vacios";
	private String dbConectionError = "Error de conexión con la Base de Datos";
	private String ftpConectionError = "Error de conexión con el servidor";
	private String createDirLeghtMaxException = "Escribe un nombre para el directorio de máximo 20 caracteres";
	private String createDirTextEmpty = "Escribe un nombre para el directorio";
	private String createFileTextLeght = "Escriba el nombre del fichero (máximo 20 caracteres) y su extensión si lo desea.";
	private String renameFileLeghtMax = "Escriba el nombre nuevo para el fichero(máximo 20 caracteres)";

	// Tags & labels
	private String userTag = "Usuario";
	private String passwordTag = "Contraseña";
	private String loginBtnTag = "Entrar";
	private String errorTag = "Error";
	private String acceptTag = "Aceptar";
	private String uploadTag = "Cargar";
	private String uploadFileTag = "Cargar fichero";
	private String downloadTag = "Descargar";
	private String deleteTag = "Borrar";
	private String deleteDirTag = "borrar directorio";
	private String deleteFileTag = "borrar fichero";
	private String createFolderTag = "Crear Directorio";
	private String createFileTag = "Crear Fichero";
	private String renameTag = "Renombrar";
	private String documentsTag = "Documentos";
	private String patientsTag = "Pacientes";
	private String mailTag = "Correo";
	private String doctorTag = "MEDICO";
	private String patientTag = "PACIENTE";
	private String renameFileTag = "Renombrar fichero";
	private String insertNewNameLbl = "Introduce el nuevo nombre del fichero: ";
	private String insertNewFileLbl = "Introduce el nombre del fichero: ";
	private String insertNewDirLbl = "Introduce el nombre del directorio: ";
	private String logoutTag = "Cerrar Sesión";
	private String deleteQuestion = "¿Seguro que desea borrar el fichero?";
	private String correo = "Correo Electrónico";
	private String emails = "EMAILS";
	private String titulo = "Titulo:   ";
	private String sender = "Remitente:     ";

	// Data base info
	private String db = "jdbc:mysql://localhost/royalhospital";
	private String dbUser = "root";
	private String dbPassword = "";
	// FTP info
	private String ftpServer = "localhost";
	private int port = 9000;
	private String ftpUser = "usuario";
	private String ftpPassword = "usuario";
	// Record messages
	private String selectedFileMsg = "Elemento seleccionado: ";
	private String fileAlreadyExist = "El fichero ya existe en el directorio actual";
	private String fileUpload = "Cargó satisfactoriamente el fichero";
	private String uploadFake = "No se pudo cargar el fichero";
	private String selectFileForUpload = "Seleccione primero un directorio donde cargar el fichero";
	private String isFileMsg = "Para cargar un fichero debe seleccionar un directorio";
	private String isDirMsg = "Para crear un directorio es necesario seleccionar un directorio de creación";
	private String FileMsg = "Para crear un fichero es necesario seleccionar un directorio de creación";
	private String selectFileOrDir = "Seleccione primero un fichero o directorio de la lista";
	private String selectDirFirst = "Seleccione primero un directorio de creación";
	private String selectFileFirst = "Seleccione primero un fichero de la lista";
	private String selectDir = "Seleccione primero un directorio donde crear el fichero";
	private String deleteNoPossibleWithElems = "No es posible borrar el directorio si contiene algún elemento";
	private String deleteSuccess = "Se borró el directorio con éxito";
	private String deleteFileSuccess = "Se borró el fichero con éxito";
	private String deleteDirNoPossible = "No se pudo borrar el directorio";
	private String deleteFileNoPossible = "No se pudo borrar el fichero";
	private String verifyServer = "No se pudo borrar el archivo verifica que estás conectado al servidor";
	private String createDirNoPossible = "El directorio ya existe";
	private String fileRename = "El fichero fue renombrado con éxito";
	private String fileRenameError = "El fichero no pudo ser renombrado";
	private String TheDir = "El directorio '";
	private String TheFile = "El fichero '";
	private String createdSuccess = "' ha sido creado";
	private String downLoadExits = "El fichero ya está descargado, revise su directorio de descargas";
	private String downLoadSuccess = "Se descargó el fichero con éxito en su directorio de descargas ";
	private String downLoadFail = "No se pudo descargar el fichero ";
	private String downLoadDirError = "No es posible descargar un directorio ";
	private String errorAdjunto = "Error getting attachments for Email";
	private String errorCreatingJScrollPane = "Error creating JScrollPane";
	private String errorUpdatingJScrollPane = "error updating JScroll";
	private String messageErrorContentMessage = "Error reading content about messages";
	private String messageErrorGettingBodyPart = "Error getting body part about Message";
	private String messageErrorGettingMessages = "Error getting messages";
	private String messageErrorReceiveListEmail = "Error receive list of folders Email";
	private String messageErrorSettingProperties = "Error setting properties about E-mail";
	private String messageErrorUpdatingEmails = "Error auto updating emails";
	private String errorFormat = "Formato de correo inválido";
	private String messageErrorLogin = "Login Fallido";
	private String messageErrorListenerLink = "Input/outPut error";
	private String urlSyntaxError = "URL syntax error";

	// Icons
	private String mailIncon = "mailIcon.png";

	// Parameters
	private String userHome = "user.home";
	private String folderCode = "INBOX";
	private String storeCode = "imaps";
	private String hostPop3 = "mail.pop3.host";
	private String portPop3 = "mail.pop3.port";
	private String portPop = "995";
	private String codeStarttls = "mail.pop3.starttls.enable";
	private String trueString = "true";
	private String popGmail = "pop.gmail.com";
	private String pop3 = "pop3";
	private String keyMail = "@alumnado.fundacionloyola.net";

	// Path
	private String downloadRute = "\\Downloads\\";
	private String slash = "\\'";

	// HTML tags
	private String subjectHTML = "<p align= 'left'>Subject:   ";
	private String finishP = "</p>";
	private String onlyTagP = "<p></p>";
	private String senderCode = "<p align= 'left'> ";
	private String codeImg = "<img";
	private String fileSource = "<a href=file:///'";
	private String closeSymbol = "'>";
	private String closeSymbolSimple = ">";
	private String openSymbol = "<";
	private String closeLink = " </a>";
	private String openBrTag = "<br>";
	private String closeBrTag = "</br>";

	// One syllable
	private String iCaracter = "i";
	private String mCaracter = "m";
	private String gCaracter = "g";

	// bodyEmailTags
	private String codeHTML = "text/html";
	private String mineTypeText = "text/*";
	private String mineTypeAlternative = "multipart/alternative";
	private String minetypeTextPlain = "text/plain";
	private String minetypeHTML = "text/html";
	private String mineTypeMultiplart = "multipart/*";

	public DataModel() {

	}

	public String getBigRoyalLogoRoute() {
		return bigRoyalLogoRoute;
	}

	public void setBigRoyalLogoRoute(String bigRoyalLogoRoute) {
		this.bigRoyalLogoRoute = bigRoyalLogoRoute;
	}

	public String getIconRoyalLogo() {
		return iconRoyalLogo;
	}

	public void setIconRoyalLogo(String iconRoyalLogo) {
		this.iconRoyalLogo = iconRoyalLogo;
	}

	public String getLoginTitle() {
		return loginTitle;
	}

	public void setLoginTitle(String loginTitle) {
		this.loginTitle = loginTitle;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	public String getFontType() {
		return fontType;
	}

	public void setFontType(String fontType) {
		this.fontType = fontType;
	}

	public String getUserTag() {
		return userTag;
	}

	public void setUserTag(String userTag) {
		this.userTag = userTag;
	}

	public String getPasswordTag() {
		return passwordTag;
	}

	public void setPasswordTag(String passwordTag) {
		this.passwordTag = passwordTag;
	}

	public String getLoginBtnTag() {
		return loginBtnTag;
	}

	public void setLoginBtnTag(String loginBtnTag) {
		this.loginBtnTag = loginBtnTag;
	}

	public String getPasswordUserIncorrectMsg() {
		return passwordUserIncorrectMsg;
	}

	public void setPasswordUserIncorrectMsg(String passwordUserIncorrectMsg) {
		this.passwordUserIncorrectMsg = passwordUserIncorrectMsg;
	}

	public String getNoEmptyLoginFieldsMsg() {
		return noEmptyLoginFieldsMsg;
	}

	public void setNoEmptyLoginFieldsMsg(String noEmptyLoginFieldsMsg) {
		this.noEmptyLoginFieldsMsg = noEmptyLoginFieldsMsg;
	}

	public String getDbConectionError() {
		return dbConectionError;
	}

	public void setDbConectionError(String dbConectionError) {
		this.dbConectionError = dbConectionError;
	}

	public String getErrorTag() {
		return errorTag;
	}

	public void setErrorTag(String errorTag) {
		this.errorTag = errorTag;
	}

	public String getErrorIconRoute() {
		return errorIconRoute;
	}

	public void setErrorIconRoute(String errorIconRoute) {
		this.errorIconRoute = errorIconRoute;
	}

	public String getWarningIconRoute() {
		return warningIconRoute;
	}

	public void setWarningIconRoute(String warningIconRoute) {
		this.warningIconRoute = warningIconRoute;
	}

	public String getAcceptTag() {
		return acceptTag;
	}

	public void setAcceptTag(String aceptTag) {
		this.acceptTag = aceptTag;
	}

	public String getUploadTag() {
		return uploadTag;
	}

	public void setUploadTag(String uploadTag) {
		this.uploadTag = uploadTag;
	}

	public String getUploadFileTag() {
		return uploadFileTag;
	}

	public void setUploadFileTag(String uploadFileTag) {
		this.uploadFileTag = uploadFileTag;
	}

	public String getDownloadTag() {
		return downloadTag;
	}

	public void setDownloadTag(String downloadTag) {
		this.downloadTag = downloadTag;
	}

	public String getDeleteTag() {
		return deleteTag;
	}

	public void setDeleteTag(String deleteTag) {
		this.deleteTag = deleteTag;
	}

	public String getCreateFolderTag() {
		return createFolderTag;
	}

	public void setCreateFolderTag(String createFolderTag) {
		this.createFolderTag = createFolderTag;
	}

	public String getCreateFileTag() {
		return createFileTag;
	}

	public void setCreateFileTag(String createFileTag) {
		this.createFileTag = createFileTag;
	}

	public String getRenameTag() {
		return renameTag;
	}

	public void setRenameTag(String renameTag) {
		this.renameTag = renameTag;
	}

	public String getDocumentsTag() {
		return documentsTag;
	}

	public void setDocumentsTag(String documentsTag) {
		this.documentsTag = documentsTag;
	}

	public String getPatientsTag() {
		return patientsTag;
	}

	public void setPatientsTag(String patientsTag) {
		this.patientsTag = patientsTag;
	}

	public String getMailTag() {
		return mailTag;
	}

	public void setMailTag(String mailTag) {
		this.mailTag = mailTag;
	}

	public String getDoctorTag() {
		return doctorTag;
	}

	public void setDoctorTag(String doctorTag) {
		this.doctorTag = doctorTag;
	}

	public String getPatientTag() {
		return patientTag;
	}

	public void setPatientTag(String patientTag) {
		this.patientTag = patientTag;
	}

	public String getFtpConectionError() {
		return ftpConectionError;
	}

	public void setFtpConectionError(String ftpConectionError) {
		this.ftpConectionError = ftpConectionError;
	}

	public String getSelectedFileMsg() {
		return selectedFileMsg;
	}

	public void setSelectedFileMsg(String selectedFileMsg) {
		this.selectedFileMsg = selectedFileMsg;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getFtpServer() {
		return ftpServer;
	}

	public void setFtpServer(String ftpServer) {
		this.ftpServer = ftpServer;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getFtpUser() {
		return ftpUser;
	}

	public void setFtpUser(String ftpUser) {
		this.ftpUser = ftpUser;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public String getFileAlreadyExist() {
		return fileAlreadyExist;
	}

	public void setFileAlreadyExist(String fileAlreadyExist) {
		this.fileAlreadyExist = fileAlreadyExist;
	}

	public String getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(String fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getUploadFake() {
		return uploadFake;
	}

	public void setUploadFake(String uploadFake) {
		this.uploadFake = uploadFake;
	}

	public String getSelectFileForUpload() {
		return selectFileForUpload;
	}

	public void setSelectFileForUpload(String selectFileForUpload) {
		this.selectFileForUpload = selectFileForUpload;
	}

	public String getIsFileMsg() {
		return isFileMsg;
	}

	public void setIsFileMsg(String isFileMsg) {
		this.isFileMsg = isFileMsg;
	}

	public String getRenameFileTag() {
		return renameFileTag;
	}

	public void setRenameFileTag(String renameFileTag) {
		this.renameFileTag = renameFileTag;
	}

	public String getInsertNewNameLbl() {
		return insertNewNameLbl;
	}

	public void setInsertNewNameLbl(String insertNewNameLbl) {
		this.insertNewNameLbl = insertNewNameLbl;
	}

	public String getSelectFileOrDir() {
		return selectFileOrDir;
	}

	public void setSelectFileOrDir(String selectFileOrDir) {
		this.selectFileOrDir = selectFileOrDir;
	}

	public String getDeleteNoPossibleWithElems() {
		return deleteNoPossibleWithElems;
	}

	public void setDeleteNoPossibleWithElems(String deleteNoPossible) {
		this.deleteNoPossibleWithElems = deleteNoPossible;
	}

	public String getDeleteDirTag() {
		return deleteDirTag;
	}

	public void setDeleteDirTag(String deleteDirTag) {
		this.deleteDirTag = deleteDirTag;
	}

	public String getDeleteSuccess() {
		return deleteSuccess;
	}

	public void setDeleteSuccess(String deleteSuccess) {
		this.deleteSuccess = deleteSuccess;
	}

	public String getDeleteDirNoPossible() {
		return deleteDirNoPossible;
	}

	public void setDeleteDirNoPossible(String deleteDirNoPossible) {
		this.deleteDirNoPossible = deleteDirNoPossible;
	}

	public String getDeleteFileTag() {
		return deleteFileTag;
	}

	public void setDeleteFileTag(String deleteFileTag) {
		this.deleteFileTag = deleteFileTag;
	}

	public String getDeleteFileSuccess() {
		return deleteFileSuccess;
	}

	public void setDeleteFileSuccess(String deleteFileSuccess) {
		this.deleteFileSuccess = deleteFileSuccess;
	}

	public String getDeleteFileNoPossible() {
		return deleteFileNoPossible;
	}

	public void setDeleteFileNoPossible(String deleteFileNoPossible) {
		this.deleteFileNoPossible = deleteFileNoPossible;
	}

	public String getVerifyServer() {
		return verifyServer;
	}

	public void setVerifyServer(String verifyServer) {
		this.verifyServer = verifyServer;
	}

	public String getSelectFileFirst() {
		return selectFileFirst;
	}

	public void setSelectFileFirst(String selectFileFirst) {
		this.selectFileFirst = selectFileFirst;
	}

	public String getLogoutTag() {
		return logoutTag;
	}

	public void setLogoutTag(String logoutTag) {
		this.logoutTag = logoutTag;
	}

	public String getCreateDirNoPossible() {
		return createDirNoPossible;
	}

	public void setCreateDirNoPossible(String createDirNoPossible) {
		this.createDirNoPossible = createDirNoPossible;
	}

	public String getCreateDirLeghtMaxException() {
		return createDirLeghtMaxException;
	}

	public void setCreateDirLeghtMaxException(String createDirLeghtMaxException) {
		this.createDirLeghtMaxException = createDirLeghtMaxException;
	}

	public String getCreateDirTextEmpty() {
		return createDirTextEmpty;
	}

	public void setCreateDirTextEmpty(String createDirTextEmpty) {
		this.createDirTextEmpty = createDirTextEmpty;
	}

	public String getCreateFileTextLeght() {
		return createFileTextLeght;
	}

	public void setCreateFileTextLeght(String createFileTextLeght) {
		this.createFileTextLeght = createFileTextLeght;
	}

	public String getRenameFileLeghtMax() {
		return renameFileLeghtMax;
	}

	public void setRenameFileLeghtMax(String renameFileLeghtMax) {
		this.renameFileLeghtMax = renameFileLeghtMax;
	}

	public String getFileRename() {
		return fileRename;
	}

	public void setFileRename(String fileRename) {
		this.fileRename = fileRename;
	}

	public String getFileRenameError() {
		return fileRenameError;
	}

	public void setFileRenameError(String fileRenameError) {
		this.fileRenameError = fileRenameError;
	}

	public String getIsDirMsg() {
		return isDirMsg;
	}

	public void setIsDirMsg(String isDirMsg) {
		this.isDirMsg = isDirMsg;
	}

	public String getSelectDirFirst() {
		return selectDirFirst;
	}

	public void setSelectDirFirst(String selectDirFirst) {
		this.selectDirFirst = selectDirFirst;
	}

	public String getTheDir() {
		return TheDir;
	}

	public void setTheDir(String theDir) {
		TheDir = theDir;
	}

	public String getTheFile() {
		return TheFile;
	}

	public void setTheFile(String theFile) {
		TheFile = theFile;
	}

	public String getFileMsg() {
		return FileMsg;
	}

	public void setFileMsg(String fileMsg) {
		FileMsg = fileMsg;
	}

	public String getSelectDir() {
		return selectDir;
	}

	public void setSelectDir(String selectDir) {
		this.selectDir = selectDir;
	}

	public String getInsertNewFileLbl() {
		return insertNewFileLbl;
	}

	public void setInsertNewFileLbl(String insertNewFileLbl) {
		this.insertNewFileLbl = insertNewFileLbl;
	}

	public String getCreatedSuccess() {
		return createdSuccess;
	}

	public void setCreatedSuccess(String createdSuccess) {
		this.createdSuccess = createdSuccess;
	}

	public String getInsertNewDirLbl() {
		return insertNewDirLbl;
	}

	public void setInsertNewDirLbl(String insertNewDirLbl) {
		this.insertNewDirLbl = insertNewDirLbl;
	}

	public String getDownLoadExits() {
		return downLoadExits;
	}

	public void setDownLoadExits(String downLoadExits) {
		this.downLoadExits = downLoadExits;
	}

	public String getDownLoadSuccess() {
		return downLoadSuccess;
	}

	public void setDownLoadSuccess(String downLoadSuccess) {
		this.downLoadSuccess = downLoadSuccess;
	}

	public String getDownLoadFail() {
		return downLoadFail;
	}

	public void setDownLoadFail(String downLoadFail) {
		this.downLoadFail = downLoadFail;
	}

	public String getDownLoadDirError() {
		return downLoadDirError;
	}

	public void setDownLoadDirError(String downLoadDirError) {
		this.downLoadDirError = downLoadDirError;
	}

	public String getDeleteQuestion() {
		return deleteQuestion;
	}

	public void setDeleteQuestion(String deleteQuestion) {
		this.deleteQuestion = deleteQuestion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getMailIncon() {
		return mailIncon;
	}

	public void setMailIncon(String mailIncon) {
		this.mailIncon = mailIncon;
	}

	public String getUserHome() {
		return userHome;
	}

	public void setUserHome(String userHome) {
		this.userHome = userHome;
	}

	public String getDownloadRute() {
		return downloadRute;
	}

	public void setDownloadRute(String downloadRute) {
		this.downloadRute = downloadRute;
	}

	public String getErrorAdjunto() {
		return errorAdjunto;
	}

	public void setErrorAdjunto(String errorAdjunto) {
		this.errorAdjunto = errorAdjunto;
	}

	public String getErrorCreatingJScrollPane() {
		return errorCreatingJScrollPane;
	}

	public void setErrorCreatingJScrollPane(String errorCreatingJScrollPane) {
		this.errorCreatingJScrollPane = errorCreatingJScrollPane;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getErrorUpdatingJScrollPane() {
		return errorUpdatingJScrollPane;
	}

	public void setErrorUpdatingJScrollPane(String errorUpdatingJScrollPane) {
		this.errorUpdatingJScrollPane = errorUpdatingJScrollPane;
	}

	public String getSubjectHTML() {
		return subjectHTML;
	}

	public void setSubjectHTML(String subjectHTML) {
		this.subjectHTML = subjectHTML;
	}

	public String getFinishP() {
		return finishP;
	}

	public void setFinishP(String finishP) {
		this.finishP = finishP;
	}

	public String getOnlyTagP() {
		return onlyTagP;
	}

	public void setOnlyTagP(String onlyTagP) {
		this.onlyTagP = onlyTagP;
	}

	public String getSenderCode() {
		return senderCode;
	}

	public void setSenderCode(String senderCode) {
		this.senderCode = senderCode;
	}

	public String getCodeImg() {
		return codeImg;
	}

	public void setCodeImg(String codeImg) {
		this.codeImg = codeImg;
	}

	public String getFileSource() {
		return fileSource;
	}

	public void setFileSource(String fileSource) {
		this.fileSource = fileSource;
	}

	public String getCloseSymbol() {
		return closeSymbol;
	}

	public void setCloseSymbol(String closeSymbol) {
		this.closeSymbol = closeSymbol;
	}

	public String getCloseSymbolSimple() {
		return closeSymbolSimple;
	}

	public void setCloseSymbolSimple(String closeSymbolSimple) {
		this.closeSymbolSimple = closeSymbolSimple;
	}

	public String getOpenSymbol() {
		return openSymbol;
	}

	public void setOpenSymbol(String openSymbol) {
		this.openSymbol = openSymbol;
	}

	public String getiCaracter() {
		return iCaracter;
	}

	public void setiCaracter(String iCaracter) {
		this.iCaracter = iCaracter;
	}

	public String getmCaracter() {
		return mCaracter;
	}

	public void setmCaracter(String mCaracter) {
		this.mCaracter = mCaracter;
	}

	public String getgCaracter() {
		return gCaracter;
	}

	public void setgCaracter(String gCaracter) {
		this.gCaracter = gCaracter;
	}

	public String getCloseLink() {
		return closeLink;
	}

	public void setCloseLink(String closeLink) {
		this.closeLink = closeLink;
	}

	public String getCodeHTML() {
		return codeHTML;
	}

	public void setCodeHTML(String codeHTML) {
		this.codeHTML = codeHTML;
	}

	public String getMessageErrorContentMessage() {
		return messageErrorContentMessage;
	}

	public void setMessageErrorContentMessage(String messageErrorContentMessage) {
		this.messageErrorContentMessage = messageErrorContentMessage;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSlash() {
		return slash;
	}

	public void setSlash(String slash) {
		this.slash = slash;
	}

	public String getOpenBrTag() {
		return openBrTag;
	}

	public void setOpenBrTag(String openBrTag) {
		this.openBrTag = openBrTag;
	}

	public String getCloseBrTag() {
		return closeBrTag;
	}

	public void setCloseBrTag(String closeBrTag) {
		this.closeBrTag = closeBrTag;
	}

	public String getMineTypeText() {
		return mineTypeText;
	}

	public void setMineTypeText(String mineTypeText) {
		this.mineTypeText = mineTypeText;
	}

	public String getMineTypeAlternative() {
		return mineTypeAlternative;
	}

	public void setMineTypeAlternative(String mineTypeAlternative) {
		this.mineTypeAlternative = mineTypeAlternative;
	}

	public String getMinetypeTextPlain() {
		return minetypeTextPlain;
	}

	public void setMinetypeTextPlain(String minetypeTextPlain) {
		this.minetypeTextPlain = minetypeTextPlain;
	}

	public String getMinetypeHTML() {
		return minetypeHTML;
	}

	public void setMinetypeHTML(String minetypeHTML) {
		this.minetypeHTML = minetypeHTML;
	}

	public String getMineTypeMultiplart() {
		return mineTypeMultiplart;
	}

	public void setMineTypeMultiplart(String mineTypeMultiplart) {
		this.mineTypeMultiplart = mineTypeMultiplart;
	}

	public String getMessageErrorGettingBodyPart() {
		return messageErrorGettingBodyPart;
	}

	public void setMessageErrorGettingBodyPart(String messageErrorGettingBodyPart) {
		this.messageErrorGettingBodyPart = messageErrorGettingBodyPart;
	}

	public String getMessageErrorGettingMessages() {
		return messageErrorGettingMessages;
	}

	public void setMessageErrorGettingMessages(String messageErrorGettingMessages) {
		this.messageErrorGettingMessages = messageErrorGettingMessages;
	}

	public String getFolderCode() {
		return folderCode;
	}

	public void setFolderCode(String folderCode) {
		this.folderCode = folderCode;
	}

	public String getMessageErrorReceiveListEmail() {
		return messageErrorReceiveListEmail;
	}

	public void setMessageErrorReceiveListEmail(String messageErrorReceiveListEmail) {
		this.messageErrorReceiveListEmail = messageErrorReceiveListEmail;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getHostPop3() {
		return hostPop3;
	}

	public void setHostPop3(String hostPop3) {
		this.hostPop3 = hostPop3;
	}

	public String getPortPop3() {
		return portPop3;
	}

	public void setPortPop3(String portPop3) {
		this.portPop3 = portPop3;
	}

	public String getPortPop() {
		return portPop;
	}

	public void setPortPop(String portPop) {
		this.portPop = portPop;
	}

	public String getCodeStarttls() {
		return codeStarttls;
	}

	public void setCodeStarttls(String codeStarttls) {
		this.codeStarttls = codeStarttls;
	}

	public String getTrueString() {
		return trueString;
	}

	public void setTrueString(String trueString) {
		this.trueString = trueString;
	}

	public String getMessageErrorSettingProperties() {
		return messageErrorSettingProperties;
	}

	public void setMessageErrorSettingProperties(String messageErrorSettingProperties) {
		this.messageErrorSettingProperties = messageErrorSettingProperties;
	}

	public String getPopGmail() {
		return popGmail;
	}

	public void setPopGmail(String popGmail) {
		this.popGmail = popGmail;
	}

	public String getPop3() {
		return pop3;
	}

	public void setPop3(String pop3) {
		this.pop3 = pop3;
	}

	public String getMessageErrorUpdatingEmails() {
		return messageErrorUpdatingEmails;
	}

	public void setMessageErrorUpdatingEmails(String messageErrorUpdatingEmails) {
		this.messageErrorUpdatingEmails = messageErrorUpdatingEmails;
	}

	public String getKeyMail() {
		return keyMail;
	}

	public void setKeyMail(String keyMail) {
		this.keyMail = keyMail;
	}

	public String getMessageErrorLogin() {
		return messageErrorLogin;
	}

	public void setMessageErrorLogin(String messageErrorLogin) {
		this.messageErrorLogin = messageErrorLogin;
	}

	public String getMessageErrorListenerLink() {
		return messageErrorListenerLink;
	}

	public void setMessageErrorListenerLink(String messageErrorListenerLink) {
		this.messageErrorListenerLink = messageErrorListenerLink;
	}

	public String getUrlSyntaxError() {
		return urlSyntaxError;
	}

	public void setUrlSyntaxError(String urlSyntaxError) {
		this.urlSyntaxError = urlSyntaxError;
	}

	public String getErrorFormat() {
		return errorFormat;
	}

	public void setErrorFormat(String errorFormat) {
		this.errorFormat = errorFormat;
	}

}
