package org.ardlema.mailer

import javax.mail.{Session, _}
import javax.mail.internet._

object MenuMailer {

  val sender = "****@gmail.com"
  val recipientAlberto = "***@gmail.com"
  //val recipientRebeca = "***@gmail.com"
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

  // Set From: header field of the header.
  message.setFrom(new InternetAddress(sender))

  // Set To: header field of the header.
  message.addRecipient(Message.RecipientType.TO,
    new InternetAddress(recipientAlberto))
/*  message.addRecipient(Message.RecipientType.TO,
    new InternetAddress(recipientRebeca))*/


  // Set Subject: header field
  message.setSubject("Menu Planner - Este es su menu para esta semana")

  // Now set the actual message
  message.setText("Probando")

  // Send message
  def sendMessage() {
    val tr = session.getTransport("smtp")
    tr.connect(sender, "****")
    tr.sendMessage(message, message.getAllRecipients())
  }
}
