package org.yjgoo.jpdmviewer.ui.pdm;

import org.yjgoo.jpdmviewer.pdm.PDMFileModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Philip on 2018/12/4.
 */
public class TableListPanel extends JPanel {

    private ModelTree modelTree;
    private JButton searchBtn;
    private JTextField searchTextField;
    private JRadioButton tableNameJRadioBtn;
    private JRadioButton columnNameJRadioBtn;

    public TableListPanel() {
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        this.modelTree = new ModelTree();
        JScrollPane jScrollTree = new JScrollPane(v, h);
        jScrollTree.getViewport().add(modelTree);
        jScrollTree.setPreferredSize(new Dimension(200, 600));
        this.setLayout(new BoxLayout(this,
                BoxLayout.Y_AXIS));
        this.add(jScrollTree, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel,
                BoxLayout.Y_AXIS));
        searchPanel.setPreferredSize(new Dimension(200, 50));

        JPanel searchTopPanel = new JPanel();
        searchTopPanel.setLayout(new BoxLayout(searchTopPanel,
                BoxLayout.X_AXIS));
        searchPanel.add(searchTopPanel);
        searchTextField = new JTextField();
        searchTextField.setPreferredSize(new Dimension(200, 10));
        searchTopPanel.add(searchTextField);


        JPanel searchBottomPanel = new JPanel();
        searchBottomPanel.setLayout(new BoxLayout(searchBottomPanel,
                BoxLayout.X_AXIS));
        searchPanel.add(searchBottomPanel);
        searchBtn = new JButton("搜索");
        searchBottomPanel.add(searchBtn);
        tableNameJRadioBtn = new JRadioButton("搜表");
        tableNameJRadioBtn.setSelected(true);
        columnNameJRadioBtn = new JRadioButton("搜字段");
        searchBottomPanel.add(tableNameJRadioBtn);
        searchBottomPanel.add(columnNameJRadioBtn);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(tableNameJRadioBtn);
        buttonGroup.add(columnNameJRadioBtn);

        this.add(searchPanel, BorderLayout.SOUTH);

        addEvent();
    }

    public void addEvent() {
        this.searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String searchText = searchTextField.getText();
                if (null == searchText || "".equals(searchText)) {
                    getModelTree().refreshModel();
                } else {
                    if (tableNameJRadioBtn.isSelected()) {
                        getModelTree().refreshModel(searchText, ModelTree.MATCH_TYPE_TABLENAME);
                    } else {
                        getModelTree().refreshModel(searchText, ModelTree.MATCH_TYPE_COLUMNNAME);
                    }
                }
            }
        });
    }

    public ModelTree getModelTree() {
        return modelTree;
    }

}
