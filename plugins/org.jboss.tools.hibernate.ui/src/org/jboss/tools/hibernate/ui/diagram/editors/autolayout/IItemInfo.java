/*******************************************************************************
 * Copyright (c) 2007-2009 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributor:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.hibernate.ui.diagram.editors.autolayout;

/**
 * Information about item which is essential for our autolayout implementation. 
 */
public interface IItemInfo {
	/**
	 * @return unique item id
	 */
	public String getID();
	public boolean isComment();
	/**
	 * gets shape vertices
	 */
	public int[] getShape();
	/**
	 * setup OrmShape up-left point location, using s[0] and s[1]
	 * -> this is really thing behind this function, change the comment if you change the behavior
	 */
	public void setShape(int[] s);

	ILinkInfo[] getLinks();
}
