package jda.app.opasys.knowledgeasset.modules.confasset;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jda.app.opasys.knowledgeasset.modules.confasset.model.ConfAsset;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;

@Controller
public class ConfAssetController extends DefaultController<ConfAsset, Integer>{
	public final static String PATH = "/conf";
	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		if (path.matches("(.*)"+PATH+"(\\/\\d+)*$")) {
			return super.handleRequest(req, res, ids.isEmpty()? null : ids.get(0));
		}else {
			// invalid path
			return ResponseEntity.badRequest().build();
		}
	}
}
