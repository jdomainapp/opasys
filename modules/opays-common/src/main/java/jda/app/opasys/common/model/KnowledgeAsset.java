package jda.app.opasys.common.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass
@JsonIgnoreProperties()
public class KnowledgeAsset extends OPA {

	@Column(name = "project_id")
	private int projectId;
	
	@Column(name = "activity_id")
	private int activityId;
	
	public KnowledgeAsset() {
		
	}

	public KnowledgeAsset(String name, String description, int status, String attachment, int userId, int projectId,
			int activityId, int id) {
		super(name, description, status, attachment, userId, id);
		this.projectId = projectId;
		this.activityId = activityId;
	}
}