package jda.app.opasys.hr.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

import jda.app.opasys.hr.controller.ManageHRController;
import jda.app.opasys.hr.kafka.source.SimpleSourceBean;
import jda.app.opasys.hr.modules.model.User;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;
import jda.modules.msacommon.messaging.kafka.KafkaChangeAction;

@Controller
public class UserController extends DefaultController<User, Integer> {

	@Autowired
	SimpleSourceBean sourceBean;

	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		ResponseEntity<?> responseEntity = handleRequest(req, res, ids.isEmpty() ? null : ids.get(0));
		String requestMethod = req.getMethod();
		String kafkaPath = ControllerTk.getServiceUri(ManageHRController.SERVICE_NAME,
				ManageHRController.PATH + "/id/{id}");
		if (requestMethod.equals(RequestMethod.POST.toString())) {
			User responseUser = (User) responseEntity.getBody();
			sourceBean.publishChange(User.class.getTypeName(), KafkaChangeAction.CREATED, responseUser.getId(),
					kafkaPath);
		} else if (requestMethod.equals(RequestMethod.PUT.toString())) {
			User responseUser = (User) responseEntity.getBody();
			sourceBean.publishChange(User.class.getTypeName(), KafkaChangeAction.UPDATED, responseUser.getId(),
					kafkaPath);
		} else if (requestMethod.equals(RequestMethod.DELETE.toString())) {
			sourceBean.publishChange(User.class.getTypeName(), KafkaChangeAction.DELETED,
					Integer.parseInt((String) responseEntity.getBody()), kafkaPath);
		}
		return responseEntity;
	}

}
