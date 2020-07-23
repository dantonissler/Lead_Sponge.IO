package com.leadsponge.IO.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("leadsponge")
public class LeadSpongeApiProperty {

	private final Seguranca seguranca = new Seguranca();

	private final Mail mail = new Mail();

	private final Disco disco = new Disco();

	private final S3 s3 = new S3();

	private final BancoMysql bancoMysql = new BancoMysql();

	public BancoMysql getBancoMysql() {
		return bancoMysql;
	}

	public S3 getS3() {
		return s3;
	}

	public Mail getMail() {
		return mail;
	}

	public Seguranca getSeguranca() {
		return seguranca;
	}

	public Disco getDisco() {
		return disco;
	}

	public class BancoMysql {

		private String username;
		private String password;
		private String host;
		private String port;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

	}

	public class Disco {
		private String raiz;

		private String diretorioFotos;

		public String getRaiz() {
			return raiz;
		}

		public void setRaiz(String raiz) {
			this.raiz = raiz;
		}

		public String getDiretorioFotos() {
			return diretorioFotos;
		}

		public void setDiretorioFotos(String diretorioFotos) {
			this.diretorioFotos = diretorioFotos;
		}
	}

	public class Mail {

		private String host;

		private Integer port;

		private String username;

		private String password;

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public Integer getPort() {
			return port;
		}

		public void setPort(Integer port) {
			this.port = port;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}

	public class S3 {

		private String accessKeyId;

		private String secretAccessKey;

		private String bucket;

		public String getBucket() {
			return bucket;
		}

		public void setBucket(String bucket) {
			this.bucket = bucket;
		}

		public String getAccessKeyId() {
			return accessKeyId;
		}

		public void setAccessKeyId(String accessKeyId) {
			this.accessKeyId = accessKeyId;
		}

		public String getSecretAccessKey() {
			return secretAccessKey;
		}

		public void setSecretAccessKey(String secretAccessKey) {
			this.secretAccessKey = secretAccessKey;
		}

	}

	public class Seguranca {

		private String originPermitida;

		private boolean enableHttps;

		public String getOriginPermitida() {
			return originPermitida;
		}

		public void setOriginPermitida(String originPermitida) {
			this.originPermitida = originPermitida;
		}

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
	}

}
