package org.ardlema.mailer

import javax.mail.{Session, _}
import javax.mail.internet._

import com.typesafe.scalalogging.LazyLogging
import org.ardlema.parser.CommandLineParams

object MenuMailer extends LazyLogging {

  /*val sender = "ardlema@gmail.com"
  val recipientAlberto = "ardlema@gmail.com"
  val recipientRebeca = "rbkmena@gmail.com"*/
  val host = "smtp.gmail.com"
  val port = "587"

  val properties = System.getProperties()
  properties.setProperty("mail.smtp.host", host)
  properties.setProperty("mail.smtp.port", port)
  properties.setProperty("mail.smtp.starttls.enable", "true")
  properties.setProperty("mail.smtp.auth", "true")

  val session = Session.getInstance(properties)

  // Create a default MimeMessage object.
  val message = new MimeMessage(session)

  // Set Subject: header field
  message.setSubject("Menu Planner - Este es su menu para esta semana")

  // Now set the actual message


  // Send message
  def sendMessage(body: String, parameters: CommandLineParams) {
    val tr = session.getTransport("smtp")
    message.setContent(body, "text/html")
    message.setFrom(new InternetAddress(parameters.sender))
    tr.connect(parameters.sender, parameters.password)
    for (recipient <- parameters.recipients) message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient))
    tr.sendMessage(message, message.getAllRecipients())
  }
}
