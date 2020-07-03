package com.android.okonomi.extension

fun String.categoryStringSize(numberOfCharacter: Int): String {
    if(this.length > numberOfCharacter){
        val firstCharacter = 0
        return "${this.substring(firstCharacter, numberOfCharacter)}..."
    }
    return this
}
