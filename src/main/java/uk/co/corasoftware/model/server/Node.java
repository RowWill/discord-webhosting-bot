package uk.co.corasoftware.model.server;

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
import uk.co.corasoftware.enums.NodeType;
import uk.co.corasoftware.enums.ScalingMode;
import uk.co.corasoftware.model.IssuedService;

@Data
@Entity
@Builder
@Table(name = "node")
public class Node {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "tag")
	private String tag;

	@Column(name = "display_name")
	private String displayName;

	@Column(name = "node_group")
	private String nodeGroup;

	@Column(name = "disklimit")
	private int diskLimit;

	@Column(name = "extip")
	private int extip;

	@Column(name = "extipv6")
	private int extipv6;

	@Column(name = "fixed_cloudlets")
	private int fixedCloudlets;

	@Column(name = "flexible_cloudlets")
	private int flexibleCloudlets;

	@Column(name = "restart_delay")
	private int restartDelay;

	@Column(name = "node_type")
	private NodeType nodeType;

	@Column(name = "scaling_mode")
	private ScalingMode scalingMode;

	@ManyToOne
	@JoinColumn(name = "issued_service_id")
	private IssuedService issuedInstance;
}
