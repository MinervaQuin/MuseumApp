package com.example.museumapp.model.resources

class AuthorFb(
    var id: Int = 0,
    var name: String = "",
    var cover: String = "",
    var biography: String = "",
    var Place_Birth_and_Dead: String = "",
    var type: String = "",
    var works: List<String> = emptyList()
)       {
}