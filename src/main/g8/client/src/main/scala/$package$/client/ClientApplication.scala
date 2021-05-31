package $package$.client

import $package$.shared.SharedMessages
import org.scalajs.dom

@main def ClientApplication(args: String*): Unit =
  dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks
