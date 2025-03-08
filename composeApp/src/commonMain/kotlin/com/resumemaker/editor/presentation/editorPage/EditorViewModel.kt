package com.resumemaker.editor.presentation.editorPage

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EditorViewModel : ViewModel() {

    private val _isEdTabOpen = MutableStateFlow(false)
    private val _showEdForm = MutableStateFlow(false)

    // exposed to public
    val showEdForm: StateFlow<Boolean> get() = _showEdForm
    val isEdTabOpen: StateFlow<Boolean> get() = _isEdTabOpen

    fun toggleEdTab(){
        _isEdTabOpen.value = !_isEdTabOpen.value
    }

    fun toggleShowEdForm() {
        _showEdForm.value = !_showEdForm.value
    }
}