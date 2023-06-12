package jda.app.opasys.project.modules.issue;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jda.app.opasys.project.controller.ManageProjectController;
import jda.app.opasys.project.modules.issue.model.Comment;
import jda.app.opasys.project.modules.issue.model.Issue;
import jda.modules.msacommon.controller.ControllerRegistry2;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController2;

@Controller
public class IssueController extends DefaultController2<Issue, Integer> {

	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		//project/{10}/issue/{id}
		if (ControllerTk.isPathContainModule(ManageProjectController.PATH_ISSUE, path)) {
			return super.handleRequest(req, res, ids.size()==2 ? ids.get(1):null);
			
		//project/{10}/issue/{issue_id}/comment/{comment_id}
		}else if (ControllerTk.isPathContainModule(ManageProjectController.PATH_ISSUE_COMMENT, path)) {
			DefaultController2<Comment, Integer> childController = ControllerRegistry2.getInstance().get(Comment.class);
			return childController.handleRequest(req, res, ids.size()==3 ? ids.get(2):null);
		}else{
			return ResponseEntity.badRequest().build();
		}
	}
}
