package jda.app.opasys.assetstorage.modules.orgasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor @Getter @Setter @ToString
@Entity
@Table(name = "org_asset", schema = "asset_storage")
public class OrgAssetStorage extends RepresentationModel<OrgAssetStorage>{
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "file_path")
	private String filePath;
	
	public OrgAssetStorage() {
		
	}

}