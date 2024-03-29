package com.blife.blife.domain.library.enums

enum class REGION(val code: Long, val cityName: String, val districtName: String) {

	// NOTE: 서울특별시
	SEOUL_JONGRO(11010, "서울특별시", "종로구"),
	SEOUL_JUNG(11020, "서울특별시", "중구"),
	SEOUL_YONGSAN(11030, "서울특별시", "용산구"),
	SEOUL_SEONGDONG(11040, "서울특별시", "성동구"),
	SEOUL_GWANGJIN(11050, "서울특별시", "광진구"),
	SEOUL_DONGDAEMUN(11060, "서울특별시", "동대문구"),
	SEOUL_JUNGNANG(11070, "서울특별시", "중랑구"),
	SEOUL_SEONGBUK(11080, "서울특별시", "성북구"),
	SEOUL_GANGBUK(11090, "서울특별시", "강북구"),
	SEOUL_DOBONG(11100, "서울특별시", "도봉구"),
	SEOUL_NOWON(11110, "서울특별시", "노원구"),
	SEOUL_EUNPYEONG(11120, "서울특별시", "은평구"),
	SEOUL_SEODAEMUN(11130, "서울특별시", "서대문구"),
	SEOUL_MAPO(11140, "서울특별시", "마포구"),
	SEOUL_YANGCHEON(11150, "서울특별시", "양천구"),
	SEOUL_GANGSEO(11160, "서울특별시", "강서구"),
	SEOUL_GURO(11170, "서울특별시", "구로구"),
	SEOUL_GEUMCHEON(11180, "서울특별시", "금천구"),
	SEOUL_YEONGDEUNGPO(11190, "서울특별시", "영등포구"),
	SEOUL_DONGJAK(11200, "서울특별시", "동작구"),
	SEOUL_GWANAK(11210, "서울특별시", "관악구"),
	SEOUL_SEOCHO(11220, "서울특별시", "서초구"),
	SEOUL_GANGNAM(11230, "서울특별시", "강남구"),
	SEOUL_SONGPA(11240, "서울특별시", "송파구"),
	SEOUL_GANGDONG(11250, "서울특별시", "강동구"),

	// NOTE: 부산광역시
	BUSAN_JUNG(21010, "부산광역시", "중구"),
	BUSAN_SEO(21020, "부산광역시", "서구"),
	BUSAN_DONG(21030, "부산광역시", "동구"),
	BUSAN_YEONGDO(21040, "부산광역시", "영도구"),
	BUSAN_JIN(21050, "부산광역시", "부산진구"),
	BUSAN_DONGLAE(21060, "부산광역시", "동래구"),
	BUSAN_NAM(21070, "부산광역시", "남구"),
	BUSAN_BUK(21080, "부산광역시", "북구"),
	BUSAN_HAEUNDAE(21090, "부산광역시", "해운대구"),
	BUSAN_SAHAGU(21100, "부산광역시", "사하구"),
	BUSAN_GEUMJEONG(21110, "부산광역시", "금정구"),
	BUSAN_GANGSEO(21120, "부산광역시", "강서구"),
	BUSAN_YEONJE(21130, "부산광역시", "연제구"),
	BUSAN_SUYEONG(21140, "부산광역시", "수영구"),
	BUSAN_SASANG(21150, "부산광역시", "사상구"),
	BUSAN_GIJANG(21310, "부산광역시", "기장군"),


	// NOTE: 대구광역시
	DAEGU_JUNG(22010, "대구광역시", "중구"),
	DAEGU_DONG(22020, "대구광역시", "동구"),
	DAEGU_SEO(22030, "대구광역시", "서구"),
	DAEGU_NAM(22040, "대구광역시", "남구"),
	DAEGU_BUK(22050, "대구광역시", "북구"),
	DAEGU_SU(22060, "대구광역시", "수성구"),
	DAEGU_DALSEO(22070, "대구광역시", "달서구"),
	DAEGU_DALSEONG(22310, "대구광역시", "달성군"),

