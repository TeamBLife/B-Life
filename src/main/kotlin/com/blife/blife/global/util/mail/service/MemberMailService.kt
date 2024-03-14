package com.blife.blife.global.util.mail.service

//import com.blife.blife.domain.mail.RedisUtils
import org.springframework.stereotype.Service

@Service
class MemberMailService(
//    private val redisUtils: RedisUtils,
	private val mailService: MailService
) {

	fun memberSendMail(email: String): Long {
		val certificationNumber = MailServiceUtils.certificationNum()
		val subject = "[B-life] 본인 인증 메일"
		val text = getText(certificationNumber.toString())
//        redisUtils.setDataExpire(certificationNumber, email)
		mailService.sendMail(email, subject, text, true)
		return certificationNumber
	}


//    fun checkCertification(certificationNumber: String) {
//        redisUtils.getData(certificationNumber)
//            ?: throw MailSendException("인증번호가 잘못되었거나 인증 시간이 초과되었습니다. 다시 확인해주세요.")
//        redisUtils.deleteData(certificationNumber)
//    }


	private fun getText(certificationNum: String): String {
		return certificationNum

	}
}