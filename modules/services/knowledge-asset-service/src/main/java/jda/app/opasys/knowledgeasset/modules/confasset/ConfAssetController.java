package jda.app.opasys.knowledgeasset.modules.confasset;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jda.app.opasys.knowledgeasset.controller.ManageKnowledgeAssetController;
import jda.app.opasys.knowledgeasset.modules.confasset.model.ConfAsset;
import jda.app.opasys.knowledgeasset.modules.knowledgeasset.model.KnowledgeAsset;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;

@Controller
public class ConfAssetController extends DefaultController<ConfAsset, Integer>{

	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		DefaultController<KnowledgeAsset, Integer> knowledgeController = ControllerRegistry.getInstance().get(KnowledgeAsset.class);
//		ResponseEntity<?> resp = knowledgeController != null ? knowledgeController.handleRequest(req, res)
//				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		return ControllerTk.isPathContainId(ManageKnowledgeAssetController.PATH_CONF, path)
				? super.handleRequest(req, res, ids.isEmpty() ? null : ids.get(0))
				: ResponseEntity.badRequest().build();
	}
}
