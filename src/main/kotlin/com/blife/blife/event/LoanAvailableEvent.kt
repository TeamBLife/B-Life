package com.blife.blife.event

import com.blife.blife.domain.book.model.LibBook
import com.blife.blife.domain.wishlist.model.WishList


class LoanAvailableEvent (val libBook: LibBook, val wishList: WishList)