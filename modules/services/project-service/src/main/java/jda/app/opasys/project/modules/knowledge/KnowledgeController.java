package jda.app.opasys.project.modules.knowledge;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import jda.app.opasys.project.modules.knowledge.model.Knowledge;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;

@Controller
public class KnowledgeController extends DefaultController<Knowledge, Integer>{
	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		return  super.handleRequest(req, res, ids.isEmpty() ? null : ids.get(0));
	}
}
