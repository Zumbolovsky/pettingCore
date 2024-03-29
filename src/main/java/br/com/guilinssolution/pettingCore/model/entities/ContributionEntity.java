package br.com.guilinssolution.pettingCore.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idContribution"})
@EntityListeners({AuditingEntityListener.class})
@Table(name = "contribution", schema = "dbo", catalog = "pettingdb")
public class ContributionEntity implements Serializable {

	private static final long serialVersionUID = -2444817615816991554L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contribution", unique = true, nullable = false)
	private Integer idContribution;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_postAnimal", unique = true)
	private PostAnimalEntity postAnimalEntity;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_postItem", unique = true)
	private PostItemEntity postItemEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_donator", nullable = false)
	private UsurEntity usurEntityByIdDonator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_request", nullable = false)
	private UsurEntity usurEntityByIdRequest;

	@Column(name = "description_contribution", nullable = false, length = 100)
	private String descriptionContribution;

	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(name = "createdDate_contribution", nullable = false)
	private Date createdDatePostItem;

	@LastModifiedDate
	@Temporal(TemporalType.DATE)
	@Column(name = "lastModifiedDate_contribution", nullable = false)
	private Date lastModifiedDatePostItem;

	public void update(ContributionEntity entity) {
		this.setPostAnimalEntity(entity.getPostAnimalEntity());
		this.setPostItemEntity(entity.getPostItemEntity());
		this.setUsurEntityByIdDonator(entity.getUsurEntityByIdDonator());
		this.setUsurEntityByIdRequest(entity.getUsurEntityByIdRequest());
		this.setDescriptionContribution(entity.getDescriptionContribution());
	}

}
