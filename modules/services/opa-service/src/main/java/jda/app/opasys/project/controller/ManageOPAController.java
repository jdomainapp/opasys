package jda.app.opasys.project.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.project.modules.knowledgeasset.model.KnowledgeAsset;
import jda.app.opasys.project.modules.orgasset.model.OrgAsset;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;

@RestController
@RequestMapping(value = "/")
public class ManageOPAController {
	
	
	public final static String PATH_KNOWLEDGE = "/opa/knowledge_asset";
	public final static String PATH_ORG = "/opa/org_asset";

	@RequestMapping(value = PATH_KNOWLEDGE + "/**")
	public ResponseEntity<?> handleKnowledgeAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<KnowledgeAsset, Integer> controller = ControllerRegistry.getInstance().get(KnowledgeAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_ORG + "/**")
	public ResponseEntity<?> handleOrgAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<OrgAsset, Integer> controller = ControllerRegistry.getInstance().get(OrgAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}