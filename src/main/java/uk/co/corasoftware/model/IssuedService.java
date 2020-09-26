package uk.co.corasoftware.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import uk.co.corasoftware.enums.ApiType;
import uk.co.corasoftware.enums.InstanceType;
import uk.co.corasoftware.model.server.Environment;
import uk.co.corasoftware.model.server.Node;

@Data
@Entity
@Builder
@Table(name = "issued_service")
public class IssuedService {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customerId;

	@Column(name = "description")
	private String description;

	@Column(name = "instance_type")
	private InstanceType instanceType;

	@Column(name = "api_type")
	private ApiType apiType;

	@ManyToOne(targetEntity = RedeemableService.class)
	private List<RedeemableService> redeemableService;

	@ManyToOne(targetEntity = Node.class)
	private List<Node> node;

	@ManyToOne(targetEntity = Environment.class)
	private List<Environment> environment;

}
