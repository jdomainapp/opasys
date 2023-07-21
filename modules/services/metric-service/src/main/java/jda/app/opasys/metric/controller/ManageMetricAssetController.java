package jda.app.opasys.metric.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.metric.modules.metricasset.model.MetricAsset;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;

@RestController
@RequestMapping(value = "/")
public class ManageMetricAssetController {
	
	public final static String PATH_METRIC = "/metric";

	@RequestMapping(value = PATH_METRIC + "/**")
	public ResponseEntity<?> handlePlan(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<MetricAsset, Integer> controller = ControllerRegistry.getInstance().get(MetricAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}
