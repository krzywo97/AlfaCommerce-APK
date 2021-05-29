package pl.makrohard.alfacommerce.model.request

class GetProducts : HashMap<String, Any>() {
    companion object {
        private const val NAME = "name"
        private const val CATEGORY = "category"
        private const val COLOR = "color"
        private const val MIN_PRICE = "minPrice"
        private const val MAX_PRICE = "maxPrice"
        private const val MIN_WEIGHT = "minWeight"
        private const val MAX_WEIGHT = "maxWeight"
        private const val PAGE = "page"
        private const val PER_PAGE = "perPage"
    }

    var name: String? = null
        set(value) {
            if (value != null) {
                put(NAME, value)
            } else {
                remove(NAME)
            }
        }

    var category: Int? = null
        set(value) {
            if (value != null) {
                put(CATEGORY, value)
            } else {
                remove(CATEGORY)
            }
        }

    var color: Int? = null
        set(value) {
            if (value != null) {
                put(COLOR, value)
            } else {
                remove(COLOR)
            }
        }

    var minPrice: Float? = null
        set(value) {
            if (value != null) {
                put(MIN_PRICE, value)
            } else {
                remove(MIN_PRICE)
            }
        }

    var maxPrice: Float? = null
        set(value) {
            if (value != null) {
                put(MAX_PRICE, value)
            } else {
                remove(MAX_PRICE)
            }
        }

    var minWeight: Int? = null
        set(value) {
            if (value != null) {
                put(MIN_WEIGHT, value)
            } else {
                remove(MIN_WEIGHT)
            }
        }

    var maxWeight: Int? = null
        set(value) {
            if (value != null) {
                put(MAX_WEIGHT, value)
            } else {
                remove(MAX_WEIGHT)
            }
        }

    var page: Int? = null
        set(value) {
            if (value != null) {
                put(PAGE, value)
            } else {
                remove(PAGE)
            }
        }

    var perPage: Int? = null
        set(value) {
            if (value != null) {
                put(PER_PAGE, value)
            } else {
                remove(PER_PAGE)
            }
        }
}