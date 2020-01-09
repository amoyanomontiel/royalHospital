/**
 * This class contains the file name and the file path of every file attached to the message
 * @author Fernando Cañadas Ortega
 * @version 1.0
 * Realizado el 9 november 2020
 */

package com.royalhospital.royalHospital;

public class UploadedFile {

	private String fileName;
	private String filePath;

	/**
	 * Builder of this class that fill the file name and file path of one file attached to the message
	 */
	public UploadedFile(String fileName, String filePath) {
		this.fileName = fileName;
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
