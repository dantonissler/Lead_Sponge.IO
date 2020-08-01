package teste;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "pedido")
public class Pedido {

	public Pedido(Long id, Date dataPedido, BigDecimal total, BigDecimal cliente) {
		this.id = id;
		this.dataPedido = dataPedido;
		this.total = total;
		this.cliente = cliente;
	}

	public Pedido() {
	}

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "data_pedido")
	private Date dataPedido;

	@Column(name = "total")
	private BigDecimal total;

	@Column(name = "cliente_id")
	private BigDecimal cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getCliente() {
		return cliente;
	}

	public void setCliente(BigDecimal cliente) {
		this.cliente = cliente;
	}

}
