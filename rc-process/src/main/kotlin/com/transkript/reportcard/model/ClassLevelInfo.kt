package com.transkript.reportcard.model

class ClassLevelInfo(val name: String, val subName: String, val academicYear: String) {
    override fun toString(): String {
        return "RcClassLevel(name='$name', subName='$subName')"
    }
}
