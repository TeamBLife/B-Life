package com.blife.blife.domain.mail.controller

import com.blife.blife.domain.mail.dto.CertificationCheckRequest
import com.blife.blife.domain.mail.dto.CertificationRequest
import com.blife.blife.domain.mail.service.MemberMailService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/user-certification")
class MemberMailController(
	private val memberMailService: MemberMailService
) {
    @PostMapping("")
    fun memberCertification(@RequestBody request: CertificationRequest): ResponseEntity<String>{
        memberMailService.memberSendMail(request.email)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Success")
    }


//    @PostMapping("/check")
//    fun memberCheckCertification(@RequestBody request: CertificationCheckRequest): ResponseEntity<String>{
//        memberMailService.checkCertification(request.certificationNumber)
//        return ResponseEntity
//            .status(HttpStatus.OK)
//            .body("Success")
//    }

}