package teste;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {

	public Produto(Long id, String descricao, BigDecimal precoUnitario) {
		this.id = id;
		this.descricao = descricao;
		this.precoUnitario = precoUnitario;
	}

	public Produto() {
	}

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;

	@Valid
	@OneToMany(mappedBy = "contato", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemPedido> produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public List<ItemPedido> getProduto() {
		return produto;
	}

	public void setProduto(List<ItemPedido> produto) {
		this.produto = produto;
	}
}