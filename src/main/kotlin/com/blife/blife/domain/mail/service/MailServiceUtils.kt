package com.blife.blife.domain.mail.service


class MailServiceUtils {
	companion object {
		fun certificationNum(): String {
			val charset = ('0' .. '9')
			val rangeRandom = List(6) { charset.random() }
				.joinToString { "" }

			return rangeRandom
		}
	}


}