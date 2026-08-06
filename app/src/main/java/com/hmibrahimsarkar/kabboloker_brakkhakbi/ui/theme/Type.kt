package com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme

import android.content.Context
import android.graphics.Typeface
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.font.BengaliFonts

/**
 * Creates an Android [Typeface] directly from a custom font file in `app/src/main/assets/fonts`.
 */
fun createAssetTypeface(context: Context, assetFileName: String): Typeface {
    return try {
        Typeface.createFromAsset(context.assets, "fonts/$assetFileName")
    } catch (e: Exception) {
        Typeface.DEFAULT
    }
}

/**
 * Creates a Compose [FontFamily] directly from an Android [Typeface] loaded from assets.
 */
fun createAssetFontFamily(context: Context, assetFileName: String): FontFamily {
    val typeface = createAssetTypeface(context, assetFileName)
    return FontFamily(typeface)
}

// Custom FontFamilies created using the asset font files from app/src/main/assets/fonts
val AnupamMahdiFontFamily: FontFamily get() = BengaliFonts.getFontByKey("anupam_mahdi").fontFamily
val AlinurIchamatiFontFamily: FontFamily get() = BengaliFonts.getFontByKey("alinur_ichamati").fontFamily
val AlinurGaudhuliFontFamily: FontFamily get() = BengaliFonts.getFontByKey("alinur_gaudhuli").fontFamily
val AlinurEffortFontFamily: FontFamily get() = BengaliFonts.getFontByKey("alinur_effort").fontFamily
val AtmaFontFamily: FontFamily get() = BengaliFonts.getFontByKey("atma").fontFamily
val BadhonJananiFontFamily: FontFamily get() = BengaliFonts.getFontByKey("badhon_janani").fontFamily
val BasantiUnicodeFontFamily: FontFamily get() = BengaliFonts.getFontByKey("basanti_unicode").fontFamily
val KalpanaUnicodeFontFamily: FontFamily get() = BengaliFonts.getFontByKey("kalpana_unicode").fontFamily
val KobitaFontFamily: FontFamily get() = BengaliFonts.getFontByKey("kobita").fontFamily
val LiShohidTahmidTaminFontFamily: FontFamily get() = BengaliFonts.getFontByKey("li_shohid_tahmid_tamin_unicode").fontFamily
val MahfuzAKFontFamily: FontFamily get() = BengaliFonts.getFontByKey("mahfuz_a_k").fontFamily
val MahfuzBonolotaFontFamily: FontFamily get() = BengaliFonts.getFontByKey("mahfuz_bonolota").fontFamily
val MahfuzBornomalaFontFamily: FontFamily get() = BengaliFonts.getFontByKey("mahfuz_bornomala").fontFamily
val MahfuzChirkutFontFamily: FontFamily get() = BengaliFonts.getFontByKey("mahfuz_chirkut_stylish").fontFamily
val MohineeUnicodeFontFamily: FontFamily get() = BengaliFonts.getFontByKey("mohinee_unicode").fontFamily
val OrinUnicodeFontFamily: FontFamily get() = BengaliFonts.getFontByKey("orin_unicode").fontFamily
val RuposhiBanglaBoldFontFamily: FontFamily get() = BengaliFonts.getFontByKey("ruposhi_bangla_bold").fontFamily
val ShakuntalaUnicodeFontFamily: FontFamily get() = BengaliFonts.getFontByKey("shakuntala_unicode").fontFamily
val SharifaFifaUnicodeFontFamily: FontFamily get() = BengaliFonts.getFontByKey("sharifa_fifa_unicode").fontFamily
val ShobujNoluyaUnicodeFontFamily: FontFamily get() = BengaliFonts.getFontByKey("shobuj_noluya_unicode").fontFamily

val AppDisplayFont: FontFamily
    get() = AnupamMahdiFontFamily
val AppHeadlineFont: FontFamily
    get() = AlinurIchamatiFontFamily
val AppTitleFont: FontFamily
    get() = AlinurGaudhuliFontFamily
val AppBodyFont: FontFamily
    get() = AtmaFontFamily

val AppTypography: Typography
    get() = Typography(
        displayLarge = TextStyle(
            fontFamily = AppDisplayFont,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp
        ),
        displayMedium = TextStyle(
            fontFamily = AppDisplayFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp
        ),
        displaySmall = TextStyle(
            fontFamily = AppDisplayFont,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = AppHeadlineFont,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 30.sp,
            letterSpacing = 0.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = AppHeadlineFont,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.15.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = AppHeadlineFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 26.sp,
            letterSpacing = 0.1.sp
        ),
        titleLarge = TextStyle(
            fontFamily = AppTitleFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        titleMedium = TextStyle(
            fontFamily = AppTitleFont,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.1.sp
        ),
        titleSmall = TextStyle(
            fontFamily = AppTitleFont,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = AppBodyFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.25.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = AppBodyFont,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall = TextStyle(
            fontFamily = AppBodyFont,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        labelLarge = TextStyle(
            fontFamily = AppBodyFont,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelMedium = TextStyle(
            fontFamily = AppBodyFont,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            letterSpacing = 0.4.sp
        ),
        labelSmall = TextStyle(
            fontFamily = AppBodyFont,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        )
    )

