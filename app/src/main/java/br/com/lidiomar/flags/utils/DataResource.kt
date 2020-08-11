package br.com.lidiomar.flags.utils

data class DataResource<out T>(val status: Status, val data: T?, val message: String?) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
    companion object {
        fun <T> success(data: T): DataResource<T> {
            return DataResource(Status.SUCCESS, data, null)
        }

        fun <T> error(message:String): DataResource<T> {
            return DataResource(Status.ERROR, null, message)
        }

        fun <T> loading(message:String): DataResource<T> {
            return DataResource(Status.LOADING, null, null)
        }
    }
}