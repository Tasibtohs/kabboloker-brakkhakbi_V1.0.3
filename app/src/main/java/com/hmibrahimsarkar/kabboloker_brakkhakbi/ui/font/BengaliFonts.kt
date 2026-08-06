package com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.font

import android.content.Context
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.hmibrahimsarkar.kabboloker_brakkhakbi.R

data class BengaliFontOption(
    val key: String,
    val name: String,
    val category: String, // "ক্যালিগ্রাফি", "আর্টিস্টিক", "ডেকোরেটিভ"
    val description: String,
    val fontFamily: FontFamily,
    val assetFileName: String,
    val resId: Int = 0
)

object BengaliFonts {

    private fun font(resId: Int): FontFamily = FontFamily(Font(resId))

    val fonts: List<BengaliFontOption> = listOf(
        // ১. অনুপম মাহদী (Anupam Mahdi)
        BengaliFontOption(
            key = "anupam_mahdi",
            name = "অনুপম মাহদী (Anupam Mahdi)",
            category = "ক্যালিগ্রাফি",
            description = "নান্দনিক রাজকীয় বাঁকানো স্ট্রোকের বাংলা ক্যালিগ্রাফি",
            fontFamily = font(R.font.anupam_mahdi),
            assetFileName = "anupam_mahdi.ttf",
            resId = R.font.anupam_mahdi
        ),
        // ২. আলিনূর ইচ্ছামতী (Alinur Ichamati)
        BengaliFontOption(
            key = "alinur_ichamati",
            name = "আলিনূর ইচ্ছামতী (Alinur Ichamati)",
            category = "ক্যালিগ্রাফি",
            description = "প্রবাহমান মিষ্টি নদীর স্রোতের মতো নান্দনিক ক্যালিগ্রাফিক রূপ",
            fontFamily = font(R.font.alinur_ichamati),
            assetFileName = "alinur_ichamati.ttf",
            resId = R.font.alinur_ichamati
        ),
        // ৩. আলিনূর গোধূলি (Alinur Gaudhuli)
        BengaliFontOption(
            key = "alinur_gaudhuli",
            name = "আলিনূর গোধূলি (Alinur Gaudhuli)",
            category = "আর্টিস্টিক",
            description = "গোধূলির কোমল রোদের আলোয় অলংকৃত শৈল্পিক বাংলা হরফ",
            fontFamily = font(R.font.alinur_gaudhuli),
            assetFileName = "alinur_gaudhuli.ttf",
            resId = R.font.alinur_gaudhuli
        ),
        // ৪. আলিনূর প্রচেষ্টা (Alinur's Effort)
        BengaliFontOption(
            key = "alinur_effort",
            name = "আলিনূর প্রচেষ্টা (Alinur's Effort)",
            category = "ডেকোরেটিভ",
            description = "বিশেষ কারুকাজ করা মডার্ন ডেকোরেটিভ ডিসপ্লে ফন্ট",
            fontFamily = font(R.font.alinur_effort),
            assetFileName = "alinur_effort.ttf",
            resId = R.font.alinur_effort
        ),
        // ৫. আত্মা (Atma Medium)
        BengaliFontOption(
            key = "atma",
            name = "আত্মা (Atma Medium)",
            category = "আর্টিস্টিক",
            description = "ব্যক্তিগত সাহিত্য ও কবিতা লেখার জন্য জীবন্ত হাতে লেখা স্টাইল",
            fontFamily = font(R.font.atma),
            assetFileName = "atma.ttf",
            resId = R.font.atma
        ),
        // ৬. বাঁধন জননী (Badhon Janani)
        BengaliFontOption(
            key = "badhon_janani",
            name = "বাঁধন জননী (Badhon Janani)",
            category = "ক্যালিগ্রাফি",
            description = "স্নেহ ও আবেগে ভরপুর সুন্দর বাংলা ক্যালিগ্রাফি ফন্ট",
            fontFamily = font(R.font.badhon_janani),
            assetFileName = "badhon_janani.ttf",
            resId = R.font.badhon_janani
        ),
        // ৭. বাসন্তী (Basanti Unicode)
        BengaliFontOption(
            key = "basanti_unicode",
            name = "বাসন্তী (Basanti Unicode)",
            category = "ডেকোরেটিভ",
            description = "বসন্তের আমেজে ঘেরা সুবিন্যস্ত ও উজ্জ্বল ডিসপ্লে ফন্ট",
            fontFamily = font(R.font.basanti_unicode),
            assetFileName = "basanti_unicode.ttf",
            resId = R.font.basanti_unicode
        ),
        // ৮. কল্পনা (Kalpana Unicode)
        BengaliFontOption(
            key = "kalpana_unicode",
            name = "কল্পনা (Kalpana Unicode)",
            category = "আর্টিস্টিক",
            description = "কল্পনাপ্রসূত মিষ্টি ও আকর্ষণীয় বাংলা অক্ষর রূপাতুর",
            fontFamily = font(R.font.kalpana_unicode),
            assetFileName = "kalpana_unicode.ttf",
            resId = R.font.kalpana_unicode
        ),
        // ৯. কবিতা (Kobita Font)
        BengaliFontOption(
            key = "kobita",
            name = "কবিতা (Kobita Font)",
            category = "আর্টিস্টিক",
            description = "কাব্য ও সাহিত্য রচনার উপযোগী ছন্দময় শৈল্পিক ফন্ট",
            fontFamily = font(R.font.kobita),
            assetFileName = "kobita.ttf",
            resId = R.font.kobita
        ),
        // ১০. শহীদ তাহমিদ তামিম (Li Tahmid Tamin)
        BengaliFontOption(
            key = "li_shohid_tahmid_tamin_unicode",
            name = "তাহমিদ তামিম (Li Tahmid Tamin)",
            category = "ক্যালিগ্রাফি",
            description = "শ্রদ্ধাঞ্জলিস্বরূপ তৈরীকৃত বলিষ্ঠ ও ভাবগম্ভীর বাংলা ক্যালিগ্রাফি",
            fontFamily = font(R.font.li_shohid_tahmid_tamin_unicode),
            assetFileName = "li_shohid_tahmid_tamin_unicode.ttf",
            resId = R.font.li_shohid_tahmid_tamin_unicode
        ),
        // ১১. মাহফুজ এ. কে. (Mahfuz A.K.)
        BengaliFontOption(
            key = "mahfuz_a_k",
            name = "মাহফুজ এ. কে. (Mahfuz A.K.)",
            category = "ডেকোরেটিভ",
            description = "আধুনিক দৃষ্টিনন্দন ডিজাইনের স্টাইলিশ টাইপোগ্রাফি",
            fontFamily = font(R.font.mahfuz_a_k),
            assetFileName = "mahfuz_a_k.ttf",
            resId = R.font.mahfuz_a_k
        ),
        // ১২. মাহফুজ বনলতা (Mahfuz Bonolota)
        BengaliFontOption(
            key = "mahfuz_bonolota",
            name = "মাহফুজ বনলতা (Mahfuz Bonolota)",
            category = "আর্টিস্টিক",
            description = "বনলতা সেনের রূপময়তার ছোঁয়ায় শৈল্পিক বাংলা ফন্ট",
            fontFamily = font(R.font.mahfuz_bonolota),
            assetFileName = "mahfuz_bonolota.ttf",
            resId = R.font.mahfuz_bonolota
        ),
        // ১৩. মাহফুজ বর্ণমালা (Mahfuz Bornomala)
        BengaliFontOption(
            key = "mahfuz_bornomala",
            name = "মাহফুজ বর্ণমালা (Mahfuz Bornomala)",
            category = "ডেকোরেটিভ",
            description = "বাংলা বর্ণমালার ঐতিহ্য ও আধুনিকতার চমৎকার সংমিশ্রণ",
            fontFamily = font(R.font.mahfuz_bornomala),
            assetFileName = "mahfuz_bornomala.otf",
            resId = R.font.mahfuz_bornomala
        ),
        // ১৪. মাহফুজ চিরকুট (Mahfuz Chirkut)
        BengaliFontOption(
            key = "mahfuz_chirkut_stylish",
            name = "মাহফুজ চিরকুট (Mahfuz Chirkut)",
            category = "আর্টিস্টিক",
            description = "চিরকুটে লেখা মিষ্টি অনুভূতির রূপভঙ্গিমা ফন্ট",
            fontFamily = font(R.font.mahfuz_chirkut_stylish),
            assetFileName = "mahfuz_chirkut_stylish.ttf",
            resId = R.font.mahfuz_chirkut_stylish
        ),
        // ১৫. মোহিনী (Mohinee Unicode)
        BengaliFontOption(
            key = "mohinee_unicode",
            name = "মোহিনী (Mohinee Unicode)",
            category = "ক্যালিগ্রাফি",
            description = "মোহনীয় ও আকর্ষণীয় বাঁকের বাংলা ক্যালিগ্রাফিক ট্রাফাত",
            fontFamily = font(R.font.mohinee_unicode),
            assetFileName = "mohinee_unicode.ttf",
            resId = R.font.mohinee_unicode
        ),
        // ১৬. ওরিন (Orin Unicode)
        BengaliFontOption(
            key = "orin_unicode",
            name = "ওরিন (Orin Unicode)",
            category = "ডেকোরেটিভ",
            description = "পরিচ্ছন্ন ও মসৃণ কারুকাজের আধুনিক ডিসপ্লে ফন্ট",
            fontFamily = font(R.font.orin_unicode),
            assetFileName = "orin_unicode.ttf",
            resId = R.font.orin_unicode
        ),
        // ১৭. রূপসী বাংলা (Ruposhi Bangla Bold)
        BengaliFontOption(
            key = "ruposhi_bangla_bold",
            name = "রূপসী বাংলা (Ruposhi Bangla Bold)",
            category = "ডেকোরেটিভ",
            description = "গৌরবময় রূপসী বাংলার গাঢ় ও স্পষ্ট আকর্ষণীয় বোল্ড ফন্ট",
            fontFamily = font(R.font.ruposhi_bangla_bold),
            assetFileName = "ruposhi_bangla_bold.ttf",
            resId = R.font.ruposhi_bangla_bold
        ),
        // ১৮. শকুন্তলা (Shakuntala Unicode)
        BengaliFontOption(
            key = "shakuntala_unicode",
            name = "শকুন্তলা (Shakuntala Unicode)",
            category = "ক্যালিগ্রাফি",
            description = "ধ্রুপদী সাহিত্যের কাব্যিক শকুন্তলা ক্যালিগ্রাফি",
            fontFamily = font(R.font.shakuntala_unicode),
            assetFileName = "shakuntala_unicode.ttf",
            resId = R.font.shakuntala_unicode
        ),
        // ১৯. শরীফা ফিফা (Sharifa Fifa)
        BengaliFontOption(
            key = "sharifa_fifa_unicode",
            name = "শরীফা ফিফা (Sharifa Fifa)",
            category = "ডেকোরেটিভ",
            description = "অনন্য ও নতুন ঘরানার স্টাইলিশ ডিসপ্লে বাংলা ফন্ট",
            fontFamily = font(R.font.sharifa_fifa_unicode),
            assetFileName = "sharifa_fifa_unicode.ttf",
            resId = R.font.sharifa_fifa_unicode
        ),
        // ২০. সবুজ নলুয়া (Shobuj Noluya)
        BengaliFontOption(
            key = "shobuj_noluya_unicode",
            name = "সবুজ নলুয়া (Shobuj Noluya)",
            category = "আর্টিস্টিক",
            description = "গ্রামবাংলার প্রকৃতির সুনিবিড় ছোঁয়ায় গড়া মনোরম ফন্ট",
            fontFamily = font(R.font.shobuj_noluya_unicode),
            assetFileName = "shobuj_noluya_unicode.ttf",
            resId = R.font.shobuj_noluya_unicode
        )
    )

    fun init(context: Context? = null) {
        if (context != null) {
            FontLoader.initFonts(context.applicationContext ?: context, fonts)
        }
    }

    fun getFontByKey(key: String): BengaliFontOption {
        val mappedKey = when (key.lowercase()) {
            "anupam_mahdi", "anupam", "mahdi" -> "anupam_mahdi"
            "alinur_ichamati", "ichamati" -> "alinur_ichamati"
            "alinur_gaudhuli", "gaudhuli" -> "alinur_gaudhuli"
            "alinur_effort", "effort" -> "alinur_effort"
            "atma", "atma_medium" -> "atma"
            else -> key.lowercase()
        }
        val option = fonts.find { it.key == mappedKey }
            ?: fonts.find { it.key == key.lowercase() }
            ?: fonts[0]
        val loadedFont = FontLoader.getFontFamily(option.key)
        return if (loadedFont != null && loadedFont != FontFamily.Default) {
            option.copy(fontFamily = loadedFont)
        } else {
            option
        }
    }
}
