package jda.app.opasys.knowledgeasset.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.knowledgeasset.modules.confasset.model.ConfAsset;
import jda.app.opasys.knowledgeasset.modules.finasset.model.FinAsset;
import jda.app.opasys.knowledgeasset.modules.metricasset.model.MetricAsset;
import jda.app.opasys.knowledgeasset.modules.planasset.model.PlanAsset;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;

@RestController
@RequestMapping(value = "/")
public class ManageKnowledgeAssetController {
	
	
	public final static String PATH_CONF = "/config";
	public final static String PATH_FIN = "/finance";
	public final static String PATH_METRIC = "/metric";
	public final static String PATH_PLAN = "/plan";

	@RequestMapping(value = PATH_CONF + "/**")
	public ResponseEntity<?> handleConfig(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<ConfAsset, Integer> controller = ControllerRegistry.getInstance().get(ConfAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_FIN + "/**")
	public ResponseEntity<?> handleFinance(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<FinAsset, Integer> controller = ControllerRegistry.getInstance().get(FinAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_METRIC + "/**")
	public ResponseEntity<?> handleMetric(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<MetricAsset, Integer> controller = ControllerRegistry.getInstance().get(MetricAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_PLAN + "/**")
	public ResponseEntity<?> handlePlan(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<PlanAsset, Integer> controller = ControllerRegistry.getInstance().get(PlanAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}
