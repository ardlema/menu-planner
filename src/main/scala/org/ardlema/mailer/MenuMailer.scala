package org.ardlema.mailer

import javax.mail.internet._
import javax.mail.{Session, _}

import com.typesafe.scalalogging.LazyLogging
import org.ardlema.parser.CommandLineParams

object MenuMailer extends LazyLogging {

  val host = "smtp.gmail.com"
  val port = "587"
  val properties = System.getProperties()
  properties.setProperty("mail.smtp.host", host)
  properties.setProperty("mail.smtp.port", port)
  properties.setProperty("mail.smtp.starttls.enable", "true")
  properties.setProperty("mail.smtp.auth", "true")
  val session = Session.getInstance(properties)
  val message = new MimeMessage(session)
  message.setSubject("Menu Planner - Este es su menu para esta semana")

  def sendMessage(body: String, parameters: CommandLineParams) {
    val tr = session.getTransport("smtp")
    message.setContent(body, "text/html")
    message.setFrom(new InternetAddress(parameters.sender))
    tr.connect(parameters.sender, parameters.password)
    for (recipient <- parameters.recipients) message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient))
    logger.info("Sending emails to the following recipients: "+parameters.recipients.toString)
    tr.sendMessage(message, message.getAllRecipients())
  }
}
