package com.android.example.compose_training.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.compose_training.data.Item
import com.android.example.compose_training.data.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel to retrieve all items in the Room database.
 */
class HomeViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {
    
    /**
     * Holds home ui state. The list of items are retrieved from [ItemsRepository] and mapped to
     * [HomeUiState]
     */
    val homeUiState: StateFlow<HomeUiState> =
        itemsRepository.getAllItemsStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )
            
    /**
     * Deletes an item from the database
     */
    suspend fun deleteItem(item: Item) {
        itemsRepository.deleteItem(item)
    }
    
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val itemList: List<Item> = listOf())