package jda.app.opasys.project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.project.modules.activity.model.Activity;
import jda.app.opasys.project.modules.project.model.Project;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;

@RestController
@RequestMapping(value = "/")
public class ManageProjectController {
	
	
	public final static String PATH_PROJECT = "/project";
	public final static String PATH_ACTIVITY = "/activity";

	@RequestMapping(value = PATH_PROJECT + "/**")
	public ResponseEntity handleProject(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<Project, Integer> controller = ControllerRegistry.getInstance().get(Project.class);
		return ResponseEntity.ok(controller.handleRequest(req, res));
	}
}