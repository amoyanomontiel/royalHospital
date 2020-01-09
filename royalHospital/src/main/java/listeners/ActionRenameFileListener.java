/**
 * 
 */
package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.royalhospital.royalHospital.AuxiliaryTools;
import com.royalhospital.royalHospital.DataModel;

import views.CreateModifyView;
import views.ErrorRoyalView;
import views.MainRoyalView;

/**
 * Rename File class with rename function and tree update
 * @author Cristina Montilla
 *
 */
public class ActionRenameFileListener implements ActionListener {

	private FTPClient ftp;
	private JTextField text;
	private MainRoyalView royal;
	private CreateModifyView nameFrame;
	private String file;

	/**
	 * 
	 */
	public ActionRenameFileListener(FTPClient ftpClient, JTextField textField, MainRoyalView royalView,
			CreateModifyView nameFrame) {
		this.ftp = ftpClient;
		this.royal = royalView;
		this.text = textField;
		this.nameFrame = nameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (DataModel.actualUserPath != "") {
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				try {
					ftp.changeWorkingDirectory(DataModel.directionPath);
					if(text.getText().toString().isEmpty() || text.getText().length()>20) {
						ErrorRoyalView error = new ErrorRoyalView("Escriba el nombre nuevo para el fichero(máximo 20 caracteres)", 1);
						error.setVisible(true);
						error.setLocationRelativeTo(null);
					}else {
						if (ftp.rename(DataModel.selectedFile, text.getText())) {
							nameFrame.dispose();
							AuxiliaryTools.saveOperationAtDBRecord(DataModel.codActualUser, "renombrar", DataModel.selectedFile, 
									AuxiliaryTools.actualDate(), AuxiliaryTools.actualTime());
							royal.getTxtaHistorial().append("El fichero fue renombrado con éxito\n");
							DefaultMutableTreeNode node = (DefaultMutableTreeNode)royal.getTree().getLastSelectedPathComponent();
							node.setUserObject(text.getText().toString());
							DefaultTreeModel model = (DefaultTreeModel) royal.getTree().getModel();
							model.nodeChanged(node);
							royal.getTree().setModel(model);
						} else {
							royal.getTxtaHistorial().append("El fichero no pudo ser renombrado\n");
						}
					}					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
