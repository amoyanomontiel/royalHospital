package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

public class DocumentsListener implements ActionListener{
	
	private DefaultMutableTreeNode raiz;
	private String user;
	
	public DocumentsListener(DefaultMutableTreeNode raiz, String user) {
		this.raiz = raiz;
		this.user = user;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
