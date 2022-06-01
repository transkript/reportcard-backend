package com.transkript.reportcard.enums

enum class Remark(s: String) {
    EXCELLENT("Excellent"),
    VERY_GOOD("Very Good"),
    GOOD("Good"),
    AVERAGE("Average"),
    POOR("Poor"),
    VERY_POOR("Very Poor"),
    FAIL("Fail"),
    UNKNOWN("Unknown");

    val value = s
}
