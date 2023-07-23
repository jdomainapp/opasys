package jda.app.opasys.project.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.project.modules.activity.model.Activity;
import jda.app.opasys.project.modules.issuecomment.model.Comment;
import jda.app.opasys.project.modules.knowledgeelement.model.KnowledgeElement;
import jda.app.opasys.project.modules.opainterface.modelasset.OPAInterface;
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
	
	public final static String PATH_CREATE_OPA = "/opa/oncomplete";
	public final static String PATH_GET_LOCAL_OPA = "/opa/local";
	public final static String PATH_GET_SUBTYPE_OPA = "/opa/subtype";
	
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
		DefaultController<KnowledgeElement, Integer> controller = ControllerRegistry.getInstance().get(KnowledgeElement.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_ISSUE_COMMENT + "/**")
	public ResponseEntity<?> handleCommentRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<Comment, Integer> controller = ControllerRegistry.getInstance().get(Comment.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@RequestMapping(value = PATH_CREATE_OPA + "/**")
	public ResponseEntity<?> handleCreateOPARequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		InterfaceController<OPAInterface, Integer> interfaceController = InterfaceControllerRegistry.getInstance().get(OPAInterface.class);
		return new SendToOPA().processCreateOPA(interfaceController, req.getServletPath());
	}
	
	@RequestMapping(value = PATH_GET_LOCAL_OPA + "/**")
	public ResponseEntity<?> handleGetLocalOPARequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		InterfaceController<OPAInterface, Integer> interfaceController = InterfaceControllerRegistry.getInstance().get(OPAInterface.class);
		return new SendToOPA().processGetLocalOPA(interfaceController, req);
	}
	
	@RequestMapping(value = PATH_GET_SUBTYPE_OPA + "/**")
	public ResponseEntity<?> handleGetSubtypeOPARequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		InterfaceController<OPAInterface, Integer> interfaceController = InterfaceControllerRegistry.getInstance().get(OPAInterface.class);
		return new SendToOPA().processGetSubtypeOPA(interfaceController, req);
	}	

		
	
	
}