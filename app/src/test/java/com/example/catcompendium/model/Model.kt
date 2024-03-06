package com.example.catcompendium.model

val failedList by lazy {
    listOf(
        DummyCatDataItem(
            name = "Abyssinian",
            description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        ),
        DummyCatDataItem(
            name = "Aegean",
            description = "Native to the Greek islands known as the Cyclades in the Aegean Sea, these are natural cats, meaning they developed without humans getting involved in their breeding. As a breed, Aegean Cats are rare, although they are numerous on their home islands. They are generally friendly toward people and can be excellent cats for families with children.",
        )
    )
}

val listBreed by lazy {
    arrayListOf(
        CatBreedItem(
            name = "Abyssinian",
            description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        ),
        CatBreedItem(
            name = "Aegean",
            description = "Native to the Greek islands known as the Cyclades in the Aegean Sea, these are natural cats, meaning they developed without humans getting involved in their breeding. As a breed, Aegean Cats are rare, although they are numerous on their home islands. They are generally friendly toward people and can be excellent cats for families with children.",
        ),
        CatBreedItem(
            name = "American Bobtail",
            description = "The American Bobtail is a medium to large, naturally occurring, bobtailed cat. It is a noticeably athletic animal, well muscled, with the look of power.",
        ),
        CatBreedItem(
            name = "American Curl",
            description = "The American Curl is a breed of cat characterized by its unusual ears, which curl back from the face toward the center of the back of the skull.",
        ),
        CatBreedItem(
            name = "American Shorthair",
            description = "The American Shorthair is known for its longevity, robust health, good looks, sweet personality, and amiability with children, dogs, and other pets.",
        ),
        CatBreedItem(
            name = "American Wirehair",
            description = "American Wirehairs are known for their unique wiry coats, springy and resilient, with a coarse texture that distinguishes them from other breeds.",
        ),
        CatBreedItem(
            name = "Arabian Mau",
            description = "Arabian Maus are known for their loyalty and affection towards their owners. They are highly intelligent cats and love to play and interact with their human family members.",
        ),
        CatBreedItem(
            name = "Australian Mist",
            description = "Australian Mists are known for their friendly and affectionate nature. They enjoy being around people and are good with children and other pets.",
        ),
        CatBreedItem(
            name = "Balinese",
            description = "Balinese cats are known for their playful and affectionate nature. They are often described as social and intelligent cats, enjoying the company of their human family members.",
        ),
        CatBreedItem(
            name = "Bambino",
            description = "Bambinos are known for their friendly and affectionate nature. They are often described as playful and social cats, enjoying the company of their human family members.",
        ),
        CatBreedItem(
            name = "Bengal",
            description = "Bengal cats are known for their beautiful and distinctive markings. They are often described as energetic and playful cats, enjoying interactive play and mental stimulation.",
        ),
        CatBreedItem(
            name = "XYZ",
            description = "Bambinos are known for their friendly and affectionate nature. They are often described as playful and social cats, enjoying the company of their human family members.",
        ),
        CatBreedItem(
            name = "ABC",
            description = "Bengal cats are known for their beautiful and distinctive markings. They are often described as energetic and playful cats, enjoying interactive play and mental stimulation.",
        )
    )
}

const val errorMessage = "Invalid Request"