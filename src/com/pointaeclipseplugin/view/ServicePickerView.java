package com.pointaeclipseplugin.view;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class ServicePickerView extends ViewPart {
	public static final String ID = "MikeTest.navigationView";
	private TreeViewer viewer;
	 
	class TreeObject {
		private String name;
		private TreeParent parent;
		
		public TreeObject(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public void setParent(TreeParent parent) {
			this.parent = parent;
		}
		public TreeParent getParent() {
			return parent;
		}
		public String toString() {
			return getName();
		}
	}
	
	class TreeParent extends TreeObject {
		private ArrayList children;
		public TreeParent(String name) {
			super(name);
			children = new ArrayList();
		}
		public void addChild(TreeObject child) {
			children.add(child);
			child.setParent(this);
		}
		public void removeChild(TreeObject child) {
			children.remove(child);
			child.setParent(null);
		}
		public TreeObject[] getChildren() {
			return (TreeObject[]) children.toArray(new TreeObject[children.size()]);
		}
		public boolean hasChildren() {
			return children.size()>0;
		}
	}

	class ViewContentProvider implements IStructuredContentProvider, 
										   ITreeContentProvider {

        public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
        
		public void dispose() {
		}
        
		public Object[] getElements(Object parent) {
			return getChildren(parent);
		}
        
		public Object getParent(Object child) {
			if (child instanceof TreeObject) {
				return ((TreeObject)child).getParent();
			}
			return null;
		}
        
		public Object[] getChildren(Object parent) {
			if (parent instanceof TreeParent) {
				return ((TreeParent)parent).getChildren();
			}
			return new Object[0];
		}

        public boolean hasChildren(Object parent) {
			if (parent instanceof TreeParent)
				return ((TreeParent)parent).hasChildren();
			return false;
		}
	}
	
	class ViewLabelProvider extends LabelProvider {

		public String getText(Object obj) {
			return obj.toString();
		}
		public Image getImage(Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof TreeParent)
			   imageKey = ISharedImages.IMG_OBJ_FOLDER;
			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		}
	}

    /**
     * We will set up a dummy model to initialize tree heararchy. In real
     * code, you will connect to a real model and expose its hierarchy.
     */
    private TreeObject createDummyModel() {
        TreeObject to1 = new TreeObject("Admob");
        TreeObject to2 = new TreeObject("AdSense");
        TreeObject to3 = new TreeObject("Google");
        TreeParent p1 = new TreeParent("Adddds");
        p1.addChild(to1);
        p1.addChild(to2);
        p1.addChild(to3);

        TreeObject to4 = new TreeObject("Parse.com");
        TreeObject to5 = new TreeObject("Google");
        TreeObject to6 = new TreeObject("MixPanel");
        TreeParent p2 = new TreeParent("Analytics");
        p2.addChild(to4);
        p2.addChild(to5);
        p2.addChild(to6);
        
        TreeObject to7 = new TreeObject("Bug Finder");
        TreeObject to8 = new TreeObject("BugSense");
        TreeObject to9 = new TreeObject("Crashlytics");
        TreeParent p3 = new TreeParent("Crash Reporter");
        p3.addChild(to7);
        p3.addChild(to8);
        p3.addChild(to9);

        TreeParent root = new TreeParent("");
        root.addChild(p1);
        root.addChild(p2);
        root.addChild(p3);
        return root;
    }

    ISelectionChangedListener x = new ISelectionChangedListener(){


		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			System.out.println("FUCK");
		}
    	
    };
    
	/**
     * This is a callback that will allow us to create the viewer and initialize
     * it.
     */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(createDummyModel());
		getSite().setSelectionProvider(viewer);
		
		
	}

	
		
	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}