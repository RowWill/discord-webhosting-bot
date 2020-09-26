package uk.co.corasoftware.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import uk.co.corasoftware.enums.ApiType;
import uk.co.corasoftware.enums.InstanceType;

@Data
@Entity
@Builder
@Table(name = "redeemable_service")
public class RedeemableService {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "instance_type")
	private InstanceType instanceType;

	@Column(name = "api_type")
	private ApiType apiType;

	@OneToMany(mappedBy = "id")
	private List<InstanceArgument> argument;
}
