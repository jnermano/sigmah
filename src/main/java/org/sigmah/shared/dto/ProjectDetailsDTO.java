package org.sigmah.shared.dto;

/*
 * #%L
 * Sigmah
 * %%
 * Copyright (C) 2010 - 2016 URD
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import org.sigmah.client.i18n.I18N;
import org.sigmah.shared.dto.base.AbstractModelDataEntityDTO;
import org.sigmah.shared.dto.layout.LayoutDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

/**
 * ProjectDetailsDTO.
 * 
 * @author Denis Colliot (dcolliot@ideia.fr)
 */
public class ProjectDetailsDTO extends AbstractModelDataEntityDTO<Integer> {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 3304868140991425311L;

	/**
	 * DTO corresponding entity name.
	 */
	public static final String ENTITY_NAME = "ProjectDetails";

	// DTO attributes keys.
	public static final String NAME = "name";
	public static final String LAYOUT = "layout";
	public static final String PROJECT_MODEL = "projectModel";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEntityName() {
		return ENTITY_NAME;
	}

	// Name (only client-side).
	public void setName() {
		if (GWT.isClient()) {
			set(NAME, I18N.CONSTANTS.Admin_PROJECT_DETAILS());
		}
	}

	public String getName() {
		if (GWT.isClient()) {
			return I18N.CONSTANTS.Admin_PROJECT_DETAILS();
		} else {
			return get(NAME);
		}
	}

	// Reference to the Layout
	public LayoutDTO getLayout() {
		return get(LAYOUT);
	}

	public void setLayout(LayoutDTO layout) {
		set(LAYOUT, layout);
	}

	// Reference to the Project Model
	public ProjectModelDTO getProjectModel() {
		return get(PROJECT_MODEL);
	}

	public void setProjectModel(ProjectModelDTO projectModel) {
		set(PROJECT_MODEL, projectModel);
	}

	public Widget getWidget() {
		return getLayout().getWidget();
	}
}
