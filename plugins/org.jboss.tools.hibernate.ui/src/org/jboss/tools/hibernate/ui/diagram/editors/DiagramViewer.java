/*******************************************************************************
 * Copyright (c) 2007 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributor:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.hibernate.ui.diagram.editors;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.hibernate.console.ConsoleConfiguration;
import org.hibernate.mapping.RootClass;
import org.jboss.tools.hibernate.ui.diagram.DiagramViewerMessages;
import org.jboss.tools.hibernate.ui.diagram.editors.actions.AutoLayoutAction;
import org.jboss.tools.hibernate.ui.diagram.editors.actions.ExportImageAction;
import org.jboss.tools.hibernate.ui.diagram.editors.actions.OpenMappingAction;
import org.jboss.tools.hibernate.ui.diagram.editors.actions.OpenSourceAction;
import org.jboss.tools.hibernate.ui.diagram.editors.model.OrmDiagram;
import org.jboss.tools.hibernate.ui.diagram.editors.model.Shape;
import org.jboss.tools.hibernate.ui.diagram.editors.parts.GEFRootEditPart;
import org.jboss.tools.hibernate.ui.diagram.editors.parts.OrmEditPart;
import org.jboss.tools.hibernate.ui.diagram.editors.parts.OrmEditPartFactory;
import org.jboss.tools.hibernate.ui.diagram.editors.popup.PopupMenuProvider;
import org.jboss.tools.hibernate.ui.view.ObjectEditorInput;

public class DiagramViewer extends GraphicalEditor {

	private OrmDiagram ormDiagram = null;

	public DiagramViewer() {
		setEditDomain(new DefaultEditDomain(this));
	}

	public void doSave(IProgressMonitor monitor) {
		ormDiagram.save();
		ormDiagram.setDirty(false);
	}

	public void doSaveAs() {
	}

	protected void initializeGraphicalViewer() {
		final GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new OrmEditPartFactory());
		viewer.setRootEditPart(new GEFRootEditPart());
		viewer.addDropTargetListener(createTransferDropTargetListener());
		viewer.setContents(ormDiagram);

		PopupMenuProvider provider = new PopupMenuProvider(viewer, getActionRegistry());
		viewer.setContextMenu(provider);
		getSite().registerContextMenu("FlowDiagramContextmenu", provider, viewer); //$NON-NLS-1$
	}

	public GraphicalViewer getEditPartViewer() {
		return getGraphicalViewer();
	}

	protected void createActions() {

		getEditorSite().getActionBars().setGlobalActionHandler(ActionFactory.REFRESH.getId(),new WorkbenchPartAction(this){

			protected boolean calculateEnabled() {
				return true;
			}
			public void run() {
				ormDiagram.refresh();
			}
		});

		super.createActions();

		getEditorSite().getActionBars().setGlobalActionHandler(ActionFactory.PRINT.getId(), getActionRegistry().getAction(ActionFactory.PRINT.getId()));

		ActionRegistry registry = getActionRegistry();
		IAction action;

		action = new OpenMappingAction(this);
		registry.registerAction(action);

		action = new OpenSourceAction(this);
		registry.registerAction(action);

		action = new ExportImageAction(this);
		registry.registerAction(action);

		action = new AutoLayoutAction(this);
		registry.registerAction(action);

	}

	private TransferDropTargetListener createTransferDropTargetListener() {
		return new TemplateTransferDropTargetListener(getGraphicalViewer()) {
			protected CreationFactory getFactory(Object template) {
				return new SimpleFactory((Class<?>) template);
			}
		};
	}

	public boolean isSaveAsAllowed() {
		return false;
	}

	public boolean isSaveOnCloseNeeded() {
		return true;
	}

	public void refreshDirty() {
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	public boolean isDirty() {
		return ormDiagram.isDirty();
	}

	protected void setInput(IEditorInput input) {
		ObjectEditorInput objectEditorInput = (ObjectEditorInput)input;
		ConsoleConfiguration configuration = objectEditorInput.getConfiguration();
		Object obj = objectEditorInput.getObject();
		if (obj instanceof RootClass) {
			RootClass rootClass = (RootClass)obj;
			setPartName(DiagramViewerMessages.DiagramViewer_diagram_for + rootClass.getEntityName());
			ormDiagram = new OrmDiagram(configuration, rootClass);
		} else if (obj instanceof RootClass[]) {
			RootClass[] rootClasses = (RootClass[])obj;
			String name = rootClasses.length > 0 ? rootClasses[0].getEntityName() : ""; //$NON-NLS-1$
			for (int i = 1; i < rootClasses.length; i++) {
				name += " & " + rootClasses[i].getEntityName(); //$NON-NLS-1$
			}
			setPartName(DiagramViewerMessages.DiagramViewer_diagram_for + name);
			ormDiagram = new OrmDiagram(configuration, rootClasses);
		}
		super.setInput(input);
	}

	@SuppressWarnings("unchecked")
	public Object getAdapter(Class type) {
		if (type == IContentOutlinePage.class) {
			DiagramContentOutlinePage outline = new DiagramContentOutlinePage(
					new TreeViewer());
			outline.setGraphicalViewer(getGraphicalViewer());
			outline.setSelectionSynchronizer(getSelectionSynchronizer());
			outline.setOrmDiagram(ormDiagram);
			outline.setEditor(this);
			return outline;
		}

		return super.getAdapter(type);
	}

	public Set<Shape> getSelectedElements() {
		Set<Shape> ret = new HashSet<Shape>();
		List<?> selectedEditParts = getGraphicalViewer().getSelectedEditParts();
		Iterator<?> iterator = selectedEditParts.iterator();
		while (iterator.hasNext()) {
			Object elem = iterator.next();
			if (elem instanceof OrmEditPart) {
				Shape shape = (Shape)((OrmEditPart)elem).getModel();
				ret.add(shape);
			}
		}
		return ret;
	}

	public OrmDiagram getViewerContents() {
		return ormDiagram;
	}

	public DefaultEditDomain getDefaultEditDomain() {
		return getEditDomain();
	}

}