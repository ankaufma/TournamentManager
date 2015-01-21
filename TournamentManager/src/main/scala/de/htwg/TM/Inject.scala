package de.htwg.TM

import com.escalatesoft.subcut.inject.{Injectable, BindingModule}
import de.htwg.controller.{ControllerTrait, DummyController, RealController}

/**
 * Created by ankaufma on 21.01.2015.
 */
class Inject(implicit val bindingModule: BindingModule) extends Injectable {
  val controller = injectOptional [ControllerTrait] getOrElse { new DummyController }
}
