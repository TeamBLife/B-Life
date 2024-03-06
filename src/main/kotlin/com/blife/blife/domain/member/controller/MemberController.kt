package com.blife.blife.domain.member.controller

import com.blife.blife.domain.member.dto.LoginResponse
import com.blife.blife.domain.member.dto.MemberLoginRequest
import com.blife.blife.domain.member.dto.MemberRespose
import com.blife.blife.domain.member.dto.MemberSignupRequest
import com.blife.blife.domain.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController (
    private val memberService: MemberService
){
    @PostMapping("/signup")
    fun signup(@RequestBody signupRequest: MemberSignupRequest): ResponseEntity<MemberRespose>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.signup(signupRequest))
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: MemberLoginRequest): ResponseEntity<LoginResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.login(loginRequest))
    }
}