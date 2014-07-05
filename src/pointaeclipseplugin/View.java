package pointaeclipseplugin;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public static final String ID = "PointAEclipsePlugin.view";

	private TableViewer viewer;

	/**
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			if (parent instanceof Object[]) {
				return (Object[]) parent;
			}
	        return new Object[0];
		}
	}

	class ViewLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		
		//viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL	| SWT.V_SCROLL);
		//viewer.setContentProvider(new ViewContentProvider());
		//viewer.setLabelProvider(new ViewLabelProvider());
		// Provide the input to the ContentProvider
		//viewer.setInput(new String[] {"A", "B", "C"});
		
	//	Composite top = new Composite(parent, SWT.NONE);
		
		GridLayout layout = new GridLayout();
		
		Composite banner = new Composite(parent, SWT.NONE);
		banner.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
		layout = new GridLayout();
		layout.marginHeight = 10;
		layout.marginWidth = 30;
		layout.numColumns = 2;
		banner.setLayout(layout);
		
		Button checkbox1 = new Button(banner, SWT.CHECK);
		
		checkbox1.setText("Checkbox 1");
		
		Button checkbox2 = new Button(banner, SWT.CHECK);
		
		checkbox2.setText("Checkbox 2");
		
		Combo comboDropDown = new Combo(banner, SWT.DROP_DOWN | SWT.BORDER);
		
	    for(int i=0; i<3; i++) {
	        comboDropDown.add("item " + i);
	    }
		
		Button pushButton = new Button(banner, SWT.PUSH);
		
		pushButton.setText("Push Button");
		
		
		
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}