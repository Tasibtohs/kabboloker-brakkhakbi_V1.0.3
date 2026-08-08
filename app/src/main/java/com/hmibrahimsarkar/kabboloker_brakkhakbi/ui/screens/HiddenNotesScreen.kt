package com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.components.AppTopBar
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.components.HideNotesPickerModal
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.components.NoteCardItem
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.components.PasswordPromptDialog
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.components.SetPasswordDialog
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme.AmberAccent
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme.GoldPrimary
import com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.viewmodel.MainViewModel

@Composable
fun HiddenNotesScreen(
    viewModel: MainViewModel,
    onOpenDrawer: () -> Unit,
    onBack: () -> Unit = {},
    onOpenEditor: (noteId: Long) -> Unit
) {
    val hiddenNotes by viewModel.hiddenNotes.collectAsState()
    val allNotes by viewModel.allNotes.collectAsState()
    val passwordHash by viewModel.appPasswordHash.collectAsState()

    var isUnlocked by remember { mutableStateOf(false) }
    var showSetPasswordDialog by remember { mutableStateOf(false) }
    var showPickerModal by remember { mutableStateOf(false) }
    var passwordErrorMsg by remember { mutableStateOf<String?>(null) }

    BackHandler {
        onBack()
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "হাইডেন নোটস",
                subtitle = "আপনার গোপন কবিতা ও সাহিত্য সংকলন",
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = AmberAccent,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            if (isUnlocked && !passwordHash.isNullOrEmpty()) {
                FloatingActionButton(
                    onClick = { showPickerModal = true },
                    shape = CircleShape,
                    containerColor = GoldPrimary,
                    contentColor = Color.White
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add Notes to Hidden")
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (passwordHash.isNullOrEmpty()) {
                // If password is not set up
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Lock",
                            tint = GoldPrimary,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "হাইডেন নোটস লক করা নেই",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldPrimary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "হাইডেন নোটস ফিচার ব্যবহারের পূর্বে একটি সিকিউরিটি পাসওয়ার্ড সেট আপ করুন।",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            onClick = { showSetPasswordDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary)
                        ) {
                            Text("পাসওয়ার্ড সেটআপ করুন", color = Color.White)
                        }
                    }
                }
            } else if (!isUnlocked) {
                // Lock screen prompt
                PasswordPromptDialog(
                    title = "হাইডেন নোটসে প্রবেশ করুন",
                    subtitle = "আপনার সিকিউরিটি পাসওয়ার্ড টাইপ করুন",
                    errorText = passwordErrorMsg,
                    onConfirm = { inputPass ->
                        if (viewModel.themePreferences.verifyPassword(inputPass, passwordHash)) {
                            isUnlocked = true
                            passwordErrorMsg = null
                        } else {
                            passwordErrorMsg = "ভুল পাসওয়ার্ড!"
                        }
                    },
                    onDismiss = { onBack() }
                )
            } else {
                // Unlocked view
                if (hiddenNotes.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "কোনো হাইডেন নোট নেই",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldPrimary
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "নোট গোপন করতে নিচে '+' বাটনে চাপ দিন",
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 110.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(hiddenNotes, key = { it.id }) { note ->
                            NoteCardItem(
                                note = note,
                                onClick = { onOpenEditor(note.id) },
                                onLongClick = { viewModel.toggleHideNote(note) },
                                onTogglePin = { viewModel.togglePinNote(note) },
                                onToggleLock = { viewModel.toggleLockNote(note) },
                                onDelete = { viewModel.softDeleteNote(note.id) },
                                onUnhide = { viewModel.toggleHideNote(note) }
                            )
                        }
                    }
                }
            }
        }
    }

    if (showSetPasswordDialog) {
        SetPasswordDialog(
            onSetPassword = { pass, q, a ->
                viewModel.setPassword(pass, q, a)
                showSetPasswordDialog = false
                isUnlocked = true
            },
            onDismiss = { showSetPasswordDialog = false }
        )
    }

    if (showPickerModal) {
        val availableNotes = allNotes.filter { !it.isHidden && !it.isTrashed }
        HideNotesPickerModal(
            availableNotes = availableNotes,
            onConfirm = { selectedIds ->
                viewModel.hideNotesByIds(selectedIds)
                showPickerModal = false
            },
            onDismiss = { showPickerModal = false }
        )
    }
}
