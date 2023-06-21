package jda.app.opasys.issue.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.issue.modules.comment.model.Comment;
import jda.app.opasys.issue.modules.issueasset.model.IssueAsset;
import jda.modules.msacommon.controller.ControllerRegistry;
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
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		//issue/{10}
		if (ControllerTk.isPathContainModule(ManageIssueAssetController.PATH_ISSUE, path)) {
			DefaultController<IssueAsset, Integer> childController = ControllerRegistry.getInstance().get(IssueAsset.class);
			return childController.handleRequest(req, res, ids.isEmpty()? null : ids.get(0));
			
		//issue/{issue_id}/comment/{comment_id}
		}else if (ControllerTk.isPathContainModule(ManageIssueAssetController.PATH_COMMENT, path)) {
			DefaultController<Comment, Integer> childController = ControllerRegistry.getInstance().get(Comment.class);
			return childController.handleRequest(req, res, ids.size()==2 ? ids.get(1):null);
		}else{
			return ResponseEntity.badRequest().build();
		}
	}
}