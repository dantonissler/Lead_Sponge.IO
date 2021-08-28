package br.com.blinkdev.leadsponge.models.email;

import br.com.blinkdev.leadsponge.models.View;
import br.com.blinkdev.leadsponge.models.contato.Contato;
import com.fasterxml.jackson.annotation.JsonView;
import br.com.blinkdev.leadsponge.models.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emails")
@TableGenerator(name = "email_id", table = "sequencia_tabelas", pkColumnName = "tabela", valueColumnName = "identificador", pkColumnValue = "emails", allocationSize = 1)
public class Email extends UserDateAudit {

	@Id
	@Column(name = "id")
	@JsonView(View.Email.class)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "email_id")
	private Long id;

	@NotEmpty(message = "{email.null}")
	@javax.validation.constraints.Email(message = "{email.not.valid}")
	private String email;

	@ManyToOne
	@JoinColumn(name = "contato_id")
	private Contato contato;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		if (id == null) {
			return other.id == null;
		} else return id.equals(other.id);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
