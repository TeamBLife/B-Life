package com.blife.blife.domain.book.repository

import com.blife.blife.domain.library.model.Library
import org.springframework.data.jpa.repository.JpaRepository

interface LibraryRepository : JpaRepository <Library,Long> {
}