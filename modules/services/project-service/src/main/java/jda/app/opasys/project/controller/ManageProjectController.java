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
import jda.app.opasys.project.modules.defect.model.Defect;
import jda.app.opasys.project.modules.issue.model.Issue;
import jda.app.opasys.project.modules.knowlegde.model.Knowledge;
import jda.app.opasys.project.modules.opa.OPAController;
import jda.app.opasys.project.modules.opa.OPA2Controller;
import jda.app.opasys.project.modules.opa.OpaUrl;
import jda.app.opasys.project.modules.opa.model.OPA2;
import jda.app.opasys.project.modules.project.model.Project;
import jda.app.opasys.project.modules.risk.model.Risk;
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
	public final static String PATH_KNOWLEDGE = "/knowledge";
	public final static String PATH_RISK = "/risk";
	public final static String PATH_DEFECT = "/defect";
	public final static String PATH_ISSUE = "/issue";
	public final static String PATH_ISSUE_COMMENT = "/comment";
	public final static String PATH_LOCAL_OPA = "/local_opa";
	public final static String PATH_CREATE_LOCAL_OPA = "/local_opa/oncomplete";
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
		}else if (ControllerTk.isPathContainModule(ManageProjectController.PATH_KNOWLEDGE, path)) {
			DefaultController2<Knowledge, Integer> childController = ControllerRegistry2.getInstance().get(Knowledge.class);
			return childController.handleRequest(req, res, ids.size()==2 ? ids.get(1):null);
		}else if (ControllerTk.isPathContainModule(ManageProjectController.PATH_RISK, path)) {
			DefaultController2<Risk, Integer> childController = ControllerRegistry2.getInstance().get(Risk.class);
			return childController.handleRequest(req, res, ids.size()==2 ? ids.get(1):null);
		}else if (ControllerTk.isPathContainModule(ManageProjectController.PATH_DEFECT, path)) {
			DefaultController2<Defect, Integer> childController = ControllerRegistry2.getInstance().get(Defect.class);
			return childController.handleRequest(req, res, ids.size()==2 ? ids.get(1):null);
		}else if (ControllerTk.checkParentChildService(ManageProjectController.PATH_ISSUE, ManageProjectController.PATH_ISSUE_COMMENT, path)) {
			DefaultController2<Issue, Integer> childController = ControllerRegistry2.getInstance().get(Issue.class);
			return childController.handleRequest(req, res);
		}else{
			// invalid path
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value = PATH_LOCAL_OPA + "/**")
	public ResponseEntity<?> handleRedirectRequestForLocalOpa(HttpServletRequest req, HttpServletResponse res) throws IOException {
		InterfaceController<OPA, Integer> interfaceController = InterfaceControllerRegistry.getInstance().get(OPA.class);
		return interfaceController.handleRequest(req, res);
	}
	
	@RequestMapping(value = PATH_OPA + "/**")
	public ResponseEntity<?> handleRedirectRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		InterfaceController<OPA2, Integer> interfaceController = InterfaceControllerRegistry.getInstance().get(OPA2.class);
		return interfaceController.handleRequest(req, res);
	}
}