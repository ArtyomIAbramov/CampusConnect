package dev.cremenb.api.models

data class PlaceAndSlot (
    val place : Place,
    val bookingSlot : List<BookingSlot>,
)