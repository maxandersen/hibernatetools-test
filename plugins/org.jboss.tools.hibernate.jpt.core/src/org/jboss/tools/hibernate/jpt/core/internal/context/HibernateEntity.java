/*******************************************************************************
 * Copyright (c) 2009 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributor:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.hibernate.jpt.core.internal.context;

import org.eclipse.jpt.core.context.Entity;

/**
 * @author Dmitry Geraskov
 *
 */
public interface HibernateEntity extends Entity,
	GenericGeneratorHolder, HibernateQueryContainer {
	
	String DISCRIMINATOR_FORMULA_PROPERTY = "discriminatorFormula"; //$NON-NLS-1$
	
	DiscriminatorFormula getDiscriminatorFormula();
	
	DiscriminatorFormula addDiscriminatorFormula();
	
	void removeDiscriminatorFormula();

}
