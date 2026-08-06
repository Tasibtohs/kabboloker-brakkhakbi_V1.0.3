package com.hmibrahimsarkar.kabboloker_brakkhakbi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.components.NavigationDrawerContent
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.screens.BackupRestoreScreen
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.screens.EditorScreen
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.screens.GroupsScreen
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.screens.HiddenNotesScreen
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.screens.NotesListScreen
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.screens.SettingsAboutScreen
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.screens.SplashScreen
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.screens.TrashScreen
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme.KabyolokorTheme
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.viewmodel.MainViewModel
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.viewmodel.Screen
import kotlinx.coroutines.launch

import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.font.BengaliFonts

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BengaliFonts.init(this)
        enableEdgeToEdge()

        setContent {
            val isDarkPref by mainViewModel.isDarkMode.collectAsState()
            val systemInDark = isSystemInDarkTheme()
            val effectiveDark = isDarkPref ?: systemInDark

            KabyolokorTheme(darkTheme = effectiveDark) {
                MainAppContent(
                    viewModel = mainViewModel,
                    isDarkMode = effectiveDark
                )
            }
        }
    }
}

@Composable
fun MainAppContent(
    viewModel: MainViewModel,
    isDarkMode: Boolean
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val currentScreen by viewModel.currentScreen.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val exportAllPdfLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/pdf")
    ) { uri ->
        if (uri != null) {
            viewModel.exportAllNotesToPdfToUri(context, uri)
        }
    }

    // Back button handling
    BackHandler(enabled = currentScreen !is Screen.NotesList && currentScreen !is Screen.Splash) {
        if (drawerState.isOpen) {
            scope.launch { drawerState.close() }
        } else {
            viewModel.navigateTo(Screen.NotesList)
        }
    }

    if (currentScreen is Screen.Splash) {
        SplashScreen(
            onSplashFinished = {
                viewModel.navigateTo(Screen.NotesList)
            }
        )
    } else {
        ModalNavigationDrawer(
            drawerState = drawerState,
            gesturesEnabled = currentScreen !is Screen.Editor,
            drawerContent = {
                NavigationDrawerContent(
                    currentScreen = currentScreen,
                    isDarkMode = isDarkMode,
                    onNavigate = { targetScreen ->
                        viewModel.navigateTo(targetScreen)
                    },
                    onToggleDarkMode = { isDark ->
                        viewModel.toggleDarkMode(isDark)
                    },
                    onCloseDrawer = {
                        scope.launch { drawerState.close() }
                    },
                    onExportAllPdf = {
                        val dateStr = SimpleDateFormat("dd_MMM_yyyy", Locale("bn", "BD")).format(Date())
                        exportAllPdfLauncher.launch("কাব্যলোকের_ব্রহ্মকবি_সংকলন_$dateStr.pdf")
                    }
                )
            }
        ) {
            AnimatedContent(
                targetState = currentScreen,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                modifier = Modifier.fillMaxSize(),
                label = "ScreenTransition"
            ) { screen ->
                when (screen) {
                    is Screen.Splash -> {
                        SplashScreen(
                            onSplashFinished = {
                                viewModel.navigateTo(Screen.NotesList)
                            }
                        )
                    }

                    is Screen.NotesList, is Screen.PinnedNotes -> {
                        NotesListScreen(
                            viewModel = viewModel,
                            onOpenDrawer = { scope.launch { drawerState.open() } },
                            onOpenEditor = { noteId ->
                                viewModel.navigateTo(Screen.Editor(noteId))
                            }
                        )
                    }

                    is Screen.Editor -> {
                        EditorScreen(
                            noteId = screen.noteId,
                            mainViewModel = viewModel,
                            onBack = {
                                viewModel.navigateTo(Screen.NotesList)
                            }
                        )
                    }

                    is Screen.Groups -> {
                        GroupsScreen(
                            viewModel = viewModel,
                            onOpenDrawer = { scope.launch { drawerState.open() } },
                            onBack = { viewModel.navigateTo(Screen.NotesList) },
                            onOpenEditor = { noteId ->
                                viewModel.navigateTo(Screen.Editor(noteId))
                            }
                        )
                    }

                    is Screen.HiddenNotes -> {
                        HiddenNotesScreen(
                            viewModel = viewModel,
                            onOpenDrawer = { scope.launch { drawerState.open() } },
                            onBack = { viewModel.navigateTo(Screen.NotesList) },
                            onOpenEditor = { noteId ->
                                viewModel.navigateTo(Screen.Editor(noteId))
                            }
                        )
                    }

                    is Screen.Trash -> {
                        TrashScreen(
                            viewModel = viewModel,
                            onOpenDrawer = { scope.launch { drawerState.open() } },
                            onBack = { viewModel.navigateTo(Screen.NotesList) }
                        )
                    }

                    is Screen.BackupRestore -> {
                        BackupRestoreScreen(
                            viewModel = viewModel,
                            onOpenDrawer = { scope.launch { drawerState.open() } },
                            onBack = { viewModel.navigateTo(Screen.NotesList) }
                        )
                    }

                    is Screen.SettingsAbout -> {
                        SettingsAboutScreen(
                            viewModel = viewModel,
                            onOpenDrawer = { scope.launch { drawerState.open() } },
                            onBack = { viewModel.navigateTo(Screen.NotesList) }
                        )
                    }
                }
            }
        }
    }
}
