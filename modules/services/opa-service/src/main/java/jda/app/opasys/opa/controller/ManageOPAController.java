package jda.app.opasys.opa.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.common.model.OPA;
import jda.app.opasys.opa.modules.activityasset.model.ActivityAsset;
import jda.app.opasys.opa.modules.configasset.model.ConfigAsset;
import jda.app.opasys.opa.modules.defectasset.model.DefectAsset;
import jda.app.opasys.opa.modules.financeasset.model.FinanceAsset;
import jda.app.opasys.opa.modules.issueasset.model.CommentAsset;
import jda.app.opasys.opa.modules.issueasset.model.IssueAsset;
import jda.app.opasys.opa.modules.metricasset.model.MetricAsset;
import jda.app.opasys.opa.modules.orgasset.model.OrgAsset;
import jda.app.opasys.opa.modules.planasset.model.PlanAsset;
import jda.app.opasys.opa.modules.projectasset.model.ProjectAsset;
import jda.app.opasys.opa.modules.riskasset.model.RiskAsset;
import jda.modules.msacommon.connections.CachedBodyHttpServletRequest;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;
import jda.modules.msacommon.controller.InterfaceController;
import jda.modules.msacommon.controller.InterfaceControllerRegistry;

@RestController
@RequestMapping(value = "/")
public class ManageOPAController {
	public final static String PATH_PROJECT = "/project";
	public final static String PATH_ACTIVITY = "/activity";
	public final static String PATH_OPA_ORG_ASSET = "/org_asset";
	public final static String PATH_OPA_REDIRECT = "/redirect";
	
	@RequestMapping(value = PATH_OPA_ORG_ASSET + "/**")
	public ResponseEntity<?> handleOrgAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<OrgAsset, Integer> controller = ControllerRegistry.getInstance().get(OrgAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@PostMapping(value = PATH_PROJECT + "/**")
	public ResponseEntity<?> handleProject(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<ProjectAsset, Integer> controller = ControllerRegistry.getInstance().get(ProjectAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@PostMapping(value = PATH_ACTIVITY + "/**")
	public ResponseEntity<?> handleActivity(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<ActivityAsset, Integer> controller = ControllerRegistry.getInstance().get(ActivityAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@PostMapping(value = "opa_**")
	public ResponseEntity<?> handleCreateAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Class<?> clazz = SubtypeUrls.opaUrls.get(req.getServletPath());
		HttpServletRequest cloneRequest = new CachedBodyHttpServletRequest(req);
		processAssetSubtype(cloneRequest, res);
		DefaultController<?, Integer> controller = ControllerRegistry.getInstance().get(clazz);
		return controller != null ? controller.handleRequest(cloneRequest, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@GetMapping(value = PATH_OPA_REDIRECT + "/**")
	public ResponseEntity<?> processAssetSubtype(HttpServletRequest req, HttpServletResponse res) throws IOException {
		InterfaceController<OPA, Integer> controller = InterfaceControllerRegistry.getInstance().get(OPA.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}