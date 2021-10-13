package br.com.blinkdev.leadsponge.end_points.root_entry_point.controller;

import br.com.blinkdev.leadsponge.end_points.campaign.filter.CampaignFilters;
import br.com.blinkdev.leadsponge.end_points.campaign.controller.CampaignController;
import br.com.blinkdev.leadsponge.end_points.root_entry_point.model.RootEntryPointModel;
import io.swagger.annotations.Api;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
@Api(tags = "Root entry point")
public class RootEntryPointController {

    @GetMapping
    public RootEntryPointModel root() {
        var model = new RootEntryPointModel();
        model.add(linkTo(methodOn(CampaignController.class).searchWithFilters(new CampaignFilters(), null)).withRel("campanha"));
//        model.add(linkTo(methodOn(ClienteEndPoint.class).list(new ClienteFilter(), null)).withRel("cliente"));
//        model.add(linkTo(methodOn(ContatoEndPoint.class).list(new ContatoFilter(), null)).withRel("contato"));
//        model.add(linkTo(methodOn(EmailEndPoint.class).list(new EmailFilter(), null)).withRel("email"));
//        model.add(linkTo(methodOn(EstagioNegociacaoEndPoint.class).list(new EstagioNegociacaoFilter(), null)).withRel("estagio_negociacao"));
//        model.add(linkTo(methodOn(FonteNegociacaoEndPoint.class).list(new FonteNegociacaoFilter(), null)).withRel("fonte_negociacao"));
//        model.add(linkTo(methodOn(MotivoPerdaEndPoint.class).list(new MotivoPerdaFilter(), null)).withRel("motivo_perda"));
//        model.add(linkTo(methodOn(NegociacaoEndPoint.class).list(new NegociacaoFilter(), null)).withRel("negociacao"));
//        model.add(linkTo(methodOn(NegociacaoProdutoEndPoint.class).list(new NegociacaoProdutoFilter(), null)).withRel("negociacao_produto"));
//        model.add(linkTo(methodOn(ProdutoEndPoint.class).list(new ProdutoFilter(), null)).withRel("produto"));
//        model.add(linkTo(methodOn(RoleEndPoint.class).list(new RoleFilter(), null)).withRel("role"));
//        model.add(WebMvcLinkBuilder.linkTo(methodOn(SegmentoEndPoint.class).list(new SegmentoFilter(), null)).withRel("segmento"));
//        model.add(WebMvcLinkBuilder.linkTo(methodOn(TarefaEndPoint.class).list(new TarefaFilter(), null)).withRel("tareafa"));
//        model.add(WebMvcLinkBuilder.linkTo(methodOn(TelefoneEndPoint.class).list(new TelefoneFilter(), null)).withRel("telefone"));
//        model.add(WebMvcLinkBuilder.linkTo(methodOn(UsuarioEndPoint.class).list(new UsuarioFilter(), null)).withRel("usuario"));
        return model;
    }
}
