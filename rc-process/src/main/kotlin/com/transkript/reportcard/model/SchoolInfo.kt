package com.transkript.reportcard.model

class SchoolInfo(val name: String, val section: String, val yearName: String, val termName: String) {
    override fun toString(): String {
        return "RcSchool(name='$name', section='$section')"
    }
}
