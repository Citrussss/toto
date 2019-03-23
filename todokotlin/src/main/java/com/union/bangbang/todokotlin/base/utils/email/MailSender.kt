package com.union.bangbang.todokotlin.base.utils.email

import java.io.File
import java.util.*
import javax.activation.DataHandler
import javax.activation.FileDataSource
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart


/**
 * @name bysj
 * @anthor bangbang QQ:740090077
 * @time 2019/3/22 8:56 PM
 * 只有编译器可能不骗你。
 */
class MailSender(
        val auth: Boolean = true,
        val debuggable: Boolean = true,
        val host: String = "smtp.qq.com",
        val port: String = "465",
        val sport: String = "465",
        val from: String = "lonely292837678@qq.com",
        val to: List<String> = Arrays.asList("lonely292837678@qq.com"),
        val password: String = "XXX") : Authenticator() {

    fun send(title: String, file: File, delete: Boolean = true) {
        val props = setProperties()
        val session = Session.getInstance(props, this)
        session.debug = debuggable
        val ts = session.transport
        ts.connect(host, from, password)
        val msg = createSimpleMail(session)
        msg.subject = title

        val text = MimeBodyPart()
        text.setContent(file.readLines().toString(), "text/html;charset=UTF-8");
//        msg.setContent(file.readLines(), "text/html;charset=UTF-8");

        val attach = MimeBodyPart()
        val dh = DataHandler(FileDataSource(file))
        attach.dataHandler = dh
        attach.fileName = dh.name

        val mp = MimeMultipart();
        mp.addBodyPart(text)
        mp.addBodyPart(attach)

        msg.setContent(mp)
        msg.saveChanges()

        ts.sendMessage(msg, msg.allRecipients)
        ts.close()
        if (delete) {
            file.delete()
        }
    }

    fun send(title: String, content: String) {
        val props = setProperties()
        val session = Session.getInstance(props, this)
        session.debug = debuggable
        val ts = session.transport
        ts.connect(host, from, password)
        val msg = createSimpleMail(session)
        msg.setContent(content, "text/html;charset=UTF-8");
        msg.subject = title
        ts.sendMessage(msg, msg.allRecipients)
        ts.close()
    }

    private fun setProperties(): Properties {
        val props = Properties();
        props["mail.smtp.host"] = host
        if (debuggable) {
            props["mail.debug"] = "true"
        }
        if (auth) {
            props["mail.smtp.auth"] = "true"
        }
        props["mail.smtp.port"] = port;
        props["mail.smtp.socketFactory.port"] = sport;
        props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory";
        props["mail.smtp.socketFactory.fallback"] = "false";
        return props
    }

    private fun createSimpleMail(session: Session): MimeMessage {
        //创建邮件对象
        val message = MimeMessage(session)
        //指明邮件的发件人
        message.setFrom(InternetAddress(from))
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipients(Message.RecipientType.TO, to.map { InternetAddress(it) }.toTypedArray())
        //邮件的标题
//        message.subject = BuildConfig.APPLICATION_ID

        /*//邮件的文本内容
        message.setContent("你好啊！", "text/html;charset=UTF-8")*/
        //返回创建好的邮件对象
        return message
    }
}