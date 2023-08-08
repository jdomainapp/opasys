package jda.app.opasys.filestorage.modules.knowledgeasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @Getter @Setter @ToString
@Entity
@Table(name = "knowledge_asset", schema = "asset_storage")
public class KnowledgeAssetStorage extends RepresentationModel<KnowledgeAssetStorage>{
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "file_path")
	private String filePath;
	
	public KnowledgeAssetStorage() {
		
	}

}