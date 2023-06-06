package jda.app.opasys.project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.common.model.OPA;
import jda.app.opasys.project.modules.activity.model.Activity;
import jda.app.opasys.project.modules.defectasset.model.DefectAsset;
import jda.app.opasys.project.modules.issueasset.model.IssueAsset;
import jda.app.opasys.project.modules.opa.OPAController;
import jda.app.opasys.project.modules.project.model.Project;
import jda.app.opasys.project.modules.riskasset.model.RiskAsset;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.ControllerRegistry2;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController2;
import jda.modules.msacommon.controller.InterfaceController;
import jda.modules.msacommon.controller.InterfaceControllerRegistry;

@RestController
@RequestMapping(value = "/")
public class ManageProjectController {
	
	
	public final static String PATH_PROJECT = "/project";
	public final static String PATH_ACTIVITY = "/activity";
	public final static String PATH_RISK = "/risk";
	public final static String PATH_DEFECT = "/defect";
	public final static String PATH_ISSUE = "/issue";
	public final static String PATH_ISSUE_COMMENT = "/comment";
	public final static String PATH_OPA = "/opa";
	public final static String PATH_CREATE_OPA = "/opa/oncomplete";

	@RequestMapping(value = PATH_PROJECT + "/**")
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		//manageproject/project/{10}: CRUD project (click link to get activites of a project)
		//project/2/acitivity: get list of activities in a project
		if (ControllerTk.isPathContainModule(ManageProjectController.PATH_PROJECT, path)) {
			DefaultController2<Project, Integer> childController = ControllerRegistry2.getInstance().get(Project.class);
			return childController.handleRequest(req, res, ids.isEmpty()? null : ids.get(0));
		//project/{10}/activity/{id}: CRUD a activity
		//TODO: getActivityList by projectId
		//TODO: File upload????
		} else if (ControllerTk.isPathContainModule(ManageProjectController.PATH_ACTIVITY, path)) {
			DefaultController2<Activity, Integer> childController = ControllerRegistry2.getInstance().get(Activity.class);
			return childController.handleRequest(req, res, ids.size()==2 ? ids.get(1):null);
		//project/{10}/risk/{id}
		}else if (ControllerTk.isPathContainModule(ManageProjectController.PATH_RISK, path)) {
			DefaultController2<RiskAsset, Integer> childController = ControllerRegistry2.getInstance().get(RiskAsset.class);
			return childController.handleRequest(req, res, ids.size()==2 ? ids.get(1):null);
		}else if (ControllerTk.isPathContainModule(ManageProjectController.PATH_DEFECT, path)) {
			DefaultController2<DefectAsset, Integer> childController = ControllerRegistry2.getInstance().get(DefectAsset.class);
			return childController.handleRequest(req, res, ids.size()==2 ? ids.get(1):null);
		}else if (ControllerTk.checkParentChildService(ManageProjectController.PATH_ISSUE, ManageProjectController.PATH_ISSUE_COMMENT, path)) {
			DefaultController2<IssueAsset, Integer> childController = ControllerRegistry2.getInstance().get(IssueAsset.class);
			return childController.handleRequest(req, res);
		}else{
			// invalid path
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value = PATH_OPA + "/**")
	public ResponseEntity<?> handleRedirectRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		InterfaceController<Integer, OPA> interfaceController = InterfaceControllerRegistry.getInstance().get(OPA.class);
		return interfaceController.handleRequest(req, res);
	}
}