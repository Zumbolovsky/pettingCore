package br.com.guilinssolution.pettingCore.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import br.com.guilinssolution.pettingCore.model.enums.State;
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
@EqualsAndHashCode(of = {"idUsur"})
@Table(name = "usur", schema = "dbo", catalog = "pettingdb")
public class UsurEntity implements Serializable {

	private static final long serialVersionUID = 2129748439735669799L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usur", unique = true, nullable = false)
	private Integer idUsur;

	@Column(name = "name_usur", nullable = false, length = 30)
	private String nameUsur;

	@Column(name = "cpf_usur", nullable = false, length = 11)
	private String cpfUsur;

	@Column(name = "address_usur", nullable = false, length = 30)
	private String addressUsur;

	@Column(name = "city_usur", nullable = false, length = 30)
	private String cityUsur;

	@Column(name = "email_usur", nullable = false, length = 30)
	private String emailUsur;

	@Enumerated(EnumType.STRING)
	@Column(name = "state_usur", nullable = false, length = 2)
	private State stateUsur;

	@Column(name = "cellphone_usur", nullable = false, length = 15)
	private String cellphoneUsur;

	@Column(name = "phone_usur", nullable = false, length = 15)
	private String phoneUsur;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usurEntity")
	private List<PostAnimalEntity> postAnimalEntities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usurEntity")
	private List<PostItemEntity> postItemEntities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usurEntityByIdDonator")
	private List<ContributionEntity> contributionsForIdDonator;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usurEntityByIdRequest")
	private List<ContributionEntity> contributionsForIdRequest;

	//todo: reset operations
	public void update(UsurEntity entity) {
		this.setAddressUsur(entity.getAddressUsur());
		this.setCellphoneUsur(entity.getCellphoneUsur());
		this.setCityUsur(entity.getCityUsur());
		this.setCpfUsur(entity.getCpfUsur());
		this.setEmailUsur(entity.getEmailUsur());
		this.setNameUsur(entity.getNameUsur());
		this.setPhoneUsur(entity.getPhoneUsur());
		this.setStateUsur(entity.getStateUsur());
		//this.setContributionEntity(entity.getContributionEntity());
		//this.setPostAnimalEntity(entity.getPostAnimalEntity());
		//this.setPostItemEntity(entity.getPostItemEntity());
	}

}
