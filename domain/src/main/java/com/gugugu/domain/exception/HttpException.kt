package com.gugugu.domain.exception

import java.lang.RuntimeException

class NotFoundException(
    override val message: String
): RuntimeException()


class ServerDownException(
    override val message: String
): RuntimeException()


class UnknownHttpException(
    override val message: String
): RuntimeException()