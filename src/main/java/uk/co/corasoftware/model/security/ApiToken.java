package uk.co.corasoftware.model.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import uk.co.corasoftware.enums.TokenType;

@Data
@Entity
@Builder
@Table(name = "api_token")
public class ApiToken {

	@Id
	@Getter
	@GeneratedValue(generator = "uuid", strategy = GenerationType.AUTO)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@NonNull
	@Column(name = "token", unique = true)
	private String token;

	@NonNull
	@Column(name = "name", unique = true)
	private String name;

	@NonNull
	@Column(name = "issued_to", unique = true)
	private String issuedTo;

	@Column(name = "description")
	private String description;

	@NonNull
	@Column(name = "issued_by")
	private String issuedBy;

	@NonNull
	@Column(name = "token_type")
	private TokenType tokenType;
}
