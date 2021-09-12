package br.com.blinkdev.leadsponge.endPoints.campaign.event;

import br.com.blinkdev.leadsponge.event.ResourcesCreatedEvent;

import javax.servlet.http.HttpServletResponse;

public class CampaignPatchEvent extends ResourcesCreatedEvent {
    public CampaignPatchEvent(Object source, HttpServletResponse response, Long id) {
        super(source, response, id);
    }
}
