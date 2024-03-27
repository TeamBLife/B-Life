package com.blife.blife.global.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val code: Long, val httpStatus: HttpStatus, val message: String) {
    DOES_NOT_EXIST_EMAIL(1001, HttpStatus.NOT_FOUND, "존재 하지 않는 이메일입니다."), //auth 부분
    NUMBER_DOES_NOT_MATCH(1002, HttpStatus.NOT_FOUND, "인증 번호가 일지 하지 않습니다."),
    PLEASE_CONTACT_ADMINISTRATOR(1003, HttpStatus.UNAUTHORIZED, "관리자 문의가 필요합니다."),//auth 부분

    NO_DATE_EXIST(2001, HttpStatus.NOT_FOUND, "데이터가 존재하지 않습니다."),//book 부분 , review 부분에도 있음

    BOOK_NOT_FOUND(3001, HttpStatus.NOT_FOUND, "도서관에서 책을 찾을 수 없습니다."), //checkout 부분 review 부분에도 있음 wish 부분에도 있음
    MEMBER_NOT_FOUND(3002, HttpStatus.NOT_FOUND, "멤버를 찾을 수 없습니다."),  //member 부분에도 있음, review 부분에도 있음 wish 부분에도 있음
    ALL_BOOKS_ARE_ON_LOAN(3003, HttpStatus.NOT_FOUND, "모든 책이 대출 중입니다."),
    EXCEEDING_THE_MAXIMUM_NUMBER_OF_BOOKS(3004, HttpStatus.NOT_FOUND, "대여 가능한 책의 수를 초과하였습니다."),
    NOT_A_LIBRARY_BOOK(3005, HttpStatus.NOT_FOUND, "해당 도서관의 책이 아닙니다."),
    NO_PERMISSION(3006, HttpStatus.UNAUTHORIZED, "권한이 없습니다."),  //review 부분에소 있음
    LOAN_RECORDS_NOT_FOUND(3007, HttpStatus.NOT_FOUND, "대출 기록을 찾을 수 없습니다."), //checkout 부분

    SOCIAL_MEMBER_CANNOT_LOGIN(4001, HttpStatus.FORBIDDEN, "소셜 회원은 로그인할 수 없습니다."), //member 부분
    AUTHENTICATE_NOT_YET(4002, HttpStatus.FORBIDDEN, "아직 인증되지 않았습니다."),
    WRONG_PASSWORD(4003, HttpStatus.BAD_REQUEST, "틀린 비밀번호입니다."),
    ADMINISTRATORS_CANNOT_WITHDRAW(4004, HttpStatus.FORBIDDEN, "관리자는 탈퇴 할 수 없습니다."),
    WRONG_ROLE(4005, HttpStatus.BAD_REQUEST, "잘못된 역할입니다."), //member 부분
    EMAIL_ALREADY_REGISTERED(4006, HttpStatus.NOT_FOUND, "이미 가입되어 있는 Email입니다."),

    SCORE_BETWEEN_1_AND_10(5002, HttpStatus.BAD_REQUEST, "점수는 1~10점 사이여야 합니다."),
    REVIEW_NOT_FOUND(5003, HttpStatus.NOT_FOUND, "리뷰를 찾을 수 없습니다."),// review 부분
}