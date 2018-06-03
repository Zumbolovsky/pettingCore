package br.com.guilinssolution.pettingCore.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.guilinssolution.pettingCore.model.enums.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

	@Column(name = "password_usur", nullable = false, length = 15)
	private String passwordUsur;

	@Enumerated(EnumType.STRING)
	@Column(name = "state_usur", nullable = false, length = 2)
	private State stateUsur;

	@Column(name = "cellphone_usur", nullable = false, length = 15)
	private String cellphoneUsur;

	@Column(name = "phone_usur", nullable = false, length = 15)
	private String phoneUsur;

    public void update(UsurEntity entity) {
		this.setNameUsur(entity.getNameUsur());
		this.setCpfUsur(entity.getCpfUsur());
		this.setAddressUsur(entity.getAddressUsur());
		this.setCityUsur(entity.getCityUsur());
		this.setEmailUsur(entity.getEmailUsur());
		this.setPasswordUsur(entity.getPasswordUsur());
		this.setStateUsur(entity.getStateUsur());
		this.setCellphoneUsur(entity.getCellphoneUsur());
		this.setPhoneUsur(entity.getPhoneUsur());
	}

}
