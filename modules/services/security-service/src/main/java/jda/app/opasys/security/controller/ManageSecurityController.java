package jda.app.opasys.security.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.security.modules.model.User;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;

@RestController
@RequestMapping(value = "/")
public class ManageSecurityController {
	public final static String PATH = "/user";

	@RequestMapping(value = PATH + "/**")
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<User, Integer> controller = ControllerRegistry.getInstance().get(User.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
