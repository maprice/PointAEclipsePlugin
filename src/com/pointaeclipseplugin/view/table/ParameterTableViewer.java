package com.pointaeclipseplugin.view.table;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.pointaeclipseplugin.model.ModelProvider;
import com.pointaeclipseplugin.model.Provider2;

public class ParameterTableViewer extends TableViewer{

	public ParameterTableViewer(Composite parent, List<Provider2> mParams) {
		super(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		// TODO Auto-generated constructor stub
		createColumns(parent, this);

		
		final Table table = this.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		this.setContentProvider(new ArrayContentProvider());
		// get the content for the viewer, setInput will call getElements in the
		// contentProvider
		this.setInput(mParams);
		// make the selection available to other views
	
		// set the sorter for the table

		// define layout for the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		this.getControl().setLayoutData(gridData);
	}
	
	// create the columns for the table
		private void createColumns(final Composite parent, final TableViewer viewer) {
			String[] titles = { "Key", "Value"};
			int[] bounds = { 100, 300};

			// first column is for the first name
			TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
			col.setLabelProvider(new ColumnLabelProvider() {
				@Override
				public String getText(Object element) {
					Provider2 p = (Provider2) element;
					return p.getFirstName();
				}
			});

			// second column is for the last name
			col = createTableViewerColumn(titles[1], bounds[1], 1);
			EditValueEditingSupport x = new EditValueEditingSupport(viewer);
			col.setEditingSupport(x);
			col.setLabelProvider(new ColumnLabelProvider() {
				@Override
				public String getText(Object element) {
					Provider2 p = (Provider2) element;
					return p.getLastName();
				}
			});

		}

		private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
			final TableViewerColumn viewerColumn = new TableViewerColumn(this,
					SWT.NONE);
			final TableColumn column = viewerColumn.getColumn();
			column.setText(title);
			column.setWidth(bound);
			column.setResizable(true);
			column.setMoveable(true);
			return viewerColumn;
		}



}
