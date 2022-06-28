package com.transkript.reportcard.business.service.impl;

import com.transkript.reportcard.api.dto.SchoolDto;
import com.transkript.reportcard.api.dto.SchoolSettingsDto;
import com.transkript.reportcard.api.dto.response.EntityResponse;
import com.transkript.reportcard.business.mapper.SchoolSettingsMapper;
import com.transkript.reportcard.business.service.AcademicYearService;
import com.transkript.reportcard.business.service.SchoolService;
import com.transkript.reportcard.business.service.SchoolSettingsService;
import com.transkript.reportcard.business.service.SequenceService;
import com.transkript.reportcard.config.constants.EntityName;
import com.transkript.reportcard.config.constants.ResponseSeverity;
import com.transkript.reportcard.data.entity.AcademicYear;
import com.transkript.reportcard.data.entity.School;
import com.transkript.reportcard.data.entity.SchoolSettings;
import com.transkript.reportcard.data.entity.Sequence;
import com.transkript.reportcard.data.repository.SchoolRepository;
import com.transkript.reportcard.data.repository.SchoolSettingsRepository;
import com.transkript.reportcard.exception.EntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchoolSettingsServiceImpl implements SchoolSettingsService {
    private final SchoolSettingsRepository schoolSettingsRepository;
    private final SchoolRepository schoolRepository;
    private final SchoolSettingsMapper schoolSettingsMapper;
    private final SequenceService sequenceService;
    private final AcademicYearService academicYearService;
    private final SchoolService schoolService;


    @Override
    @Transactional
    public EntityResponse save(SchoolSettingsDto schoolSettingsDto) {
        log.info("Updating settings to {}", schoolSettingsDto);
        SchoolSettings schoolSettings = schoolSettingsMapper.mapDtoToSchoolSettings(schoolSettingsDto);
        School school;

        // the application was created with no school id, so we need to create a school
        if (schoolSettingsDto.schoolId() == 0) {
            school = schoolRepository.save(
                    School.builder().id(null).name(schoolSettingsDto.schoolName()).sections(List.of()).build()
            );
        } else {
            school = schoolRepository.findById(schoolSettingsDto.schoolId()).orElseThrow(
                    () -> new EntityException.EntityNotFoundException(EntityName.SCHOOL, schoolSettingsDto.schoolId())
            );

            // update name of the school from the dto
            if (!Objects.equals(school.getName(), schoolSettingsDto.schoolName())) {
                school.setName(schoolSettingsDto.schoolName());
                school = schoolRepository.save(school);
            }
        }

        var id = 0L;
        // if this school already has settings, update them
        if (school.getSchoolSettings() == null) {
            Sequence currSequence = sequenceService.getSequenceEntity(schoolSettingsDto.currentSequenceId());
            AcademicYear currAcademicYear = academicYearService.getAcademicYearEntity(schoolSettingsDto.currentYearId());

            schoolSettings.setSchool(school);
            schoolSettings.setApplicationOpen(schoolSettingsDto.applicationOpen() != null && schoolSettingsDto.applicationOpen());
            schoolSettings.setCurrentTerm(currSequence.getTerm().getName());
            schoolSettings.setCurrentSequence(currSequence);
            schoolSettings.setCurrentAcademicYear(currAcademicYear);
            schoolSettings = schoolSettingsRepository.save(schoolSettings);
            id = schoolSettings.getId();
        } else {
            id = update(schoolSettingsDto).id();
        }

        return EntityResponse.builder().id(id).entityName("school settings")
                .message(String.format("Successfully added settings for %s", schoolSettingsDto.schoolName())).build();
    }

    @Override
    public EntityResponse update(SchoolSettingsDto schoolSettingsDto) {
        schoolSettingsRepository.findById(schoolSettingsDto.id()).ifPresentOrElse(
                settings -> {
                    settings.setMaxGrade(schoolSettingsDto.maxGrade());
                    settings.setMinGrade(schoolSettingsDto.minGrade());
                    settings.setApplicationOpen(schoolSettingsDto.applicationOpen() != null && schoolSettingsDto.applicationOpen());

                    if(!Objects.equals(settings.getSchoolName(), schoolSettingsDto.schoolName())) {
                        School school = schoolRepository.getById(schoolSettingsDto.schoolId());
                        school.setName(schoolSettingsDto.schoolName());
                        school = schoolRepository.save(school);
                        settings.setSchoolName(school.getName());
                    }
                    if(!Objects.equals(settings.getCurrentAcademicYear().getId(), schoolSettingsDto.currentYearId())) {
                        AcademicYear year = academicYearService.getAcademicYearEntity(schoolSettingsDto.currentYearId());
                        settings.setCurrentAcademicYear(year);
                    }
                    if(!Objects.equals(settings.getCurrentSequence().getId(), schoolSettingsDto.currentSequenceId())) {
                        Sequence sequence = sequenceService.getSequenceEntity(schoolSettingsDto.currentSequenceId());
                        settings.setCurrentSequence(sequence);
                        settings.setCurrentTerm(sequence.getTerm().getName());
                    }
                    schoolSettingsRepository.save(settings);
                },
                () -> {
                    throw new EntityException.EntityNotFoundException(EntityName.SCHOOL_SETTINGS, schoolSettingsDto.id());
                }
        );
        return EntityResponse.builder().id(schoolSettingsDto.id()).entityName("school settings").log(true)
                .message(String.format("Successfully updated settings for the school %s", schoolSettingsDto.schoolName()))
                .severity(ResponseSeverity.SUCCESS).status(HttpStatus.OK.value()).build();
    }

    @Override
    public SchoolSettingsDto retrieve(Long id) {
        return schoolSettingsMapper.mapSchoolSettingsToDto(retrieveEntity(id));
    }

    @Override
    public SchoolSettingsDto retrieveBySchoolId(Long schoolId) {
        return schoolSettingsMapper.mapSchoolSettingsToDto(schoolService.getSchoolEntity(schoolId).getSchoolSettings());
    }

    @Override
    public List<SchoolSettingsDto> retrieveAll() {
        return schoolSettingsRepository.findAll().stream().map(schoolSettingsMapper::mapSchoolSettingsToDto).toList();
    }

    @Override
    public SchoolSettings retrieveEntity(Long id) {
        return schoolSettingsRepository.findById(id).orElseThrow(
                () -> new EntityException.EntityNotFoundException(EntityName.SCHOOL_SETTINGS, id)
        );
    }
}
