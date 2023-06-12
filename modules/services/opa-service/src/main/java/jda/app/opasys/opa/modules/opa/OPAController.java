package jda.app.opasys.opa.modules.opa;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jda.app.opasys.common.model.OPA;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.InterfaceController;

@Controller
public class OPAController extends InterfaceController<Integer, OPA> {
	private static final Logger logger = LoggerFactory.getLogger(InterfaceController.class);
	

	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String targetPath = ControllerTk.getServiceUri("", req.getServletPath().replace("redirect/", ""));
		String requestData;
		try {
			requestData = req.getReader().lines().collect(Collectors.joining()).trim();
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return super.forwardRequest(targetPath, HttpMethod.resolve(req.getMethod()), requestData);

	}

}
