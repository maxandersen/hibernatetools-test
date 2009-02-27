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
package org.jboss.tools.hibernate.ui.veditor;

import org.eclipse.osgi.util.NLS;

public class UIVEditorMessages extends NLS {
	private static final String BUNDLE_NAME = "org.jboss.tools.hibernate.ui.veditor.UIVEditorMessages"; //$NON-NLS-1$

	private UIVEditorMessages() {
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, UIVEditorMessages.class);
	}

	public static String EditorActionContributor_refresh_visual_mapping;
	public static String VisualEditor_diagram_for;
	public static String ExportImageAction_bmp_format;
	public static String ExportImageAction_error;
	public static String ExportImageAction_failed_to_export_image;
	public static String ExportImageAction_jpg_format;
	public static String ExportImageAction_png_format;
	public static String OpenMappingAction_canot_find_or_open_mapping_file;
	public static String OpenMappingAction_open_mapping_file;
	public static String OpenSourceAction_canot_find_source_file;
	public static String OpenSourceAction_canot_open_source_file;
	public static String OpenSourceAction_open_source_file;
	public static String ShapeSetConstraintCommand_move;
	public static String PartFactory_canot_create_part_for_model_element;
	public static String PartFactory_null;
}
