package com.kitm.library.backend.admin;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 19.02.22
 */
@Controller
public class ErrorsController implements ErrorController {

  @GetMapping("/404")
  public String handle404() {
    return "errors/404";
  }

  @GetMapping("/403")
  public String handle403() {
    return "errors/403";
  }

  @GetMapping("/500")
  public String handle500() {
    return "errors/500";
  }

  @RequestMapping("/error")
  public String handleError(HttpServletRequest request, Model model) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    model.addAttribute("status", status);
    model.addAttribute("error", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));

    if (status != null) {
      int statusCode = Integer.valueOf(status.toString());

      if (statusCode == HttpStatus.NOT_FOUND.value()) {
        return "errors/404";
      } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
        return "errors/500";
      }
    }

    return "errors/error";
  }
}
