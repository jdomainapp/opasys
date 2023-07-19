package jda.app.opasys.common.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass
public class KnowledgeAsset extends OPA {

	@Column(name = "project_id")
	private int projectId;
	
	@Column(name = "activity_id")
	private int activityId;
}