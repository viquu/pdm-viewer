package org.yjgoo.jpdmviewer.ui.pdm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.yjgoo.jpdmviewer.pdm.TableModel;
import org.yjgoo.jpdmviewer.pdm.code.JavaBeanGenerator;
import org.yjgoo.jpdmviewer.ui.DataCleanable;

/**
 * convert table to java bean code or other development language entity code
 * 
 * @author LiuChuanfeng
 * 
 */
public class BeanConvertorPanel extends JPanel implements DataCleanable {
	private static final long serialVersionUID = -1172152208152896866L;
	private JButton createBtn;
	private JTextArea codeArea;
	private JavaBeanGenerator beanGenerator;
	private TableModel tm;

	public BeanConvertorPanel() {
		super(new BorderLayout());

		createBtn = new JButton();
		createBtn.setText("Generator");
		codeArea = new JTextArea();

		JScrollPane scroll = new JScrollPane(codeArea);
		//把定义的JTextArea放到JScrollPane里面去 

		//分别设置水平和垂直滚动条自动出现 
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		this.add(createBtn, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);

		beanGenerator = new JavaBeanGenerator();

		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tm != null) {
					codeArea.setText(beanGenerator.convert(tm));
				}
			}
		});
	}

	public void setTableModel(TableModel tm) {
		this.tm = tm;
	}

	public void clean() {
		codeArea.setText("");
	}

}
