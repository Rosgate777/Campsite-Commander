package com.example.imadexamination

object CampData {
    // Parallel arrays initialized with the sample data
    var itemNames = arrayOf("Tent", "Marshmallows", "Flashlight")
    var itemCategories = arrayOf("Shelter", "Food", "Safety")
    var itemQuantities = intArrayOf(1, 3, 2)
    var itemComments = arrayOf("4-person waterproof", "For S'mores (Mega size)", "Check batteries (AA)")

    // Function to add a new item to the parallel arrays
    fun addItem(name: String, category: String, quantity: Int, comment: String) {
        itemNames += name
        itemCategories += category
        itemQuantities += quantity
        itemComments += comment
    }
}