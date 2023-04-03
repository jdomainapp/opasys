package jda.app.opasys.knowledgeasset.modules.riskasset.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jda.app.opasys.knowledgeasset.modules.knowledgeasset.model.KnowledgeAsset;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString
@Entity
@Table(name = "risk_asset", schema = "knowledge_asset")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = RiskAsset.class)
public class RiskAsset extends RepresentationModel<RiskAsset>{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="opa_id", nullable = false)
	private KnowledgeAsset knowledgeAsset;
}
