package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SchoolSettingsDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.SchoolSettingsMapper;
import com.transkript.reportcard.business.service.AcademicYearService;
import com.transkript.reportcard.business.service.SchoolSettingsService;
import com.transkript.reportcard.business.service.SequenceService;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.SchoolSettings;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.entity.Term;
import com.transkript.reportcard.data.repository.SchoolSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SchoolSettingsServiceImpl implements SchoolSettingsService {
    private final SchoolSettingsRepository schoolSettingsRepository;
    private final SchoolSettingsMapper schoolSettingsMapper;
    private final SequenceService sequenceService;
    private final AcademicYearService academicYearService;

    @Override
    @Transactional
    public EntityResponse saveSettings(SchoolSettingsDto schoolSettingsDto) {
        SchoolSettings schoolSettings = schoolSettingsMapper.mapDtoToSchoolSettings(schoolSettingsDto);

        {
            Sequence currSequence = sequenceService.getSequenceEntity(schoolSettingsDto.currentSequenceId());
            Term currTerm = currSequence.getTerm();
            AcademicYear currAcademicYear = academicYearService.getAcademicYearEntity(schoolSettingsDto.currentYearId());

            schoolSettings.setCurrentTerm(currTerm);
            schoolSettings.setCurrentSequence(currSequence);
            schoolSettings.setCurrentAcademicYear(currAcademicYear);
        }

        if(schoolSettingsRepository.findAll().size() == 0) {
            schoolSettings.setApplicationOpen(schoolSettings.getApplicationOpen() != null && schoolSettings.getApplicationOpen());
            schoolSettingsRepository.save(schoolSettings);
        } else {
            // update if not found
            schoolSettingsRepository.findAll().stream().findFirst().ifPresentOrElse(
                    settings -> {
                        settings.setMaxGrade(schoolSettingsDto.maxGrade());
                        settings.setMinGrade(schoolSettingsDto.minGrade());
                        settings.setApplicationOpen(schoolSettings.getApplicationOpen());

                        if(!Objects.equals(settings.getCurrentAcademicYear().getId(), schoolSettings.getCurrentAcademicYear().getId())) {
                            settings.setCurrentAcademicYear(schoolSettings.getCurrentAcademicYear());
                        }
                        if(!Objects.equals(settings.getCurrentTerm().getId(), schoolSettings.getCurrentTerm().getId())) {
                            settings.setCurrentTerm(schoolSettings.getCurrentTerm());
                        }
                        if(!Objects.equals(settings.getCurrentSequence().getId(), schoolSettings.getCurrentSequence().getId())) {
                            settings.setCurrentSequence(schoolSettings.getCurrentSequence());
                        }
                        schoolSettingsRepository.save(settings);
                    },
                    () -> schoolSettingsRepository.save(schoolSettings)
            );
        }

        return EntityResponse.builder().id(
                schoolSettingsRepository.findAll().stream().findFirst().orElse(schoolSettings).getId()
        ).message("Successfully added settings for the system").entityName("school settings").build();
    }

    @Override
    public SchoolSettingsDto getSettings() {
        return schoolSettingsMapper.mapSchoolSettingsToDto(
                schoolSettingsRepository.findAll().stream().findFirst().orElse(null)
        );
    }
}
