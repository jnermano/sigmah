/*
 * All Sigmah code is released under the GNU General Public License v3
 * See COPYRIGHT.txt and LICENSE.txt.
 */

package org.sigmah.server.endpoint.gwtrpc.handler;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.sigmah.server.domain.Attribute;
import org.sigmah.server.domain.AttributeGroup;
import org.sigmah.server.domain.Indicator;
import org.sigmah.server.domain.User;
import org.sigmah.server.policy.ActivityPolicy;
import org.sigmah.server.policy.PropertyMap;
import org.sigmah.server.policy.SitePolicy;
import org.sigmah.shared.command.UpdateEntity;
import org.sigmah.shared.command.result.CommandResult;
import org.sigmah.shared.exception.CommandException;
import org.sigmah.shared.exception.IllegalAccessCommandException;

import javax.persistence.EntityManager;
import java.util.Map;

/**
 * @author Alex Bertram
 * @see org.sigmah.shared.command.UpdateEntity
 */
public class UpdateEntityHandler extends BaseEntityHandler implements CommandHandler<UpdateEntity> {

    private final Injector injector;

    @Inject
    public UpdateEntityHandler(EntityManager em, Injector injector) {
        super(em);
        this.injector = injector;
    }

    @Override
    public CommandResult execute(UpdateEntity cmd, User user)
            throws CommandException {

        Map<String, Object> changes = cmd.getChanges().getTransientMap();
        PropertyMap changeMap = new PropertyMap(changes);

        if ("Activity".equals(cmd.getEntityName())) {
            ActivityPolicy policy = injector.getInstance(ActivityPolicy.class);
            policy.update(user, cmd.getId(), changeMap);

        } else if ("AttributeGroup".equals(cmd.getEntityName())) {
            updateAttributeGroup(cmd, changes);

        } else if ("Attribute".equals(cmd.getEntityName())) {
            updateAttribute(user, cmd, changes);

        } else if ("Indicator".equals(cmd.getEntityName())) {
            updateIndicator(user, cmd, changes);

        } else if ("Site".equals(cmd.getEntityName())) {
            SitePolicy policy = injector.getInstance(SitePolicy.class);
            policy.update(user, cmd.getId(), changeMap);             

        } else {
            throw new RuntimeException("unknown entity type");
        }

        return null;
    }

    private void updateIndicator(User user, UpdateEntity cmd, Map<String, Object> changes) throws IllegalAccessCommandException {
        Indicator indicator = em.find(Indicator.class, cmd.getId());

        assertDesignPriviledges(user, indicator.getActivity().getDatabase());

        updateIndicatorProperties(indicator, changes);
    }

    private void updateAttribute(User user, UpdateEntity cmd, Map<String, Object> changes) {
        Attribute attribute = em.find(Attribute.class, cmd.getId());

        // TODO: decide where attributes belong and how to manage them
        //     assertDesignPriviledges(user, attribute.get);

        updateAttributeProperties(changes, attribute);
    }

    private void updateAttributeGroup(UpdateEntity cmd, Map<String, Object> changes) {
        AttributeGroup group = em.find(AttributeGroup.class, cmd.getId());


        updateAttributeGroupProperties(group, changes);
    }

}