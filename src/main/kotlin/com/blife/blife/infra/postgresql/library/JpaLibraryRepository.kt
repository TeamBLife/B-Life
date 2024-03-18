package com.blife.blife.infra.postgresql.library

import com.blife.blife.domain.member.model.Member
import com.blife.blife.infra.postgresql.library.entity.LibraryEntity
import org.springframework.data.repository.CrudRepository

interface JpaLibraryRepository : CrudRepository<LibraryEntity, Long>{
    fun findByMember(member: Member): LibraryEntity  // 라이브러리의 엔티티에 해당 member가 있는지 없는지 확인하는 함수
}