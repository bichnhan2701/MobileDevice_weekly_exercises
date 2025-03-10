package com.example.library.model

object Library {
    private var bookIdCounter = 1
    private var userIdCounter = 1
    private var isDataInitiallized = false

    val books = mutableListOf<Book>()
    val users = mutableListOf<User>()

    fun addBook(title: String, author: String) {
        books.add(Book(bookIdCounter ++, title, author))
    }

    fun addUser(name: String) {
        users.add(User(userIdCounter++, name))
    }

    fun initializeData() {
        if (!isDataInitiallized) {
            addBook("Harry Porter", "J. K. Rowling")
            addBook("Sherlock Holmes", "Arthur Conan Doyle")
            addBook("The Great Gatsby", "F. Scott Fitzgerald")
            addBook("Đất rừng phương Nam", "Đoàn Giỏi")
            addBook("Tắt đèn", "Ngô Tất Tố")
            addBook("Mắt Biếc", "Nguyễn Nhật Ánh")
            addBook("Vang bóng một thời", "Nguyễn Tuân")
            addBook("Chí Phèo", "Nam Cao")
            addBook("Dế Mèn phiêu lưu ký", "Tô Hoài")
            addBook("Bỉ Vỏ", "Nguyên Hồng")

            addUser("Bích Nhân")
            addUser("Minh Châu")
            addUser("Thanh Vân")
            addUser("Anh Thi")
            addUser("Nhi")
            addUser("Xuân Khanh")
            addUser("Thu Hương")
            addUser("Tuệ Nhi")

            isDataInitiallized = true
        }
    }

    fun borrowBook(book: Book, user: User): Boolean {
        if (!book.isBorrowed) {
            book.isBorrowed = true
            book.borrowBy = user
            user.listBookBorrowed.add(book)
            return true
        }
        return false
    }

    fun returnBook(book: Book, user: User): Boolean {
        if (book.isBorrowed && book.borrowBy == user) {
            book.isBorrowed = false
            book.borrowBy = null
            user.listBookBorrowed.remove(book)
            return true
        }
        return false
    }
}