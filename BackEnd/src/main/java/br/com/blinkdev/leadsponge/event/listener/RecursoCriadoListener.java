package br.com.blinkdev.leadsponge.event.listener;

import br.com.blinkdev.leadsponge.event.ResourcesCreatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class RecursoCriadoListener implements ApplicationListener<ResourcesCreatedEvent> {

    @Override
    public void onApplicationEvent(ResourcesCreatedEvent recursoCriadoEvent) {
        HttpServletResponse response = recursoCriadoEvent.getResponse();
        Long codigo = recursoCriadoEvent.getId();
        adicionarHeaderLocation(response, codigo);
    }

    private void adicionarHeaderLocation(HttpServletResponse response, Long codigo) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(codigo).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
