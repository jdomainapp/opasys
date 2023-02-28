package jda.app.opasys.project.modules.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jda.app.opasys.project.modules.activity.model.Activity;
import jda.app.opasys.project.modules.project.model.Project;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;
import jda.modules.msacommon.model.MyResponseEntity;

public class ProjectController extends DefaultController<Project, Integer> {

	public final static String PATH_PROJECT = "/project";
	public final static String PATH_ACTIVITY = "/activity";

	@Override
	public MyResponseEntity handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		//manageproject/project/{10}
		//CRUD project (click link to get activites of a project)
		if (path.matches("(.*)"+PATH_PROJECT+"(\\/\\d+)")) {
			return super.handleRequest(req, res, ids.isEmpty()? null : ids.get(0));
			
		//manageproject/project/{10}/activity/{12}
		//manageproject/project/{10}/activity	
		// CRUD activies of a project
		// input: projectId, output: List<Activity>
		// Can activityController.getActivityByProjectId
		} else if (path.matches("(.*)"+PATH_ACTIVITY+"(\\/\\d+)")) {
			DefaultController<Activity, Integer> childController = ControllerRegistry.getInstance().get(Activity.class);
			return childController.handleRequest(req, res, ids.size()==2 ? null : ids.get(1)).getResponseEntity();
		}
		
	}

}
