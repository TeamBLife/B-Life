package com.blife.blife.infra.postgresql.library

import com.blife.blife.infra.postgresql.library.entity.LibBookEntity
import org.springframework.data.repository.CrudRepository

interface JpaLibBookRepository : CrudRepository<LibBookEntity, Long>