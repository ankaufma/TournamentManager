package de.htwg.TM

import com.escalatesoft.subcut.inject._
import de.htwg.controller.{DummyController, ControllerTrait, RealController}

/**
 * Created by ankaufma on 21.01.2015.
 */
object SubCutConfig extends NewBindingModule(module => {
  import module._   // can now use bind directl

  bind [ControllerTrait] to newInstanceOf [RealController]
})
