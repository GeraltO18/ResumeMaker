package com.resumemaker.editor.presentation.editorPage

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EditorViewModel : ViewModel() {
    private val _isEdTabOpen = MutableStateFlow(false)
    val isEdTabOpen: StateFlow<Boolean> get() = _isEdTabOpen

    fun toggleEdTab(){
        _isEdTabOpen.value = !_isEdTabOpen.value
    }
}