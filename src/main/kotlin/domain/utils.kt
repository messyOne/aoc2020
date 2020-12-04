package domain

fun String.isHexadecimal(): Boolean = this.length == 7 && this.first() == '#' && this.removePrefix("#").filter { c -> c in '0'..'9' || c in 'a'..'f'}.length == 6