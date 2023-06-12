/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.deskprotaxidentifiersstore.controllers

import play.api.mvc.{Action, AnyContent, ControllerComponents, Request}
import uk.gov.hmrc.internalauth.client._
import uk.gov.hmrc.play.bootstrap.backend.controller.BackendController

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton()
class MicroserviceHelloWorldController @Inject() (auth: BackendAuthComponents, cc: ControllerComponents)
    extends BackendController(cc) {

  private val readPermission: Predicate.Permission = Predicate.Permission(
    Resource(ResourceType("deskpro-tax-identifiers-store"), ResourceLocation("*")),
    IAAction("READ")
  )

  def hello(): Action[AnyContent] =
    auth.authorizedAction(readPermission).async {
        Future.successful(Ok("Hello world"))
    }
}
