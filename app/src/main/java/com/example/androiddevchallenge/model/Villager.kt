package com.example.androiddevchallenge.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

enum class Species { CAT, DOG }
enum class Hobbies { PLAY, FASHION, FITNESS, NATURE, EDUCATION, MUSIC }

@Immutable
data class Villager(
    val id: Long,
    val name: String,
    val species: Species,
    val personality: String,
    val birthday: String,
    val gender: String,
    val saying: String,
    val catchphrase: String,
    val hobbies: Hobbies,
    val txtColor: Color,
    val iconUrl: String,
    val imageUrl: String
)

val villagers = listOf(
    Villager(
        1,
        "Ankha",
        Species.CAT,
        "Snooty",
        "September 22nd",
        "F",
        "me meow",
        "All that glitters is not gold",
        Hobbies.NATURE,
        Color(0xFF9b8986),
        "https://acnhapi.com/v1/icons/villagers/61",
        "https://acnhapi.com/v1/images/villagers/61"
    ),
    Villager(
        2,
        "Bob",
        Species.CAT,
        "Lazy",
        "January 1st",
        "M",
        "pthhpth",
        "You only live once...or nine times",
        Hobbies.PLAY,
        Color(0xFFFFFCE9),
        "https://acnhapi.com/v1/icons/villagers/42",
        "https://acnhapi.com/v1/images/villagers/42"
    ),
    Villager(
        3,
        "Felicity",
        Species.CAT,
        "Peppy",
        "March 30th",
        "F",
        "mimimi",
        "Love has no bounds",
        Hobbies.FASHION,
        Color(0xFF879b96),
        "https://acnhapi.com/v1/icons/villagers/59",
        "https://acnhapi.com/v1/images/villagers/59"
    ),
    Villager(
        4,
        "Kabuki",
        Species.CAT,
        "Cranky",
        "November 29th",
        "F",
        "meooo-OH",
        "If you make your opponent flinch, you have already won",
        Hobbies.MUSIC,
        Color(0xFF848484),
        "https://acnhapi.com/v1/icons/villagers/51",
        "https://acnhapi.com/v1/images/villagers/51"
    ),
    Villager(
        5,
        "Bea",
        Species.DOG,
        "Normal",
        "October 15th",
        "F",
        "bingo",
        "The perfect pair complements each other",
        Hobbies.NATURE,
        Color(0xFFFFFCE9),
        "https://acnhapi.com/v1/icons/villagers/121",
        "https://acnhapi.com/v1/images/villagers/121"
    ),
    Villager(
        6,
        "Benjamin",
        Species.DOG,
        "Lazy",
        "August 3rd",
        "M",
        "alrighty",
        "Get while the getting's good",
        Hobbies.NATURE,
        Color(0xFF9B553A),
        "https://acnhapi.com/v1/icons/villagers/124",
        "https://acnhapi.com/v1/images/villagers/124"
    ),
    Villager(
        7,
        "Biskit",
        Species.DOG,
        "Lazy",
        "May 13th",
        "M",
        "dawg",
        "Let sleeping dogs lie",
        Hobbies.PLAY,
        Color(0xFF874C25),
        "https://acnhapi.com/v1/icons/villagers/114",
        "https://acnhapi.com/v1/images/villagers/114"
    ),
    Villager(
        8,
        "Bones",
        Species.DOG,
        "Lazy",
        "August 4th",
        "M",
        "yip yip",
        "A snack a day keeps the vacuum away",
        Hobbies.PLAY,
        Color(0xFF848484),
        "https://acnhapi.com/v1/icons/villagers/115",
        "https://acnhapi.com/v1/images/villagers/115"
    ),
    Villager(
        9,
        "Katt",
        Species.CAT,
        "Sisterly",
        "April 27th",
        "F",
        "purrty",
        "MeowMEOWmeow!",
        Hobbies.MUSIC,
        Color(0xFFFFFCE9),
        "https://acnhapi.com/v1/icons/villagers/63",
        "https://acnhapi.com/v1/images/villagers/63"
    ),
    Villager(
        10,
        "Cookie",
        Species.DOG,
        "Peppy",
        "June 18th",
        "F",
        "arfer",
        "Don't lose sight of what you're really after",
        Hobbies.NATURE,
        Color(0xFFFFFCE9),
        "https://acnhapi.com/v1/icons/villagers/119",
        "https://acnhapi.com/v1/images/villagers/119"
    ),
)