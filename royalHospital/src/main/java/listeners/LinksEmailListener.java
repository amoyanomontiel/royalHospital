package listeners;

// All imports
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * Class that add listener to links of content emails, for to access to
 * attachments
 * 
 * @author Javier
 * @version 1.0
 */
public class LinksEmailListener {

	/**
	 * Add listener to links
	 * 
	 * @param mail JEditorPane, this element containt the context of email in HTML
	 *             code
	 */
	public static void addListenerLink(JEditorPane mail) {
		mail.addHyperlinkListener(new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					if (Desktop.isDesktopSupported()) {
						try {
							Desktop.getDesktop().browse(e.getURL().toURI());
						} catch (IOException e1) {
							System.out.println("Input/outPut error");
						} catch (URISyntaxException e1) {
							System.out.println("URL sintax error");
						}
					}
				}
			}
		});
	}

}
