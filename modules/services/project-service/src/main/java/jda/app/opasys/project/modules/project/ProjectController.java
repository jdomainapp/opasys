package jda.app.opasys.project.modules.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jda.app.opasys.project.modules.activity.model.Activity;
import jda.app.opasys.project.modules.project.model.Project;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;

@Controller
public class ProjectController extends DefaultController<Project, Integer> {
//	@Column(name = "type_id")
//	private int typeId;
	public final static String PATH_PROJECT = "/project";
	public final static String PATH_ACTIVITY = "/activity";

	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		//manageproject/project/{10}: CRUD project (click link to get activites of a project)
		//project/2/acitivity: get list of activities in a project
		if (path.matches("(.*)"+PATH_PROJECT+"(\\/\\d+)*$")) {
			return super.handleRequest(req, res, ids.isEmpty()? null : ids.get(0));
			
		//manageproject/project/{10}/activity/{12}: CRUD a activity
		//TODO: getActivityList by projectId
		//TODO: File upload????
		} else if (path.matches("(.*)"+PATH_ACTIVITY+"(\\/\\d+)*$")) {
			DefaultController<Activity, Integer> childController = ControllerRegistry.getInstance().get(Activity.class);
			return childController.handleRequest(req, res, ids.size()==2 ? ids.get(1):null);
		}else {
			// invalid path
			return ResponseEntity.badRequest().build();
		}
	}

}
