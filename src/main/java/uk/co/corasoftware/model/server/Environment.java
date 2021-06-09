package uk.co.corasoftware.model.server;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "environment")
public class Environment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "region")
	private String region;

	@Column(name = "short_domain")
	private String shortDomain;

	@Column(name = "ha_enabled")
	private boolean isHaEnabled;

	@Column(name = "ssl_enabled")
	private boolean sslState;
}
