package uk.co.corasoftware.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.co.corasoftware.enums.ApiType;
import uk.co.corasoftware.enums.RewardType;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "reward")
public class Reward {
	@Id
	@Getter
	@GeneratedValue(generator = "uuid", strategy = GenerationType.AUTO)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Getter
	@Setter
	@Column(name = "name")
	private String name;

	@Getter
	@Setter
	@Column(name = "description")
	private String description;

	@Getter
	@Setter
	@Column(name = "available_at_level")
	private int availableAtLevel;

	@Getter
	@Setter
	@Column(name = "reward_type")
	private RewardType rewardType;

	@Getter
	@Setter
	@Column(name = "api_type")
	private ApiType apiType;
}
