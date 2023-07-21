package jda.app.opasys.plan.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.plan.modules.planasset.model.PlanAsset;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;

@RestController
@RequestMapping(value = "/")
public class ManagePlanAssetController {
	
	public final static String PATH_PLAN = "/plan";

	@RequestMapping(value = PATH_PLAN + "/**")
	public ResponseEntity<?> handlePlan(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<PlanAsset, Integer> controller = ControllerRegistry.getInstance().get(PlanAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}
