package br.com.blinkdev.leadsponge.models.cliente;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.com.blinkdev.leadsponge.models.contato.Contato;
import br.com.blinkdev.leadsponge.models.negociacao.Negociacao;
import br.com.blinkdev.leadsponge.models.segmento.Segmento;
import br.com.blinkdev.leadsponge.models.usuario.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ {
	public static volatile SingularAttribute<Cliente, Long> id;
	public static volatile SingularAttribute<Cliente, String> nome;
	public static volatile SingularAttribute<Cliente, String> url;
	public static volatile SingularAttribute<Cliente, String> resumo;
	public static volatile ListAttribute<Cliente, Negociacao> negociacoes;
	public static volatile ListAttribute<Cliente, Contato> contato;
	public static volatile ListAttribute<Cliente, Segmento> segmentos;
	public static volatile SingularAttribute<Cliente, Usuario> responsavel;
	public static volatile ListAttribute<Cliente, Usuario> seguidores;
}