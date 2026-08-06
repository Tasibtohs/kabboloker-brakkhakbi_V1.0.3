# কাব্যলোকের ব্রক্ষকবি (Kabboloker Brakkhakbi)

একটি আধুনিক, নমনীয় এবং দৃষ্টিনন্দন বাংলা নোটপ্যাড ও সাহিত্য লিখন অ্যান্ড্রয়েড অ্যাপ্লিকেশান। এটি লেখক, কবি ও সাধারণ ব্যবহারকারীদের সুবিধার্থে বিশেষ বাংলা ফন্ট, ক্যাটাগরি, হিডেন নোটস, ট্র্যাশ বিন, পিডিএফ এক্সপোর্ট এবং ব্যাকআপ সুবিধা প্রদান করে।

---

## 🌟 বৈশিষ্ট্যসমূহ (Features)

1. **সমৃদ্ধ বাংলা ফন্ট সাপোর্ট (20+ Bengali Fonts):**
   - আলিনুর এফোর্ট, আলিনুর গোধূলি, আলিনুর ইচ্ছামতী, অনুপম মাহদী, আত্মা, বাঁধন জননী, বাসন্তী, কল্পনা, কবিতা, শহীদ তাহমিদ তামিম, মাহফুজ এ. কে., মাহফুজ বনলতা, মাহফুজ বর্ণমালা, মাহফুজ চিরকুট, মোহিনী, ওরিন, রূপসী বাংলা, শকুন্তলা, শরীফা ফিফা, এবং সবুজ নলুয়া।

2. **নোট সম্পাদনা ও সাজসজ্জা:**
   - রিচ টেক্সট স্টাইলিং ও টাইপোগ্রাফি অপশন।
   - ফন্ট সাইজ, টেক্সট কালার, ব্যাকগ্রাউন্ড কালার, ও ফন্ট ফ্যামিলি কাস্টমাইজেশন।

3. **গ্রুপ ও বিভাগ (Groups & Categories):**
   - নোটগুলোকে নিজস্ব ক্যাটাগরি বা গ্রুপ অনুযায়ী সুবিন্যস্ত করার সুবিধা।

4. **প্রাইভেসি ও সিকিউরিটি (Hidden Notes):**
   - পাসওয়ার্ড সুরক্ষাসহ গোপন নোট সংরক্ষণের ব্যবস্থা।

5. **ট্র্যাশ ও রিকভারি (Trash Bin):**
   - দুর্ঘটনাবশত মুছে ফেলা নোটসমূহ পুনরায় ফিরিয়ে আনার সুবিধা।

6. **পিডিএফ ও শেয়ারিং (PDF Export & Share):**
   - নোটসমূহকে সুন্দর পিডিএফ (PDF) ডকুমেন্টে রুপান্তর ও সংরক্ষণ/শেয়ার করার সুবিধা।

7. **ড্যাটা ব্যাকআপ ও রিস্টোর (Backup & Restore):**
   - লোকাল ব্যাকআপ তৈরি ও পরবর্তীতে ডাটা রিস্টোর করার সুবিধা।

---

## 🛠 টেকনোলজি স্ট্যাক (Tech Stack)

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose (Material Design 3)
- **Database:** Room Database (SQLite Engine)
- **Architecture:** MVVM (Model-View-ViewModel) + Clean Architecture Principles
- **Async & Reactive:** Kotlin Coroutines & Flow
- **Export Engine:** Android PdfDocument API

---

## 📂 প্রজেক্ট স্ট্রাকচার (Project Structure)

```
app/src/main/
├── java/com/hmibrahimsarkar/kabboloker_brakkhakbi/
│   ├── data/             # Local database (Room), entities, and DAOs
│   ├── ui/               # Jetpack Compose UI Screens, Components & Themes
│   │   ├── components/   # Reusable UI widgets and dialogs
│   │   ├── font/         # Custom Bengali font loaders and options
│   │   ├── screens/      # Main screens (Editor, Notes, Trash, Groups, etc.)
│   │   ├── theme/        # Color schemes, Typography, and M3 styling
│   │   └── viewmodel/    # State management with ViewModel
│   └── util/             # PDF exporters, File utilities, and helpers
└── res/
    ├── font/             # TrueType (.ttf) and OpenType (.otf) font resources
    ├── drawable/         # Custom vector drawables & icons
    └── values/           # Strings, themes, and dimensions
```

---

## 🚀 কিভাবে অ্যাপটি তৈরি ও রান করবেন (Building & Running)

### পূর্বশর্ত (Prerequisites):
- Android Studio Ladybug (বা যেকোনো নতুন সংস্করণ)
- JDK 17+
- Android SDK (API Level 24 - API 35 Support)

### ইন্সটলেশন প্রসেস:
1. প্রজেক্ট ফাইল ডাউনলোড বা ক্লোন করুন।
2. Android Studio-তে প্রজেক্ট ওপেন করুন।
3. Gradle Sync সম্পূর্ণ হতে দিন।
4. যেকোনো অ্যান্ড্রয়েড এমুলেটর বা আসল অ্যান্ড্রয়েড ডিভাইসে অ্যাপটি রান করুন:
   ```bash
   ./gradlew assembleDebug
   ```

---

## 📜 লাইসেন্স (License)

কপিরাইট © 2026 **HM Ibrahim Sarkar**। সর্বস্বত্ব সংরক্ষিত।
