package com.leadsponge.IO.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("leadsponge")
public class LeadSpongeApiProperty {

	private final Seguranca seguranca = new Seguranca();
	private final Mail mail = new Mail();
	private final Disco disco = new Disco();
	private final S3 s3 = new S3();
	private final Oauth2 oauth2 = new Oauth2();

	@Data
	public class Disco {
		private String raiz;
		private String diretorioFotos;
	}

	@Data
	public class Mail {
		private String host;
		private Integer port;
		private String username;
		private String password;
	}

	@Data
	public class S3 {
		private String accessKeyId;
		private String secretAccessKey;
		private String bucket;
	}

	@Data
	public class Seguranca {
		private String originPermitida;
		private boolean enableHttps;
	}

	@Data
	public class Oauth2 {
		private String clientId;
		private String clientSecret;
		private int tokenTimeout;
		private int refreshTokenTimeout;
		private String privateKey;
		private String publicKey;
		private String accessTokenUri;
	}
}
