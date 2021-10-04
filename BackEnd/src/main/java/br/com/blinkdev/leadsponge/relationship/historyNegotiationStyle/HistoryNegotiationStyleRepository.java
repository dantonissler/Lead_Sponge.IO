package br.com.blinkdev.leadsponge.relationship.historyNegotiationStyle;

import br.com.blinkdev.leadsponge.relationship.historyNegotiationStyle.entity.HistoryNegotiationStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryNegotiationStyleRepository extends JpaRepository<HistoryNegotiationStyle, Long> {

}
