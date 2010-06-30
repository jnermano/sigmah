/*
 * All Sigmah code is released under the GNU General Public License v3
 * See COPYRIGHT.txt and LICENSE.txt.
 */

package org.sigmah.client.page.common.widget;

import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import org.sigmah.client.Application;
import org.sigmah.client.dispatch.AsyncMonitor;

/**
 * @author Alex Bertram (akbertram@gmail.com)
 */
public class LoadingPlaceHolder extends LayoutContainer implements AsyncMonitor {

    private Html html;

    public LoadingPlaceHolder() {
        setLayout(new CenterLayout());
        html = new Html();
        html.addStyleName("loading-placeholder");
        html.setHtml(Application.CONSTANTS.loadingComponent());
        add(html);
    }

    @Override
    public void beforeRequest() {

    }

    @Override
    public void onCompleted() {
        html.setHtml("Loaded.");
    }

    @Override
    public void onConnectionProblem() {
        html.setHtml(Application.CONSTANTS.connectionProblem());
    }

    @Override
    public boolean onRetrying() {
        html.setHtml(Application.CONSTANTS.retrying());
        return false;
    }

    @Override
    public void onServerError() {
        html.setHtml(Application.CONSTANTS.serverError());
    }
}