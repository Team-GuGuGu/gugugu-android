package com.gugugu.domain.exception

import java.lang.RuntimeException

class UnknownException(
    override val message: String
): RuntimeException(message)