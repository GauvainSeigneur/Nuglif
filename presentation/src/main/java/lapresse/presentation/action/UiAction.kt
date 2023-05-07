package lapresse.presentation.action

sealed interface UiAction {
    interface NoArgs : UiAction {
        fun execute()
    }
}
