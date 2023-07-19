package jda.app.opasys.project.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.common.model.KnowledgeAsset;
import jda.app.opasys.common.model.OPA;
import jda.app.opasys.project.modules.activity.model.Activity;
import jda.app.opasys.project.modules.asset.model.LocalKnowledgeAsset;
import jda.app.opasys.project.modules.issuecomment.model.Comment;
import jda.app.opasys.project.modules.knowledge.model.Knowledge;
import jda.app.opasys.project.modules.project.model.Project;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;
import jda.modules.msacommon.controller.InterfaceController;
import jda.modules.msacommon.controller.InterfaceControllerRegistry;

@RestController
@RequestMapping(value = "/")
public class ManageProjectController {
	
	public final static String PATH_PROJECT = "/project";
	public final static String PATH_ACTIVITY = "/activity";
	public final static String PATH_KNOWLEDGE = "/knowledge";
	public final static String PATH_ISSUE_COMMENT = "/comment";
	
	public final static String PATH_LOCAL_OPA = "/local_opa";
	public final static String PATH_CREATE_LOCAL_OPA = "/local_opa/oncomplete";
	public final static String PATH_OPA = "/opa";
	public final static String PATH_CREATE_OPA = "/opa/oncomplete";
	
	@Value("${filestorage.path}")
	private String fileStoragePath;

	@RequestMapping(value = PATH_PROJECT + "/**")
	public ResponseEntity<?> handleProjectRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<Project, Integer> controller = ControllerRegistry.getInstance().get(Project.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_ACTIVITY + "/**")
	public ResponseEntity<?> handleActivityRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<Activity, Integer> controller = ControllerRegistry.getInstance().get(Activity.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_KNOWLEDGE + "/**")
	public ResponseEntity<?> handleKnowledgeRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<Knowledge, Integer> controller = ControllerRegistry.getInstance().get(Knowledge.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_ISSUE_COMMENT + "/**")
	public ResponseEntity<?> handleCommentRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<Comment, Integer> controller = ControllerRegistry.getInstance().get(Comment.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_LOCAL_OPA + "/**")
	public ResponseEntity<?> handleRedirectRequestForLocalOpa(HttpServletRequest req, HttpServletResponse res) throws IOException {
		InterfaceController<LocalKnowledgeAsset, Integer> interfaceController = InterfaceControllerRegistry.getInstance().get(LocalKnowledgeAsset.class);
		return interfaceController.handleRequest(req, res);
	}
	
	@RequestMapping(value = PATH_OPA + "/**")
	public ResponseEntity<?> handleRedirectRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		InterfaceController<KnowledgeAsset, Integer> interfaceController = InterfaceControllerRegistry.getInstance().get(KnowledgeAsset.class);
		return interfaceController.handleRequest(req, res);
	}
}