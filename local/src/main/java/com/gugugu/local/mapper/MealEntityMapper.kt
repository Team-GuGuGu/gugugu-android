package com.gugugu.local.mapper

import com.gugugu.domain.model.MealLocalSchool
import com.gugugu.local.entity.MealEntity


internal fun MealEntity.toModel() =
    MealLocalSchool(
        local = local,
        school = school
    )