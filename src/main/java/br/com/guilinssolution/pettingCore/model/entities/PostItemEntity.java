package br.com.guilinssolution.pettingCore.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import br.com.guilinssolution.pettingCore.model.enums.Species;
import br.com.guilinssolution.pettingCore.model.enums.Type;
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
@EqualsAndHashCode(of = {"idPostItem"})
@EntityListeners({AuditingEntityListener.class})
@Table(name = "postItem", schema = "dbo", catalog = "pettingdb")
public class PostItemEntity implements Serializable {

	private static final long serialVersionUID = 1651057555186766600L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_postItem", unique = true, nullable = false)
	private Integer idPostItem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usur", nullable = false)
	private UsurEntity usurEntity;

	@Column(name = "title_postItem", nullable = false, length = 30)
	private String titlePostItem;

	@Column(name = "description_postItem", nullable = false, length = 100)
	private String descriptionPostItem;

	@Column(name = "image_postItem", length = 50)
	private String imagePostItem;

	@Enumerated(EnumType.STRING)
	@Column(name = "type_postItem", nullable = false, length = 7)
	private Type typePostItem;

	@Enumerated(EnumType.STRING)
	@Column(name = "species_postItem", nullable = false, length = 8)
	private Species speciesPostItem;

	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(name = "createdDate_postItem", nullable = false)
	private Date createdDatePostItem;

	@LastModifiedDate
	@Temporal(TemporalType.DATE)
	@Column(name = "lastModifiedDate_postItem", nullable = false)
	private Date lastModifiedDatePostItem;

	public void update(PostItemEntity entity) {
		this.setUsurEntity(entity.getUsurEntity());
		this.setTitlePostItem(entity.getTitlePostItem());
		this.setDescriptionPostItem(entity.getDescriptionPostItem());
		this.setImagePostItem(entity.getImagePostItem());
		this.setTypePostItem(entity.getTypePostItem());
	}

}
