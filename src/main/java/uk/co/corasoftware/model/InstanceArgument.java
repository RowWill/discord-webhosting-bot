package uk.co.corasoftware.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class InstanceArgument {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "key")
	private String key;

	@Column(name = "value")
	private String value;

	@ManyToOne
	@JoinColumn(name = "redeemable_service_id")
	private RedeemableService instance;

}
