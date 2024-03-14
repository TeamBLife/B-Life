package com.blife.blife.global.util.mail.service


class MailServiceUtils {
	companion object {
		fun certificationNum(): Long {
			val charset = ('0' .. '9')
			val rangeRandom = List(6) { charset.random() }
				.joinToString("")

			return rangeRandom.toLong()
		}
	}
}