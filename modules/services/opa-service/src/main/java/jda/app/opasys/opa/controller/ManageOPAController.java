package jda.app.opasys.opa.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.opa.modules.knowledgeasset.model.KnowledgeAsset;
import jda.app.opasys.opa.modules.orgasset.model.OrgAsset;
import jda.app.opasys.opa.modules.project.model.Project;
import jda.modules.msacommon.controller.ControllerRegistry2;
import jda.modules.msacommon.controller.DefaultController2;

@RestController
@RequestMapping(value = "/")
public class ManageOPAController {
	
	
	public final static String PATH_KNOWLEDGE = "/knowledge_asset";
	public final static String PATH_ORG = "/org_asset";
	public final static String PATH_PROJECT = "/project";
	public final static String PATH_ISSUE = "/issue_asset";

	@RequestMapping(value = PATH_KNOWLEDGE + "/**")
	public ResponseEntity<?> handleKnowledgeAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController2<KnowledgeAsset, Integer> controller = ControllerRegistry2.getInstance().get(KnowledgeAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_ORG + "/**")
	public ResponseEntity<?> handleOrgAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController2<OrgAsset, Integer> controller = ControllerRegistry2.getInstance().get(OrgAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_PROJECT + "/**")
	public ResponseEntity<?> handleProject(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController2<OrgAsset, Integer> controller = ControllerRegistry2.getInstance().get(Project.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}