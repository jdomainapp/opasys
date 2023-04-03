package jda.app.opasys.knowledgeasset.modules.knowledgeasset.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jda.app.opasys.knowledgeasset.modules.confasset.model.ConfAsset;
import jda.app.opasys.knowledgeasset.modules.finasset.model.FinAsset;
import jda.app.opasys.knowledgeasset.modules.metricasset.model.MetricAsset;
import jda.app.opasys.knowledgeasset.modules.planasset.model.PlanAsset;
import jda.app.opasys.knowledgeasset.modules.riskasset.model.RiskAsset;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "knowledge_asset", schema = "knowledge_asset")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = KnowledgeAsset.class)
public class KnowledgeAsset extends RepresentationModel<KnowledgeAsset>{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;
	
	@Column(name = "project_id")
	private int projectId;
	
	private int status;
	
	private String attachment;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "knowledgeAsset", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<ConfAsset> confAssets;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "knowledgeAsset", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<FinAsset> finAssets;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "knowledgeAsset", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<MetricAsset> metricAssets;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "knowledgeAsset", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<PlanAsset> planAssets;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "knowledgeAsset", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<RiskAsset> riskAssets;
}