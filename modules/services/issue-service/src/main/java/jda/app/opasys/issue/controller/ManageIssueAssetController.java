package jda.app.opasys.issue.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.issue.modules.commentasset.model.CommentAsset;
import jda.app.opasys.issue.modules.issueasset.model.IssueAsset;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;

@RestController
@RequestMapping(value = "/")
public class ManageIssueAssetController {
	public final static String PATH_ISSUE = "/issue";
	public final static String PATH_COMMENT = "/comment";

	@RequestMapping(value = PATH_ISSUE + "/**")
	public ResponseEntity<?> handleIssueRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<IssueAsset, Integer> controller = ControllerRegistry.getInstance().get(IssueAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_COMMENT + "/**")
	public ResponseEntity<?> handleCommentRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<CommentAsset, Integer> controller = ControllerRegistry.getInstance().get(CommentAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
}