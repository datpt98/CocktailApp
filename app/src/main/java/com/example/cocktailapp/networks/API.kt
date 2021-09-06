package com.example.cocktailapp.networks

object API {
    var Categories = "https://www.thecocktaildb.com/api/json/v1/1/list.php?c=list"
    var Drinks = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c={strCategory}"
    var Detail = "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=15300"
    var Random = "https://www.thecocktaildb.com/api/json/v1/1/random.php"
}