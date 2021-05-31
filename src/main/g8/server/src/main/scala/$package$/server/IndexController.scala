package $package$.server

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{ RequestMapping, RequestMethod }
import org.springframework.web.servlet.ModelAndView

import $package$.shared.SharedMessages

@Controller
class IndexController:
  @RequestMapping(path = Array("/"), method = Array(RequestMethod.GET))
  def sample(): ModelAndView =
    new ModelAndView("index").addObject("message", SharedMessages.itWorks)
