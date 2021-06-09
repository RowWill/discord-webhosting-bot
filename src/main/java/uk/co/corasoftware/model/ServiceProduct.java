package uk.co.corasoftware.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.corasoftware.enums.ApiType;
import uk.co.corasoftware.enums.InstanceType;
import uk.co.corasoftware.model.server.Environment;
import uk.co.corasoftware.model.server.Node;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service_product")
public class ServiceProduct {

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

	@OneToOne(targetEntity = Environment.class)
	private Environment environment;

	@OneToMany(targetEntity = Node.class, fetch = FetchType.EAGER)
	private List<Node> nodes;

}
