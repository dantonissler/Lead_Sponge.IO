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
        model.add(linkTo(methodOn(CampanhaEndPoint.class).entryPoint(new CampanhaFilter(), null)).withRel("campanha"));
        model.add(linkTo(methodOn(ClienteEndPoint.class).entryPoint(new ClienteFilter(), null)).withRel("cliente"));
        model.add(linkTo(methodOn(ContatoEndPoint.class).entryPoint(new ContatoFilter(), null)).withRel("contato"));
        model.add(linkTo(methodOn(EmailEndPoint.class).entryPoint(new EmailFilter(), null)).withRel("email"));
        model.add(linkTo(methodOn(EstagioNegociacaoEndPoint.class).entryPoint(new EstagioNegociacaoFilter(), null)).withRel("estagio_negociacao"));
        model.add(linkTo(methodOn(FonteNegociacaoEndPoint.class).entryPoint(new FonteNegociacaoFilter(), null)).withRel("fonte_negociacao"));
        model.add(linkTo(methodOn(MotivoPerdaEndPoint.class).entryPoint(new MotivoPerdaFilter(), null)).withRel("motivo_perda"));
        model.add(linkTo(methodOn(NegociacaoEndPoint.class).entryPoint(new NegociacaoFilter(), null)).withRel("negociacao"));
        model.add(linkTo(methodOn(NegociacaoProdutoEndPoint.class).entryPoint(new NegociacaoProdutoFilter(), null)).withRel("negociacao_produto"));
        model.add(linkTo(methodOn(ProdutoEndPoint.class).entryPoint(new ProdutoFilter(), null)).withRel("produto"));
        model.add(linkTo(methodOn(RoleEndPoint.class).entryPoint(new RoleFilter(), null)).withRel("role"));
        model.add(linkTo(methodOn(SegmentoEndPoint.class).entryPoint(new SegmentoFilter(), null)).withRel("segmento"));
        model.add(linkTo(methodOn(TarefaEndPoint.class).entryPoint(new TarefaFilter(), null)).withRel("tareafa"));
        model.add(linkTo(methodOn(TelefoneEndPoint.class).entryPoint(new TelefoneFilter(), null)).withRel("telefone"));
        model.add(linkTo(methodOn(UsuarioEndPoint.class).entryPoint(new UsuarioFilter(), null)).withRel("usuario"));
        return model;
    }
}
