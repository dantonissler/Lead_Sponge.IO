package br.com.blinkdev.leadsponge.end_points.campaign.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignFilters {
	private String name;
	private String description;
}
