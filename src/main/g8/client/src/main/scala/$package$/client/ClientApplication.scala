package $package$.client

import $package$.shared.SharedMessages
import org.scalajs.dom

object ClientApplication extends App {
  dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks
}
