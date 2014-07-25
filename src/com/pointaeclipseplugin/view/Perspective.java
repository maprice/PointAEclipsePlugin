package com.pointaeclipseplugin.view;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	/**
	 * The ID of the perspective as specified in the extension.
	 */
	public static final String ID = "MikeTest.perspective";

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);

		layout.addStandaloneView(ServicePickerView.ID,  true, IPageLayout.LEFT, 0.25f, editorArea);
	
		layout.addStandaloneView(ServiceView.ID,  false, IPageLayout.RIGHT, 0.25f, editorArea);

		layout.getViewLayout(ServicePickerView.ID).setCloseable(false);
			
		layout.getViewLayout(ServiceView.ID).setCloseable(false);
		
		
	}
}
