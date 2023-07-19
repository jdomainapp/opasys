package jda.app.opasys.opa.modules.commonknowledgeasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jda.app.opasys.common.model.KnowledgeAsset;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "knowledge_asset", schema = "opa")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = CommonKnowledgeAsset.class)
public class CommonKnowledgeAsset extends KnowledgeAsset{
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "knowledge_type")
	private int knowledgeType;

}