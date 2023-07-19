package jda.app.opasys.project.modules.knowledge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jda.app.opasys.project.modules.knowledge.model.Knowledge;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "common_knowledge")
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("common") 
public class CommonKnowledge extends Knowledge{
	@Column(name = "common_knowledge_type")
	private String commonKnowledgeType;

}