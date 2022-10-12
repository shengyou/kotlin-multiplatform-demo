package io.kraftsman.common.ui.states

// Consider a verification code valid
private const val AUTHENTICATION_CODE_VALIDATION_REGEX = "^\\d{6}\$"

class AuthenticationCodeState :
    TextFieldState(validator = ::isCodeValid, errorFor = ::codeValidationError)

/**
 * Returns an error to be displayed or null if no error was found
 */
private fun codeValidationError(code: String): String {
    return "Invalid code"
}

private fun isCodeValid(code: String): Boolean {
    return AUTHENTICATION_CODE_VALIDATION_REGEX.toRegex().matches(code)
}