package uk.co.corasoftware.model.security;

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
import lombok.NonNull;
import lombok.Setter;
import uk.co.corasoftware.enums.TokenType;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "api_token")
public class ApiToken {

	@Id
	@Getter
	@GeneratedValue(generator = "uuid", strategy = GenerationType.AUTO)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Getter
	@Setter
	@NonNull
	@Column(name = "token", unique = true)
	private String token;

	@Getter
	@Setter
	@NonNull
	@Column(name = "name", unique = true)
	private String name;

	@Getter
	@Setter
	@Column(name = "issued_to", unique = true)
	private String issuedTo;

	@Getter
	@Setter
	@Column(name = "description")
	private String description;

	@Getter
	@Setter
	@Column(name = "issued_by")
	private String issuedBy;

	@Getter
	@Setter
	@Column(name = "token_type")
	private TokenType tokenType;
}
