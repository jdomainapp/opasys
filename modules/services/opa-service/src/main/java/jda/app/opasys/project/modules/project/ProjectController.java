package jda.app.opasys.project.modules.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jda.app.opasys.project.controller.ManageOPAController;
import jda.app.opasys.project.modules.project.model.Project;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;

@Controller
public class ProjectController extends DefaultController<Project, Integer> {

	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		return ControllerTk.isPathContainId(ManageOPAController.PATH_PROJECT, path)
				? super.handleRequest(req, res, ids.isEmpty() ? null : ids.get(0))
				: ResponseEntity.badRequest().build();
	}

}
