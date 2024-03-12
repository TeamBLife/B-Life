package com.blife.blife.event

import com.blife.blife.domain.book.model.LibBook
import com.blife.blife.domain.wishlist.model.WishList
import org.springframework.context.ApplicationEvent


class LoanAvailableEvent (val libBook: LibBook) : ApplicationEvent(libBook)