	// NOTE: 인천광역시
	INCHEON_JUNG(23010, "인천광역시", "중구"),
	INCHEON_DONG(23020, "인천광역시", "동구"),
	INCHEON_NAM(23030, "인천광역시", "남구"),
	INCHEON_YEONSU(23040, "인천광역시", "연수구"),
	INCHEON_NAMDONG(23050, "인천광역시", "남동구"),
	INCHEON_BUPEONG(23060, "인천광역시", "부평구"),
	INCHEON_GYEGYANG(23070, "인천광역시", "계양구"),
	INCHEON_SEO(23080, "인천광역시", "서구"),
	INCHEON_GANGHWA(23310, "인천광역시", "강화군"),
	INCHEON_ONGJIN(23320, "인천광역시", "옹진군"),

	// NOTE: 광주광역시
	GWANGJU_DONG(24010, "광주광역시", "동구"),
	GWANGJU_SEO(24020, "광주광역시", "서구"),
	GWANGJU_NAM(24030, "광주광역시", "남구"),
	GWANGJU_BUK(24040, "광주광역시", "북구"),
	GWANGJU_GWANGSAN(24050, "광주광역시", "광산구"),


	// NOTE: 대전광역시
	DAEJEON_DONG(25010, "대전광역시", "동구"),
	DAEJEON_JUNG(25020, "대전광역시", "중구"),
	DAEJEON_SEO(25030, "대전광역시", "서구"),
	DAEJEON_YUSEONG(25040, "대전광역시", "유성구"),
	DAEJEON_DAEDEOK(25050, "대전광역시", "대덕구"),

	//NOTE: 울산광역시
	ULSAN_JUNG(26010, "울산광역시", "중구"),
	ULSAN_NAM(26020, "울산광역시", "남구"),
	ULSAN_DONG(26030, "울산광역시", "동구"),
	ULSAN_BUK(26040, "울산광역시", "북구"),
	ULSAN_ULJU(26310, "울산광역시", "울주군"),


	// NOTE: 세종특별자치시
	SEJONG(29010, "세종특별자치시", "세종시"),

