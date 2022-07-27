package com.transkript.reportcard.business.service.i

import com.transkript.reportcard.data.entity.relation.StudentApplicationTrial

interface StudentApplicationTrialService {
    fun getEntity(satId: Long?): StudentApplicationTrial?
    fun getEntitiesByYear(yearId: Long?): List<StudentApplicationTrial?>?
}
