package jda.app.opasys.project.modules.knowledgeelement.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "metric")
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("metric") 
public class Metric extends KnowledgeElement{

}