	// NOTE: 경기도
	GYEONGGI_SUWON(31010, "경기도", "수원시"),
	GYEONGGI_SUWON_JANGAN(31011, "경기도", "수원시 장안구"),
	GYEONGGI_SUWON_GWONSEON(31012, "경기도", "수원시 권선구"),
	GYEONGGI_SUWON_PALDAL(31013, "경기도", "수원시 팔달구"),
	GYEONGGI_SUWON_YEONGTONG(31014, "경기도", "수원시 영통구"),
	GYEONGGI_SEONGNAM(31020, "경기도", "성남시"),
	GYEONGGI_SEONGNAM_SUSEONG(31021, "경기도", "성남시 수정구"),
	GYEONGGI_SEONGNAM_JUNGWON(31022, "경기도", "성남시 중원구"),
	GYEONGGI_SEONGNAM_BUNDANG(31023, "경기도", "성남시 분당구"),
	GYEONGGI_UIJEONGBU(31030, "경기도", "의정부시"),
	GYEONGGI_ANYANG(31040, "경기도", "안양시"),
	GYEONGGI_ANYANG_MANAN(31041, "경기도", "안양시 만안구"),
	GYEONGGI_ANYANG_DONGAN(31042, "경기도", "안양시 동안구"),
	GYEONGGI_BUCHEON(31050, "경기도", "부천시"),
	GYEONGGI_GWANGMYEONG(31060, "경기도", "광명시"),
	GYEONGGI_PYEOCTAEK(31070, "경기도", "평택시"),
	GYEONGGI_DONGDUCHEON(31080, "경기도", "동두천시"),
	GYEONGGI_ANSAN(31090, "경기도", "안산시"),
	GYEONGGI_ANSAN_SANGROK(31091, "경기도", "안산시 상록구"),
	GYEONGGI_ANSAN_DANWON(31092, "경기도", "안산시 단원구"),
	GYEONGGI_GOYANG(31100, "경기도", "고양시"),
	GYEONGGI_GOYANG_DEOKYANG(31101, "경기도", "고양시 덕양구"),
	GYEONGGI_GOYANG_ILSANDONG(31103, "경기도", "고양시 일산동구"),
	GYEONGGI_GOYANG_ILSANSI(31104, "경기도", "고양시 일산서구"),
	GYEONGGI_GWACHEON(31110, "경기도", "과천시"),
	GYEONGGI_GURI(31120, "경기도", "구리시"),
	GYEONGGI_NAMYANGJU(31130, "경기도", "남양주시"),
	GYEONGGI_OSAN(31140, "경기도", "오산시"),
	GYEONGGI_SIHEUNG(31150, "경기도", "시흥시"),
	GYEONGGI_GUNPO(31160, "경기도", "군포시"),
	GYEONGGI_UIWANG(31170, "경기도", "의왕시"),
	GYEONGGI_HANAM(31180, "경기도", "하남시"),
	GYEONGGI_YONGIN(31190, "경기도", "용인시"),
	GYEONGGI_YONGIN_CHEOIN(31191, "경기도", "용인시 처인구"),
	GYEONGGI_YONGIN_GIHEUNG(31192, "경기도", "용인시 기흥구"),
	GYEONGGI_YONGIN_SUJI(31193, "경기도", "용인시 수지구"),
	GYEONGGI_PAJU(31200, "경기도", "파주시"),
	GYEONGGI_ICHEON(31210, "경기도", "이천시"),
	GYEONGGI_ANSEONG(31220, "경기도", "안성시"),
	GYEONGGI_GIMPO(31230, "경기도", "김포시"),
	GYEONGGI_HWASEONG(31240, "경기도", "화성시"),
	GYEONGGI_GWANGJU(31250, "경기도", "광주시"),
	GYEONGGI_YANGJU(31260, "경기도", "양주시"),
	GYEONGGI_POCHEON(31270, "경기도", "포천시"),
	GYEONGGI_YEOJU(31280, "경기도", "여주시"),
	GYEONGGI_YEONCHEON(31350, "경기도", "연천군"),
	GYEONGGI_GAPYEONG(31370, "경기도", "가평군"),
	GYEONGGI_YANGPYEONG(31380, "경기도", "양평군"),

	// NOTE: 강원특별자치도
	GANGWON_CHUNCHEON(32010, "강원특별자치도", "춘천시"),
	GANGWON_WONJU(32020, "강원특별자치도", "원주시"),
	GANGWON_GANGNEUNG(32030, "강원특별자치도", "강릉시"),
	GANGWON_DONGHAE(32040, "강원특별자치도", "동해시"),
	GANGWON_TAEBACK(32050, "강원특별자치도", "태백시"),
	GANGWON_SOKCHO(32060, "강원특별자치도", "속초시"),
	GANGWON_SAMCHEOK(32070, "강원특별자치도", "삼척시"),
	GANGWON_HONGCHEON(32310, "강원특별자치도", "홍천군"),
	GANGWON_HOENGSEONG(32320, "강원특별자치도", "횡성군"),
	GANGWON_YEONGWOL(32330, "강원특별자치도", "영월군"),
	GANGWON_PYEONGCHANG(32340, "강원특별자치도", "평창군"),
	GANGWON_JEONGSEON(32350, "강원특별자치도", "정선군"),
	GANGWON_CHEORWON(32360, "강원특별자치도", "철원군"),
	GANGWON_HWACHEON(32370, "강원특별자치도", "화천군"),
	GANGWON_YANGGU(32380, "강원특별자치도", "양구군"),
	GANGWON_INJE(32390, "강원특별자치도", "인제군"),
	GANGWON_GOSEONG(32400, "강원특별자치도", "고성군"),
	GANGWON_YANGYANG(32410, "강원특별자치도", "양양군"),

	// NOTE: 충청북도
	CHUNGBUK_CHUNGJU(33020, "충청북도", "충주시"),
	CHUNGBUK_JECHEON(33030, "충청북도", "제천시"),
	CHUNGBUK_CHEONGJU(33040, "충청북도", "청주시"),
	CHUNGBUK_CHEONGJU_SANGDANG(33041, "충청북도", "청주시 상당구"),
	CHUNGBUK_CHEONGJU_SEOWON(33042, "충청북도", "청주시 서원구"),
	CHUNGBUK_CHEONGJU_HEUNGDEOK(33043, "충청북도", "청주시 흥덕구"),
	CHUNGBUK_CHEONGJU_CHEONWON(33044, "충청북도", "청주시 청원구"),
	CHUNGBUK_BOEUN(33320, "충청북도", "보은군"),
	CHUNGBUK_OKCHEON(33330, "충청북도", "옥천군"),
	CHUNGBUK_YEONGDONG(33340, "충청북도", "영동군"),
	CHUNGBUK_JINCHEON(33350, "충청북도", "진천군"),
	CHUNGBUK_GOESAN(33360, "충청북도", "괴산군"),
	CHUNGBUK_EUMSEONG(33370, "충청북도", "음성군"),
	CHUNGBUK_DANYANG(33380, "충청북도", "단양군"),
	CHUNGBUK_JEUNGPYEONG(33390, "충청북도", "증평군"),

	// NOTE: 충청남도
	CHUNGNAM_CHEONAN(34010, "충청남도", "천안시"),
	CHUNGNAM_CHEONAN_DONGNAM(34011, "충청남도", "천안시 동남구"),
	CHUNGNAM_CHEONAN_SEOBUK(34012, "충청남도", "천안시 서북구"),
	CHUNGNAM_GONGJU(34020, "충청남도", "공주시"),
	CHUNGNAM_BOLEUNG(34030, "충청남도", "보령시"),
	CHUNGNAM_ASAN(34040, "충청남도", "아산시"),
	CHUNGNAM_SEOSAN(34050, "충청남도", "서산시"),
	CHUNGNAM_NONSAN(34060, "충청남도", "논산시"),
	CHUNGNAM_GYERYONG(34070, "충청남도", "계룡시"),
	CHUNGNAM_DANGJIN(34080, "충청남도", "당진시"),
	CHUNGNAM_GEUMSAN(34310, "충청남도", "금산군"),
	CHUNGNAM_BUO(34330, "충청남도", "부여군"),
	CHUNGNAM_SEOCHON(34340, "충청남도", "서천군"),
	CHUNGNAM_CHEongyang(34350, "충청남도", "청양군"),
	CHUNGNAM_HONGSEONG(34360, "충청남도", "홍성군"),
	CHUNGCHEONGNAMDO_YESANGUN(34370, "충청남도", "예산군"),
	CHUNGCHEONGNAMDO_TAAN(34380, "충청남도", "태안군"),

	// NOTE: 전라북도
	JEOLLABUKDO_JEONJUSI(35010, "전라북도", "전주시"),
	JEOLLABUKDO_JEONJUSI_WANSANGU(35011, "전라북도", "전주시 완산구"),
	JEOLLABUKDO_JEONJUSI_DEOKJINGU(35012, "전라북도", "전주시 덕진구"),
	JEOLLABUKDO_GUNSAKSI(35020, "전라북도", "군산시"),
	JEOLLABUKDO_IKSANSI(35030, "전라북도", "익산시"),
	JEOLLABUKDO_JEONGEUPSI(35040, "전라북도", "정읍시"),
	JEOLLABUKDO_NAMWONSI(35050, "전라북도", "남원시"),
	JEOLLABUKDO_GIMJESI(35060, "전라북도", "김제시"),
	JEOLLABUKDO_WANJUGUN(35310, "전라북도", "완주군"),
	JEOLLABUKDO_JINANGUN(35320, "전라북도", "진안군"),
	JEOLLABUKDO_MUJUGUN(35330, "전라북도", "무주군"),
	JEOLLABUKDO_JANGSUGUN(35340, "전라북도", "장수군"),
	JEOLLABUKDO_IMSILGUN(35350, "전라북도", "임실군"),
	JEOLLABUKDO_SUNCHANGGUN(35360, "전라북도", "순창군"),
	JEOLLABUKDO_GOCHANGGUN(35370, "전라북도", "고창군"),
	JEOLLABUKDO_BUANGUN(35380, "전라북도", "부안군"),

	// NOTE: 전라남도
	JEOLLANAMDO_MOKPO(36010, "전라남도", "목포시"),
	JEOLLANAMDO_YEOSU(36020, "전라남도", "여수시"),
	JEOLLANAMDO_SUNCHEON(36030, "전라남도", "순천시"),
	JEOLLANAMDO_NAJU(36040, "전라남도", "나주시"),
	JEOLLANAMDO_GWANGYANG(36060, "전라남도", "광양시"),
	JEOLLANAMDO_DAMYANG(36310, "전라남도", "담양군"),
	JEOLLANAMDO_GOKSEONG(36320, "전라남도", "곡성군"),
	JEOLLANAMDO_GURYE(36330, "전라남도", "구례군"),
	JEOLLANAMDO_GOHEUNG(36350, "전라남도", "고흥군"),
	JEOLLANAMDO_BOSEONG(36360, "전라남도", "보성군"),
	JEOLLANAMDO_HWASOON(36370, "전라남도", "화순군"),
	JEOLLANAMDO_JANGHEUNG(36380, "전라남도", "장흥군"),
	JEOLLANAMDO_GANGJIN(36390, "전라남도", "강진군"),
	JEOLLANAMDO_HAENAM(36400, "전라남도", "해남군"),
	JEOLLANAMDO_YEONGAM(36410, "전라남도", "영암군"),
	JEOLLANAMDO_MUAN(36420, "전라남도", "무안군"),
	JEOLLANAMDO_HAMTAN(36430, "전라남도", "함평군"),
	JEOLLANAMDO_YEONGGWANG(36440, "전라남도", "영광군"),
	JEOLLANAMDO_JANGSEONG(36450, "전라남도", "장성군"),
	JEOLLANAMDO_WANDO(36460, "전라남도", "완도군"),
	JEOLLANAMDO_JINDO(36470, "전라남도", "진도군"),
	JEOLLANAMDO_SHINAN(36480, "전라남도", "신안군"),

	// NOTE: 경상북도
	GYEONGSANGBUKDO_POHANG(37010, "경상북도", "포항시"),
	GYEONGSANGBUKDO_POHANG_NAMGU(37011, "경상북도", "포항시 남구"),
	GYEONGSANGBUKDO_POHANG_BUKGU(37012, "경상북도", "포항시 북구"),
	GYEONGSANGBUKDO_GYEONGJU(37020, "경상북도", "경주시"),
	GYEONGSANGBUKDO_GIMCHEON(37030, "경상북도", "김천시"),
	GYEONGSANGBUKDO_ANDONG(37040, "경상북도", "안동시"),
	GYEONGSANGBUKDO_GUMI(37050, "경상북도", "구미시"),
	GYEONGSANGBUKDO_YEONGJU(37060, "경상북도", "영주시"),
	GYEONGSANGBUKDO_YEONGCHUN(37070, "경상북도", "영천시"),
	GYEONGSANGBUKDO_SANGJU(37080, "경상북도", "상주시"),
	GYEONGSANGBUKDO_MOONKYUNG(37090, "경상북도", "문경시"),
	GYEONGSANGBUKDO_GYEONGSAN(37100, "경상북도", "경산시"),
	GYEONGSANGBUKDO_GUNWI(37310, "경상북도", "군위군"),
	GYEONGSANGBUKDO_UISEONG(37320, "경상북도", "의성군"),
	GYEONGSANGBUKDO_CHEONGSONG(37330, "경상북도", "청송군"),
	GYEONGSANGBUKDO_YEONGYANG(37340, "경상북도", "영양군"),
	GYEONGSANGBUKDO_YEONGDEOK(37350, "경상북도", "영덕군"),
	GYEONGSANGBUKDO_CHEONGDO(37360, "경상북도", "청도군"),
	GYEONGSANGBUKDO_GORYEONG(37370, "경상북도", "고령군"),
	GYEONGSANGBUKDO_SEONGJU(37380, "경상북도", "성주군"),
	GYEONGSANGBUKDO_CHILGOK(37390, "경상북도", "칠곡군"),
	GYEONGSANGBUKDO_YECHEON(37400, "경상북도", "예천군"),
	GYEONGSANGBUKDO_BONGHWA(37410, "경상북도", "봉화군"),
	GYEONGSANGBUKDO_ULJIN(37420, "경상북도", "울진군"),
	GYEONGSANGBUKDO_ULLEUNG(37430, "경상북도", "울릉군"),

	// NOTE: 경상남도
	GYEONGSANGNAMDO_JINJU(38030, "경상남도", "진주시"),
	GYEONGSANGNAMDO_TONGYEONG(38050, "경상남도", "통영시"),
	GYEONGSANGNAMDO_SACHUN(38060, "경상남도", "사천시"),
	GYEONGSANGNAMDO_GIMHAE(38070, "경상남도", "김해시"),
	GYEONGSANGNAMDO_MIRYANG(38080, "경상남도", "밀양시"),
	GYEONGSANGNAMDO_GEOJE(38090, "경상남도", "거제시"),
	GYEONGSANGNAMDO_YANGSAN(38100, "경상남도", "양산시"),
	GYEONGSANGNAMDO_CHANGWON(38110, "경상남도", "창원시"),
	GYEONGSANGNAMDO_CHANGWON_UECHANG(38111, "경상남도", "창원시 의창구"),
	GYEONGSANGNAMDO_CHANGWON_SEONGSAN(38112, "경상남도", "창원시 성산구"),
	GYEONGSANGNAMDO_CHANGWON_HAPPO(38113, "경상남도", "창원시 마산합포구"),
	GYEONGSANGNAMDO_CHANGWON_HOEWON(38114, "경상남도", "창원시 마산회원구"),
	GYEONGSANGNAMDO_CHANGWON_JINHAE(38115, "경상남도", "창원시 진해구"),
	GYEONGSANGNAMDO_UIRYEONG(38310, "경상남도", "의령군"),
	GYEONGSANGNAMDO_HAMAN(38320, "경상남도", "함안군"),
	GYEONGSANGNAMDO_CHANGNYEONG(38330, "경상남도", "창녕군"),
	GYEONGSANGNAMDO_GOSEONG(38340, "경상남도", "고성군"),
	GYEONGSANGNAMDO_NAMHAE(38350, "경상남도", "남해군"),
	GYEONGSANGNAMDO_HADONG(38360, "경상남도", "하동군"),
	GYEONGSANGNAMDO_SANCHEONG(38370, "경상남도", "산청군"),
	GYEONGSANGNAMDO_HAMYANG(38380, "경상남도", "함양군"),
	GYEONGSANGNAMDO_GEOCHANG(38390, "경상남도", "거창군"),
	GYEONGSANGNAMDO_HAPCHEON(38400, "경상남도", "합천군"),

	// NOTE: 제주특별자치도
	JEJU_JEJU(39010, "제주특별자치도", "제주시"),
	JEJU_SEOGWIPO(39020, "제주특별자치도", "서귀포시");


	companion object {
		fun getByCode(regionCode: Long): REGION =
			entries.find { it.code == regionCode } ?: throw TODO("없는 지역 코드 log 찍기")
		
		fun getCodeByCityAndDistrict(city: String, district: String): REGION =
			entries.find { it.cityName == city && it.districtName == district }
				?: throw TODO("찾을 수 없음 log 찍기")
	}
}