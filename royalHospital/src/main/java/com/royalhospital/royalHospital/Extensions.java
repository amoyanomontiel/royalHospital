package com.royalhospital.royalHospital;

import java.util.ArrayList;

public class Extensions {
	
	private ArrayList<String> textExtensions;
	private ArrayList<String> forbiddenGmailExtensions;
	
	public Extensions() {
		textExtensions = new ArrayList<String>();
		forbiddenGmailExtensions = new ArrayList<String>();
		
		fillExtensions();
		fillForbiddenGmailExtensions();
	}
	
	private void fillExtensions() {
		textExtensions.add("doc");
		textExtensions.add("docx");
		textExtensions.add("txt");
		textExtensions.add("odt");
		textExtensions.add("epub");
		textExtensions.add("log");
		textExtensions.add("dic");
		textExtensions.add("notebook");
		textExtensions.add("rtf");
		textExtensions.add("nfo");
	}
	
	private void fillForbiddenGmailExtensions() {
		forbiddenGmailExtensions.add("ade");
		forbiddenGmailExtensions.add("adp");
		forbiddenGmailExtensions.add("apk");
		forbiddenGmailExtensions.add("appx");
		forbiddenGmailExtensions.add("appxbundle");
		forbiddenGmailExtensions.add("bat");
		forbiddenGmailExtensions.add("cab");
		forbiddenGmailExtensions.add("chm");
		forbiddenGmailExtensions.add("cmd");
		forbiddenGmailExtensions.add("com");
		forbiddenGmailExtensions.add("cpl");
		forbiddenGmailExtensions.add("dll");
		forbiddenGmailExtensions.add("dmg");
		forbiddenGmailExtensions.add("exe");
		forbiddenGmailExtensions.add("hta");
		forbiddenGmailExtensions.add("ins");
		forbiddenGmailExtensions.add("isp");
		forbiddenGmailExtensions.add("iso");
		forbiddenGmailExtensions.add("jar");
		forbiddenGmailExtensions.add("js");
		forbiddenGmailExtensions.add("jse");
		forbiddenGmailExtensions.add("lib");
		forbiddenGmailExtensions.add("lnk");
		forbiddenGmailExtensions.add("mde");
		forbiddenGmailExtensions.add("msc");
		forbiddenGmailExtensions.add("msi");
		forbiddenGmailExtensions.add("msix");
		forbiddenGmailExtensions.add("msixbundle");
		forbiddenGmailExtensions.add("msp");
		forbiddenGmailExtensions.add("mst");
		forbiddenGmailExtensions.add("nsh");
		forbiddenGmailExtensions.add("pif");
		forbiddenGmailExtensions.add("ps1");
		forbiddenGmailExtensions.add("scr");
		forbiddenGmailExtensions.add("sct");
		forbiddenGmailExtensions.add("shb");
		forbiddenGmailExtensions.add("sys");
		forbiddenGmailExtensions.add("vb");
		forbiddenGmailExtensions.add("vbe");
		forbiddenGmailExtensions.add("vbs");
		forbiddenGmailExtensions.add("vxd");
		forbiddenGmailExtensions.add("wsc");
		forbiddenGmailExtensions.add("wsf");
		forbiddenGmailExtensions.add("wsh");
		forbiddenGmailExtensions.add("rar");
		forbiddenGmailExtensions.add("zip");
		forbiddenGmailExtensions.add("7zip");
	}


	public ArrayList<String> getTextExtensions() {
		return textExtensions;
	}

	public void setTextExtensions(ArrayList<String> textExtensions) {
		this.textExtensions = textExtensions;
	}

	public ArrayList<String> getForbiddenGmailExtensions() {
		return forbiddenGmailExtensions;
	}

	public void setForbiddenGmailExtensions(ArrayList<String> forbiddenGmailExtensions) {
		this.forbiddenGmailExtensions = forbiddenGmailExtensions;
	}

}
