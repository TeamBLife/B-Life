package com.blife.blife.domain.member.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController (

){
    @PostMapping("/signup")
    fun signup(){
        TODO()
    }
}