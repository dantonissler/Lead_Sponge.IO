package br.com.blinkdev.leadsponge.endPoints;

import br.com.blinkdev.leadsponge.models.RootEntryPointModel;
import br.com.blinkdev.leadsponge.models.campanha.CampanhaFilter;
import br.com.blinkdev.leadsponge.models.cliente.ClienteFilter;
import br.com.blinkdev.leadsponge.models.contato.ContatoFilter;
import br.com.blinkdev.leadsponge.models.email.EmailFilter;
import br.com.blinkdev.leadsponge.models.estagioNegociacao.EstagioNegociacaoFilter;
import br.com.blinkdev.leadsponge.models.fonteNegociacao.FonteNegociacaoFilter;
import br.com.blinkdev.leadsponge.models.motivoPerda.MotivoPerdaFilter;
import br.com.blinkdev.leadsponge.models.negociacao.NegociacaoFilter;
import br.com.blinkdev.leadsponge.models.negociacaoProduto.NegociacaoProdutoFilter;
import br.com.blinkdev.leadsponge.models.produto.ProdutoFilter;
import br.com.blinkdev.leadsponge.models.role.RoleFilter;
import br.com.blinkdev.leadsponge.models.segmento.SegmentoFilter;
import br.com.blinkdev.leadsponge.models.tarefa.TarefaFilter;
import br.com.blinkdev.leadsponge.models.telefone.TelefoneFilter;
import br.com.blinkdev.leadsponge.models.usuario.UsuarioFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(produces = "application/hal+json")
public class RootEntryPointEndpoint {

    @GetMapping
    RootEntryPointModel root() {
        var model = new RootEntryPointModel();
        model.add(linkTo(methodOn(CampanhaEndPoint.class).getCampanhaByFilter(new CampanhaFilter(), null)).withRel("campanha"));
        model.add(linkTo(methodOn(ClienteEndPoint.class).list(new ClienteFilter(), null)).withRel("cliente"));
        model.add(linkTo(methodOn(ContatoEndPoint.class).list(new ContatoFilter(), null)).withRel("contato"));
        model.add(linkTo(methodOn(EmailEndPoint.class).list(new EmailFilter(), null)).withRel("email"));
        model.add(linkTo(methodOn(EstagioNegociacaoEndPoint.class).list(new EstagioNegociacaoFilter(), null)).withRel("estagio_negociacao"));
        model.add(linkTo(methodOn(FonteNegociacaoEndPoint.class).list(new FonteNegociacaoFilter(), null)).withRel("fonte_negociacao"));
        model.add(linkTo(methodOn(MotivoPerdaEndPoint.class).list(new MotivoPerdaFilter(), null)).withRel("motivo_perda"));
        model.add(linkTo(methodOn(NegociacaoEndPoint.class).list(new NegociacaoFilter(), null)).withRel("negociacao"));
        model.add(linkTo(methodOn(NegociacaoProdutoEndPoint.class).list(new NegociacaoProdutoFilter(), null)).withRel("negociacao_produto"));
        model.add(linkTo(methodOn(ProdutoEndPoint.class).list(new ProdutoFilter(), null)).withRel("produto"));
        model.add(linkTo(methodOn(RoleEndPoint.class).list(new RoleFilter(), null)).withRel("role"));
        model.add(linkTo(methodOn(SegmentoEndPoint.class).list(new SegmentoFilter(), null)).withRel("segmento"));
        model.add(linkTo(methodOn(TarefaEndPoint.class).list(new TarefaFilter(), null)).withRel("tareafa"));
        model.add(linkTo(methodOn(TelefoneEndPoint.class).list(new TelefoneFilter(), null)).withRel("telefone"));
        model.add(linkTo(methodOn(UsuarioEndPoint.class).list(new UsuarioFilter(), null)).withRel("usuario"));
        return model;
    }
}
