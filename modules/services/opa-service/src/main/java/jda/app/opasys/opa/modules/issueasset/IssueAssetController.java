package jda.app.opasys.opa.modules.issueasset;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jda.app.opasys.opa.controller.ManageOPAController;
import jda.app.opasys.opa.modules.issueasset.model.CommentAsset;
import jda.app.opasys.opa.modules.issueasset.model.IssueAsset;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;

@Controller
public class IssueAssetController extends DefaultController<IssueAsset, Integer> {

	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		//project/{10}/issue/{id}
		if (ControllerTk.isPathContainModule(ManageOPAController.PATH_ISSUE, path)) {
			return super.handleRequest(req, res, ids.size()==1 ? ids.get(0):null);
			
		//project/{10}/issue/{issue_id}/comment/{comment_id}
		}else if (ControllerTk.isPathContainModule(ManageOPAController.PATH_ISSUE_COMMENT, path)) {
			DefaultController<CommentAsset, Integer> childController = ControllerRegistry.getInstance().get(CommentAsset.class);
			return childController.handleRequest(req, res, ids.size()==2 ? ids.get(1):null);
		}else{
			return ResponseEntity.badRequest().build();
		}
	}
}
