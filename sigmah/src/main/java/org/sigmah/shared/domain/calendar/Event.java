/*
 * All Sigmah code is released under the GNU General Public License v3
 * See COPYRIGHT.txt and LICENSE.txt.
 */

package org.sigmah.shared.domain.calendar;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Raphaël Calabro (rcalabro@ideia.fr)
 */
public class Event implements Serializable {
    private String summary;
    private String description;
    private Date dtstart;
    private Date dtend;
    private Calendar parent;

    public Event() {
    }

    public Event(String summary, String description, Date dtstart, Date dtend, Calendar parent) {
        this.summary = summary;
        this.description = description;
        this.dtstart = dtstart;
        this.dtend = dtend;
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDtend() {
        return dtend;
    }

    public void setDtend(Date dtend) {
        this.dtend = dtend;
    }

    public Date getDtstart() {
        return dtstart;
    }

    public void setDtstart(Date dtstart) {
        this.dtstart = dtstart;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Calendar getParent() {
        return parent;
    }

    public void setParent(Calendar parent) {
        this.parent = parent;
    }
}