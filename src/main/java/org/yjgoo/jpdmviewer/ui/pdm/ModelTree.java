package org.yjgoo.jpdmviewer.ui.pdm;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.yjgoo.jpdmviewer.pdm.ColumnModel;
import org.yjgoo.jpdmviewer.pdm.PDMFileModel;
import org.yjgoo.jpdmviewer.pdm.TableModel;

public class ModelTree extends JTree {
	private static final long serialVersionUID = 2384351839472362324L;

    public static final int MATCH_TYPE_NONE = 0;
    public static final int MATCH_TYPE_TABLENAME = 1;
    public static final int MATCH_TYPE_COLUMNNAME = 2;


    private PDMFileModel pdmFileModel;

	public ModelTree() {
		TreeNode root = new ModelTreeNode("PDM");
		DefaultTreeModel tm = new DefaultTreeModel(root);
		this.setModel(tm);
	}

    /**
     * 展现原始的PDM
     */
    public void refreshModel() {
        refreshModel(pdmFileModel, null, MATCH_TYPE_NONE);
    }

    /**
     * 根据条件过滤PDM
     *
     * @param text
     * @param matchType
     */
    public void refreshModel(String text, int matchType) {
        refreshModel(pdmFileModel, text, matchType);
    }

    /**
     * 根据输入的model和条件展现PDM
     *
     * @param model
     */
    public void refreshModel(PDMFileModel model, String text, int matchType) {
        this.pdmFileModel = model;
        ModelTreeNode root = new ModelTreeNode("PDM");
		DefaultTreeModel tm = new DefaultTreeModel(root);
		this.setModel(tm);
		ModelTreeNode tbls = new ModelTreeNode("Tables");
		root.add(tbls);

        // load trees
        if (null == model) {
            return;
        }

        ModelTreeNode tbl;
        for (TableModel t : model.getTableModelList()) {

            if (isMatchTable(t, text, matchType)) {
                tbl = new ModelTreeNode(t.getName());
                tbl.setData(t);
                tbls.add(tbl);
            }

        }

		this.expandPath(new TreePath(tbls.getPath()));
    }

    private boolean isMatchTable(TableModel tableModel, String text, int matchType) {
        if (MATCH_TYPE_NONE == matchType || (null == text || "".equals(text))) {
            return true;
        }

        if (MATCH_TYPE_TABLENAME == matchType) {
            return isTextInArray(text, tableModel.getName(), tableModel.getCode());
        } else {
            for (ColumnModel columnModel : tableModel.getCols()) {
                if (isTextInArray(text, columnModel.getName(), columnModel.getCode(), columnModel.getComment())) {
                    return true;
                }
            }
            return false;
        }
    }

    private boolean isTextInArray(String text, String... texts) {

        if (null == text || "".equals(text)) {
            return false;
        }

        text = text.toUpperCase();

        for (String t : texts) {
            if (null == t) {
                continue;
            }

            if (t.toUpperCase().contains(text)) {
                return true;
            }
        }

        return false;
    }

}
