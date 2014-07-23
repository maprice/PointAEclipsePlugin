package com.pointaeclipseplugin.view.table;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import com.pointaeclipseplugin.model.Provider2;

public class EditValueEditingSupport extends EditingSupport {

	private final TableViewer viewer;
	private final CellEditor editor;

	public EditValueEditingSupport(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
		this.editor = new TextCellEditor(viewer.getTable());
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		return ((Provider2) element).getFirstName();
	}

	@Override
	protected void setValue(Object element, Object userInputValue) {
		((Provider2) element).setLastName(String.valueOf(userInputValue));
		viewer.update(element, null);
	}
} 