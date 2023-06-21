package jda.app.opasys.opa.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.common.model.OPA;
import jda.app.opasys.opa.modules.activityasset.model.ActivityAsset;
import jda.app.opasys.opa.modules.defectasset.model.DefectAsset;
import jda.app.opasys.opa.modules.issueasset.model.IssueAsset;
import jda.app.opasys.opa.modules.knowledgeasset.model.OpaKnowledgeAsset;
import jda.app.opasys.opa.modules.orgasset.model.OrgAsset;
import jda.app.opasys.opa.modules.projectasset.model.ProjectAsset;
import jda.app.opasys.opa.modules.riskasset.model.RiskAsset;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;
import jda.modules.msacommon.controller.InterfaceController;
import jda.modules.msacommon.controller.InterfaceControllerRegistry;

@RestController
@RequestMapping(value = "/")
public class ManageOPAController {
	
	public final static String PATH_KNOWLEDGE = "/opa_knowledge_asset";
	public final static String PATH_ORG = "/org_asset";
	public final static String PATH_PROJECT = "/project";
	public final static String PATH_ACTIVITY = "/activity";
	public final static String PATH_RISK = "/risk_asset";
	public final static String PATH_DEFECT = "/defect_asset";
	public final static String PATH_ISSUE = "/issue_asset";
	public final static String PATH_ISSUE_COMMENT = "/comment";
	public final static String PATH_OPA_REDIRECT = "/redirect";
	
	
	@RequestMapping(value = PATH_ORG + "/**")
	public ResponseEntity<?> handleOrgAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<OrgAsset, Integer> controller = ControllerRegistry.getInstance().get(OrgAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_PROJECT + "/**")
	public ResponseEntity<?> handleProject(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<ProjectAsset, Integer> controller = ControllerRegistry.getInstance().get(ProjectAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_ACTIVITY + "/**")
	public ResponseEntity<?> handleActivity(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<ActivityAsset, Integer> controller = ControllerRegistry.getInstance().get(ActivityAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_KNOWLEDGE + "/**")
	public ResponseEntity<?> handleKnowledgeAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<OpaKnowledgeAsset, Integer> controller = ControllerRegistry.getInstance().get(OpaKnowledgeAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_RISK + "/**")
	public ResponseEntity<?> handleRiskAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<RiskAsset, Integer> controller = ControllerRegistry.getInstance().get(RiskAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_DEFECT + "/**")
	public ResponseEntity<?> handleDefectAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<DefectAsset, Integer> controller = ControllerRegistry.getInstance().get(DefectAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_ISSUE + "/**")
	public ResponseEntity<?> handleIssueAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<IssueAsset, Integer> controller = ControllerRegistry.getInstance().get(IssueAsset.class);
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