package com.example.data.person.mapper

import com.example.data.person.dto.Dob
import com.example.data.person.dto.Id
import com.example.data.person.dto.Info
import com.example.data.person.dto.Location
import com.example.data.person.dto.Name
import com.example.data.person.dto.PersonResponse
import com.example.data.person.dto.Result
import com.example.domain.person.model.Coordinates
import com.example.domain.person.model.Login
import com.example.domain.person.model.PersonResponseModel
import com.example.domain.person.model.Picture
import com.example.domain.person.model.Registered
import com.example.domain.person.model.Street
import com.example.domain.person.model.Timezone

fun PersonResponse.toModel() = PersonResponseModel(
    info = info.toModel(),
    results = results.map { it.toModel() }
)

fun Info.toModel() = com.example.domain.person.model.Info(
    page = page,
    results = results,
    seed = seed,
    version = version
)

fun Name.toModel() = com.example.domain.person.model.Name(
    first = first,
    last = last,
    title = title,
)

fun Dob.toModel() = com.example.domain.person.model.Dob(
    age = age,
    date = date,
)

fun Id.toModel() = com.example.domain.person.model.Id(
    name = name,
    value = value,
)

fun Location.toModel() = com.example.domain.person.model.Location(
    city = city,
    coordinates = Coordinates(
        latitude = coordinates.latitude,
        longitude = coordinates.longitude
    ),
    country = country,
    postcode = postcode,
    state = state,
    street = Street(
        name = street.name,
        number = street.number
    ),
    timezone = Timezone(
        description = timezone.description,
        offset = timezone.offset
    )
)

fun Result.toModel() = com.example.domain.person.model.Result(
    name = name.toModel(),
    dob = dob?.toModel(),
    cell = cell,
    email = email,
    gender = gender,
    id = id?.toModel(),
    picture = Picture(
        large = picture?.large,
        medium = picture?.medium,
        thumbnail = picture?.thumbnail
    ),
)
