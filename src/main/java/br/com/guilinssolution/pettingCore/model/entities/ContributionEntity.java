package br.com.guilinssolution.pettingCore.model.entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idContribution"})
@Table(name = "contribution", schema = "dbo", catalog = "pettingdb")
public class ContributionEntity implements Serializable {

	private static final long serialVersionUID = -2444817615816991554L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contribution", unique = true, nullable = false)
	private Integer idContribution;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_postAnimal")
	private PostAnimalEntity postAnimalEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_postItem")
	private PostItemEntity postItemEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_donator", nullable = false)
	private UsurEntity usurEntityByIdDonator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_request", nullable = false)
	private UsurEntity usurEntityByIdRequest;

	@Column(name = "description_contribution", nullable = false, length = 100)
	private String descriptionContribution;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contributionEntity")
//	private List<UsurEntityLite> usurEntities;

	public void update(ContributionEntity entity) {
		this.setIdContribution(entity.getIdContribution());
		this.setDescriptionContribution(entity.getDescriptionContribution());
		this.setIdContribution(entity.getIdContribution());
		this.setPostAnimalEntity(entity.getPostAnimalEntity());
		this.setPostItemEntity(entity.getPostItemEntity());
		this.setUsurEntityByIdDonator(entity.getUsurEntityByIdDonator());
		this.setUsurEntityByIdRequest(entity.getUsurEntityByIdRequest());
	}

}
