package com.leadsponge.IO.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties("leadsponge")
public class LeadSpongeApiProperty {

	private final Seguranca seguranca = new Seguranca();
	private final Mail mail = new Mail();
	private final Disco disco = new Disco();
	private final S3 s3 = new S3();
	private final BancoMysql bancoMysql = new BancoMysql();

	@Getter
	@Setter
	public class BancoMysql {
		private String username;
		private String password;
		private String host;
		private String port;

	}

	@Getter
	@Setter
	public class Disco {
		private String raiz;
		private String diretorioFotos;
	}

	@Getter
	@Setter
	public class Mail {
		private String host;
		private Integer port;
		private String username;
		private String password;
	}

	@Getter
	@Setter
	public class S3 {
		private String accessKeyId;
		private String secretAccessKey;
		private String bucket;
	}

	@Getter
	@Setter
	public class Seguranca {
		private String originPermitida;
		private boolean enableHttps;
		private String version;
	}
}
