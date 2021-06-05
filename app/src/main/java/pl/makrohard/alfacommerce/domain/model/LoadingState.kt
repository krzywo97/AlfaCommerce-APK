package pl.makrohard.alfacommerce.domain.model

class LoadingState private constructor(val state: State, val message: String? = null) {
    companion object {
        val INITIAL = LoadingState(State.INITIAL)
        val LOADING = LoadingState(State.LOADING)
        val SUCCESS = LoadingState(State.SUCCESS)
        fun FAILED(message: String) = LoadingState(State.FAILED, message)
    }

    enum class State {
        INITIAL,
        LOADING,
        SUCCESS,
        FAILED
    }
}