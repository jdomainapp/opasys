package jda.app.opasys.opa.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.common.model.OPA;
import jda.app.opasys.opa.modules.defectasset.model.DefectAsset;
import jda.app.opasys.opa.modules.issueasset.model.IssueAsset;
import jda.app.opasys.opa.modules.knowledgeasset.model.OpaKnowledgeAsset;
import jda.app.opasys.opa.modules.orgasset.model.OrgAsset;
import jda.app.opasys.opa.modules.projectasset.model.ProjectAsset;
import jda.app.opasys.opa.modules.riskasset.model.RiskAsset;
import jda.modules.msacommon.controller.ControllerRegistry2;
import jda.modules.msacommon.controller.DefaultController2;
import jda.modules.msacommon.controller.InterfaceController;
import jda.modules.msacommon.controller.InterfaceControllerRegistry;

@RestController
@RequestMapping(value = "/")
public class ManageOPAController {
	
	public final static String PATH_KNOWLEDGE = "/opa_knowledge_asset";
	public final static String PATH_ORG = "/org_asset";
	public final static String PATH_PROJECT = "/project";
	public final static String PATH_RISK = "/risk_asset";
	public final static String PATH_DEFECT = "/defect_asset";
	public final static String PATH_ISSUE = "/issue_asset";
	public final static String PATH_ISSUE_COMMENT = "/comment";
	public final static String PATH_OPA_REDIRECT = "/redirect";
	
	
	@RequestMapping(value = PATH_ORG + "/**")
	public ResponseEntity<?> handleOrgAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController2<OrgAsset, Integer> controller = ControllerRegistry2.getInstance().get(OrgAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_PROJECT + "/**")
	public ResponseEntity<?> handleProject(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController2<ProjectAsset, Integer> controller = ControllerRegistry2.getInstance().get(ProjectAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_KNOWLEDGE + "/**")
	public ResponseEntity<?> handleKnowledgeAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController2<OpaKnowledgeAsset, Integer> controller = ControllerRegistry2.getInstance().get(OpaKnowledgeAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_RISK + "/**")
	public ResponseEntity<?> handleRiskAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController2<RiskAsset, Integer> controller = ControllerRegistry2.getInstance().get(RiskAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_DEFECT + "/**")
	public ResponseEntity<?> handleDefectAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController2<DefectAsset, Integer> controller = ControllerRegistry2.getInstance().get(DefectAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_ISSUE + "/**")
	public ResponseEntity<?> handleIssueAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController2<IssueAsset, Integer> controller = ControllerRegistry2.getInstance().get(IssueAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@RequestMapping(value = PATH_OPA_REDIRECT + "/**")
	public ResponseEntity<?> handleRedirect(HttpServletRequest req, HttpServletResponse res) throws IOException {
		InterfaceController<OPA, Integer> controller = InterfaceControllerRegistry.getInstance().get(OPA.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}