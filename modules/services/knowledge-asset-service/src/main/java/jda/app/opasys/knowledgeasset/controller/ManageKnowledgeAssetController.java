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
import jda.app.opasys.knowledgeasset.modules.riskasset.model.RiskAsset;
import jda.modules.msacommon.controller.ControllerRegistry2;
import jda.modules.msacommon.controller.DefaultController2;

@RestController
@RequestMapping(value = "/")
public class ManageKnowledgeAssetController {
	
	
	public final static String PATH_CONF = "/config";
	public final static String PATH_FIN = "/finance";
	public final static String PATH_METRIC = "/metric";
	public final static String PATH_PLAN = "/plan";
	public final static String PATH_RISK = "/risk";

	@RequestMapping(value = PATH_CONF + "/**")
	public ResponseEntity<?> handleConfig(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController2<ConfAsset, Integer> controller = ControllerRegistry2.getInstance().get(ConfAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_FIN + "/**")
	public ResponseEntity<?> handleFinance(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController2<FinAsset, Integer> controller = ControllerRegistry2.getInstance().get(FinAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_METRIC + "/**")
	public ResponseEntity<?> handleMetric(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController2<MetricAsset, Integer> controller = ControllerRegistry2.getInstance().get(MetricAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_PLAN + "/**")
	public ResponseEntity<?> handlePlan(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController2<PlanAsset, Integer> controller = ControllerRegistry2.getInstance().get(PlanAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_RISK + "/**")
	public ResponseEntity<?> handleRisk(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController2<RiskAsset, Integer> controller = ControllerRegistry2.getInstance().get(RiskAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
