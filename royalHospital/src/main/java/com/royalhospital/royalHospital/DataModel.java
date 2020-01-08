package com.royalhospital.royalHospital;
/**
 * Data Model class for all messages, routes, button tags and general info
 * @author Cristina Montilla
 *
 */
public class DataModel {

	public static String selectedFile = "";
	public static String actualUserPath = "";
	public static String directionPath = "";
	
	private String bigRoyalLogoRoute = "/views/royalhospital.png";
	private String iconRoyalLogo = "src\\main\\java\\views\\ic_launcher.png";//Porque tan larga
	private String errorIconRoute = "/views/errorIcono.png";
	private String warningIconRoute = "/views/warning.png";
	private String loginTitle = "Royal Hospital - Login";
	private String mainTitle = "Royal Hospital - Main";
	private String fontType = "Tahoma";
	private String passwordUserIncorrectMsg = "El usuario o la contraseña no son correctos";
	private String noEmptyLoginFieldsMsg = "El campo usuario y/o contraseña no pueden estar vacios";
	private String dbConectionError = "Error de conexión con la Base de Datos";
	private String ftpConectionError = "Error de conexión con el servidor";
	//Tags
	private String userTag = "Usuario";
	private String passwordTag = "Contraseña";
	private String loginBtnTag = "Entrar";
	private String errorTag = "Error";
	private String acceptTag = "Aceptar";
	private String uploadTag =  "Cargar";
	private String uploadFileTag = "Cargar fichero";
	private String downloadTag = "Descargar";
	private String deleteTag = "Borrar";
	private String createFolderTag = "Crear Carpeta";
	private String createFileTag = "Crear Fichero";
	private String renameTag = "Renombrar";
	private String documentsTag = "Documentos";
	private String patientsTag = "Pacientes";
	private String mailTag = "Correo";
	private String doctorTag = "MEDICO";
	private String patientTag = "PACIENTE";
	//Data base info
	private String db = "jdbc:mysql://localhost/royalhospital";
	private String dbUser = "root";
	private String dbPassword = "";
	//FTP info
	private String ftpServer = "localhost";
	private int port = 9000;
	private String ftpUser = "usuario";
	private String ftpPassword = "usuario";
	//Record messages
	private String selectedFileMsg = "Archivo seleccionado: ";
	private String fileAlreadyExist = "El fichero ya existe en el directorio actual";
	private String fileUpload = "Cargó satisfactoriamente el fichero";
	private String uploadFake = "No se pudo cargar el fichero";
	private String selectFileForUpload = "Seleccione primero un directorio donde cargar el fichero";
	
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

	
	
}
