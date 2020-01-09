/**
 * 
 */
package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.royalhospital.royalHospital.AuxiliaryTools;
import com.royalhospital.royalHospital.DataModel;

import views.CreateModifyView;
import views.MainRoyalView;

/**
 * @author Cristina
 *
 */
public class ConfirmDeleteListener implements ActionListener {

	JButton button;
	CreateModifyView frame;
	FTPClient ftpClient;
	MainRoyalView royal;
	String fileType;
	DataModel data = new DataModel();
	
	/**
	 * @param frame 
	 * @param fileType 
	 * @param btnOk 
	 * 
	 */
	public ConfirmDeleteListener(JButton button, CreateModifyView frame, FTPClient ftpClient, 
			MainRoyalView principalFrame, String fileType) {
		this.button = button;
		this.frame = frame;
		this.ftpClient = ftpClient;
		this.royal = principalFrame;
		this.fileType = fileType;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(button.getText());
		if(button.getText().equals("Aceptar")) {
			if(fileType.equals("fichero")) {
				try {
					if (ftpClient.deleteFile(DataModel.selectedFile)) {
						AuxiliaryTools.saveOperationAtDBRecord(DataModel.codActualUser, data.getDeleteFileTag(),
								DataModel.selectedFile, AuxiliaryTools.actualDate(), AuxiliaryTools.actualTime());
						royal.getTxtaHistorial().append(data.getDeleteFileSuccess() + "\n");
						upDateTree();
						royal.rootsToBlank();
					} else {
						royal.getTxtaHistorial().append(data.getDeleteFileNoPossible() + "\n");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {
				try {
					if (FTPReply.isPositiveCompletion(ftpClient.rmd(DataModel.selectedFile))) {
						AuxiliaryTools.saveOperationAtDBRecord(DataModel.codActualUser, data.getDeleteDirTag(),
								DataModel.selectedFile, AuxiliaryTools.actualDate(),
								AuxiliaryTools.actualTime());
						royal.getTxtaHistorial().append(data.getDeleteSuccess() + "\n");
						upDateTree();
						royal.rootsToBlank();
					} else {
						royal.getTxtaHistorial().append(data.getDeleteDirNoPossible() + "\n");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else {
			frame.dispose();
		}
	}
	
	/**
	 * Function which updates the files tree after delete
	 */
	private void upDateTree() {
		DefaultTreeModel model = (DefaultTreeModel) royal.getTree().getModel();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) royal.getTree().getLastSelectedPathComponent();
		model.removeNodeFromParent(node);
	}

}
