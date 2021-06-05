package pl.makrohard.alfacommerce.data.dto.request

import pl.makrohard.alfacommerce.domain.model.Filters

object GetProductsRequestDto {
    private const val NAME = "name"
    private const val CATEGORY = "category"
    private const val COLOR = "color"
    private const val MIN_PRICE = "minPrice"
    private const val MAX_PRICE = "maxPrice"
    private const val MIN_WEIGHT = "minWeight"
    private const val MAX_WEIGHT = "maxWeight"
    private const val PAGE = "page"
    private const val PER_PAGE = "perPage"

    fun fromFilters(filters: Filters): Map<String, Any> {
        return HashMap<String, Any>().apply {
            if (filters.name != null) put(NAME, filters.name!!)
            if (filters.category != null) put(CATEGORY, filters.category!!)
            if (filters.color != null) put(COLOR, filters.color!!)
            if (filters.minPrice != null) put(MIN_PRICE, filters.minPrice!!)
            if (filters.maxPrice != null) put(MAX_PRICE, filters.maxPrice!!)
            if (filters.minWeight != null) put(MIN_WEIGHT, filters.minWeight!!)
            if (filters.maxWeight != null) put(MAX_WEIGHT, filters.maxWeight!!)
            if (filters.page != null) put(PAGE, filters.page!!)
            if (filters.perPage != null) put(PER_PAGE, filters.perPage!!)
        }
    }
}