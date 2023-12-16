package com.example.museumapp.model.resources

class Collection (
    var name: String = "",
    var description: String = "",
    var works: Array<Work?> = arrayOf(),
    var cover: String = ""
){
    override fun toString(): String {

        val booksString = works.joinToString(separator = ", ", transform = { book ->
            book?.toString() ?: "null"
        })

        return "Collection(name='$name', description='$description', books=[$booksString])"
    }
}