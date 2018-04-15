package br.com.guilinssolution.pettingCore.model.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UsurEntity")
@Table(name = "usur", schema = "dbo", catalog = "pettingdb")
public class UsurEntity implements Serializable {

	private static final long serialVersionUID = 2129748439735669799L;

	@Id
	@Column(name = "id_usur", unique = true, nullable = false)
	private int idUsur;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_contribution")
	private ContributionEntity contributionEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_postAnimal")
	private PostAnimalEntity postAnimalEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_postItem")
	private PostItemEntity postItemEntity;

	@Column(name = "name_usur", nullable = false, length = 30)
	private String nameUsur;

	@Column(name = "cpf_usur", nullable = false, length = 11)
	private String cpfUsur;

	@Column(name = "address_usur", nullable = false, length = 30)
	private String addressUsur;

	@Column(name = "city_usur", nullable = false, length = 30)
	private String cityUsur;

	@Column(name = "state_usur", nullable = false, length = 2)
	private String stateUsur;

	@Column(name = "cellphone_usur", nullable = false, length = 15)
	private String cellphoneUsur;

	@Column(name = "phone_usur", nullable = false, length = 15)
	private String phoneUsur;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usurEntityByIdDonator")
	private Set<ContributionEntity> contributionsForIdDonator;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usurEntityByIdRequest")
	private Set<ContributionEntity> contributionsForIdRequest;

}
