package org.yjgoo.jpdmviewer.ui.menu;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * ֧pdm格式过滤
 * 
 * @author yjgoo
 * 
 */
public class MyFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.getName().toLowerCase().endsWith(".pdm")) {
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		return "Powerdesinger PDM File";
	}

}
