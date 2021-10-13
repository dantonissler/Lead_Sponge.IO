package br.com.blinkdev.leadsponge.end_points.campaign.event;

import br.com.blinkdev.leadsponge.event.ResourcesCreatedEvent;

import javax.servlet.http.HttpServletResponse;

public class CampaignCreatedEvent extends ResourcesCreatedEvent {

    public CampaignCreatedEvent(Object source, HttpServletResponse response, Long id) {
        super(source, response, id);
    }
}
