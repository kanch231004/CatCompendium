package com.example.catcompendium.model
import com.google.gson.annotations.SerializedName

data class CatBreedItem(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("reference_image_id")
    val referenceImageId: String? = "",

/*
    @SerializedName("adaptability")
    val adaptability: Int?,
    @SerializedName("affection_level")
    val affectionLevel: Int?,
    @SerializedName("alt_names")
    val altNames: String?,
    @SerializedName("cfa_url")
    val cfaUrl: String?,
    @SerializedName("child_friendly")
    val childFriendly: Int?,
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("country_codes")
    val countryCodes: String?,

    @SerializedName("dog_friendly")
    val dogFriendly: Int?,
    @SerializedName("energy_level")
    val energyLevel: Int?,
    @SerializedName("experimental")
    val experimental: Int?,
    @SerializedName("grooming")
    val grooming: Int?,
    @SerializedName("hairless")
    val hairless: Int?,
    @SerializedName("health_issues")
    val healthIssues: Int?,
    @SerializedName("hypoallergenic")
    val hypoallergenic: Int?,

    @SerializedName("image")
    val image: Image?,
    @SerializedName("indoor")
    val indoor: Int?,
    @SerializedName("intelligence")
    val intelligence: Int?,
    @SerializedName("lap")
    val lap: Int?,
    @SerializedName("life_span")
    val lifeSpan: String?,

    @SerializedName("natural")
    val natural: Int?,
    @SerializedName("origin")
    val origin: String?,
    @SerializedName("rare")
    val rare: Int?,

    @SerializedName("rex")
    val rex: Int?,
    @SerializedName("shedding_level")
    val sheddingLevel: Int?,
    @SerializedName("short_legs")
    val shortLegs: Int?,
    @SerializedName("social_needs")
    val socialNeeds: Int?,
    @SerializedName("stranger_friendly")
    val strangerFriendly: Int?,
    @SerializedName("suppressed_tail")
    val suppressedTail: Int?,
    @SerializedName("temperament")
    val temperament: String?,
    @SerializedName("vcahospitals_url")
    val vcahospitalsUrl: String?,
    @SerializedName("vetstreet_url")
    val vetstreetUrl: String?,
    @SerializedName("vocalisation")
    val vocalisation: Int?,
    @SerializedName("weight")
    val weight: Weight?,
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String?*/
)

data class Image(
    @SerializedName("height")
    val height: Int? = -1,
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("width")
    val width: Int? = -1
)

data class Weight(
    @SerializedName("imperial")
    val imperial: String?,
    @SerializedName("metric")
    val metric: String?
)

data class DummyCatDataItem(
    val name : String? = "a",
    val description: String?  = "b",
    val test: String? = "c"
)