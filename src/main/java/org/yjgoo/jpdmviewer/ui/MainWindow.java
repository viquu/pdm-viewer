package org.yjgoo.jpdmviewer.ui;

import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import org.dom4j.DocumentException;
import org.yjgoo.jpdmviewer.pdm.PDMFileModel;
import org.yjgoo.jpdmviewer.pdm.TableModel;
import org.yjgoo.jpdmviewer.ui.menu.MainMenu;
import org.yjgoo.jpdmviewer.ui.pdm.ModelTree;
import org.yjgoo.jpdmviewer.ui.pdm.ModelTreeNode;
import org.yjgoo.jpdmviewer.ui.pdm.TableListPanel;
import org.yjgoo.jpdmviewer.ui.pdm.TableInfoPanel;

/**
 * main window
 * 
 * @author yjgoo
 * 
 */
public class MainWindow extends JFrame {
	private static final long serialVersionUID = 5212714499914863032L;
    private TableListPanel tableListPanel;
    private TableInfoPanel tablePanel;

	public MainWindow() {
		this.setBounds(100, 100, 1000, 600);
		this.setTitle("PDM-Viewer V0.1a");
		this.addWindowListener(new WindowListenerHandler());
		this.setMenuBar(new MainMenu(this));

        this.tableListPanel = new TableListPanel();
        this.getContentPane().add(tableListPanel, BorderLayout.WEST);

		tablePanel = new TableInfoPanel();
		this.getContentPane().add(tablePanel, BorderLayout.CENTER);

		addEvents();

		new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE,
				new DropFileLoader(this), true, null);
	}

	private void addEvents() {
        this.tableListPanel.getModelTree().addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent e) {
                ModelTreeNode node = (ModelTreeNode) tableListPanel.getModelTree()
                        .getLastSelectedPathComponent();
                TableModel tm;
                if (node != null && node.getData() != null
                        && node.getData() instanceof TableModel) {
                    tm = (TableModel) node.getData();
                    tablePanel.showModel(tm);
                }
			}
		});
	}

	public void loadPDM(File file) {
		PDMFileModel model;
		try {
			model = PDMFileModel.loadFile(file);
            ModelTree modelTree = this.tableListPanel.getModelTree();
            modelTree.refreshModel(model, null, ModelTree.MATCH_TYPE_NONE);
        } catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private class WindowListenerHandler implements WindowListener {

		public void windowActivated(WindowEvent arg0) {
		}

		public void windowClosed(WindowEvent arg0) {
		}

		public void windowClosing(WindowEvent arg0) {
			System.exit(0);
		}

		public void windowDeactivated(WindowEvent arg0) {
		}

		public void windowDeiconified(WindowEvent arg0) {
		}

		public void windowIconified(WindowEvent arg0) {

		}

		public void windowOpened(WindowEvent arg0) {
		}

	}

}
