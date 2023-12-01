package com.example.museumapp.model.resources

class Book (
    val isbn: Long = 0L, // Suponiendo que ISBN es un número largo
    val author_id: Long = 0L, // Suponiendo que author_id es un número largo
    val author_name: String = "",
    val title: String = "",
    val sinopsis: String = "",
    val score: Int = 0, // Cambiado a Int, suponiendo que score es un valor entero
    val cover: String = "",
    val price: Double = 0.0, // Cambiado a Double, suponiendo que el precio puede ser decimal
    val ref: String = "",
    val editorial: String = "DEBOLSILLO",
    val encuadernacion: String = "Tapa blanda",
    val publicationDate: String = "2013",
    val language: String = "Castellano",
    val num_pag: Int = 312,


){
    override fun toString(): String {
        return "Book(ISBN=$isbn, author_id=$author_id, author_name='$author_name', title='$title', sinopsis='$sinopsis', score=$score, cover='$cover', price=$price, ref=$ref)"
    }
}