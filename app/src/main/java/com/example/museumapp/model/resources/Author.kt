package com.example.museumapp.model.resources

class Author (
    var id: String = "",
    var name: String = "",
    var cover: String = "",
    var biography: String = "",
    var Place_Birth_and_Dead: String = "",
    var type: String = "",
    var works: Array<Book?> = arrayOf()
)       {
    override fun toString(): String {
        val worksString = works.joinToString(separator = ", ", transform = { book ->
            book?.toString() ?: "null"
        })
        return "Author(id='$id', name='$name', biography='$biography', works=[$worksString])"
    }
}