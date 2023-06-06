package jda.app.opasys.knowledgeasset.modules.metricasset;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jda.app.opasys.knowledgeasset.controller.ManageKnowledgeAssetController;
import jda.app.opasys.knowledgeasset.modules.metricasset.model.MetricAsset;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController2;

@Controller
public class MetricAssetController extends DefaultController2<MetricAsset, Integer>{

	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		return ControllerTk.isPathContainModule(ManageKnowledgeAssetController.PATH_METRIC, path)
				? super.handleRequest(req, res, ids.isEmpty() ? null : ids.get(0))
				: ResponseEntity.badRequest().build();
	}
}
