package com.resumemaker.editor.domain

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BaseViewModel : ViewModel() {

    private val _isTabOpenMap = mutableMapOf<String,MutableStateFlow<Boolean>>()
    private val _displayPage = MutableStateFlow("Home")

    val displayPage:StateFlow<String> get() = _displayPage

    fun init(tabName: List<String>){
        tabName.forEach {
            tab ->
            _isTabOpenMap[tab] = MutableStateFlow(false)
        }
    }

    fun changeDisplayPage(pageName:String){
        _displayPage.value = pageName
    }

    fun getTabStateFlow(tabName: String): StateFlow<Boolean> {
        return _isTabOpenMap.getOrPut(tabName) {
            MutableStateFlow(false)
        }
    }


    fun toggleTab(tabName: String) {
        val flow = _isTabOpenMap[tabName] ?: return
        flow.value = !flow.value
    }
}