package com.kitm.library.backend.admin;

import com.kitm.library.backend.admin.common.models.Layout;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 19.02.22
 */
@Controller
public class HomeController {

  @GetMapping("/admin")
  public String home(Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Home));

    return "home";
  }
}
