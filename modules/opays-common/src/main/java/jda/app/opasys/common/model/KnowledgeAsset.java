package jda.app.opasys.common.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@MappedSuperclass
@JsonIgnoreProperties()
public class KnowledgeAsset extends OPA {
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "project_id")
	private int projectId;
	
	@Column(name = "activity_id")
	private int activityId;
	
	public KnowledgeAsset() {
		
	}

	public KnowledgeAsset(int id) {
		this.id=id;
	}

	public KnowledgeAsset(String name, String description, int status, String attachment, int userId, int projectId,
			int activityId, int id) {
		super(name, description, status, attachment, userId);
		this.id=id;
		this.projectId = projectId;
		this.activityId = activityId;
	}
}