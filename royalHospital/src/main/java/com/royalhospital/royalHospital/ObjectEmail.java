package com.royalhospital.royalHospital;

public class ObjectEmail {
	private String subject;
	private String from;
	private String bodyText;
	
	public ObjectEmail(String subjectParam, String fromParam, String bodyTextParam) {
		subject = subjectParam;
		from = fromParam;
		bodyText = bodyTextParam;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getBodyText() {
		return bodyText;
	}

	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
}
